package model;

import java.awt.*;

public class Platform extends GameObject {
    private int velocityX; // Pergerakan horizontal platform
    private boolean passed; // Status apakah platform ini sudah dilewati

    public Platform(int posX, int posY, int width, int height, Image image) {
        super(posX, posY, width, height, image);
        this.velocityX = -3; // Pergerakan awal horizontal platform
        this.passed = false; // Awalnya platform belum dilewati
    }

    public int getVelocityX() {
        return velocityX; // Mengembalikan pergerakan horizontal platform
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX; // Mengatur pergerakan horizontal platform
    }

    public boolean getPassed() {
        return passed; // Mengembalikan status apakah platform ini sudah dilewati
    }

    public void setPassed(boolean passed) {
        this.passed = passed; // Mengatur status apakah platform ini sudah dilewati
    }
}