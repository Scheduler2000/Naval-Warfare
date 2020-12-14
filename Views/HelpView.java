package Views;

import java.util.Scanner;

import Others.Console.Console;
import Others.Console.ConsoleColors;

public class HelpView {

        public void DrawRules() {

                Console.LineBreak();
                Console.WriteLine(" ==================================", null);
                Console.Write("|          ", null);
                Console.Write("    RÈGLES    ", ConsoleColors.GREEN_UNDERLINED);
                Console.Write("          |\n", null);
                Console.WriteLine(" ==================================", null);

                Console.Write("\nBienvenue dans la zone d'aide au combat naval !\n\n", ConsoleColors.WHITE_UNDERLINED);
                Console.Write("Dans ce tutoriel, vous apprendrez les règles de base de ce jeu.\n", null);
                Console.Write("Lorsque vous demarrez le jeu, vous avez le choix entre 4 options: \n\t1: Jouer une partie\n\t2: Charger une partie\n\t3: Aide\n\t4: Quitter le jeu\n\n",
                                null);
                Console.Write("Dans le mode 'Jouer une partie', vous disposez d'une flotte constituée de 10 bateaux : \n\t-1 cuirasse de 7 cases;\n\t-2 croiseurs de 5 cases;\n\t-3 destroyers de 3 cases;\n\t-4 sous-marins de 1 case;\n",
                                null);
                Console.Write("Ces navires sont placés aleatoirement dans une grille de 15x15 cases tout comme les navires de l'ordinateur.\n\n",
                                null);
                Console.Write("Lorsque la bataille commence, une grille vide s'affiche: c'est votre grille de tir qui correspond a la flotte de votre adversaire, sans la position de ses bateaux bien evidemment !\n",
                                null);
                Console.Write("Dans votre grille, les <C> correspondent aux cases occupees par le cuirasse, les <D> aux cases occupees par le destroyer, les <X> pour les croiseurs et les <S> pour les sous-marins\n",
                                null);
                Console.Write("Lors du 1er tour,vous jouez en 1er et pouvez utiliser n'importe quel des navires et le deplacer ou attaquer l'adversaire.\n",
                                null);
                Console.Write("-Les cuirasses ont une puissance de tir de 9 cases a partir du coin en haut � gauche;\n-Les croiseurs ont une portee de 4 cases;\n-Les destroyes ont une portee d'une case;\n-Les sous-marins ont une portee d'une case\n\n",
                                null);
                Console.Write("Ces navires se distinguent tous et n'ont pas les memes capacites: Les destroyers disposent d'une seul fusée eclairantes permettant de devoiler une zone de 4x4 cases au coin haut gauche mais cet effet s'estompe a la fin du tour \nLes sous-marins ne peuvent etre coulés que par d'autres sous-marins. \n",
                                null);
                Console.Write("A chaque tour, 2 choix s'offrent a vous: Deplacer un navire ou Tirer avec un navire",
                                null);
                Console.Write("Le deplacement s'effectue selon la direction de votre bateau: si votre bateau est a l'horizontale, il ne peut se deplacer que vers l Est ou l Ouest et si le bateau est a la verticale, il ne peut se deplacer que vers le Nord ou le Sud, cela ne s'applique pas aux bateaux 1x1 case.\n",
                                null);
                Console.Write("La partie est gagnée lorsque tous les bateaux ennemis sont detruits!\n\n", null);

                Console.Write("Que la chance soit de votre coté soldat !!!\n", null);
                Console.Write("Appuyez sur une touche pour quitter.", ConsoleColors.RED_BACKGROUND);

                var scanner = new Scanner(System.in);
                scanner.next();

        }

}
