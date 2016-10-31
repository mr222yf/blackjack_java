package blackjack.model;

import java.util.LinkedList;
import java.util.List;

class Player {
    private final List<Card> hand = new LinkedList<>();
    final List<Runnable> listeners = new LinkedList<>();

    public Iterable<Card> getHand() {
        return hand;
    }

    public int calcScore() {
        final int MAX_SCORE = 21;

        int score = hand.stream()
                .filter(card -> card.getValue().isPresent())
                .mapToInt(card -> card.getValue().get().getScore())
                .sum();

        if (score > MAX_SCORE && hasCardWithValue(Card.Value.Ace)) {
            score -= 10;
        }
        return score;
    }

    public void addListener(Runnable newCardListener) {
        listeners.add(newCardListener);
    }

    void clearHand() {
        hand.clear();
    }

    void dealCard(Card card) {
        hand.add(card);
        listeners.forEach(Runnable::run);
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