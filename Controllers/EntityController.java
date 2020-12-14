package Controllers;

import Models.Ships.Ship;

public abstract class EntityController {
    protected final ShipController shipController;

    public abstract Ship[][] GetShips();

    public abstract boolean Won();

    public EntityController(ShipController shipController) {
        this.shipController = shipController;
    }

    public abstract void PlayTurn();

    protected abstract void Shoot();

    protected abstract void Move();

}
