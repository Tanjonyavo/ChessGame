package Model;

import Controller.Input;
import Model.AllPieces.*;
import App.Main;
import View.GameOverWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Board extends JPanel {
    private static Board instanceBoard;
    public static int cadence;
    public int tileSize = 85;
    int cols = 8;
    int rows = 8;
    public static ArrayList<Piece> pieceList = new ArrayList<>();
    public Piece selectedPiece;
    Input input = new Input(this);
    public int enPassantTile = -1;
    public CheckScanneur checkScanneur = new CheckScanneur(this);
    public static boolean isWhiteToMove = true;
    public static boolean isGameOver = false;

    private HashMap<String, Integer> positionHistory = new HashMap<>();

    public static Board getBoard() {
        if (instanceBoard == null) {
            instanceBoard = new Board().createBoard();
        }
        return instanceBoard;
    }

    private Board createBoard() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
        return this;
    }

    public void addPieces() {
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(this, i, 6, true));
            pieceList.add(new Pawn(this, i, 1, false));
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g2d.setColor((i + j) % 2 == 0 ? Color.white : Color.GRAY);
                g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }

        if (selectedPiece != null) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (isValidMove(new Move(this, selectedPiece, j, i))) {
                        g2d.setColor(new Color(129, 209, 129, 200));
                        g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                    }
                }
            }
        }

        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.getCol() == col && piece.getRow() == row) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if (isGameOver) return false;
        if (move.piece.isWhite != isWhiteToMove) return false;
        if (sameTeam(move.piece, move.capture)) return false;
        if (!move.piece.isValidMovement(move.getNewCol(), move.getNewRow())) return false;
        if (move.piece.moveCollidesWithPiece(move.getNewCol(), move.getNewRow())) return false;
        if (checkScanneur.isKingChecked(move)) return false;

        if (Main.bottomClock.timeRemaining <= 0) {
            new GameOverWindow("black");
            isGameOver = true;
            return false;
        }

        if (Main.topClock.timeRemaining <= 0) {
            new GameOverWindow("white");
            isGameOver = true;
            return false;
        }

        return true;
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }

    public void makeMove(Move move) {
        if (move.piece.name.equals("Pawn")) movePawn(move);
        else if (move.piece.name.equals("King")) moveKing(move);

        move.piece.setCol(move.getNewCol());
        move.piece.setRow(move.getNewRow());
        move.piece.setxPos(move.getNewCol() * tileSize);
        move.piece.setyPos(move.getNewRow() * tileSize);
        move.piece.isFirstMove = false;
        capture(move.capture);

        isWhiteToMove = !isWhiteToMove;
        updateGameState();

        // répétition 3 fois
        String positionKey = getPositionKey();
        if(positionHistory.size() > 3){
            positionHistory.remove(4);}
        positionHistory.put(positionKey, positionHistory.getOrDefault(positionKey, 0) + 1);
        if (positionHistory.get(positionKey) >= 3) {
            new GameOverWindow("Draw by repetition");
            isGameOver = true;
        }

    }

    private void movePawn(Move move) {
        int colorIndex = move.piece.isWhite ? 1 : -1;
        if (getTileNum(move.getNewCol(), move.getNewRow()) == enPassantTile) {
            move.capture = getPiece(move.getNewCol(), move.getNewRow() + colorIndex);
        }

        if (Math.abs(move.piece.getRow() - move.getNewRow()) == 2) {
            enPassantTile = getTileNum(move.getNewCol(), move.getNewRow() + colorIndex);
        } else {
            enPassantTile = -1;
        }

        int promotionRow = move.piece.isWhite ? 0 : 7;
        if (move.getNewRow() == promotionRow) {
            promotePawn(move);
        }
    }

    private void promotePawn(Move move) {
        String[] options = {"Reine", "Tour", "Fou", "Cavalier"};
        int choice = JOptionPane.showOptionDialog(null, "Choisir la promotion du pion", "Promotion",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        Piece promotedPiece = null;
        if (choice == 0) promotedPiece = new Queen(this, move.getNewCol(), move.getNewRow(), move.piece.isWhite);
        else if (choice == 1) promotedPiece = new Rook(this, move.getNewCol(), move.getNewRow(), move.piece.isWhite);
        else if (choice == 2) promotedPiece = new Bishop(this, move.getNewCol(), move.getNewRow(), move.piece.isWhite);
        else if (choice == 3) promotedPiece = new Knight(this, move.getNewCol(), move.getNewRow(), move.piece.isWhite);

        if (promotedPiece != null) {
            pieceList.add(promotedPiece);
            capture(move.piece);
        }
    }

    public void capture(Piece piece) {
        pieceList.remove(piece);
    }

    public int getTileNum(int col, int row) {
        return row * rows + col;
    }

    public Piece findKing(boolean isWhite) {
        for (Piece piece : pieceList) {
            if (piece.isWhite == isWhite && piece.name.equals("King")) {
                return piece;
            }
        }
        return null;
    }

    private void moveKing(Move move) {
        if (Math.abs(move.piece.getCol() - move.getNewCol()) == 2) {
            Piece rook;
            if (move.piece.getCol() < move.getNewCol()) {
                rook = getPiece(7, move.piece.getRow());
                rook.setCol(5);
            } else {
                rook = getPiece(0, move.piece.getRow());
                rook.setCol(3);
            }
            rook.setxPos(rook.getCol() * tileSize);
        }
    }

    private void updateGameState() {
        Piece king = findKing(isWhiteToMove);
        if (checkScanneur.isGameOver(king)) {
            if (checkScanneur.isKingChecked(new Move(this, king, king.getCol(), king.getRow()))) {
                new GameOverWindow(isWhiteToMove ? "black" : "white");
            } else {
                new GameOverWindow("Stalemate");
            }
            isGameOver = true;
        } else if (insufficientMaterial(true) && insufficientMaterial(false)) {
            new GameOverWindow("Insufficient Material");
            isGameOver = true;
        }
    }

    private boolean insufficientMaterial(boolean isWhite) {
        ArrayList<String> names = pieceList.stream()
                .filter(p -> p.isWhite == isWhite)
                .map(p -> p.name)
                .collect(Collectors.toCollection(ArrayList::new));
        if (names.contains("Queen") || names.contains("Rook") || names.contains("Pawn")) return false;
        return names.size() < 3;
    }

    private String getPositionKey() {
        StringBuilder key = new StringBuilder();
        for (Piece piece : pieceList) {
            key.append(piece.name)
                    .append(piece.getCol())
                    .append(piece.getRow())
                    .append(piece.isWhite ? "W" : "B");
        }
        key.append(isWhiteToMove ? "W" : "B");
        return key.toString();
    }
    public void resetPos(){
        positionHistory.clear();
    }
    public void makeFakePosition(String positionKey) {
        positionHistory.put(positionKey, positionHistory.getOrDefault(positionKey, 0) + 1);
    }
    public int getPositionCount(String positionKey) {
        return positionHistory.getOrDefault(positionKey, 0);
    }

    public void addOnePiece(Piece newPiece) {
        pieceList.add(newPiece);
    }
    public void removOnePiece(Piece newPiece) {
        pieceList.remove(newPiece);
    }
}
