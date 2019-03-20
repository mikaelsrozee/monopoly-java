package codes.msr.monopoly.gameobjects;

import java.util.Arrays;

public class Tile {

    private String name;
    private int cost, numHouses;
    private EnumTileType type;
    private Player owner;

    // [noHouses, 1House, 2House, 3House, 4House, hotel]
    private int[] rent;
    private int mortgageValue;
    private int houseCost;

    public Tile(String name, EnumTileType type) {
        this.name = name;
        this.type = type;
    }

    public Tile(String name, int cost, EnumTileType type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Tile(String name, int cost, EnumTileType type, int mortgageValue) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.mortgageValue = mortgageValue;
    }

    public Tile(String name, int cost, EnumTileType type, int[] rent, int mortgageValue, int houseCost) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.houseCost = houseCost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        if (getOwner() == null || rent == null)
            return cost;

        return rent[getNumHouses()];
    }

    public EnumTileType getType() {
        return type;
    }

    public String toString() {
        return name + ": $" + cost + ", " + type + ", mortgage: $" + mortgageValue + ", rent: " + Arrays.toString(rent) + ", house: $" + houseCost;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void onLandedOn(Player player, int valRolled) {
        Game game = player.getGameIn();
        System.out.println(player.getName() + " has landed on " + this.getName());
        switch (getType()) {
            case GO:
                System.out.println(player.getName() + "'s balance before: $" + player.getBalance());
                player.addBalance((int) (game.getGoMoney() * game.getLandOnGoMultiplier()));
                System.out.println(player.getName() + "'s balance after: $" + player.getBalance());
                break;
            case GO_TO_JAIL:
                game.sendToJail(player);
                break;
            case TAX:
                System.out.println(player.getName() + "'s balance before: $" + player.getBalance());
                player.removeBalance(this.getCost());
                System.out.println(player.getName() + "'s balance after: $" + player.getBalance());
                break;
            case FREE_PARKING:
                System.out.println(player.getName() + "'s balance before: $" + player.getBalance());
                player.addBalance(game.getFreeParkingMoney());
                game.resetFreeParkingMoney();
                System.out.println(player.getName() + "'s balance after: $" + player.getBalance());
                break;
            case JAIL:
                break;
            case CHANCE:
                game.drawChanceCard(player);
                break;
            case COMMUNITY_CHEST:
                game.drawCommunityChest(player);
                break;
            case UTILITY:
                if (this.getOwner() == null) {
                    System.out.println("Tile " + this.getName() + " has no owner. Offering to " + player.getName());
                    game.offerSale(this, player);
                    break;
                } else if (this.getOwner() == player) {
                    System.out.println(player.getName() + " already owns " + this.getName());
                    break;
                } else {
                    int multiplier = 4;

                    int numUtilities = 0;
                    for (Tile tile : game.getBoard().getGameBoard()) {
                        if (tile.getType().equals(EnumTileType.UTILITY))
                            numUtilities++;
                    }

                    int numUtilitiesOwned = 0;
                    for (Tile tile : getOwner().getTilesOwned()) {
                        if (tile.getType().equals(EnumTileType.UTILITY))
                            numUtilitiesOwned++;
                    }

                    if (numUtilities == numUtilitiesOwned)
                        multiplier = 10;

                    this.getOwner().addBalance(player.removeBalance(valRolled * multiplier));
                }
            case STATION:
                if (this.getOwner() == null) {
                    System.out.println("Tile " + this.getName() + " has no owner. Offering to " + player.getName());
                    game.offerSale(this, player);
                    break;
                } else if (this.getOwner() == player) {
                    System.out.println(player.getName() + " already owns " + this.getName());
                    break;
                } else {
                    int rent = 25;
                    int numOwned = 0;
                    for (Tile tile : getOwner().getTilesOwned()) {
                        if (tile.getType().equals(EnumTileType.STATION))
                            numOwned++;
                    }

                    this.getOwner().addBalance(player.removeBalance(rent * numOwned));
                }
            default:
                if (this.getOwner() == null) {
                    System.out.println("Tile " + this.getName() + " has no owner. Offering to " + player.getName());
                    game.offerSale(this, player);
                    break;
                } else if (this.getOwner() == player) {
                    System.out.println(player.getName() + " already owns " + this.getName());
                    break;
                } else {
                    this.getOwner().addBalance(player.removeBalance(this.getCost()));
                }
        }
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public boolean addHouse() {
        if (getNumHouses() >= 5)
            return false;

        this.numHouses++;
        return true;
    }

    public boolean removeHouse() {
        if (getNumHouses() <= 0)
            return false;

        this.numHouses--;
        return true;
    }
}
