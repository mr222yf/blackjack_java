package blackjack.model;

import blackjack.model.rules.HitStrategy;
import blackjack.model.rules.NewGameStrategy;
import blackjack.model.rules.RulesFactory;
import blackjack.model.rules.WinRule;

class Dealer extends Player {
    private final Player player;
    private final NewGameStrategy newGameRule;
    private final HitStrategy hitRule;
    private final WinRule winRule;
    private Deck deck;

    public Dealer(Player player, RulesFactory rulesFactory) {
        this.player = player;
        this.newGameRule = rulesFactory.getNewGameRule();
        this.hitRule = rulesFactory.getHitRule();
        this.winRule = rulesFactory.getWinRule();
    }

    public void newGame() {
        deck = new Deck();
        clearHand();
        player.clearHand();
        newGameRule.newGame(this::hitPlayer, this::hitDealerAndShow, this::hitDealerAndKeepHidden);
    }

    public void hitPlayer() {
        player.dealCard(deck.getCard().show());
    }

    private void hitDealerAndShow(){
        dealCard(deck.getCard().show());
    }

    private void hitDealerAndKeepHidden(){
        dealCard(deck.getCard());
    }

    public boolean isDealerWinner() {
        return winRule.apply(calcScore(), player.calcScore());
    }

    public boolean isGameOver() {
        return deck != null && !deck.isEmpty() && !hitRule.test(calcScore(), getHand());
    }

    public void stand() {
        showHand();

        while (hitRule.test(calcScore(), getHand())) {
            dealCard(deck.getCard().show());
        }
    }

    private void showHand() {
        if (hasHiddenCard()) {
            getHand().forEach(Card::show);
            listeners.forEach(Runnable::run);
        }
    }
}