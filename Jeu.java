
import Controllers.ComputerController;
import Controllers.FileController;
import Controllers.GameController;
import Controllers.InputController;
import Controllers.MenuController;
import Controllers.PlayerController;
import Controllers.ShipController;
import Models.Enums.*;
import Models.File.LoadingContent;
import Models.Ships.ShipFactory;
import Views.ErrorView;
import Views.GameView;
import Views.HelpView;
import Views.InputView;
import Views.MenuView;
import Others.Console.Console;

public class Jeu {
        public static void main(String[] args) throws Exception {
                Console.ClearScreen(false);

                InputController inputController = new InputController(new InputView());
                MenuController menuController = new MenuController(new MenuView(), new HelpView());
                MenuChoiceEnum mc = menuController.ChooseGame();
                FileController fc = new FileController();

                GameController game = null;
                ShipController shipController = null;

                switch (mc) {
                        case NewGame:
                                shipController = new ShipController(new ErrorView(), new ShipFactory());
                                game = new GameController(new GameView(),
                                                new PlayerController(shipController, inputController, fc),
                                                new ComputerController(shipController));
                                game.InitializeGame();

                                while (true) {
                                        game.PlayerTurn();
                                        if (game.PlayerWon()) {
                                                System.out.println("Player won !");
                                                break;
                                        }
                                        game.ComputerTurn();
                                        if (game.ComputerWon()) {
                                                System.out.println("IA won !");
                                                break;
                                        }
                                }

                                break;
                        case LoadGame:
                                fc = new FileController();
                                String filename = inputController.AskFile();
                                LoadingContent content = fc.Load(filename);

                                shipController = new ShipController(new ErrorView(), content.GetPlayerFleet(),
                                                content.GetComputerFleet());

                                game = new GameController(new GameView(),
                                                new PlayerController(shipController, inputController, fc),
                                                new ComputerController(shipController));

                                game.InitializeGame();

                                while (true) {
                                        game.PlayerTurn();
                                        if (game.PlayerWon()) {
                                                System.out.println("Player won !");
                                                break;
                                        }
                                        game.ComputerTurn();
                                        if (game.ComputerWon()) {
                                                System.out.println("IA won !");
                                                break;
                                        }
                                }

                                break;
                        case Exit:
                                System.exit(0);
                                break;
                }

        }
}