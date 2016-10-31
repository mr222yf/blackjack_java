package blackjack.model.rules;

class PlayerLosesOnEqualRule implements WinRule {
    @Override
    public Boolean apply(Integer dealerScore, Integer playerScore) {
        final int MAX_SCORE = 21;
        if (playerScore > MAX_SCORE) {
            return true;
        } else if (dealerScore > MAX_SCORE) {
            return false;
        }
        return dealerScore >= playerScore;
    }
}
