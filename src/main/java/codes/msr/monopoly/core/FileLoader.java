package codes.msr.monopoly.core;

import codes.msr.monopoly.gameobjects.Board;
import codes.msr.monopoly.gameobjects.EnumTileType;
import codes.msr.monopoly.gameobjects.Tile;

import java.io.*;
import java.util.Scanner;

public class FileLoader {

    private File file;

    public FileLoader() throws IOException {
        this.file = createDefaultFile();
    }

    public FileLoader(File fileIn) {
        this.file = fileIn;
    }

    public File createDefaultFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/boards/default.mply"));

        writer.append("***DO NOT CHANGE THIS FILE UNLESS YOU KNOW WHAT YOU ARE DOING***\n");
        writer.append("Go GO\n");
        writer.append("Old_Kent_Road BROWN 60 2 10 30 90 160 250 50 30\n");
        writer.append("Community_Chest COMMUNITY_CHEST\n");
        writer.append("Whitechapel_Road BROWN 60 4 20 60 180 360 450 50 30\n");
        writer.append("Income_Tax TAX 200\n");
        writer.append("Kings_Cross_Station STATION 200 100\n");
        writer.append("The_Angel,_Islington LIGHT_BLUE 100 6 30 90 270 400 550 50 50\n");
        writer.append("Chance CHANCE\n");
        writer.append("Euston_Road LIGHT_BLUE 100 6 30 90 270 400 550 50 50\n");
        writer.append("Pentonville_Road LIGHT_BLUE 120 8 40 100 300 450 600 60 50\n");
        writer.append("Jail JAIL\n");
        writer.append("Pall_Mall PINK 140 10 50 150 450 625 750 70 100\n");
        writer.append("Electric_Company UTILITY 150 75\n");
        writer.append("Whitehall PINK 140 10 50 150 450 625 750 70 100\n");
        writer.append("Northumbrl'd_Avenue PINK 160 12 60 180 500 700 900 80 100\n");
        writer.append("Marylebone_Station STATION 200 100\n");
        writer.append("Bow_Street ORANGE 180 14 70 200 550 750 950 90 100\n");
        writer.append("Community_Chest COMMUNITY_CHEST\n");
        writer.append("Marlborough_Street ORANGE 180 14 70 200 550 750 950 90 100\n");
        writer.append("Vine_Street ORANGE 200 16 80 220 600 800 1000 100 100\n");
        writer.append("Free_Parking FREE_PARKING\n");
        writer.append("Strand RED 220 18 90 250 700 875 1050 110 150\n");
        writer.append("Chance CHANCE\n");
        writer.append("Fleet_Street RED 220 18 90 250 700 875 1050 110 150\n");
        writer.append("Trafalgar_Square RED 240 20 100 300 750 925 1100 120 150\n");
        writer.append("Fenchurch_St._Station STATION 200 100\n");
        writer.append("Leicester_Square YELLOW 260 22 110 330 800 975 1150 150 150\n");
        writer.append("Coventry_Street YELLOW 260 22 110 330 800 975 1150 150 150\n");
        writer.append("Water_Works UTILITY 150 75\n");
        writer.append("Piccadilly YELLOW 280 22 120 360 850 1025 1200 150 140\n");
        writer.append("Go_to_Jail GO_TO_JAIL\n");
        writer.append("Regent_Street GREEN 300 26 130 390 900 1100 1275 200 150\n");
        writer.append("Oxford_Street GREEN 300 26 130 390 900 1100 1275 200 150\n");
        writer.append("Community_Chest COMMUNITY_CHEST\n");
        writer.append("Bond_Street GREEN 320 28 150 450 1000 1200 1400 200 160\n");
        writer.append("Liverpool_St._Station STATION 200 100\n");
        writer.append("Chance CHANCE\n");
        writer.append("Park_Lane DARK_BLUE 350 35 175 500 1100 1300 1500 175 200\n");
        writer.append("Super_Tax TAX 100\n");
        writer.append("Mayfair DARK_BLUE 400 50 200 600 1400 1700 2000 200 200\n");

        writer.close();

        return new File("src/main/resources/boards/default.mply");
    }

    public Board loadBoardFromFile() throws FileNotFoundException {
        Board board = new Board();
        Scanner scanner = new Scanner(new FileInputStream(file));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == '*')
                continue;

            String[] parameters = line.split(" ");
            String tileName = parameters[0].replaceAll("_", " ");
            EnumTileType tileType = EnumTileType.valueOf(parameters[1]);

            if (tileType.equals(EnumTileType.GO) || tileType.equals(EnumTileType.CHANCE) || tileType.equals(EnumTileType.JAIL) || tileType.equals(EnumTileType.COMMUNITY_CHEST) || tileType.equals(EnumTileType.GO_TO_JAIL) || tileType.equals(EnumTileType.FREE_PARKING)) {
                board.getGameBoard().add(new Tile(tileName, tileType));
                continue;
            }

            int cost = Integer.parseInt(parameters[2]);

            if (tileType.equals(EnumTileType.TAX)) {
                board.getGameBoard().add(new Tile(tileName, cost, tileType));
                continue;
            }

            if (tileType.equals(EnumTileType.STATION) || tileType.equals(EnumTileType.UTILITY)) {
                int mortgageValue = Integer.parseInt(parameters[3]);
                board.getGameBoard().add(new Tile(tileName, cost, tileType, mortgageValue));
                continue;
            }

            String[] rentParameters = new String[6];
            System.arraycopy(parameters, 3, rentParameters, 0, 6);
            int[] rent = new int[6];
            for (int i = 0; i < 6; i++) {
                rent[i] = Integer.parseInt(rentParameters[i]);
            }

            int mortgageValue = Integer.parseInt(parameters[9]);
            int houseCost = Integer.parseInt(parameters[10]);

            board.getGameBoard().add(new Tile(tileName, cost, tileType, rent, mortgageValue, houseCost));
        }

        return board;
    }

}
