package blackjack;

import blackjack.controller.Controller;
import blackjack.model.Game;
import blackjack.view.SimpleView;
import blackjack.view.View;

import java.util.Locale;

class Program {

    public static void main(String[] a_args) {
        Game game = new Game();
        View view = new SimpleView(new Locale("sv"));
        Controller controller = new Controller(game, view);

        while (controller.play()) {}
    }
}