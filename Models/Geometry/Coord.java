package Models.Geometry;

/**
 * Represents a case on gameboard.
 */
public class Coord {

    private final char _row;
    private final int _column;

    public char GetRow() {
        return _row;
    }

    public int GetColumn() {
        return _column;
    }

    public Coord(char row, int column) {
        this._row = row;
        this._column = column;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", _row, _column);
    }

    @Override
    public boolean equals(Object obj) {
        var coord = (Coord) obj;

        return this._row == coord.GetRow() && this._column == coord.GetColumn();
    }

}
