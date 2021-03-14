package org.ledyaeva.chess.pieces;

import org.jetbrains.annotations.NotNull;
import org.ledyaeva.chess.Cell;
import org.ledyaeva.chess.Colors;

public class Rook extends Piece {

    public Rook(@NotNull Colors color, @NotNull Cell position) {
        super(color, position);
    }
}
