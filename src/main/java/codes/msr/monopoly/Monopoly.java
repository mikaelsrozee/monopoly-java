package codes.msr.monopoly;

import codes.msr.monopoly.core.FileLoader;
import codes.msr.monopoly.gameobjects.ai.AIPlayer;
import codes.msr.monopoly.gameobjects.*;
import codes.msr.monopoly.graphics.UserInterface;

import java.io.File;
import java.io.IOException;

public class Monopoly {

    public static void main(String[] args) throws IOException {
        FileLoader fileLoader = new FileLoader(new File("src/main/resources/boards/test.mply"));

        Board board = fileLoader.loadBoardFromFile();
        for (Tile tile : board.getGameBoard()) {
            System.out.println(tile.toString());
        }

        Game game = new Game(board);

        game.addPlayer(new AIPlayer(game, "Mikael", EnumPiece.TOP_HAT));
        game.addPlayer(new AIPlayer(game, "Leah", EnumPiece.CAT));
        game.addPlayer(new AIPlayer(game, "Harry", EnumPiece.TREX));

        game.startGame();

        UserInterface userInterface = new UserInterface(game);

        while (game.getPlayers().size() > 1) {
            game.nextTurn();
        }

        System.out.println("Game over. " + game.getPlayers().get(0).getName() + " has won.");
        System.out.println(game.getGameInfo());
    }

}
