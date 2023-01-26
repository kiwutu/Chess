package GameBase.Chess.Figures;

import GameBase.Base.Coordinate;
import GameBase.Chess.ChessFigure;

public class King extends ChessFigure {
    public King(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2654' : '\u265a', coordinateFrom);
    }

    @Override
    public boolean canMove(Coordinate to) {
        return Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 1 ||
                Math.abs(from.getX() - to.getX()) == 0 && Math.abs(from.getY() - to.getY()) == 1 ||
                Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 0;
    }
}
