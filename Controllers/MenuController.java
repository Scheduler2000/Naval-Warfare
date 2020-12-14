package Controllers;

import java.util.Scanner;

import Models.Enums.MenuChoiceEnum;
import Others.Console.Console;
import Others.Console.ConsoleColors;
import Views.HelpView;
import Views.MenuView;

public class MenuController {
    private final HelpView _helpView;

    public MenuController(MenuView view, HelpView helpView) {
        this._helpView = helpView;
        view.DrawMenu();
    }

    public MenuChoiceEnum ChooseGame() {

        int choice = 0;
        var scanner = new Scanner(System.in);

        Console.LineBreak();
        Console.SaveCursorPosition();

        do {
            Console.MoveToSavedCursorPosition();
            Console.ClearScreen(true);

            Console.Write("Select option : ", ConsoleColors.GREEN_BRIGHT);
            try {
                choice = scanner.nextInt();

                if (choice == 3) {
                    _helpView.DrawRules();
                    choice = -1;
                }
            } catch (Exception ex) {
                scanner.next();
                continue;
            }

        } while (choice < 1 || choice > 4);

        Console.MoveToSavedCursorPosition();
        Console.ClearToEOL();

        return (choice == 1) ? MenuChoiceEnum.NewGame : (choice == 2) ? MenuChoiceEnum.LoadGame : MenuChoiceEnum.Exit;
    }

}
