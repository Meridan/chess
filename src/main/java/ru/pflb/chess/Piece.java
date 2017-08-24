package ru.pflb.chess;

import static ru.pflb.chess.Color.BLACK;
import static ru.pflb.chess.Color.WHITE;
import static ru.pflb.chess.PieceType.*;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class Piece {

    /**
     * Поле пустое и доступно для перемещения.
     */
    public static final Piece EMP = new Piece(0);

    /**
     * Поле недоступно для перемещения.
     */
    public static final Piece OUT = new Piece(-1);

    public static final Piece W_KING = new Piece(KING, WHITE);
    public static final Piece B_KING = new Piece(KING, BLACK);
    public static final Piece W_ROOK = new Piece(ROOK, WHITE);
    public static final Piece B_ROOK = new Piece(ROOK, BLACK);
    public static final Piece W_BISHOP = new Piece(BISHOP, WHITE);
    public static final Piece B_BISHOP = new Piece(BISHOP, BLACK);
    public static final Piece W_QUEEN = new Piece(QUEEN, WHITE);
    public static final Piece B_QUEEN = new Piece(QUEEN, BLACK);

    private final int code;

    public Piece(PieceType pieceType, Color color) {
        this(color.getCode() * 6 + pieceType.getCode() + 1);
    }

    private Piece(int code) {
        this.code = code;
    }

    /**
     * -1 - за пределами доски
     * 0 - пустое поле внутри доски
     *
     * 1 - белый король
     * 2 - белая ладья
     *
     * 7 - черный король
     * 8 - черная ладья
     */
    public int getCode() {
        return code;
    }

    public PieceType getPieceType() {
        switch (code) {
            case -1:
                return null;
            case 0:
                return null;
            case 1:
            case 7:
                return KING;
            case 2:
            case 8:
                return ROOK;
            case 3:
            case 9:
                return BISHOP;
            case 4:
            case 10:
                return QUEEN;
            case 5:
            case 11:
                return KNIGHT;
            case 6:
            case 12:
                return PAWN;
            default:
                return null;
        }
    }

    public boolean isEmpty() {
        return code == 0;
    }

    public boolean isEnemy(Color color) {
        Color pieceColor = getColor();
        return pieceColor != null && pieceColor != color;
    }

    public Color getColor() {
        if (code >= 1 && code <= 6) {
            return WHITE;
        } else if (code >= 7 && code <= 12) {
            return BLACK;
        } else {
            return null;
        }
    }

    public String toString() {
        return code == -1 ? "OUT" : code == 0 ? "EMPTY" : getPieceType().name();
    }
}
