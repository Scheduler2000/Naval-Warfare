package Views;

import Others.Console.Console;
import Others.Console.ConsoleColors;

public class InputView {

    public void DrawAskCoord(boolean computerCoord) {

        Console.MoveCursorTo(53, 1);
        Console.ClearScreen(true);

        Console.WriteLine(" ==================================", null);
        Console.Write("|          ", null);
        Console.Write("INPUT COORD", ConsoleColors.GREEN_UNDERLINED);
        Console.Write("             |\n", null);
        Console.WriteLine(" ==================================", null);
        if (!computerCoord) {
            Console.WriteLine("| Entrer les coordonnées du bateau |", null);
            Console.WriteLine("| à utiliser sous la forme         |", null);
        } else {
            Console.WriteLine("| Entrer les coordonnées de la     |", null);
            Console.WriteLine("| cellule souhaitée sous la forme  |", null);
        }
        Console.WriteLine("| [Column;Row] :                   |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine(" ==================================", null);
    }

    public void DrawAskAction() {
        Console.MoveCursorTo(53, 1);
        Console.ClearScreen(true);

        Console.WriteLine(" ==================================", null);
        Console.Write("|         ", null);
        Console.Write("INPUT ACTION", ConsoleColors.GREEN_UNDERLINED);
        Console.Write("             |\n", null);
        Console.WriteLine(" ==================================", null);
        Console.WriteLine("| Entrer l'action à effectuer      |", null);
        Console.WriteLine("| sous la forme                    |", null);
        Console.WriteLine("| [Tirer | Deplacer] :             |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine(" ==================================", null);

    }

    public void DrawAskDirection() {
        Console.MoveCursorTo(53, 1);
        Console.ClearScreen(true);

        Console.WriteLine(" ==================================", null);
        Console.Write("|        ", null);
        Console.Write("INPUT DIRECTION", ConsoleColors.GREEN_UNDERLINED);
        Console.Write("          |\n", null);
        Console.WriteLine(" ==================================", null);
        Console.WriteLine("| Entrer l'orientation pour le     |", null);
        Console.WriteLine("| déplacement sous la forme        |", null);
        Console.WriteLine("| [Sud | Nord | Est | Ouest] :     |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine(" ==================================", null);

    }

    public void DrawAskFile() {
        Console.MoveCursorTo(53, 1);
        Console.ClearScreen(true);

        Console.WriteLine(" ==================================", null);
        Console.Write("|        ", null);
        Console.Write("INPUT FILENAME", ConsoleColors.GREEN_UNDERLINED);
        Console.Write("            |\n", null);
        Console.WriteLine(" ==================================", null);
        Console.WriteLine("| Entrer le nom du fichier         |", null);
        Console.WriteLine("| utilisé pour charger le jeu      |", null);
        Console.WriteLine("| [Nom du fichier de sauvegarde] : |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine("|                                  |", null);
        Console.WriteLine(" ==================================", null);
    }
}
