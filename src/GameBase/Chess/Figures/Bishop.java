package GameBase.Chess.Figures;

import GameBase.Base.Coordinate;
import GameBase.Chess.ChessBoard;
import GameBase.Chess.ChessFigure;
import GameBase.Game.Main;

public class Bishop extends ChessFigure {
    public Bishop(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2657' : '\u265d', coordinateFrom);
    }

    @Override
    public boolean canMove(Coordinate to) {
        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY()) && Math.abs(from.getX() - to.getX()) != 0 ;
    }
}


