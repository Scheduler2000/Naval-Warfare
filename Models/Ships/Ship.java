package Models.Ships;

import java.util.ArrayList;
import java.util.Collection;

import Models.Enums.OrientationEnum;
import Models.Enums.ShipTypeEnum;
import Models.Enums.VisibilityStateEnum;
import Models.Geometry.Coord;
import Others.Console.Console;
import Others.Console.ConsoleColors;

public abstract class Ship {

    private Coord _anchor;
    private int _healthPoints;
    private final int _length;
    private final int _range;
    private final ShipTypeEnum _type;
    private final OrientationEnum _orientation;
    private VisibilityStateEnum _visibilityState;
    private Collection<Coord> _rocketImpact;

    public int GetHealthPoints() {
        return _healthPoints;
    }

    public void SetHealthPoints(int hp) {
        _healthPoints = hp;
    }

    public int GetLenth() {
        return _length;
    }

    public Coord GetAnchor() {
        return _anchor;
    }

    public void SetAnchor(Coord anchor) {
        _anchor = anchor;
    }

    public int GetRange() {
        return _range;
    }

    public ShipTypeEnum GetType() {
        return _type;
    }

    public OrientationEnum GetOrientation() {
        return _orientation;
    }

    public VisibilityStateEnum GetVisibilityState() {
        return _visibilityState;
    }

    public void SetVisibilityState(VisibilityStateEnum state) {
        _visibilityState = state;
    }

    public Collection<Coord> GetRocketImpact() {
        return _rocketImpact;
    }

    protected Ship(int length, int range, ShipTypeEnum type, OrientationEnum orientation) {
        this._healthPoints = length;
        this._length = length;
        this._anchor = null;
        this._range = range;
        this._type = type;
        this._orientation = orientation;
        this._visibilityState = VisibilityStateEnum.Visible;
        this._rocketImpact = new ArrayList<Coord>();
    }

    public boolean IsDamaged() {
        return _healthPoints != _length;
    }

    public boolean IsSank() {
        return _healthPoints == 0;
    }

    public void SufferDamage() {
        _healthPoints -= 1;
    }

    public void SufferRocket(Collection<Coord> cells) {
        SetVisibilityState(VisibilityStateEnum.PartiallyHidden);
        _rocketImpact.addAll(cells);
    }

    public void RepairRocketDamage() {
        SetVisibilityState(VisibilityStateEnum.Hidden);
        _rocketImpact.clear();
    }

    public void Shoot(Collection<Ship> targets) {

        for (var target : targets) {

            target.SufferDamage();

            if (target.IsSank())
                Console.Write(target.GetType().toString() + " sank.", ConsoleColors.RED_UNDERLINED);

        }

    }
}
