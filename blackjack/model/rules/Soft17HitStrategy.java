package blackjack.model.rules;

import blackjack.model.Card;

import java.util.stream.StreamSupport;

class Soft17HitStrategy implements HitStrategy {
    @Override
    public boolean test(Integer dealerScore, Iterable<Card> cards) {
        final int HIT_LIMIT = 17;
        if (dealerScore < HIT_LIMIT) {
            return true;
        } else if(dealerScore == HIT_LIMIT) {
            return StreamSupport.stream(cards.spliterator(), false)
                    .anyMatch(card -> card.getValue().get() == Card.Value.Ace)
                    && StreamSupport.stream(cards.spliterator(), false)
                    .filter(card -> card.getValue().get() != Card.Value.Ace)
                    .mapToInt(card -> card.getValue().get().getScore())
                    .sum() <= 6;
        } else {
            return false;
        }
    }
}
