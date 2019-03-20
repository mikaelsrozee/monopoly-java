package codes.msr.monopoly.graphics;

import codes.msr.monopoly.gameobjects.Game;
import codes.msr.monopoly.gameobjects.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserInterface extends Canvas {

    private Game game;
    private JFrame jFrame;

    public UserInterface(Game game) {
        this.game = game;
        this.jFrame = new JFrame();

        this.jFrame.add(this);

        init();
    }

    public void init() {
        getjFrame().setSize(750, 750);

        getjFrame().setVisible(true);
    }

    public Game getGame() {
        return game;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void paint(Graphics graphics) {
        try {
            drawTiles(graphics, 10, 10, 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Graphics graphics) {

    }

    private void drawGrid(Graphics graphics, int posX, int posY, int length) {
        int numSquares = 13;
        int size = length * numSquares;
        int unit = size / numSquares; // 2 BU = side of a corner tile

        graphics.setColor(Color.BLACK);

        graphics.drawRect(posX, posY, 2 * unit, 2 * unit);
        graphics.drawRect(posX, posY + (size - (2 * unit)), 2 * unit, 2 * unit);
        graphics.drawRect(posX + (size - (2 * unit)), posY, 2 * unit, 2 * unit);
        graphics.drawRect(posX + (size - (2 * unit)), posY + (size - (2 * unit)), 2 * unit, 2 * unit);

        for (int i = 2; i < 11; i++) {
            graphics.drawRect(posX + (i * unit), posY, unit, 2 * unit);
            graphics.drawRect(posX + (i * unit), posY + (size - (2 * unit)), unit, 2 * unit);

            graphics.drawRect(posX, posY + (i * unit), 2 * unit, unit);
            graphics.drawRect(posX + (size - (2 * unit)), posY + (i * unit), 2 * unit, unit);
        }
    }

    private void drawTiles(Graphics graphics, int posX, int posY, int length) throws IOException {
        int numSquares = 13;
        int size = length * numSquares;
        int unit = size / numSquares; // 2 BU = side of a corner tile

        int currentSide = -1; // 0: bottom; 1: left; 2: top; 3: right;
        int locX = unit * 11 + posX;
        int locY = unit * 11 + posY;
        boolean cornerPiece = false;
        for (Tile tile : getGame().getBoard().getGameBoard()) {
            int width = unit;
            int height = unit;

            if (currentSide % 2 == 0) {
                height /= 2;
            } else {
                width /= 2;
            }

            // Test if it was a corner piece
            if (getGame().getBoard().getGameBoard().indexOf(tile) % 10 == 0) {
                if (currentSide == 0) {
                    locX += unit / 2;
                } else if (currentSide == 1) {
                    locX -= unit / 2;
                    locY += unit / 2;
                } else if (currentSide == 2) {
                    locY -= unit / 2;
                }

                cornerPiece = true;
                currentSide++;
            } else {
                cornerPiece = false;
            }

            if (tile.getType().getColor() == null) {
                if (tile.getType().getImageSrc() != null) {
                    File file = new File("src/main/resources/assets/" + tile.getType().getImageSrc());
                    System.out.println(file.getAbsolutePath());
                    BufferedImage image = ImageIO.read(file);

                    if (image != null) {
                        if (cornerPiece) {
                            switch (currentSide) {
                                case 0:
                                    graphics.drawImage(image, locX + unit / 4, locY, width + unit, height + unit, null);
                                    break;
                                case 1:
                                    graphics.drawImage(image, locX - (int)(unit * 1.5), locY + unit / 4, width + unit, height + unit, null);
                                    break;
                                case 2:
                                    graphics.drawImage(image, locX - (3 * unit / 4), locY - (int)(unit * 1.5), width + unit, height + unit, null);
                                    break;
                                case 3:
                                    graphics.drawImage(image, locX, locY - (3 * unit / 4), width + unit, height + unit, null);
                                    break;
                            }
                        } else {
                            graphics.drawImage(image, locX, locY, width, height, null);
                        }
                    }
                } else {
                    graphics.setColor(null);
                }
            } else {
                graphics.setColor(tile.getType().getColor());
                graphics.fillRect(locX, locY, width, height);
            }

            switch (currentSide) {
                case 0:
                    locX -= unit;
                    break;
                case 1:
                    locY -= unit;
                    break;
                case 2:
                    locX += unit;
                    break;
                case 3:
                    locY += unit;
                    break;
            }
        }

        drawGrid(graphics, posX, posY, length);
    }
}
