package codes.msr.monopoly.gameobjects;

import java.util.ArrayList;

public class Board {

    private ArrayList<Tile> gameBoard;

    public Board() {
        gameBoard = new ArrayList<>();
    }

    public void populateDefaultBoard() {
        gameBoard.add(new Tile("Go", EnumTileType.GO));
        gameBoard.add(new Tile("Old Kent Road", 60, EnumTileType.BROWN, new int[]{2, 10, 30, 90, 160}, 50, 30));
        gameBoard.add(new Tile("Community Chest", EnumTileType.COMMUNITY_CHEST));
        gameBoard.add(new Tile("Whitechapel Road", 60, EnumTileType.BROWN, new int[]{4, 20, 60, 180, 360, 450}, 50, 30));
        gameBoard.add(new Tile("Income Tax", 200, EnumTileType.TAX));
        gameBoard.add(new Tile("Kings Cross Station", 200, EnumTileType.STATION, 100));
        gameBoard.add(new Tile("The Angel, Islington", 100, EnumTileType.LIGHT_BLUE, new int[]{6, 30, 90, 270, 400, 550}, 50, 50));
        gameBoard.add(new Tile("Chance", EnumTileType.CHANCE));
        gameBoard.add(new Tile("Euston Road", 100, EnumTileType.LIGHT_BLUE, new int[]{6, 30, 90, 270, 400, 550}, 50, 50));
        gameBoard.add(new Tile("Pentonville Road", 120, EnumTileType.LIGHT_BLUE, new int[]{8, 40, 100, 300, 450, 600}, 60, 50));
        gameBoard.add(new Tile("Jail", EnumTileType.JAIL));
        gameBoard.add(new Tile("Pall Mall", 140, EnumTileType.PINK, new int[]{10, 50, 150, 450, 625, 750}, 70, 100));
        gameBoard.add(new Tile("Electric Company", 150, EnumTileType.UTILITY, 75));
        gameBoard.add(new Tile("Whitehall", 140, EnumTileType.PINK, new int[]{10, 50, 150, 450, 625, 750}, 70, 100));
        gameBoard.add(new Tile("Northumbrl'd Avenue", 160, EnumTileType.PINK, new int[]{12, 60, 180, 500, 700, 900}, 80, 100));
        gameBoard.add(new Tile("Marylebone Station", 200, EnumTileType.STATION, 100));
        gameBoard.add(new Tile("Bow Street", 180, EnumTileType.ORANGE, new int[]{14, 70, 200, 550, 750, 950}, 90, 100));
        gameBoard.add(new Tile("Community Chest", EnumTileType.COMMUNITY_CHEST));
        gameBoard.add(new Tile("Marlborough Street", 180, EnumTileType.ORANGE, new int[]{14, 70, 200, 550, 750, 950}, 90, 100));
        gameBoard.add(new Tile("Vine Street", 200, EnumTileType.ORANGE, new int[]{16, 80, 220, 600, 800, 1000}, 100, 100));
        gameBoard.add(new Tile("Free Parking", EnumTileType.FREE_PARKING));
        gameBoard.add(new Tile("Strand", 220, EnumTileType.RED, new int[]{18, 90, 250, 700, 875, 1050}, 110, 150));
        gameBoard.add(new Tile("Chance", EnumTileType.CHANCE));
        gameBoard.add(new Tile("Fleet Street", 220, EnumTileType.RED, new int[]{18, 90, 250, 700, 875, 1050}, 110, 150));
        gameBoard.add(new Tile("Trafalgar Square", 240, EnumTileType.RED, new int[]{20, 100, 300, 750, 925, 1100}, 120, 150));
        gameBoard.add(new Tile("Fenchurch St. Station", 200, EnumTileType.STATION, 100));
        gameBoard.add(new Tile("Leicester Square", 260, EnumTileType.YELLOW, new int[]{22, 110, 330, 800, 975, 1150}, 150, 150));
        gameBoard.add(new Tile("Coventry Street", 260, EnumTileType.YELLOW, new int[]{22, 110, 330, 800, 975, 1150}, 150, 150));
        gameBoard.add(new Tile("Water Works", 150, EnumTileType.UTILITY, 75));
        gameBoard.add(new Tile("Piccadilly", 280, EnumTileType.YELLOW, new int[]{22, 120, 360, 850, 1025, 1200}, 150, 140));
        gameBoard.add(new Tile("Go to Jail", EnumTileType.GO_TO_JAIL));
        gameBoard.add(new Tile("Regent Street", 300, EnumTileType.GREEN, new int[]{26, 130, 390, 900, 1100, 1275}, 200, 150));
        gameBoard.add(new Tile("Oxford Street", 300, EnumTileType.GREEN, new int[]{26, 130, 390, 900, 1100, 1275}, 200, 150));
        gameBoard.add(new Tile("Community Chest", EnumTileType.COMMUNITY_CHEST));
        gameBoard.add(new Tile("Bond Street", 320, EnumTileType.GREEN, new int[]{28, 150, 450, 1000, 1200, 1400}, 200, 160));
        gameBoard.add(new Tile("Liverpool St. Station", 200, EnumTileType.STATION, 100));
        gameBoard.add(new Tile("Chance", EnumTileType.CHANCE));
        gameBoard.add(new Tile("Park Lane", 350, EnumTileType.DARK_BLUE, new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200));
        gameBoard.add(new Tile("Super Tax", 100, EnumTileType.TAX));
        gameBoard.add(new Tile("Mayfair", 400, EnumTileType.DARK_BLUE, new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200));
    }

    public ArrayList<Tile> getGameBoard() {
        return this.gameBoard;
    }

}
