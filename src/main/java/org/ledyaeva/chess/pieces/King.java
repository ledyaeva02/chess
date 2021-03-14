package org.ledyaeva.chess.pieces;

import org.jetbrains.annotations.NotNull;
import org.ledyaeva.chess.Cell;
import org.ledyaeva.chess.Colors;

import static java.lang.Math.abs;


public class King extends Piece {

    public King(@NotNull Colors color, @NotNull Cell position) {
        super(color, position);
    }

    public boolean threaten(@NotNull Cell whom) {
        return abs(this.position.getX() - whom.getX()) == 1 || abs(this.position.getY() - whom.getY()) == 1;
    }

}
