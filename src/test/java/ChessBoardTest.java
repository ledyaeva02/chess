import org.junit.Test;
import org.ledyaeva.chess.Cell;
import org.ledyaeva.chess.Chessboard;
import org.ledyaeva.chess.Colors;
import org.ledyaeva.chess.pieces.King;
import org.ledyaeva.chess.pieces.Knight;
import org.ledyaeva.chess.pieces.Pawn;
import org.ledyaeva.chess.pieces.Queen;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    @Test
    public void constructor() {
        // выход за границу доски
        assertThrows(IllegalArgumentException.class, () -> new Chessboard(new Cell(0, 0), new Cell(1, 2)));
        // короли-соседи
        assertThrows(IllegalArgumentException.class, () -> new Chessboard(new Cell(1, 1), new Cell(1, 2)));
        // выход за границу доски
        assertThrows(IllegalArgumentException.class, () -> new Chessboard(new Cell(9, 5), new Cell(3, 4)));
        // одинаковые координаты
        assertThrows(IllegalArgumentException.class, () -> new Chessboard(new Cell(4, 4), new Cell(4, 4)));
        // all ok
        assertDoesNotThrow(() -> new Chessboard(new Cell(4, 4), new Cell(6, 6)));
    }

    @Test
    public void mainTest() {
        Chessboard chessboard = new Chessboard(new Cell(1, 1), new Cell(8, 8));

        assertThrows(IllegalArgumentException.class, () -> chessboard.addPiece(new King(Colors.BLACK, new Cell(2, 3))));
        assertDoesNotThrow(() -> chessboard.addPiece(new Pawn(Colors.WHITE, new Cell(2, 3))));
        // поле занято
        assertThrows(IllegalArgumentException.class, () -> chessboard.addPiece(new Pawn(Colors.WHITE, new Cell(2, 3))));

        assertDoesNotThrow(() -> chessboard.clearCell(new Cell(2, 3)));

        assertDoesNotThrow(() -> chessboard.addPiece(new Knight(Colors.BLACK, new Cell(2, 3))));

        assertDoesNotThrow(() -> chessboard.movePiece(new Cell(2, 3), new Cell(3, 3)));

        // на 1:1 уже стоит король
        assertThrows(IllegalArgumentException.class, () -> chessboard.movePiece(new Cell(3, 3), new Cell(1, 1)));
        // сближение королей
        assertThrows(IllegalArgumentException.class, () -> chessboard.movePiece(new Cell(1, 1), new Cell(7, 8)));

        assertDoesNotThrow(() -> chessboard.movePiece(new Cell(3, 3), new Cell(3, 2)));

        // Удаление короля
        assertThrows(IllegalArgumentException.class, () -> chessboard.clearCell(new Cell(1, 1)));
        assertDoesNotThrow(() -> chessboard.clearCell(new Cell(3, 2)));

        assertDoesNotThrow(() -> chessboard.addPiece(new Queen(Colors.WHITE, new Cell(5, 5))));

        // добавление фигуры на занятую клетку
        assertThrows(IllegalArgumentException.class, () -> chessboard.addPiece(new Queen(Colors.BLACK, new Cell(5, 5))));

        assertDoesNotThrow(() -> chessboard.clearCell(new Cell(5, 5)));
        assertDoesNotThrow(() -> chessboard.addPiece(new Queen(Colors.BLACK, new Cell(5, 5))));
    }
}
