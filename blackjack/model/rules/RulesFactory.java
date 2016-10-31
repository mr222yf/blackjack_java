package blackjack.model.rules;

public class RulesFactory {

    public HitStrategy getHitRule() {
        return new Soft17HitStrategy();
    }

    public NewGameStrategy getNewGameRule() {
        return new AmericanNewGameStrategy();
    }

    public WinRule getWinRule() {
        return new PlayerLosesOnEqualRule();
    }
}