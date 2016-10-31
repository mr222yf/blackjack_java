package blackjack.model;

import blackjack.model.rules.RulesFactory;

public class Game {
    private final Dealer dealer;
    private final Player player;

    public Game() {
        player = new Player();
        dealer = new Dealer(player, new RulesFactory());
    }

    public enum Command {NEW_GAME, HIT, STAND, QUIT, OTHER} //TODO where should this be?

    public boolean isGameOver() {
        return dealer.isGameOver();
    }

    public boolean isDealerWinner() {
        return dealer.isDealerWinner();
    }

    public void newGame() {
        dealer.newGame();
    }

    public void hitPlayer() {
        dealer.hitPlayer();
    }

    public void stand() {
        dealer.stand();
    }

    public Iterable<Card> getDealerHand() {
        return dealer.getHand();
    }

    public Iterable<Card> getPlayerHand() {
        return player.getHand();
    }

    public int getDealerScore() {
        return dealer.calcScore();
    }

    public int getPlayerScore() {
        return player.calcScore();
    }

    public void addNewCardListener(Runnable newCardListener){
        dealer.addListener(newCardListener);
        player.addListener(newCardListener);
    }
}