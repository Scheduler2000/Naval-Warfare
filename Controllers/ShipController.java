package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Models.Enums.DirectionEnum;
import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;
import Models.Enums.ShipownerEnum;
import Models.Geometry.Coord;
import Models.Ships.Ship;
import Models.Ships.Destroyer;
import Models.Ships.ShipFactory;
import Models.Ships.Wreck;
import Views.ErrorView;

public class ShipController {
    private final ErrorView _errorView;
    private final Ship[][] _playerShips;
    private final Ship[][] _computerShips;
    private final Ship _wreck;

    public Ship[][] GetPlayerShips() {
        return _playerShips;
    }

    public Ship[][] GetComputerShips() {
        return _computerShips;
    }

    public Ship GetShip(Coord coord, ShipownerEnum owner) {
        if (coord.GetColumn() < 1 || coord.GetColumn() > 15 || coord.GetRow() < 'A' || coord.GetRow() > 'O')
            return null;

        return (owner == ShipownerEnum.Player) ? _playerShips[coord.GetRow() - 'A'][coord.GetColumn() - 1]
                : _computerShips[coord.GetRow() - 'A'][coord.GetColumn() - 1];
    }

    public ShipController(ErrorView errorView, ShipFactory shipFactory) { /* Ctor for new game */
        this._errorView = errorView;
        this._playerShips = shipFactory.CreateFleet(ShipownerEnum.Player);
        this._computerShips = shipFactory.CreateFleet(ShipownerEnum.Computer);
        this._wreck = new Wreck();
    }

    public ShipController(ErrorView errorView, Ship[][] playerFleet, Ship[][] computerFleet) { /* Ctor for load game */
        this._errorView = errorView;
        this._playerShips = playerFleet;
        this._computerShips = computerFleet;
        this._wreck = new Wreck();
    }

    public void Shoot(Ship shooter, Coord targetedCell, ShipownerEnum targetedOwner) {
        var targets = new HashMap<Coord, Ship>();

        if (shooter.GetType() == ShipTypeEnum.Destroyer && ((Destroyer) shooter).HasRocket()
                && targetedOwner == ShipownerEnum.Computer) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    var cell = new Coord((char) (targetedCell.GetRow() + i), targetedCell.GetColumn() + j);
                    targets.put(cell, GetShip(cell, targetedOwner));
                }
            }
            ((Destroyer) shooter).ThrowRocket(targets.keySet(), targets.values());

        } else {

            int range = shooter.GetRange();

            targets.put(targetedCell, GetShip(targetedCell, targetedOwner));

            if (range >= 4) /* if range is equals to 4 -> cruiser */ {
                var adjNorth = new Coord((char) (targetedCell.GetRow() - 1), targetedCell.GetColumn());
                var adjEst = new Coord(targetedCell.GetRow(), targetedCell.GetColumn() + 1);
                var adjSouth = new Coord((char) (targetedCell.GetRow() + 1), targetedCell.GetColumn());
                var adjWest = new Coord(targetedCell.GetRow(), targetedCell.GetColumn() - 1);

                targets.put(adjNorth, GetShip(adjNorth, targetedOwner));
                targets.put(adjEst, GetShip(adjEst, targetedOwner));
                targets.put(adjSouth, GetShip(adjSouth, targetedOwner));
                targets.put(adjWest, GetShip(adjWest, targetedOwner));

                if (range == 9) /* if range is equals to 9 -> battleship */ {
                    var adjNorthWest = new Coord((char) (targetedCell.GetRow() - 1), targetedCell.GetColumn() - 1);
                    var adjNorthEst = new Coord((char) (targetedCell.GetRow() - 1), targetedCell.GetColumn() + 1);
                    var adjSouthWest = new Coord((char) (targetedCell.GetRow() + 1), targetedCell.GetColumn() - 1);
                    var adjSouthEst = new Coord((char) (targetedCell.GetRow() + 1), targetedCell.GetColumn() + 1);

                    targets.put(adjNorthWest, GetShip(adjNorthWest, targetedOwner));
                    targets.put(adjNorthEst, GetShip(adjNorthEst, targetedOwner));
                    targets.put(adjSouthWest, GetShip(adjSouthWest, targetedOwner));
                    targets.put(adjSouthEst, GetShip(adjSouthEst, targetedOwner));

                }
            }

            /*
             * filtering + marking of hit targets + managing anchor maybe refactor this code
             * in another method -> Single Principle Responsability (SRP)
             */

            var toRemove = new ArrayList<Coord>();
            var ships = (targetedOwner == ShipownerEnum.Player) ? _playerShips : _computerShips;

            for (var keyVal : targets.entrySet()) {
                var target = keyVal.getValue();

                if (target == null || target.GetType() == ShipTypeEnum.Wreck
                        || (target.GetType() == ShipTypeEnum.Submarine
                                && shooter.GetType() != ShipTypeEnum.Submarine)) { /*
                                                                                    * remove the wreckage | null |
                                                                                    * submarine targeted by a ship type
                                                                                    * other than submarine.
                                                                                    */

                    toRemove.add(keyVal.getKey());
                    continue;
                }

                var targetCoord = keyVal.getKey();
                ships[targetCoord.GetRow() - 'A'][targetCoord.GetColumn() - 1] = _wreck;

                if (target.GetAnchor().equals(targetCoord)
                        && target.GetHealthPoints() > 1) /*
                                                          * anchor reassignment; ship hp > 1 otherwise the ship will
                                                          * sink so anchor doesn't matter
                                                          */ {
                    int compteur = 1;
                    boolean affected = false;
                    Coord newAnchor = null;

                    if (target.GetOrientation() == OrientationEnum.Horizontal) {
                        do {
                            var next = ships[targetCoord.GetRow() - 'A'][targetCoord.GetColumn() - 1 + compteur];

                            if (next == null)
                                break;

                            if (next.equals(target)) {
                                newAnchor = new Coord((char) (targetCoord.GetRow()),
                                        targetCoord.GetColumn() + compteur);
                                target.SetAnchor(newAnchor);
                                affected = true;
                            }
                            compteur++;
                        } while (!affected);
                    } else {
                        do {
                            var next = ships[(char) ((targetCoord.GetRow() - 'A') + compteur)][targetCoord.GetColumn()
                                    - 1];

                            if (next == null)
                                break;

                            if (next.equals(target)) {
                                newAnchor = new Coord((char) (targetCoord.GetRow() + compteur),
                                        targetCoord.GetColumn());

                                target.SetAnchor(newAnchor);
                                affected = true;
                            }
                            compteur++;
                        } while (!affected);
                    }
                }
            }

            for (var val : toRemove)
                targets.remove(val);

            /*
             * ending of filtering + marking of hit targets + managing anchor.
             */

            shooter.Shoot(targets.values());
        }

    }

    public boolean MoveShip(Coord coord, DirectionEnum direction, ShipownerEnum owner) {
        int row = coord.GetRow() - 'A';
        int column = coord.GetColumn() - 1;
        var fleet = (owner == ShipownerEnum.Player) ? _playerShips : _computerShips;
        var ship = fleet[row][column];

        /*
         * Pre-check maybe refactor this code in another method -> Single Principle
         * Responsability (SRP)
         */

        if (ship == null || !ship.GetAnchor().equals(coord)) {

            _errorView.DrawMessage("bateau ou anchor null.");
            return false;
        }

        if (ship.IsDamaged()) {
            _errorView.DrawMessage("Impossible de déplacer un navire endommagé.");
            return false;
        }

        var orientation = ship.GetOrientation();

        if (orientation == OrientationEnum.Horizontal && direction != DirectionEnum.East
                && direction != DirectionEnum.West
                || orientation == OrientationEnum.Vertical && direction != DirectionEnum.South
                        && direction != DirectionEnum.North) {
            _errorView.DrawMessage(
                    "Deplacement impossible car le bateau est orienté de manière " + orientation.toString() + ".");
            return false;
        }

        int endingCell = (orientation == OrientationEnum.Horizontal) ? column + ship.GetLenth() - 1
                : row + ship.GetLenth() - 1;

        if ((direction == DirectionEnum.East && (endingCell + 1 > 14 || fleet[row][endingCell + 1] != null))
                || (direction == DirectionEnum.West && (column - 1 < 0 || fleet[row][column - 1] != null))
                || (direction == DirectionEnum.North && (row - 1 < 0 || fleet[row - 1][column] != null))
                || (direction == DirectionEnum.South
                        && (endingCell + 1 > 14 || fleet[endingCell + 1][column] != null))) {

            _errorView.DrawMessage("Deplacement impossible.");
            return false;
        }
        /*
         * ending of Pre-checking.
         */

        Coord newAnchor = null;
        switch (direction) {
            case East:

                fleet[row][column] = null;
                fleet[row][endingCell + 1] = ship;
                newAnchor = new Coord(coord.GetRow(), coord.GetColumn() + 1);
                break;
            case West:

                fleet[row][endingCell] = null;
                fleet[row][column - 1] = ship;
                newAnchor = new Coord(coord.GetRow(), coord.GetColumn() - 1);
                break;
            case North:

                fleet[endingCell][column] = null;
                fleet[row - 1][column] = ship;
                newAnchor = new Coord((char) (coord.GetRow() - 1), coord.GetColumn());
                break;
            case South:

                fleet[row][column] = null;
                fleet[endingCell + 1][column] = ship;
                newAnchor = new Coord((char) (coord.GetRow() + 1), coord.GetColumn());
                break;
        }
        ship.SetAnchor(newAnchor);
        return true;
    }

    public boolean FleetDestroyed(ShipownerEnum target) {
        boolean noMoreShips = true;
        boolean noMoreSubmarines = true;
        var ships = (target == ShipownerEnum.Computer) ? _computerShips : _playerShips;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (ships[i][j] != null) {
                    if (ships[i][j].GetType() == ShipTypeEnum.Submarine) {
                        noMoreSubmarines = false;
                    }
                    noMoreShips = false;
                }
            }
        }
        return noMoreShips || noMoreSubmarines;
    }

    public Ship FetchAleatShip(boolean isForMove, ShipownerEnum owner) {
        Ship selected = null;

        do {
            int dice = new Random().nextInt(4);
            selected = FetchFirstOrDefaultShip(dice == 0 ? ShipTypeEnum.Battleship
                    : dice == 1 ? ShipTypeEnum.Cruiser : dice == 2 ? ShipTypeEnum.Destroyer : ShipTypeEnum.Submarine,
                    owner);

            if (selected != null && selected.IsDamaged() && isForMove)
                continue;
        } while (selected == null);

        return selected;
    }

    public Ship FetchFirstOrDefaultShip(ShipTypeEnum shipType, ShipownerEnum owner) {
        var ships = (owner == ShipownerEnum.Computer) ? _computerShips : _playerShips;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (ships[i][j] != null && ships[i][j].GetType() == shipType)
                    return ships[i][j];
            }
        }

        return null;
    }
}
