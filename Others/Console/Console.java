package Others.Console;

public class Console {

    public static void MoveCursorTo(int row, int column) {
        System.out.print(String.format("%c[%d;%df", 0x1B, row, column));
    }

    public static void Write(String content, String color) {

        if (color == null)
            color = "\033[0m";

        System.out.print(color + content + "\033[0m");
    }

    public static void WriteLine(String content, String color) {

        if (color == null)
            color = "\033[0m";

        System.out.println(color + content + "\033[0m");
    }

    public static void LineBreak() {
        System.out.print('\n');
    }

    public static void ClearScreen(boolean fromCurrentPosition) {
        System.out.print(!fromCurrentPosition ? "\033[H\033[J" : "\033[J");
    }

    public static void ClearScreenFrom(int row, int column) {
        MoveCursorTo(row, column);
        ClearScreen(false);
    }

    public static void ClearToEOL() {
        System.out.print("\033[K");
    }

    /**
     * This method is used to save cursor position for recall later
     */
    public static void SaveCursorPosition() {
        System.out.print("\033[s");
    }

    /**
     * This method is used to return to saved cursor position.
     * 
     * @Warning : If there's no saved position, it returns to (0,0) position.
     */
    public static void MoveToSavedCursorPosition() {
        System.out.print("\033[u");
    }
}
