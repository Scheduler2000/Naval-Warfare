package Controllers;

import java.io.IOException;

import Models.Enums.ShipownerEnum;
import Models.Enums.VisibilityStateEnum;
import Models.Geometry.Coord;
import Models.Ships.Ship;

public class PlayerController extends EntityController {
    private InputController _inputController;
    private FileController _filerController;

    @Override
    public Ship[][] GetShips() {
        return super.shipController.GetPlayerShips();
    }

    @Override
    public boolean Won() {
        return super.shipController.FleetDestroyed(ShipownerEnum.Computer);

    }

    public PlayerController(ShipController shipController, InputController inputController,
            FileController fileController) {
        super(shipController);
        this._inputController = inputController;
        this._filerController = fileController;
    }

    @Override
    public void PlayTurn() {
        var action = _inputController.AskAction();

        switch (action) {
            case Move:
                Move();
                break;
            case Shoot:
                Shoot();
                break;
            case DisplayIA:
                var ships = super.shipController.GetComputerShips();

                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        if (ships[i][j] != null) {
                            ships[i][j].SetVisibilityState(
                                    (ships[i][j].GetVisibilityState() == VisibilityStateEnum.Visible)
                                            ? VisibilityStateEnum.Hidden
                                            : VisibilityStateEnum.Visible);
                        }
                    }
                }
                break;
            case SaveAndExit:
                try {
                    _filerController.Save(super.shipController.GetPlayerShips(),
                            super.shipController.GetComputerShips());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
                System.exit(0);
                break;
        }
    }

    @Override
    protected void Move() {
        boolean moved = false;

        do {
            var coord = _inputController.AskCoord(false);
            var direction = _inputController.AskDirection();
            moved = super.shipController.MoveShip(coord, direction, ShipownerEnum.Player);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!moved);

    }

    @Override
    protected void Shoot() {
        Ship shooter = null;
        Coord targetedCell = null;

        do
            shooter = super.shipController.GetShip(_inputController.AskCoord(false), ShipownerEnum.Player);
        while (shooter == null);

        targetedCell = _inputController.AskCoord(true);

        super.shipController.Shoot(shooter, targetedCell, ShipownerEnum.Computer);
    }
}
