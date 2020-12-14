package Models.File;

import Models.Ships.Ship;

public class LoadingContent {
    private final Ship[][] _playerFleet;
    private final Ship[][] _computerFleet;

    public Ship[][] GetPlayerFleet() {
        return _playerFleet;
    }

    public Ship[][] GetComputerFleet() {
        return _computerFleet;
    }

    public LoadingContent(Ship[][] playerFleet, Ship[][] computerFleet) {
        this._playerFleet = playerFleet;
        this._computerFleet = computerFleet;
    }

}
