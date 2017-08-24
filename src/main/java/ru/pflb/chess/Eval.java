package ru.pflb.chess;

import static ru.pflb.chess.Color.BLACK;
import static ru.pflb.chess.Color.WHITE;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class Eval {

    private final int value;

    public Eval(Board board) {
        this.value = evaluate(board);
    }

    private final int evaluate(Board board) {
        int cp = 0;

        cp += (board.getRooksNb(WHITE) - board.getRooksNb(BLACK)) * 500;
        cp += (board.getBisNb(WHITE) - board.getBisNb(BLACK)) * 300;
        cp += (board.getQueNb(WHITE) - board.getQueNb(BLACK)) * 900;

        return cp;
    }

    public int getValue() {
        return value;
    }
}
