package Models.Geometry;

/**
 * Represents a point on screen console.
 */
public class ScreenPoint {
    private final int _row;
    private final int _column;

    public int GetRow() {
        return _row;
    }

    public int GetColumn() {
        return _column;
    }

    public ScreenPoint(int row, int column) {
        this._row = row;
        this._column = column;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", _row, _column);
    }
}
