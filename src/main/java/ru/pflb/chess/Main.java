package ru.pflb.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pflb.chess.exception.NotImplementedException;

import java.util.Scanner;

/**
 * Вход в поток выполнения программы. Принимает один параметр - <a href="https://ru.wikipedia.org/wiki/%D0%9D%D0%BE%D1%82%D0%B0%D1%86%D0%B8%D1%8F_%D0%A4%D0%BE%D1%80%D1%81%D0%B0%D0%B9%D1%82%D0%B0_%E2%80%94_%D0%AD%D0%B4%D0%B2%D0%B0%D1%80%D0%B4%D1%81%D0%B0">FEN</a>
 * представление доски, либо ноль, в таком случае автоматически будет установлена доска "4kr2/8/8/8/8/8/8/3RK3 w - -".
 *
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length >= 1) {
            Board board = new Board(args[0].replaceAll("\"", ""));
            for (int d = 1; d <= 7; d++) {
                long start = System.currentTimeMillis();
                int moves = Search.perft(board, d);
                System.out.format("%2d => %10d (%5.3f)\n", d, moves, (System.currentTimeMillis() - start) / 1000.0);
            }
        } else {
            // UCI

            Scanner scanner = new Scanner(System.in);
            Board board = null;

            for (;;) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    LOGGER.debug("> {}", line);

                    String command = line.split("\\s+")[0].toLowerCase();
                    switch (command) {
                        case "setoption":
                        case "debug":
                        case "register":
                        case "ucinewgame":
                            break;
                        case "uci":
                            LOGGER.debug("id name ChessPlayer author PFLB");
                            System.out.println("id name ChessPlayer author PFLB");
                            break;
                        case "position":
                            String nextWord = line.split("\\s+", 2)[1];
                            if (nextWord.startsWith("startpos")) {
                                throw new NotImplementedException("Необходимо дописать фигуры");
                            } else {
                                String[] parts = nextWord.split("\\s+moves");
                                if (parts.length >= 2) {
                                    board = new Board(parts[0]);
                                    throw new NotImplementedException("Необходимо дописать фигуры");
                                } else {
                                    board = new Board(nextWord.replaceAll("(^\")|(\"$)", ""));
                                }
                            }
                            break;
                        case "isready":
                            LOGGER.debug("< readyok");
                            System.out.println("readyok");
                            break;
                        case "go":
                            Move bestMove = Search.search(board);
                            LOGGER.debug("< {}", bestMove.getFrom().toString() + bestMove.getTo().toString());
                            System.out.println(bestMove.getFrom().toString() + bestMove.getTo().toString());
                    }
                }
            }



        }

    }
}
