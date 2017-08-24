package ru.pflb.chess;

import java.util.ArrayList;
import java.util.List;

import static ru.pflb.chess.Color.WHITE;
import static ru.pflb.chess.Piece.*;
import static ru.pflb.chess.PieceType.*;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class MoveGenerator {

    private final Board board;

    public MoveGenerator(Board board) {
        this.board = board;
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();

        moves.addAll(generateKingMoves());
        moves.addAll(generateRookMoves());
        moves.addAll(generateBishopMoves());
        moves.addAll(generateQueenMoves());

        return moves;
    }

    public List<Move> generateKingMoves() {
        int kingPos = board.getKingPos(board.getSideToMove());
        int[] offsets = board.getOffsets(KING);
        List<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < offsets.length; i++) {
            int newPos = kingPos + offsets[i];

            Piece piece = board.getPiece(newPos);
            if (piece.isEmpty()) {
                moves.add(new Move(new Square(kingPos), new Square(newPos), board.getSideToMove() == WHITE ? W_KING : B_KING));
            } else if (piece.isEnemy(board.getSideToMove())) {
                moves.add(new Move(new Square(kingPos), new Square(newPos), board.getSideToMove() == WHITE ? W_KING : B_KING, piece));
            } else {
                // не можем ходить:
                // либо своя фигура
                // либо ход за пределы доски
            }
        }

        return moves;
    }

    public List<Move> generateRookMoves() {
        List<Move> moves = new ArrayList<Move>();
        for (int r = 0; r < board.getRooksNb(board.getSideToMove()); r++) {
            int rookPos = board.getRookPos(board.getSideToMove(), r);
            int[] offsets = board.getOffsets(ROOK);

            for (int i = 0; i < offsets.length; i++) {
                for (int newPos = rookPos + offsets[i]; ; newPos += offsets[i]) {
                    Piece piece = board.getPiece(newPos);
                    if (piece.isEmpty()) {
                        moves.add(new Move(new Square(rookPos), new Square(newPos), board.getSideToMove() == WHITE ? W_ROOK : B_ROOK));
                    } else if (piece.isEnemy(board.getSideToMove())) {
                        moves.add(new Move(new Square(rookPos), new Square(newPos), board.getSideToMove() == WHITE ? W_ROOK : B_ROOK, piece));
                    } else {
                        // не можем ходить:
                        // либо своя фигура
                        // либо ход за пределы доски
                        break;
                    }
                }
            }
        }


        return moves;
    }

    public List<Move> generateBishopMoves() {
        List<Move> moves = new ArrayList<Move>();
        for (int r = 0; r < board.getBisNb(board.getSideToMove()); r++) {
            int bisPos = board.getBisPos(board.getSideToMove(), r);
            int[] offsets = board.getOffsets(BISHOP);

            for (int i = 0; i < offsets.length; i++) {
                for (int newPos = bisPos + offsets[i]; ; newPos += offsets[i]) {
                    Piece piece = board.getPiece(newPos);
                    if (piece.isEmpty()) {
                        moves.add(new Move(new Square(bisPos), new Square(newPos), board.getSideToMove() == WHITE ? W_BISHOP : B_BISHOP));
                    } else if (piece.isEnemy(board.getSideToMove())) {
                        moves.add(new Move(new Square(bisPos), new Square(newPos), board.getSideToMove() == WHITE ? W_BISHOP : B_BISHOP, piece));
                    } else {
                        break;
                    }
                }
            }
        }
        return moves;
    }
        public List<Move> generateQueenMoves() {
            List<Move> moves = new ArrayList<Move>();
                int quePos = board.getQuePos(board.getSideToMove());
                int[] offsets = board.getOffsets(QUEEN);
                for (int i = 0; i < offsets.length; i++) {
                    for (int newPos = quePos + offsets[i]; ; newPos += offsets[i]) {
                        Piece piece = board.getPiece(newPos);
                        if (piece.isEmpty()) {
                            moves.add(new Move(new Square(quePos), new Square(newPos), board.getSideToMove() == WHITE ? W_QUEEN : B_QUEEN));
                        } else if (piece.isEnemy(board.getSideToMove())) {
                            moves.add(new Move(new Square(quePos), new Square(newPos), board.getSideToMove() == WHITE ? W_QUEEN : B_QUEEN, piece));
                        } else {
                            break;
                        }
                    }
                }
            return moves;
        }



}
