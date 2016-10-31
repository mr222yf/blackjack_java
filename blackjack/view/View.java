package blackjack.view;

import blackjack.model.Game.Command;
import blackjack.model.Card;

public interface View {
    void displayWelcomeMessage();
    Command getCommand();
    void displayCard(Card a_card);
    void displayPlayerHand(Iterable<Card> a_hand, int a_score);
    void displayDealerHand(Iterable<Card> a_hand, int a_score);
    void displayGameOver(boolean a_dealerIsWinner);
}