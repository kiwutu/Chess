package GameBase.Chess.Figures;

import GameBase.Base.Coordinate;
import GameBase.Chess.ChessFigure;

public class Queen extends ChessFigure {
    public Queen(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2655' : '\u265b', coordinateFrom);
    }

    @Override
    public boolean canMove(Coordinate to) {
        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY()) && Math.abs(from.getX() - to.getX()) != 0 ||
                Math.abs(from.getX() - to.getX()) >= 1 && Math.abs(from.getX() - to.getX()) <= 8 && Math.abs(from.getY() - to.getY()) == 0 ||
                Math.abs(from.getY() - to.getY()) >= 1 && Math.abs(from.getY() - to.getY()) <= 8 && Math.abs(from.getX() - to.getX()) == 0;
    }
}
