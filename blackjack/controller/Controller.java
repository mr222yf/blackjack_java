package blackjack.controller;

import blackjack.model.Game;
import blackjack.view.View;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private final Game game;
    private final View view;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
        game.addObserver(this);
        view.displayWelcomeMessage();
    }

    public boolean play() {
        if (game.isGameOver()) {
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

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
        view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
    }
}