package Views;

import Others.Console.Console;
import Others.Console.ConsoleColors;

public class MenuView {

        public void DrawMenu() {
                DrawAscii();
                Console.LineBreak();
                DrawSelection();
        }

        private void DrawAscii() {
                Console.WriteLine("     ____        __        _ ____        _   __                  __    ",
                                ConsoleColors.GREEN_BRIGHT);
                Console.WriteLine("    / __ )____ _/ /_____ _(_) / /__     / | / /___ __   ______ _/ /__  ",
                                ConsoleColors.GREEN_BRIGHT);

                Console.WriteLine("   / __  / __ `/ __/ __ `/ / / / _ \\   /  |/ / __ `/ | / / __ `/ / _ \\ ", null);
                Console.WriteLine("  / /_/ / /_/ / /_/ /_/ / / / /  __/  / /|  / /_/ /| |/ / /_/ / /  __/ ", null);

                Console.WriteLine(" /_____/\\__,_/\\__/\\__,_/_/_/_/\\___/  /_/ |_/\\__,_/ |___/\\__,_/_/\\___/  ",
                                ConsoleColors.RED_BRIGHT);
        }

        private void DrawSelection() {
                Console.WriteLine(" ==================================", null);
                Console.Write("|          ", null);
                Console.Write("MENU SELECTION", ConsoleColors.GREEN_UNDERLINED);
                Console.Write("          |\n", null);
                Console.WriteLine(" ==================================", null);
                Console.WriteLine("| Options:                         |", null);
                Console.WriteLine("|        1. Nouvelle partie        |", ConsoleColors.BLACK_BOLD);
                Console.WriteLine("|        2. Charger une partie     |", ConsoleColors.BLACK_BOLD);
                Console.WriteLine("|        3. Aide                   |", ConsoleColors.BLACK_BOLD);
                Console.WriteLine("|        4. Quitter                |", ConsoleColors.BLACK_BOLD);
                Console.WriteLine(" ==================================", null);

        }

}
