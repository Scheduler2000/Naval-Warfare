package Views;

import Others.Console.Console;
import Others.Console.ConsoleColors;

public class ErrorView {
    public void DrawMessage(String message) {
        Console.MoveCursorTo(63, 1);
        Console.Write("Error : " + message, ConsoleColors.RED_BOLD);
    }
}
