package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Game.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

public class SimpleView implements View {
    private final ResourceBundle viewStringsBundle;

    public SimpleView(Locale locale) {
        Locale.setDefault(locale);
        this.viewStringsBundle = getResourceBundleAsUTF8("blackjack.view.res.view", locale);
    }

    public void displayWelcomeMessage() {
        System.out.println(viewStringsBundle.getString("hello.black.jack.world"));
    }

    public Command getCommand() {
        Map<String, Command> commands = new LinkedHashMap<>();
        commands.put(viewStringsBundle.getString("command.play"), Command.NEW_GAME);
        commands.put(viewStringsBundle.getString("command.hit"), Command.HIT);
        commands.put(viewStringsBundle.getString("command.stand"), Command.STAND);
        commands.put(viewStringsBundle.getString("command.quit"), Command.QUIT);

        System.out.println(MessageFormat.format(viewStringsBundle.getString("instructions"), commands.keySet().toArray()));

        return commands.getOrDefault(new Scanner(System.in).next(), Command.OTHER);
    }

    public void displayCard(Card card) {
        if (card.isHidden()) {
            System.out.println(viewStringsBundle.getString("hidden.card"));
        } else {
            System.out.println(MessageFormat.format(viewStringsBundle.getString("card.name.format"), viewStringsBundle.getString(card.getValue().get().name()), viewStringsBundle.getString(card.getColor().get().name())));
        }
    }

    public void displayPlayerHand(Iterable<Card> hand, int score) {
        displayHand(viewStringsBundle.getString("player"), hand, score);
    }

    public void displayDealerHand(Iterable<Card> hand, int score) {
        clearScreen();
        displayHand(viewStringsBundle.getString("dealer"), hand, score);
    }

    private void displayHand(String name, Iterable<Card> hand, int score) {
        System.out.println(name + " " + viewStringsBundle.getString("has"));
        hand.forEach(this::displayCard);
        System.out.println(viewStringsBundle.getString("score") + " " + score);
        System.out.println();
    }

    public void displayGameOver(boolean dealerIsWinner) {
        System.out.println(viewStringsBundle.getString("gameover"));
        if (dealerIsWinner) {
            System.out.println(viewStringsBundle.getString("dealer.won"));
        } else {
            System.out.println(viewStringsBundle.getString("you.won"));
        }
    }

    private void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    private ResourceBundle getResourceBundleAsUTF8(String baseName, Locale locale){
        ResourceBundle.Control control = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT);

        String bundleName = control.toBundleName(baseName, locale);
        String resourceName = control.toResourceName(bundleName, "properties");

        InputStream utf8in = getClass().getClassLoader().getResourceAsStream(resourceName);

        try {
            return new PropertyResourceBundle(new InputStreamReader(utf8in, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
