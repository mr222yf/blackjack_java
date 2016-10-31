package blackjack.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        for (Card.Color color : Card.Color.values()) {
            for (Card.Value value : Card.Value.values()) {
                cards.add(new Card(color, value));
            }
        }
        Collections.shuffle(cards);
    }

    public Card getCard() {
        return cards.remove(0);
    }

    public Iterable<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}