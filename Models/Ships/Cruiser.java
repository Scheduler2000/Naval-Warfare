package Models.Ships;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;

public class Cruiser extends Ship {

    public Cruiser(OrientationEnum orientation) {
        super(5, 4, ShipTypeEnum.Cruiser, orientation);
    }

    @Override
    public String toString() {
        return "X";
    }
}
