package blackjack.model.rules;

@FunctionalInterface
public interface NewGameStrategy {
    void newGame(Runnable hitPlayer, Runnable hitDealerAndShow, Runnable hitDealerAndKeepHidden);
}