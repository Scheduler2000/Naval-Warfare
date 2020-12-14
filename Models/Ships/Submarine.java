package Models.Ships;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;

public class Submarine extends Ship {

    public Submarine(OrientationEnum orientation) {
        super(1, 1, ShipTypeEnum.Submarine, orientation);
    }

    @Override
    public String toString() {
        return "S";
    }

}
