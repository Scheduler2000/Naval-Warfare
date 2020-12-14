package Models.Ships;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;

public class Battleship extends Ship {

    public Battleship(OrientationEnum orientation) {
        super(7, 9, ShipTypeEnum.Battleship, orientation);
    }

    @Override
    public String toString() {
        return "C";
    }

}
