package Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Models.Enums.OrientationEnum;
import Models.Enums.VisibilityStateEnum;
import Models.File.LoadingContent;
import Models.Geometry.Coord;
import Models.Ships.Battleship;
import Models.Ships.Cruiser;
import Models.Ships.Destroyer;
import Models.Ships.Ship;
import Models.Ships.Submarine;
import Models.Ships.Wreck;

public class FileController {

    public FileController() {
    }

    public void Save(Ship[][] playerFleet, Ship[][] computerFleet) throws IOException {
        ArrayList<Ship> wrote = new ArrayList<Ship>();
        var datetime = java.time.LocalTime.now();
        var writer = new FileWriter(
                "Save_" + datetime.getHour() + "h" + datetime.getMinute() + "m" + datetime.getSecond() + "s.txt");

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (playerFleet[i][j] != null) {
                    if (!wrote.contains(playerFleet[i][j])) {
                        writer.write(
                                playerFleet[i][j].toString() + playerFleet[i][j].GetOrientation().toString().charAt(0)
                                        + playerFleet[i][j].GetHealthPoints());
                        wrote.add(playerFleet[i][j]);
                    } else
                        writer.write("/");
                } else {
                    writer.write("_");
                }
                writer.write("\n");
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (computerFleet[i][j] != null) {
                    if (!wrote.contains(computerFleet[i][j])) {
                        writer.write(computerFleet[i][j].toString()
                                + computerFleet[i][j].GetOrientation().toString().charAt(0)
                                + computerFleet[i][j].GetHealthPoints());
                        wrote.add(computerFleet[i][j]);
                    } else
                        writer.write("/");
                } else {
                    writer.write("_");
                }
                writer.write("\n");
            }
        }

        writer.close();
    }

    public LoadingContent Load(String filename) throws IOException {
        var reader = new BufferedReader(new FileReader(filename));

        int compteur = 0;
        Ship[][] playerShips = new Ship[15][15];
        Ship[][] computerShips = new Ship[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                String line = reader.readLine();
                if (line.contains("/"))
                    continue;

                char shipType = line.charAt(0);

                if (shipType == '_') {
                    playerShips[i][j] = null;
                    continue;
                }

                OrientationEnum orientation = line.charAt(1) == 'V' ? OrientationEnum.Vertical
                        : OrientationEnum.Horizontal;

                int length = line.charAt(2) - '0';
                compteur = 0;
                Ship ship = null;

                switch (shipType) {

                    case 'X':
                        ship = new Cruiser(orientation);
                        break;
                    case 'C':
                        ship = new Battleship(orientation);
                        break;
                    case 'D':
                        ship = new Destroyer(orientation);

                        break;
                    case 'S':
                        ship = new Submarine(orientation);
                        break;
                    default:
                        continue;
                }
                ship.SetAnchor(new Coord((char) ('A' + i), j + 1));
                ship.SetVisibilityState(VisibilityStateEnum.Visible);
                ship.SetHealthPoints(length);

                if (orientation == OrientationEnum.Horizontal) {
                    do {
                        playerShips[i][j + compteur] = ship;
                        compteur++;
                        length -= 1;
                    } while (length > 0);
                } else {
                    do {
                        playerShips[i + compteur][j] = ship;
                        compteur++;
                        length -= 1;
                    } while (length > 0);
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                String line = reader.readLine();
                if (line.contains("/"))
                    continue;

                char shipType = line.charAt(0);

                if (shipType == '_') {
                    computerShips[i][j] = null;
                    continue;
                }

                OrientationEnum orientation = line.charAt(1) == 'V' ? OrientationEnum.Vertical
                        : OrientationEnum.Horizontal;

                int length = line.charAt(2) - '0';
                compteur = 0;
                Ship ship = null;

                switch (shipType) {

                    case 'X':
                        ship = new Cruiser(orientation);
                        break;
                    case 'C':
                        ship = new Battleship(orientation);
                        break;
                    case 'D':
                        ship = new Destroyer(orientation);

                        break;
                    case 'S':
                        ship = new Submarine(orientation);

                        break;
                }
                ship.SetVisibilityState(VisibilityStateEnum.Hidden);
                ship.SetAnchor(new Coord((char) ('A' + i), j + 1));
                ship.SetHealthPoints(length);

                if (orientation == OrientationEnum.Horizontal) {
                    do {
                        computerShips[i][j + compteur] = ship;
                        compteur++;
                        length -= 1;
                    } while (length > 0);
                } else {
                    do {
                        computerShips[i + compteur][j] = ship;
                        compteur++;
                        length -= 1;
                    } while (length > 0);
                }
            }
        }

        reader.close();
        return new LoadingContent(playerShips, computerShips);
    }

}
