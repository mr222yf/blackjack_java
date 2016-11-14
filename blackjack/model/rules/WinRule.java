package blackjack.model.rules;

public interface WinRule {
    boolean didDealerWin(int dealerScore, int playerScore, final int MAX_SCORE);
}
