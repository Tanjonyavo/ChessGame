package Model.AllPieces;

import Model.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Piece {
    protected int col, row;
    public int xPos;
    public int yPos;
    public boolean isWhite;
    public String name;
    protected BufferedImage sheet;
    protected Board board;
    {
        try{
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected int sheetScale = sheet.getWidth()/6;
    Image sprite;
    public Piece(Board board){
        this.board = board;
    }
    public abstract boolean isValidMovement(int col, int row);
    public abstract boolean moveCollidesWithPiece(int col, int row);
    public int getCol() {return col;}
    public int getRow() {return row;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public boolean isFirstMove = true;
    public void paint(Graphics g2d){
        g2d.drawImage(sprite, xPos, yPos, null);
    }
    public void setCol(int col) {this.col = col;}
    public void setRow(int row) {this.row = row;}
}
