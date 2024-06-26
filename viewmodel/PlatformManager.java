package viewmodel;

import model.Platform;
import view.UpDownGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlatformManager {
    private UpDownGame updowngame;
    private int platformStartPosX = 520;
    private int platformStartPosY = -420;
    private int platformWidth = 96;
    private int platformHeight = 512;
    private int platformHorizontalSpacing = 340;
    private ArrayList<Platform> platforms;
    private Timer platformsCooldown;

    // Konstruktor untuk kelas PlatformManager
    public PlatformManager(UpDownGame updowngame) {
        this.updowngame = updowngame;
        platforms = new ArrayList<Platform>();

        // Timer untuk mengatur interval penempatan platform baru
        platformsCooldown = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePlatforms();
            }
        });
        platformsCooldown.start();
    }

    // Metode untuk menghasilkan platform awal untuk pertama kalinya dalam jumlah
    // tertentu
    public void generateInitialPlatforms() {
        int numberOfPlatforms = 3; // Contoh jumlah platform yang dihasilkan pada awalnya
        int horizontalSpacing = platformHorizontalSpacing;

        for (int i = 0; i < numberOfPlatforms; i++) {
            int upperPlatformStartPosX = platformStartPosX + i * horizontalSpacing;

            // Tentukan posisi vertikal platform atas
            int randomPosY = (int) (platformStartPosY + platformHeight / 2
                    + Math.random() * (updowngame.getHeight() - platformHeight - platformHeight / 2));

            // Buat platform atas
            Platform upperPlatform = new Platform(upperPlatformStartPosX, randomPosY, platformWidth, platformHeight,
                    updowngame.getPlatformImage());
            platforms.add(upperPlatform);
        }
    }

    // Metode untuk menempatkan platform baru pada interval yang ditentukan
    public void placePlatforms() {
        // Tentukan posisi horizontal awal platform atas
        int upperPlatformStartPosX = updowngame.getFrameWidth() + platformWidth + 140;

        // Tentukan posisi vertikal platform atas
        int randomPosY = (int) (platformStartPosY + platformHeight / 2
                + Math.random() * (updowngame.getHeight() - platformHeight - platformHeight / 2));

        // Buat platform atas
        Platform upperPlatform = new Platform(upperPlatformStartPosX, randomPosY, platformWidth, platformHeight,
                updowngame.getPlatformImage());
        platforms.add(upperPlatform);
    }

    // Metode untuk mendapatkan daftar platform
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    // Metode untuk mendapatkan Timer yang mengatur interval penempatan platform
    // baru
    public Timer getPlatformsCooldown() {
        return platformsCooldown;
    }
}