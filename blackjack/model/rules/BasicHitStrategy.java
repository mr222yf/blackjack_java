package blackjack.model.rules;

import blackjack.model.Card;

class BasicHitStrategy implements HitStrategy {

    @Override
    public boolean test(Integer dealerScore, Iterable<Card> cards) {
        final int HIT_LIMIT = 17;
        return dealerScore < HIT_LIMIT;
    }
}