package model;

import java.awt.*;

public class Player extends GameObject {
    private int velocityY; // pergerakan vertikal pemain
    private int velocityX; // pergerakan horizontal pemain

    public Player(int posX, int posY, int width, int height, Image image) {
        super(posX, posY, width, height, image);
        this.velocityY = 0; // Menginisialisasi pergerakan vertikal menjadi 0
        this.velocityX = 0; // Menginisialisasi pergerakan horizontal menjadi 0
    }

    // Getter untuk mendapatkan pergerakan vertikal
    public int getVelocityY() {
        return velocityY;
    }

    // Setter untuk mengatur pergerakan vertikal
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    // Getter untuk mendapatkan pergerakan horizontal
    public int getVelocityX() {
        return velocityX;
    }

    // Setter untuk mengatur pergerakan vertikal
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
}