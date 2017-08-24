package ru.pflb.chess;

import java.util.List;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class Search {

    public static int perft(Board board, int depth) {
        List<Move> moves = new MoveGenerator(board).generateMoves();
        if (depth == 1) {
            return moves.size();
            // TODO - исключить недопустимые ходы
        }
        int positions = 0;
        for (Move move : moves) {
            board.doMove(move);
            positions += perft(board, depth - 1);
            board.undoMove(move);
        }
        return positions;
    }

    public static Move search(Board board) {
        List<Move> moves = new MoveGenerator(board).generateMoves();
        int maxEval = -10000;
        Move bestMove = null;
        for (Move move : moves) {
            board.doMove(move);
            // TODO - исключить недопустимые ходы
            int eval = alfabeta(-10000, +10000);
            if (eval > maxEval) {
                bestMove = move;
                maxEval = eval;
            }
            board.undoMove(move);
        }
        return bestMove;
    }

    public static int alfabeta(int alfa, int beta) {
        return 0;
    }
}
