package org.ledyaeva.chess.pieces;

import org.jetbrains.annotations.NotNull;
import org.ledyaeva.chess.Cell;
import org.ledyaeva.chess.Colors;

/**
 * Абстрактный класс, описывающий поведение шахматной фигуры
 */
public abstract class Piece {

    @NotNull protected Colors color;
    @NotNull protected Cell position;

    public Piece(@NotNull Colors color, @NotNull Cell position) {
        this.color = color;
        this.position = position;
    }


    public void move(@NotNull Cell toPosition) {
        this.position = toPosition;
    }

    @NotNull
    public Cell getPosition() {
        return position;
    }

    @NotNull
    public Colors getColor() {
        return color;
    }
}
