package ru.pflb.chess;


import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class SearchTest {

    @Test
    //@Ignore
    public void perftTest() {
        Board board = new Board("8/3k4/8/8/8/8/3K4/8 w - -");
        int movesNb = Search.perft(board, 2);

        assertThat(movesNb).isEqualTo(64);
    }

}