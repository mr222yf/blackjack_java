package blackjack.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

class Player extends Observable {
    static final int MAX_SCORE = 21;
    private final List<Card> hand = new LinkedList<>();

    public Iterable<Card> getHand() {
        return hand;
    }

    public int calcScore() {
        int score = hand.stream()
                .filter(card -> card.getValue().isPresent())
                .mapToInt(card -> card.getValue().get().getScore())
                .sum();

        if (score > MAX_SCORE && hasCardWithValue(Card.Value.Ace)) {
            score -= 10;
        }
        return score;
    }

    void clearHand() {
        hand.clear();
    }

    void dealCard(Card card) {
        hand.add(card);
        setChanged();
        notifyObservers();
    }

    boolean hasHiddenCard() {
        return hand.stream().anyMatch(Card::isHidden);
    }

    private boolean hasCardWithValue(Card.Value cardValue) {
        return hand.stream()
                .filter(card -> card.getValue().isPresent())
                .map(card -> card.getValue().get())
                .anyMatch(value -> value == cardValue);
    }
}