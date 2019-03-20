package codes.msr.monopoly.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

    private Board board;
    private ArrayList<Die> dice;
    private ArrayList<Player> players;
    private Player currentTurn;
    private int goMoney = 200;
    private double landOnGoMultiplier = 2;
    private int freeParkingMoney = 50;

    public Game() {
        this(new ArrayList<>(Arrays.asList(new Die(), new Die())));
    }

    public Game(Board board) {
        this(new ArrayList<>(Arrays.asList(new Die(), new Die())), board);
    }

    public Game(ArrayList<Die> dice) {
        this.dice = dice;
        Board board = new Board();
        board.populateDefaultBoard();
        this.players = new ArrayList<>();
    }

    public Game(ArrayList<Die> dice, Board board) {
        this.dice = dice;
        this.board = board;
        this.players = new ArrayList<>();
    }

    public void startGame() {
        if (players.size() <= 1) {
            System.out.println("[ERROR] Not enough players.");
            return;
        }

        currentTurn = players.get(new Random().nextInt(players.size()));
    }

    public void nextTurn() {
        int index = players.indexOf(currentTurn) + 1;

        if (index >= players.size())
            index = 0;

        currentTurn = players.get(index);
        currentTurn.takeTurn();
    }

    public Board getBoard() {
        return this.board;
    }

    public Die.RollResult rollAllDice() {
        int total = 0;
        int previousValue = -1;
        boolean identical = true;

        for (Die die : dice) {
            int value = die.roll();

            total += value;

            if (previousValue != -1 && previousValue != value)
                identical = false;

            previousValue = value;
        }

        return new Die.RollResult(total, identical);
    }

    public void addPlayer(String name, EnumPiece piece) {
        this.players.add(new Player(this, name, piece));
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void movePlayer(Player player, int spaces) {
        int newPos = player.getCurrentPos() + spaces;
        System.out.println(player.getName() + " is currently on " + getBoard().getGameBoard().get(player.getCurrentPos()).getName());
        System.out.println(player.getName() + " rolled " + spaces + ".");

        if (newPos >= board.getGameBoard().size()) {
            player.addBalance(getGoMoney());
            newPos -= board.getGameBoard().size();
        }

        System.out.println(player.getName() + " is moving to " + getBoard().getGameBoard().get(newPos).getName());

        player.setCurrentPos(newPos);
        getBoard().getGameBoard().get(newPos).onLandedOn(player, spaces);
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public void sendToJail(Player player) {
        Tile jail = null;

        for (Tile tile : getBoard().getGameBoard()) {
            if (tile.getType().equals(EnumTileType.JAIL)) {
                jail = tile;
            }
        }

        if (jail == null) {
            System.out.println("[ERROR] Jail not found.");
            return;
        }

        player.setCurrentPos(getBoard().getGameBoard().indexOf(jail));
    }

    public String getGameInfo() {
        StringBuilder info = new StringBuilder("Current Turn: ").append(currentTurn.getName()).append("\n");
        info.append("Players: \n");
        for (Player player : players) {
            info.append("[").append(player.getName()).append(", ").append(player.getPiece()).append(", ");
            info.append(player.getCurrentPos()).append(", $").append(player.getBalance()).append("]\n");
            info.append("Tiles owned: [");
            for (Tile tile : player.getTilesOwned()) {
                info.append(tile.getName()).append(", ");
            }
            info.delete(info.length() - 1, info.length());
            info.append("]\n");
        }
        return info.toString();
    }

    public int getGoMoney() {
        return goMoney;
    }

    public void setGoMoney(int goMoney) {
        this.goMoney = goMoney;
    }

    public double getLandOnGoMultiplier() {
        return landOnGoMultiplier;
    }

    public void setLandOnGoMultiplier(double landOnGoMultiplier) {
        this.landOnGoMultiplier = landOnGoMultiplier;
    }

    public int getFreeParkingMoney() {
        return freeParkingMoney;
    }

    public void addFreeParkingMoney(int value) {
        this.freeParkingMoney += value;
    }

    public void resetFreeParkingMoney() {
        this.freeParkingMoney = 50;
    }

    public void drawChanceCard(Player player) {
        System.out.println("[CHANCE NOT IMPLEMENTED]");
    }

    public void drawCommunityChest(Player player) {
        System.out.println("[COMMUNITY CHEST NOT IMPLEMENTED]");
    }

    public void offerSale(Tile tile, Player player) {
        player.onOfferSale(tile);
    }

    public void bankruptPlayer(Player player) {
        for (Tile tile : player.getTilesOwned()) {
            tile.setOwner(null);
        }

        this.removePlayer(player);
    }
}
