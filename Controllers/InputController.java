package Controllers;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import Models.Enums.ActionEnum;
import Models.Enums.DirectionEnum;
import Models.Geometry.Coord;
import Others.Console.Console;
import Views.InputView;

public class InputController {
    private final InputView _view;

    public InputController(InputView view) {
        this._view = view;
    }

    public Coord AskCoord(boolean computerCoord) {
        _view.DrawAskCoord(computerCoord);

        var scanner = new Scanner(System.in);
        String input;

        do {
            Console.MoveCursorTo(58, 18);
            Console.ClearToEOL();

            input = scanner.next();

            if (input.matches("\\[([1-9]|1[01-5])\\;(-?[A-O])\\]"))
                break;

        } while (true);

        var pattern = Pattern.compile("\\[([1-9]|1[01-5])\\;(-?[A-O])\\]");
        var matcher = pattern.matcher(input);

        char row = ' ';
        int col = 0;

        while (matcher.find()) {
            col = Integer.parseInt(matcher.group(1));
            row = matcher.group(2).charAt(0);
        }

        return new Coord(row, col);
    }

    public ActionEnum AskAction() {
        _view.DrawAskAction();

        var scanner = new Scanner(System.in);
        String input;

        do {
            Console.MoveCursorTo(58, 24);
            Console.ClearToEOL();

            input = scanner.next();
            input = input.toUpperCase(Locale.ROOT);

            if (input.equals("TIRER") || input.equals("DEPLACER") || input.equals("IA_DISPLAY") || input.equals("ESC"))
                break;

        } while (true);

        return input.equals("TIRER") ? ActionEnum.Shoot
                : input.equals("DEPLACER") ? ActionEnum.Move
                        : input.equals("ESC") ? ActionEnum.SaveAndExit : ActionEnum.DisplayIA;

    }

    public DirectionEnum AskDirection() {
        _view.DrawAskDirection();

        var scanner = new Scanner(System.in);
        var valids = Arrays.asList("SUD", "NORD", "EST", "OUEST");

        String input;

        do {
            Console.MoveCursorTo(58, 32);
            Console.ClearToEOL();

            input = scanner.next();
            input = input.toUpperCase(Locale.ROOT);

            if (valids.contains(input))
                break;

        } while (true);

        return input.equals("SUD") ? DirectionEnum.South
                : input.equals("NORD") ? DirectionEnum.North
                        : input.equals("EST") ? DirectionEnum.East : DirectionEnum.West;

    }

    public String AskFile() {
        _view.DrawAskFile();
        var scanner = new Scanner(System.in);

        String input;

        do {
            Console.MoveCursorTo(58, 36);
            Console.ClearToEOL();
            input = scanner.next();
            if (new File(input).exists())
                break;
        } while (true);

        return input;
    }

}
