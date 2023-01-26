package GameBase.Chess;

import GameBase.Base.Board;
import GameBase.Base.Coordinate;
import GameBase.Base.Movable;
import GameBase.Chess.Figures.*;
import GameBase.Game.Main;

import java.util.Arrays;

public class ChessBoard extends Board {
    private static final int chessFieldSize;

    static {
        chessFieldSize = 8;
    }

    private static ChessBoard instance;


    private ChessBoard() {
        this.field = new ChessFigure[chessFieldSize][chessFieldSize];
        for (Movable[] cf : field) Arrays.fill(cf, null);

    }

    public static ChessBoard getInstance() {
        if (instance == null) instance = new ChessBoard();
        return instance;
    }

    private void resetField() {
        for (Movable[] cf : field) Arrays.fill(cf, null);
    }

    public void newField() {
        this.resetField();
        // white
        field[0][0] = new Rook(true, new Coordinate(0, 0));
        field[0][1] = new Knight(true, new Coordinate(1, 0));
        field[0][2] = new Bishop(true, new Coordinate(2, 0));
        field[0][3] = new Queen(true, new Coordinate(3, 0));
        field[0][4] = new King(true, new Coordinate(4, 0));
        field[0][5] = new Bishop(true, new Coordinate(5, 0));
        field[0][6] = new Knight(true, new Coordinate(6, 0));
        field[0][7] = new Rook(true, new Coordinate(7, 0));
        for (int i = 0; i < chessFieldSize; i++)
            field[1][i] = new Pawn(true, new Coordinate(i, 1));
        //black
        field[7][0] = new Rook(false, new Coordinate(0, 7));
        field[7][1] = new Knight(false, new Coordinate(1, 7));
        field[7][2] = new Bishop(false, new Coordinate(2, 7));
        field[7][3] = new Queen(false, new Coordinate(3, 7));
        field[7][4] = new King(false, new Coordinate(4, 7));
        field[7][5] = new Bishop(false, new Coordinate(5, 7));
        field[7][6] = new Knight(false, new Coordinate(6, 7));
        field[7][7] = new Rook(false, new Coordinate(7, 7));
        for (int i = 0; i < chessFieldSize; i++)
            field[6][i] = new Pawn(false, new Coordinate(i, 6));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" |A|B|C|D|E|F|G|H\n").append("-----------------\n");
        for (int i = 0; i < chessFieldSize; i++) {
            sb.append(8 - i);
            for (int j = 0; j < chessFieldSize; j++)
                sb.append("|").append(
                        field[chessFieldSize - 1 - i][j] != null ? field[chessFieldSize - 1 - i][j] : " "
                );
            sb.append("\n-----------------\n");
        }
        sb.append(" |A|B|C|D|E|F|G|H\n").append("-----------------\n");
        return sb.toString();
    }

    public boolean canMove(Coordinate from, Coordinate to) {
        if (field[from.getY()][from.getX()] == null) return false;
        else {
            ChessFigure tempFrom = (ChessFigure) field[from.getY()][from.getX()];
            ChessFigure tempTo = (ChessFigure) field[to.getY()][to.getX()];
            System.out.println("from = " + from);
            System.out.println("Ffrom = " + tempFrom);
            boolean checkmate = false;

            switch (tempFrom.getClass().getSimpleName()) {
                case "Bishop":
                    if (
                            ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                                    (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                                    && tempFrom.canMove(to) &&
                                    (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())
                    ) {

                        if (to.getX() - from.getX() > 0 && to.getY() - from.getY() > 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() > 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() > 0 && to.getY() - from.getY() < 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() < 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }
                        } else return false;
                    } else return false;


                case "King":
                    if (
                            ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                                    (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                                    && tempFrom.canMove(to)
                    ) {
                        if (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 1 &&
                                (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())) {

                            if (to.getX() - from.getX() > 0 && to.getY() - from.getY() > 0) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }


                            } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() > 0) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }


                            } else if (to.getX() - from.getX() > 0 && to.getY() - from.getY() < 0) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() < 0) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else return false;

                        } else if (Math.abs(from.getX() - to.getX()) == 0 && Math.abs(from.getY() - to.getY()) == 1 &&
                                (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())) {

                            if (to.getY() - from.getY() > 0) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX()];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else if (to.getY() - from.getY() < 0) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX()];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else return false;

                        } else if (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 0 &&
                                (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())) {
                            if (to.getX() - from.getX() > 0) {
                                tempTo = (ChessFigure) field[to.getY()][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else if (to.getX() - from.getX() < 0) {
                                tempTo = (ChessFigure) field[to.getY()][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("");
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                        if (tempTo.getClass().getSimpleName().equals("King")) {
                                            checkmate = true;
                                            System.out.println("checkmate:" + Main.stepCounter);
                                            Main.end_Game();
                                            return false;
                                        }
                                    } else {
                                        checkmate = false;
                                        return true;
                                    }
                                }
                            } else return false;
                        } else return false;
                    } else return false;
                case "Queen":
                    if (
                            ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                                    (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                                    && tempFrom.canMove(to) &&
                                    (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())
                    ) {
                        //Rook
                        if (Math.abs(from.getY() - to.getY()) == 0) {
                            if (from.getX() - to.getX() > 0) {
                                for (int i = 0; i <= (Math.abs(from.getX() - to.getX()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY()][to.getX() + 1];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else if (from.getX() - to.getX() < 0) {
                                for (int i = 0; i <= (Math.abs(from.getX() - to.getX()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY()][to.getX() - 1];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }

                            } else return false;
                        } else if (Math.abs(from.getX() - to.getX()) == 0) {
                            if (from.getY() - to.getY() > 0) {

                                for (int i = 0; i <= (Math.abs(from.getY() - to.getY()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY() + 1][to.getX()];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else if (from.getY() - to.getY() < 0) {
                                for (int i = 0; i <= (Math.abs(from.getY() - to.getY()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY() - 1][to.getX()];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else return false;
                        } else return false;


                        //Bishop
                        if (to.getX() - from.getX() > 0 && to.getY() - from.getY() > 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() > 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() - 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() > 0 && to.getY() - from.getY() < 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() - 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }

                        } else if (to.getX() - from.getX() < 0 && to.getY() - from.getY() < 0) {
                            for (int i = 0; i <= Math.abs(from.getX() - to.getX()); i++) {
                                tempTo = (ChessFigure) field[to.getY() + 1][to.getX() + 1];
                                if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                    Main.stepCounter += 1;
                                    return true;
                                }
                            }
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }
                        } else return false;
                    } else return false;


                case "Rook":
                    if (
                            ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                                    (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                                    && tempFrom.canMove(to) &&
                                    (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite())
                    ) {

                        if (Math.abs(from.getY() - to.getY()) == 0) {
                            if (from.getX() - to.getX() > 0) {
                                for (int i = 0; i <= (Math.abs(from.getX() - to.getX()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY()][to.getX() + 1];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else if (from.getX() - to.getX() < 0) {
                                for (int i = 0; i <= (Math.abs(from.getX() - to.getX()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY()][to.getX() - 1];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }

                            } else return false;
                        } else if (Math.abs(from.getX() - to.getX()) == 0) {
                            if (from.getY() - to.getY() > 0) {

                                for (int i = 0; i <= (Math.abs(from.getY() - to.getY()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY() + 1][to.getX()];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else if (from.getY() - to.getY() < 0) {
                                for (int i = 0; i <= (Math.abs(from.getY() - to.getY()) - 1); i++) {
                                    tempTo = (ChessFigure) field[to.getY() - 1][to.getX()];
                                    if (tempTo == null || tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                                        Main.stepCounter += 1;
                                        return true;
                                    }
                                }
                                if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("");
                                        Main.end_Game();
                                        return false;
                                    }
                                } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                    if (tempTo.getClass().getSimpleName().equals("King")) {
                                        checkmate = true;
                                        System.out.println("checkmate:" + Main.stepCounter);
                                        Main.end_Game();
                                        return false;
                                    }
                                } else {
                                    checkmate = false;
                                    return true;
                                }
                            } else return false;

                        } else return false;
                    } else return false;


                case "Knight":
                    if (((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                            (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                            && tempFrom.canMove(to))
                        if (tempTo == null) {
                            Main.stepCounter += 1;
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }
                            return true;
                        } else if (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()) {
                            Main.stepCounter += 1;
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }
                        } else return false;
                    else return false;
                case "Pawn":
                    if (
                            ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite() ||
                                    (Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite())
                                    && tempFrom.canMove(to)
                    )
                        if (tempTo == null) {
                            Main.stepCounter += 1;
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return from.getX() == to.getX();
                            }
                        } else if (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite() && from.getX() != to.getX()) {
                            Main.stepCounter += 1;
                            if ((Main.stepCounter + 1) % 2 == 1 && tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("");
                                    Main.end_Game();
                                    return false;
                                }
                            } else if ((Main.stepCounter + 1) % 2 == 0 && !tempFrom.isColorIsWhite()) {
                                if (tempTo.getClass().getSimpleName().equals("King")) {
                                    checkmate = true;
                                    System.out.println("checkmate:" + Main.stepCounter);
                                    Main.end_Game();
                                    return false;
                                }
                            } else {
                                checkmate = false;
                                return true;
                            }
                        } else return false;
                    else return false;
                default:
                    return false;
            }
        }
    }


    public void move(Coordinate from, Coordinate to) {
        ((ChessFigure) field[from.getY()][from.getX()]).moveTo(to);
        field[to.getY()][to.getX()] = field[from.getY()][from.getX()];
        field[from.getY()][from.getX()] = null;
    }
}
