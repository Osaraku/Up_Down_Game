package model;

import java.awt.*;

public class GameObject {
    protected int posX;        // Posisi koordinat X dari objek game
    protected int posY;        // Posisi koordinat Y dari objek game
    protected int width;       // Lebar objek game
    protected int height;      // Tinggi objek game
    protected Image image;     // Gambar yang terkait dengan objek game

    // Konstruktor
    public GameObject(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    // Getter untuk mendapatkan posisi koordinat X
    public int getPosX() {
        return posX;
    }

    // Setter untuk mengatur posisi koordinat X
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter untuk mendapatkan posisi koordinat Y
    public int getPosY() {
        return posY;
    }

    // Setter untuk mengatur posisi koordinat Y
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter untuk mendapatkan lebar objek
    public int getWidth() {
        return width;
    }

    // Setter untuk mengatur lebar objek
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter untuk mendapatkan tinggi objek
    public int getHeight() {
        return height;
    }

    // Setter untuk mengatur tinggi objek
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter untuk mendapatkan gambar
    public Image getImage() {
        return image;
    }

    // Setter untuk mengatur gambar
    public void setImage(Image image) {
        this.image = image;
    }
}