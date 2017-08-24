package ru.pflb.chess;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public enum PieceType {

    KING(0), ROOK(1), BISHOP(2), QUEEN(3), KNIGHT(4), PAWN(5);

    private final int code;

    PieceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
