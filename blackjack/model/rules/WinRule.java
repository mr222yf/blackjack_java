package blackjack.model.rules;

import java.util.function.BiFunction;

public interface WinRule extends BiFunction<Integer, Integer, Boolean> {}
