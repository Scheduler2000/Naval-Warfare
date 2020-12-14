package Models.Ships;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;

public class Wreck extends Ship {

    public Wreck() {
        super(0, 0, ShipTypeEnum.Wreck, OrientationEnum.None);
    }

    @Override
    public String toString() {
        return "■";
    }
}
