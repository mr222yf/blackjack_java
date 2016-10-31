package blackjack.controller;

import blackjack.model.Game;
import blackjack.view.View;

public class Controller {
    private final Game game;
    private final View view;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
        game.addNewCardListener(this::displayHands);
        view.displayWelcomeMessage();
    }

    public boolean play() {
        if (game.isGameOver()) {
            //displayHands();
            view.displayGameOver(game.isDealerWinner());
        }

        switch (view.getCommand()) {
            case NEW_GAME:
                game.newGame();
                break;
            case HIT:
                game.hitPlayer();
                break;
            case STAND:
                game.stand();
                break;
            case QUIT:
                return false;
            default:
                return true;
        }
        return true;
    }
    
    private void displayHands() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
        view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
    }
}