package blackjack.model;

import java.util.Optional;

public class Card implements Comparable<Card>{
    public enum Color {
        Hearts, Spades, Diamonds, Clubs
    }

    public enum Value {
        Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10),
        Knight(10), Queen(10), King(10), Ace(11);

        private final int score;

        Value(int score) {
            this.score = score;
        }

        public int getScore() { return this.score; }
    }

    private final Color color;
    private final Value value;
    private boolean isHidden;

    public Card(Color a_color, Value a_value) {
        value = a_value;
        color = a_color;
        isHidden = true;
    }

    public Optional<Color> getColor() {
        return Optional.ofNullable(isHidden ? null : color);
    }

    public Optional<Value> getValue() {
        return Optional.ofNullable(isHidden ? null : value);
    }

    public Card show() {
        isHidden = false;
        return this;
    }

    public boolean isHidden() {
        return isHidden;
    }

    @Override
    public int compareTo(Card o) {
        if(isHidden) {
            return 1;
        } else if(o.isHidden) {
            return -1;
        } else {
            return this.value.compareTo(o.value);
        }
    }
}