package codes.msr.monopoly.gameobjects.ai;

import codes.msr.monopoly.gameobjects.*;

import java.util.HashMap;

public class AIPlayer extends Player {

    public AIPlayer(Game gameIn, String id, EnumPiece piece) {
        super(gameIn, id, piece);
    }

    @Override
    public void takeTurn() {
        System.out.println(getName() + ": $" + getBalance());

        HashMap<EnumTileType, Integer> typeCounter = new HashMap<>();
        for (Tile tile : getTilesOwned()) {
            if (!typeCounter.containsKey(tile.getType())) {
                typeCounter.put(tile.getType(), 1);
            } else {
                typeCounter.put(tile.getType(), typeCounter.get(tile.getType()) + 1);
            }
        }
        
        HashMap<EnumTileType, Integer> maxTypeCounter = new HashMap<>();
        for (Tile tile : getGameIn().getBoard().getGameBoard()) {
            if (!maxTypeCounter.containsKey(tile.getType())) {
                maxTypeCounter.put(tile.getType(), 1);
            } else {
                maxTypeCounter.put(tile.getType(), maxTypeCounter.get(tile.getType()) + 1);
            }
        }

        for (EnumTileType type : typeCounter.keySet()) {
            if (typeCounter.get(type).equals(maxTypeCounter.get(type))) {
                while (getBalance() > 1000) {
                    for (Tile tile : getTilesOwned()) {
                        if (tile.getType().equals(type)) {
                            if (tile.addHouse()) {
                                System.out.println(getName() + " is buying a house on " + tile.getName());
                                removeBalance(tile.getHouseCost());
                                System.out.println(tile.getName() + " now has " + tile.getNumHouses() + " houses.");
                                System.out.println(getName() + " now has $" + getBalance());
                            }
                        }
                    }
                }
            }
        }
        
        int rollCount = 0;
        while (true) {
            Die.RollResult result = getGameIn().rollAllDice();
            rollCount++;
            getGameIn().movePlayer(this, result.getTotal());

            if (rollCount == 3) {
                getGameIn().sendToJail(this);
                System.out.println("Rolled 3 identical die in a row. Sending to jail.");
                return;
            }

            if (!result.isIdentical())
                break;
        }
        System.out.println();
    }

    @Override
    public void onOfferSale(Tile tile) {
        if (getBalance() > tile.getCost() * 2) {
            System.out.println("Buying. Can afford.");
            System.out.println(getName() + "'s balance before: $" + getBalance());
            removeBalance(tile.getCost());
            System.out.println(getName() + "'s balance after: $" + getBalance());
            addTile(tile);
            tile.setOwner(this);
        } else {
            System.out.println("Not buying. Too expensive.");
        }
    }

    private boolean rollAndMove() {
        Die.RollResult result = getGameIn().rollAllDice();

        getGameIn().movePlayer(this, result.getTotal());
        System.out.println(this.getName() + ": " + this.getCurrentPos());

        return result.isIdentical();
    }

}
