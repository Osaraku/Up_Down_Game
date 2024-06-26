package viewmodel;

import model.Ground;
import view.UpDownGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GroundManager {
    private UpDownGame updowngame;
    private int groundStartPosX = 360;
    private int groundStartPosY = 0;
    private int groundWidth = 96;
    private int groundHeight = 512;
    private int groundHorizontalSpacing = 340;
    private ArrayList<Ground> grounds;
    private Timer groundsCooldown;

    // Konstruktor untuk kelas GroundManager
    public GroundManager(UpDownGame updowngame) {
        this.updowngame = updowngame;
        grounds = new ArrayList<Ground>();

        // Timer untuk mengatur interval penempatan tanah baru
        groundsCooldown = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeGrounds();
            }
        });
        groundsCooldown.start();
    }

    // Metode untuk menghasilkan ground awal pertama kalinya dalam jumlah tertentu
    public void generateInitialGrounds() {
        int numberOfGrounds = 3; // jumlah tanah yang dihasilkan pada awalnya
        int horizontalSpacing = groundHorizontalSpacing;

        for (int i = 0; i < numberOfGrounds; i++) {
            int lowerGroundStartPosX = groundStartPosX + i * horizontalSpacing;

            // Posisi Y acak untuk tanah baru
            int randomPosY = (int) (groundStartPosY + groundHeight / 2
                    + Math.random() * (updowngame.getHeight() - groundHeight - groundHeight / 2));

            int openingSpace = 960 / 4;

            // Membuat objek Ground baru dan menambahkannya ke daftar
            Ground lowerGround = new Ground(lowerGroundStartPosX, randomPosY + openingSpace, groundWidth, groundHeight,
                    updowngame.getLowerGroundImage());
            grounds.add(lowerGround);
        }
    }

    // Metode untuk menempatkan tanah baru pada interval yang ditentukan
    public void placeGrounds() {
        int lowerGroundStartPosX = updowngame.getFrameWidth() + groundWidth;

        // Posisi Y acak untuk tanah baru
        int randomPosY = (int) (groundStartPosY + groundHeight / 1.5
                + Math.random() * (updowngame.getHeight() - groundHeight - groundHeight / 2));

        int openingSpace = 960 / 6;

        // Membuat objek Ground baru dan menambahkannya ke daftar
        Ground lowerGround = new Ground(lowerGroundStartPosX, randomPosY + openingSpace, groundWidth, groundHeight,
                updowngame.getLowerGroundImage());
        grounds.add(lowerGround);
    }

    // Metode untuk mendapatkan daftar tanah
    public ArrayList<Ground> getGrounds() {
        return grounds;
    }

    // Metode untuk mendapatkan Timer yang mengatur interval penempatan tanah baru
    public Timer getGroundsCooldown() {
        return groundsCooldown;
    }
}