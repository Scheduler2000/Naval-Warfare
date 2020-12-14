package Views;

import java.util.ArrayList;

import Models.Enums.ShipTypeEnum;
import Models.Enums.VisibilityStateEnum;
import Models.Geometry.Coord;
import Models.Geometry.ScreenPoint;
import Models.Ships.Ship;
import Others.Console.Console;
import Others.Console.ConsoleColors;

public class GameView {

    public ScreenPoint GetCell(Coord coord, boolean playerBoard) {
        var row = 21 + (2 * ((int) coord.GetRow() - (int) 'A'));
        var column = (playerBoard ? 7 : 77) + (4 * (coord.GetColumn() - 1));

        return new ScreenPoint(row, column);
    }

    public GameView() {
    }

    public void DrawShips(Ship[][] playerShips, Ship[][] computerShips) {

        var shipsRocketed = DrawFleets(playerShips, computerShips);

        if (shipsRocketed.size() != 0) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (var ship : shipsRocketed) {
                ship.RepairRocketDamage();
            }

            DrawShips(playerShips, computerShips);
        }

        Console.MoveCursorTo(60, 1);

    }

    public void DrawGame() {
        Console.MoveToSavedCursorPosition();
        Console.Write(" Tableau du joueur :", ConsoleColors.GREEN);
        Console.Write("\t\t\t\t\t\t       Tableau de l'ordinateur : ", ConsoleColors.RED);

        System.out.print(
                "\n _______________________________________________________________       _______________________________________________________________\n");

        for (int line = 0; line < 16; line++) {
            for (int column = 0; column < 16; column++) {

                if (line == 0 && column == 0)
                    System.out.print("| \\ |");

                if (line > 0 && column == 0)
                    System.out.print(String.format("| %s |", (char) ('A' + line - 1)));

                if (line == 0 && column > 0)
                    System.out.print(column < 10 ? String.format(" %s |", column) : String.format(" %s|", column));

                if (line > 0 && column < 15)
                    System.out.print("   |");
            }

            for (int column = 0; column < 16; column++) {

                if (line == 0 && column == 0)
                    System.out.print("     | \\ |");

                if (line > 0 && column == 0)
                    System.out.print(String.format("     | %s |", (char) ('A' + line - 1)));

                if (line == 0 && column > 0)
                    System.out.print(column < 10 ? String.format(" %s |", column) : String.format(" %s|", column));

                if (line > 0 && column < 15)
                    System.out.print("   |");
            }

            System.out.print(
                    "\n|---------------------------------------------------------------       ---------------------------------------------------------------|\n");
        }
    }

    private ArrayList<Ship> DrawFleets(Ship[][] playerShips, Ship[][] computerShips) {
        var shipsRocketed = new ArrayList<Ship>();

        for (int row = 0; row < 15; row++) {
            for (int column = 0; column < 15; column++) {
                var playerShip = playerShips[row][column];
                var computerShip = computerShips[row][column];

                var coord = new Coord((char) ('A' + row), column + 1);
                var screenPos = GetCell(coord, true);
                Console.MoveCursorTo(screenPos.GetRow(), screenPos.GetColumn());

                if (playerShip != null) {
                    var shipType = playerShip.GetType();

                    Console.Write(playerShip.toString(),
                            shipType == ShipTypeEnum.Battleship ? ConsoleColors.RED
                                    : shipType == ShipTypeEnum.Cruiser ? ConsoleColors.GREEN
                                            : shipType == ShipTypeEnum.Destroyer ? ConsoleColors.WHITE_BOLD
                                                    : shipType == ShipTypeEnum.Wreck ? ConsoleColors.BLUE_BOLD
                                                            : ConsoleColors.BLACK_BOLD);
                } else {
                    Console.Write(" ", null);
                }

                screenPos = GetCell(coord, false);
                Console.MoveCursorTo(screenPos.GetRow(), screenPos.GetColumn());

                if (computerShip != null && computerShip.GetType() == ShipTypeEnum.Wreck) {
                    Console.Write("■", ConsoleColors.BLUE_BOLD);
                }

                else if (computerShip != null) {
                    if (computerShip.GetVisibilityState() == VisibilityStateEnum.Visible) {
                        var shipType = computerShip.GetType();

                        Console.Write(computerShip.toString(),
                                shipType == ShipTypeEnum.Battleship ? ConsoleColors.RED
                                        : shipType == ShipTypeEnum.Cruiser ? ConsoleColors.GREEN
                                                : shipType == ShipTypeEnum.Destroyer ? ConsoleColors.WHITE_BOLD
                                                        : ConsoleColors.BLACK_BOLD);

                    } else if (computerShip.GetVisibilityState() == VisibilityStateEnum.PartiallyHidden
                            && computerShip.GetRocketImpact().contains(coord)) {
                        Console.Write(computerShip.toString(), ConsoleColors.WHITE_UNDERLINED);
                        shipsRocketed.add(computerShip);
                    } else {
                        Console.Write(" ", null);
                    }
                } else {
                    Console.Write(" ", null);
                }
            }
        }
        Console.LineBreak();
        Console.LineBreak();
        Console.ClearScreen(true);

        return shipsRocketed;
    }

}
