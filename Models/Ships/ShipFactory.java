package Models.Ships;

import java.util.Random;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipownerEnum;
import Models.Enums.VisibilityStateEnum;
import Models.Geometry.Coord;

public class ShipFactory {

    public Ship[][] CreateFleet(ShipownerEnum owner) {

        var rnd = new Random();
        Ship[][] fleet = new Ship[15][15];
        Ship[] ships = new Ship[] {
                new Battleship((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Cruiser((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Cruiser((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Destroyer((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Destroyer((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Destroyer((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Submarine((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Submarine((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Submarine((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

                new Submarine((rnd.nextInt(100) % 2 == 0) ? OrientationEnum.Horizontal : OrientationEnum.Vertical),

        };

        for (int i = 0; i < ships.length; i++) {

            OrientationEnum orientation = ships[i].GetOrientation();
            int shipLength = ships[i].GetLenth();
            int x;
            int y;
            boolean exit;

            do {
                x = rnd.nextInt(14) + 1;
                y = rnd.nextInt(14 - ships[i].GetLenth()) + 1;
                exit = true;

                for (int j = 0; j < shipLength; j++) {

                    if (orientation == OrientationEnum.Horizontal && fleet[x][y + j] != null
                            || orientation == OrientationEnum.Vertical
                                    && fleet[y + j][x] != null) /* cell already taken by another ship. */
                        exit = false;
                }

            } while (!exit); /* pre-checking loop to avoid rewriting on existing ship. */

            for (int j = 0; j < ships[i].GetLenth(); j++) {

                int row = (orientation == OrientationEnum.Horizontal) ? x : y + j;
                int column = (orientation == OrientationEnum.Horizontal) ? y + j : x;

                if (owner == ShipownerEnum.Computer) {
                    ships[i].SetVisibilityState(VisibilityStateEnum.Hidden);
                }

                if (j == 0)
                    ships[i].SetAnchor(new Coord((char) ('A' + row), column + 1));

                fleet[row][column] = ships[i];
            }

        }
        return fleet;
    }

}
