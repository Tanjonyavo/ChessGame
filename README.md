# ChessGame

Object-oriented Java chess game implementing core chess rules.

This project was developed to strengthen skills in software architecture, game logic, and clean separation of concerns using a Model / Controller / App structure.

---

## Key Features

- Legal move validation for all chess pieces  
- Turn-based gameplay system  
- Check detection logic  
- Object-oriented implementation per piece  
- Board and move abstraction  
- Basic game timer system (Blitz / Rapid / Classic)

---

## Technologies

- Java  
- Maven  
- JUnit 5  

---

## Architecture Overview

```
src/
├── App         – Application entry point
├── Controller  – Game flow and user input handling
└── Model       – Board, pieces, moves, and rules logic
```

---

## Screenshots

<p><b>Home screen – game entry menu</b></p>
<p align="center">
  <img src="screenshots/home.jpg" width="600" style="border-radius:10px;">
</p>

<p><b>Time control selection (Blitz / Rapid / Classic)</b></p>
<p align="center">
  <img src="screenshots/mode.jpg" width="600" style="border-radius:10px;">
</p>

<p><b>Initial board setup</b></p>
<p align="center">
  <img src="screenshots/board.jpg" width="600" style="border-radius:10px;">
</p>

<p><b>Move highlighting and interaction</b></p>
<p align="center">
  <img src="screenshots/move.jpg" width="600" style="border-radius:10px;">
</p>

<p><b>Game over screen – victory detection</b></p>
<p align="center">
  <img src="screenshots/win.jpg" width="600" style="border-radius:10px;">
</p>

---

## Validation & Testing

Tests are implemented using **JUnit 5** and executed via Maven.

### Run Tests

```bash
mvn test
```

### Coverage Overview

**Core Game Logic**
- Move initialization correctness  
- Capture detection during move execution  
- Non-capture move validation  
- Position repetition tracking (3-fold repetition counter)  
- Piece coordinate handling and state integrity  

**Check Detection**
- King in check (positive case)  
- King not in check (negative case)  

**Game Flow & Timer Logic**
- Cadence selection (Blitz / Rapid / Classic)  
- Proper configuration of game time  
- Clock decrement logic  
- Clock stop at zero  
- Timer formatting validation  
- Game over message validation  

---

## Getting Started

### Clone

```bash
git clone https://github.com/Tanjonyavo/ChessGame.git
cd ChessGame
```

### Run

Open the project with IntelliJ IDEA or Eclipse, then run `Main.java`.

---

## Author

Tanjon’Yavo
