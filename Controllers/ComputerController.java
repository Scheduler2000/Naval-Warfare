package Controllers;

import java.util.ArrayList;
import java.util.Random;

import Models.Enums.DirectionEnum;
import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;
import Models.Enums.ShipownerEnum;
import Models.Geometry.Coord;
import Models.Ships.Ship;

public class ComputerController extends EntityController {
    private final Random _random;
    private final ArrayList<Coord> _targetedCells;

    @Override
    public Ship[][] GetShips() {
        return super.shipController.GetComputerShips();
    }

    @Override
    public boolean Won() {
        return super.shipController.FleetDestroyed(ShipownerEnum.Player);
    }

    public ComputerController(ShipController shipController) {
        super(shipController);
        this._random = new Random();
        this._targetedCells = new ArrayList<Coord>();
    }

    @Override
    public void PlayTurn() {
        if (_random.nextInt(100) < 75)
            Shoot();
        else
            Move();
    }

    @Override
    protected void Shoot() {
        Ship shooter = super.shipController.FetchAleatShip(false, ShipownerEnum.Computer);
        Coord cell = null;
        if (_targetedCells.size() != 225) {
            do {
                int row = _random.nextInt(16);
                int column = _random.nextInt(15) + 1;
                cell = new Coord((char) ('A' + row), column);

            } while (_targetedCells.contains(cell));
            _targetedCells.add(cell);

        } else {
            cell = super.shipController.FetchFirstOrDefaultShip(ShipTypeEnum.Submarine, ShipownerEnum.Player)
                    .GetAnchor();
        }
        super.shipController.Shoot(shooter, cell, ShipownerEnum.Player);
        System.out.print("IA : shooter = " + shooter.GetType().toString() + " | cell = " + cell.toString());
    }

    @Override
    protected void Move() {
        Ship selected = super.shipController.FetchAleatShip(true, ShipownerEnum.Computer);
        DirectionEnum direction;

        if (selected.GetOrientation() == OrientationEnum.Horizontal) {
            direction = (_random.nextInt(100) % 2 == 0) ? DirectionEnum.East : DirectionEnum.West;
        } else {
            direction = (_random.nextInt(100) % 2 == 0) ? DirectionEnum.North : DirectionEnum.South;
        }

        super.shipController.MoveShip(selected.GetAnchor(), direction, ShipownerEnum.Computer);
        System.out.print("IA : selected = " + selected.GetType().toString() + " | direction = " + direction.toString());

    }

}
