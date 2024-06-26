package model;

import java.awt.*;

public class Ground extends GameObject {
    private int velocityX; // pergerakan horizontal objek Ground
    private boolean passed; // Status apakah objek ini sudah dilewati

    public Ground(int posX, int posY, int width, int height, Image image) {
        super(posX, posY, width, height, image);
        this.velocityX = -3; // pergerakan awal horizontal objek Ground
        this.passed = false; // Awalnya objek belum dilewati
    }

    public int getVelocityX() {
        return velocityX; // Mengembalikan pergerakan horizontal objek Ground
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX; // Mengatur pergerakan horizontal objek Ground
    }

    public boolean getPassed() {
        return passed; // Mengembalikan status apakah objek ini sudah dilewati
    }

    public void setPassed(boolean passed) {
        this.passed = passed; // Mengatur status apakah objek ini sudah dilewati
    }
}