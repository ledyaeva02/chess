package org.ledyaeva.chess;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ledyaeva.chess.pieces.King;
import org.ledyaeva.chess.pieces.Pawn;
import org.ledyaeva.chess.pieces.Piece;


public class Chessboard {

    /*
    Индексы на шахматной доске:

    81 82 83 .. 88
    ...
    21 22 23 .. 28
    11 12 13 .. 18

     */
    private final Piece[][] chessboard = new Piece[8][8];

    public Chessboard(@NotNull Cell whiteKingPos, @NotNull Cell blackKingPos) {
        if (whiteKingPos.equals(blackKingPos))
            throw new IllegalArgumentException("Kings positions must not be the same!");

        King whiteKing = new King(Colors.WHITE, whiteKingPos);
        if (whiteKing.threaten(blackKingPos))
            throw new IllegalArgumentException("Kings cant's stay near with each other!");

        setPiece(whiteKingPos, whiteKing);
        setPiece(blackKingPos, new King(Colors.BLACK, blackKingPos));
    }

    /**
     * Добавление фигуры piece на шахматное поле
      */
    public void addPiece(@NotNull Piece piece) {
        if (!isEmpty(piece.getPosition()))
            throw new IllegalArgumentException("Unable to add the piece. The field is not empty!");

        if (piece instanceof King)
            throw new IllegalArgumentException("You can't add one more king!");

        if (piece instanceof Pawn && pawnsCount() > 8) {
            throw new IllegalArgumentException("You can't add more than 8 pawns on the chessboard!");
        }

        setPiece(piece.getPosition(), piece);
    }

    /**
     * Удаление фигуры с позицией position с шахматного поля
     */
    public void clearCell(@NotNull Cell position) {
        if (getPiece(position) != null && getPiece(position) instanceof King)
            throw new IllegalArgumentException("You can't remove a king from the chessboard!");

        setPiece(position, null);
    }

    /**
     * Перемещение фигуры с позиции from на позицию to шахматного поля
     */
    public void movePiece(@NotNull Cell from, @NotNull Cell to) {
        if (getPiece(to) != null)
            throw new IllegalArgumentException("Cell you are moving to the piece is not free!");

        if (getPiece(from) instanceof King && isKingNear(getPiece(from).getColor() == Colors.WHITE ? Colors.BLACK : Colors.WHITE, to))
            throw new IllegalArgumentException("You can't move the one king near to another king!");

        if (getPiece(from) != null)
            getPiece(from).move(to);

        setPiece(to, getPiece(from));
        setPiece(from, null);
    }


    private boolean isKingNear(@NotNull Colors color, @NotNull Cell position) {
        for (int i = position.getY() - 1; i < position.getY() + 2; i++) {
            for (int j = position.getX() - 1; j < position.getX() + 2; j++) {
                if (1 <= i && i <= 8 && 1 <= j && j <= 8) {
                    Piece piece = getPiece(new Cell(j, i));
                    if (piece instanceof King && piece.getColor() == color)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isEmpty(@NotNull Cell cell) {
        return this.chessboard[cell.getY() - 1][cell.getX() - 1] == null;
    }

    @Nullable
    private Piece getPiece(@NotNull Cell cell) {
        return chessboard[cell.getY() - 1][cell.getX() - 1];
    }

    private void setPiece(@NotNull Cell position, @Nullable Piece piece) {
        chessboard[position.getY() - 1][position.getX() - 1] = piece;
    }

    private int pawnsCount() {
        int count = 0;
        for (Piece[] row: chessboard) {
            for (Piece piece: row) {
                if (piece instanceof Pawn)
                    count++;
            }
        }
        return count;
    }
}
