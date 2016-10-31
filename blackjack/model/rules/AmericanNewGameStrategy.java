package blackjack.model.rules;

class AmericanNewGameStrategy implements NewGameStrategy {
    @Override
    public void newGame(Runnable hitPlayer, Runnable hitDealerAndShow, Runnable hitDealerAndKeepHidden) {
        hitPlayer.run();
        hitDealerAndShow.run();
        hitPlayer.run();
        hitDealerAndKeepHidden.run();
    }
}