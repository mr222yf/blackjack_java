package blackjack.model.rules;

class PlayerWinsOnEqualRule implements WinRule {
    @Override
    public boolean didDealerWin(int dealerScore, int playerScore, final int MAX_SCORE) {
        if (playerScore > MAX_SCORE) {
            return true;
        } else if (dealerScore > MAX_SCORE) {
            return false;
        }
        return dealerScore > playerScore;
    }

}
