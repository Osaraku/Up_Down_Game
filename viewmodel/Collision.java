package viewmodel;

import model.Platform;
import model.Player;
import model.Ground;
import view.UpDownGame;

public class Collision {
    private UpDownGame updowngame;

    // Konstruktor untuk kelas Collision
    public Collision(UpDownGame updowngame) {
        this.updowngame = updowngame;
    }

    // Metode untuk memeriksa apakah terjadi sentuhan antara pemain dan tanah
    public void collide(Player player, Ground ground) {
        if (checkGroundCollision(player, ground)) {
            handleGroundCollision(player, ground);
        }
    }

    // Metode untuk memeriksa apakah terjadi sentuhan antara pemain dan platform
    // gantungan
    public void collide(Player player, Platform platform) {
        if (checkPlatformCollision(player, platform)) {
            handlePlatformCollision(player, platform);
        }
    }

    // Metode untuk menangani sentuhan dengan tanah
    private void handleGroundCollision(Player player, Ground ground) {
        float leftDistance = Math.abs((player.getPosX() + player.getWidth()) - ground.getPosX());
        float rightDistance = Math.abs(player.getPosX() - (ground.getPosX() + ground.getWidth()));
        float downDistance = Math.abs((player.getPosY() + player.getHeight()) - ground.getPosY());
        float upDistance = Math.abs(player.getPosY() - (ground.getPosY() + ground.getHeight()));

        // Menentukan sisi mana yang paling dekat untuk menentukan respons sentuhan
        if (leftDistance < rightDistance && leftDistance < downDistance && leftDistance < upDistance) {
            player.setVelocityX(-3);
            player.setPosX(ground.getPosX() - player.getWidth());
        } else if (rightDistance < downDistance && rightDistance < upDistance) {
            player.setVelocityX(0);
            player.setPosX(ground.getPosX() + ground.getWidth());
        } else if (downDistance < upDistance) {
            player.setVelocityY(0);
            player.setPosY(ground.getPosY() - player.getHeight());
        } else {
            player.setVelocityY(0);
            player.setPosY(ground.getPosY() + ground.getHeight());
        }
    }

    // Metode untuk menangani sentuhan dengan platform
    private void handlePlatformCollision(Player player, Platform platform) {
        // Memeriksa apakah pemain bersentuhan dengan bagian bawah platform
        if (player.getPosY() + player.getHeight() > platform.getPosY() + 500 &&
                player.getPosY() < platform.getPosY() + 505 && // Memberikan margin kesalahan kecil
                player.getPosX() + player.getWidth() > platform.getPosX() &&
                player.getPosX() < platform.getPosX() + platform.getWidth() &&
                player.getVelocityY() > 0) {
            // Bersentuhan dengan bagian bawah platform
            player.setVelocityY(0);
            player.setPosY(platform.getPosY() + 500 - player.getHeight());
        }
    }

    // Metode untuk memeriksa apakah ada sentuhan dengan tanah
    private boolean checkGroundCollision(Player player, Ground ground) {
        return player.getPosX() < ground.getPosX() + ground.getWidth() &&
                player.getPosX() + player.getWidth() > ground.getPosX() &&
                player.getPosY() < ground.getPosY() + ground.getHeight() &&
                player.getPosY() + player.getHeight() > ground.getPosY();
    }

    // Metode untuk memeriksa apakah ada sentuhan dengan platform
    private boolean checkPlatformCollision(Player player, Platform platform) {
        // Hanya memeriksa jika pemain berada di bawah platform dan bergerak ke atas
        return player.getPosX() + player.getWidth() > platform.getPosX() &&
                player.getPosX() < platform.getPosX() + platform.getWidth() &&
                player.getPosY() + player.getHeight() > platform.getPosY() + 500 &&
                player.getPosY() < platform.getPosY() + 505 && // Memberikan margin kesalahan kecil
                player.getVelocityY() > 0;
    }
}