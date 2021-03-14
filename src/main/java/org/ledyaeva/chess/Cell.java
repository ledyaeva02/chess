package org.ledyaeva.chess;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        // Допустимый интервал для координат: 1..8
        if (!(1 <= x && x <= 8) || !(1 <= y && y <= 8))
            throw new IllegalArgumentException("Each coordinate must be in 1..8 interval");

        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (getX() != cell.getX()) return false;
        return getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
