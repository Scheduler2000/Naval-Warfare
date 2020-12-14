package Controllers;

import Views.GameView;

public class GameController {
    private final GameView _gameView;
    private final PlayerController _playerController;
    private final ComputerController _computerController;

    public boolean PlayerWon() {
        return _playerController.Won();
    }

    public boolean ComputerWon() {
        return _computerController.Won();
    }

    public GameController(GameView gameView, PlayerController playerController, ComputerController computerController) {
        this._gameView = gameView;
        this._playerController = playerController;
        this._computerController = computerController;
    }

    public void InitializeGame() {
        _gameView.DrawGame();
        _gameView.DrawShips(_playerController.GetShips(), _computerController.GetShips());
    }

    public void PlayerTurn() {
        _playerController.PlayTurn();

        _gameView.DrawShips(_playerController.GetShips(), _computerController.GetShips());
    }

    public void ComputerTurn() {
        _computerController.PlayTurn();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        _gameView.DrawShips(_playerController.GetShips(), _computerController.GetShips());

    }

}