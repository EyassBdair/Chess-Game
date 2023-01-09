package Game;

import Enums.*;

public class Player {
    private final String namePlayer;
    private final Color color;

    public Player(String namePlayer, Color color) {
        this.namePlayer = namePlayer;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getNamePlayer() {
        return namePlayer;
    }


    @Override
    public String toString() {
        return namePlayer;

    }
}
