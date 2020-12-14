package Models.Ships;

import java.util.Collection;
import java.util.Set;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;
import Models.Geometry.Coord;

public class Destroyer extends Ship {

    private boolean _rocketThrown;

    public Destroyer(OrientationEnum orientation) {
        super(3, 1, ShipTypeEnum.Destroyer, orientation);
        _rocketThrown = false;
    }

    public boolean HasRocket() {
        return !_rocketThrown;
    }

    public void ThrowRocket(Set<Coord> cells, Collection<Ship> targets) {
        for (var target : targets) {
            if (target != null) {
                target.SufferRocket(cells);
            }
        }
        _rocketThrown = true;
    }

    @Override
    public String toString() {
        return "D";
    }
}
