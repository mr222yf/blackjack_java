package blackjack.model.rules;

import blackjack.model.Card;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface HitStrategy extends BiPredicate<Integer, Iterable<Card>> {}