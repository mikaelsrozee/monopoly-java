package codes.msr.monopoly.gameobjects;

import java.awt.*;

public enum EnumTileType {

    BROWN(new Color(128, 80, 58)),
    LIGHT_BLUE(new Color(177, 222, 241)),
    PINK(new Color(197, 54, 136)),
    ORANGE(new Color(243, 140, 35)),
    RED(new Color(223, 38, 46)),
    YELLOW(new Color(254, 239, 0)),
    GREEN(new Color(20, 165, 90)),
    DARK_BLUE(new Color(0, 98, 159)),
    STATION("station.png"),
    UTILITY, //TODO
    CHANCE, //TODO
    COMMUNITY_CHEST, //TODO
    JAIL("jail.png"),
    GO("go.png"),
    GO_TO_JAIL("go_to_jail.png"),
    FREE_PARKING("free_parking.png"),
    TAX; //TODO

    private Color color;
    private String image;

    EnumTileType() {
        this.color = null;
    }

    EnumTileType(Color color) {
        this.color = color;
    }

    EnumTileType(String image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public String getImageSrc() {
        return image;
    }
}
