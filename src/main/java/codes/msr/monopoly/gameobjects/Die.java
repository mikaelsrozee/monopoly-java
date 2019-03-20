package codes.msr.monopoly.gameobjects;

import java.util.Random;

public class Die {

    private int numSides;

    public Die() {
        this(6);
    }

    public Die(int numSides) {
        this.numSides = numSides;
    }

    public int roll() {
        return new Random().nextInt(numSides) + 1;
    }

    public static class RollResult {
        private int total;
        private boolean identical;

        public RollResult(int total, boolean identical) {
            this.total = total;
            this.identical = identical;
        }

        public int getTotal() {
            return total;
        }

        public boolean isIdentical() {
            return identical;
        }
    }

}
