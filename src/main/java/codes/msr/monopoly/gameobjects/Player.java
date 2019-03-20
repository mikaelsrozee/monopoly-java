package codes.msr.monopoly.gameobjects;

import java.util.ArrayList;

public class Player {

    private Game gameIn;
    private String name;
    private EnumPiece piece;
    private int balance;

    private int currentPos;
    private ArrayList<Tile> tilesOwned;

    public Player(Game gameIn, String name, EnumPiece piece) {
        this.gameIn = gameIn;
        this.name = name;
        this.piece = piece;
        this.currentPos = 0;
        this.balance = 1500;
        this.tilesOwned = new ArrayList<>();
    }

    public void takeTurn() {

    }

    public void onOfferSale(Tile tile) {

    }

    public boolean owns(Tile tile) {
        return tilesOwned.contains(tile);
    }

    public void addTile(Tile tile) {
        tilesOwned.add(tile);
    }

    public void removeTile(Tile tile) {
        tilesOwned.remove(tile);
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public String getName() {
        return name;
    }

    public EnumPiece getPiece() {
        return piece;
    }

    public void addBalance(int balance) {
        System.out.println("Giving " + getName() + " $" + balance);

        this.balance += balance;
    }

    public int removeBalance(int balance) {
        System.out.println(getName() + "'s balance before: $" + getBalance());
        if (this.getBalance() - balance < 0) {
            // TODO: Auction properties
            System.out.println(getName() + " can't afford to pay.");
            getGameIn().bankruptPlayer(this);
            return this.getBalance();
        } else {
            System.out.println("Paying $" + balance);
        }

        this.balance -= balance;
        System.out.println(getName() + "'s balance after: $" + getBalance());
        return balance;
    }

    public int getBalance() {
        return balance;
    }

    public Game getGameIn() {
        return gameIn;
    }

    public ArrayList<Tile> getTilesOwned() {
        return this.tilesOwned;
    }
}
