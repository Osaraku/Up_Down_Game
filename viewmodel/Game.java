package viewmodel;

import model.Ground;
import model.Player;
import model.Platform;
import view.UpDownGame;
import view.MainMenu;
import model.TScore;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class Game mengatur logika permainan, termasuk rendering, pergerakan pemain, dan deteksi sentuhan.
public class Game implements ActionListener {
    private UpDownGame updowngame;
    private int playerStartPosX = 300;
    private int playerStartPosY = 20;
    private int playerWidth = 42;
    private int playerHeight = 32;
    private Player player;
    private Timer gameLoop;
    private int gravity = 1;
    private int init = 1;
    private boolean isGameOver = false;
    private int groundScore = 0;
    private int platformScore = 0;
    private int totalScore = 0;
    private Clip backgroundMusicClip;
    private Clip failSoundClip;

    public Game(UpDownGame updowngame) {
        this.updowngame = updowngame;
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, updowngame.getFrogImage());

        // Timer untuk game loop, dengan frame rate 60 fps
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Memutar musik latar belakang
        this.backgroundMusicClip = Sound.playSound(this.backgroundMusicClip, "bgm.wav", true);
    }

    // Metode untuk menggambar elemen-elemen permainan di layar.
    public void draw(Graphics g) {
        // Menggambar latar belakang dan pemain
        g.drawImage(updowngame.getBackgroundImage(), 0, 0, 960, 540, null);
        g.drawImage(updowngame.getFrogImage(), player.getPosX(), player.getPosY(), player.getWidth(),
                player.getHeight(), null);

        // Inisialisasi ground dan platform jika pertama kali
        g.setColor(Color.white);
        if (init == 1) {
            updowngame.getGroundManager().generateInitialGrounds();
            updowngame.getPlatformManager().generateInitialPlatforms();
            init = 0;
        }

        // Menggambar ground dan skor
        for (Ground ground : updowngame.getGroundManager().getGrounds()) {
            g.drawImage(ground.getImage(), ground.getPosX(), ground.getPosY(), ground.getWidth(), ground.getHeight(),
                    null);
            // Menampilkan Skor sesuai dengan kesulitan dipijaki
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if (ground.getPosY() - 200 < 100) {
                g.drawString(String.valueOf((int) 10), ground.getPosX() + 35, ground.getPosY() - 10);
            } else if (ground.getPosY() - 200 < 150) {
                g.drawString(String.valueOf((int) 20), ground.getPosX() + 35, ground.getPosY() - 10);
            } else if (ground.getPosY() - 200 < 200) {
                g.drawString(String.valueOf((int) 30), ground.getPosX() + 35, ground.getPosY() - 10);
            } else if (ground.getPosY() - 200 < 250) {
                g.drawString(String.valueOf((int) 40), ground.getPosX() + 35, ground.getPosY() - 10);
            } else {
                g.drawString(String.valueOf((int) 50), ground.getPosX() + 35, ground.getPosY() - 10);
            }
        }

        // Menggambar ground dan skor
        for (Platform platform : updowngame.getPlatformManager().getPlatforms()) {
            g.drawImage(platform.getImage(), platform.getPosX(), platform.getPosY(), platform.getWidth(),
                    platform.getHeight(), null);
            // Menampilkan Skor sesuai dengan kesulitan dipijaki
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            if (-100 - platform.getPosY() < 100) {
                g.drawString(String.valueOf((int) 10), platform.getPosX() + 35, platform.getPosY() + 485);
            } else if (-100 - platform.getPosY() < 150) {
                g.drawString(String.valueOf((int) 20), platform.getPosX() + 35, platform.getPosY() + 485);
            } else if (-100 - platform.getPosY() < 200) {
                g.drawString(String.valueOf((int) 30), platform.getPosX() + 35, platform.getPosY() + 485);
            } else if (-100 - platform.getPosY() < 250) {
                g.drawString(String.valueOf((int) 40), platform.getPosX() + 35, platform.getPosY() + 485);
            } else {
                g.drawString(String.valueOf((int) 50), platform.getPosX() + 35, platform.getPosY() + 485);
            }
        }

        // Menampilkan total skor
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString(updowngame.getUsername(), 30, 50);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + String.valueOf((int) totalScore), 30, 90);
        g.drawString("Up: " + String.valueOf((int) platformScore), 30, 120);
        g.drawString("Down: " + String.valueOf((int) groundScore), 30, 150);

    }

    // Metode untuk menggerakan objek-objek pada permainan
    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity); // Menerapkan gaya gravitasi
        player.setPosX(player.getPosX() + player.getVelocityX()); // Memperbarui posisi horizontal pemain
        player.setPosY(player.getPosY() + player.getVelocityY()); // Memperbarui posisi vertikal pemain

        // Memastikan pemain tidak keluar dari batas atas layar
        player.setPosY(Math.max(player.getPosY(), 0));

        // Memastikan pemain tidak keluar dari batas kanan layar
        player.setPosX(Math.min(player.getPosX(), updowngame.getFrameWidth() - player.getWidth()));

        // Memperbarui posisi ground
        for (Ground ground : updowngame.getGroundManager().getGrounds()) {
            ground.setPosX(ground.getPosX() + ground.getVelocityX());

            // Memeriksa apakah pemain telah melewati ground
            if (!ground.getPassed() && player.getPosX() >= ground.getPosX()
                    && player.getPosX() <= ground.getPosX() + ground.getWidth()
                    && player.getPosY() >= ground.getPosY() + ground.getHeight() - 600) {
                ground.setPassed(true);
                groundScore += 1;
                if (ground.getPosY() - 200 < 100) {
                    totalScore += 10;
                } else if (ground.getPosY() - 200 < 150) {
                    totalScore += 20;
                } else if (ground.getPosY() - 200 < 200) {
                    totalScore += 30;
                } else if (ground.getPosY() - 200 < 250) {
                    totalScore += 40;
                } else {
                    totalScore += 50;
                }
            }

            // Memeriksa apakah pemain bertabrakan dengan ground
            updowngame.getCollision().collide(player, ground);
        }

        // Memperbarui posisi platform
        for (Platform platform : updowngame.getPlatformManager().getPlatforms()) {
            platform.setPosX(platform.getPosX() + platform.getVelocityX());

            // Memeriksa apakah pemain telah melewati platform
            if (!platform.getPassed() && player.getPosX() >= platform.getPosX()
                    && player.getPosX() <= platform.getPosX() + platform.getWidth()
                    && player.getPosY() <= platform.getPosY() + platform.getHeight()
                    && player.getPosY() >= platform.getPosY() + 450) {
                platform.setPassed(true);
                platformScore += 1;
                if (-100 - platform.getPosY() < 100) {
                    totalScore += 10;
                } else if (-100 - platform.getPosY() < 150) {
                    totalScore += 20;
                } else if (-100 - platform.getPosY() < 200) {
                    totalScore += 30;
                } else if (-100 - platform.getPosY() < 250) {
                    totalScore += 40;
                } else {
                    totalScore += 50;
                }
            }

            // Memeriksa apakah pemain bertabrakan dengan platform
            updowngame.getCollision().collide(player, platform);
        }

        // Memeriksa apakah pemain keluar dari batas bawah layar atau keluar dari batas
        // kiri layar
        if (player.getPosY() > updowngame.getFrameHeight() || player.getPosX() < 0) {
            isGameOver = true;
        }
    }

    // Metode untuk memperbarui permainan pada setiap frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        // Pindahkan objek-objek permainan ke posisi baru
        move();
        // Gambar ulang
        updowngame.repaint();

        // Cek apakah permainan telah selesai
        if (isGameOver) {
            // Hentikan loop permainan
            gameLoop.stop();
            // Hentikan cooldown untuk generate ground dan platform
            updowngame.getGroundManager().getGroundsCooldown().stop();
            updowngame.getPlatformManager().getPlatformsCooldown().stop();
            // Hentikan musik latar belakang
            Sound.stopSound(backgroundMusicClip);
            // Mainkan sfx gagal
            this.failSoundClip = Sound.playSound(this.failSoundClip, "fail.wav", false);
            // Simpan skor pemain
            saveScore();
        }
    }

    // Metode untuk menyimpan hasil score saat ini
    public void saveScore() {
        try {
            // Buat objek TScore serta simpan atau perbarui skor
            TScore tscore = new TScore();
            tscore.insertOrUpdateScore(updowngame.getUsername(), String.valueOf(totalScore),
                    String.valueOf(platformScore), String.valueOf(groundScore));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Buat pesan hasil skor yang didapat untuk ditampilkan dalam dialog
        String message = "Username: " + updowngame.getUsername() + "\nScore: " + totalScore + "\nUp: " + platformScore
                + "\nDown: " + groundScore;

        // Tampilkan dialog pilihan untuk restart atau kembali ke menu
        int option = JOptionPane.showOptionDialog(null, message, "GAME OVER", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Restart", "Back to Menu" }, null);

        // Jika pemain memilih restart
        if (option == JOptionPane.YES_OPTION) {
            restart();
        } else if (option == JOptionPane.NO_OPTION) {
            // Tutup jendela permainan
            JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(updowngame);
            gameFrame.dispose();
            // Tampilkan menu utama
            new MainMenu().setVisible(true);
        }
    }

    public void restart() {
        // Reset posisi dan pergerakan pemain
        player.setPosY(playerStartPosY);
        player.setPosX(playerStartPosX);
        player.setVelocityY(-6);
        player.setVelocityX(0);

        // Bersihkan ground dan platform yang ada
        updowngame.getGroundManager().getGrounds().clear();
        updowngame.getPlatformManager().getPlatforms().clear();

        // Reset skor
        groundScore = 0;
        platformScore = 0;
        totalScore = 0;
        isGameOver = false;

        // Generate ulang ground dan platform saat permainan dimulai ulang
        updowngame.getGroundManager().generateInitialGrounds();
        updowngame.getPlatformManager().generateInitialPlatforms();

        // Mulai kembali loop permainan dan cooldown ground serta platform
        gameLoop.start();
        updowngame.getGroundManager().getGroundsCooldown().start();
        updowngame.getPlatformManager().getPlatformsCooldown().start();

        // Mainkan ulang musik latar belakang
        this.backgroundMusicClip = Sound.playSound(backgroundMusicClip, "bgm.wav", true);
    }

    public Player getPlayer() {
        return player; // Kembalikan objek player
    }

    public boolean getIsGameOver() {
        return isGameOver; // Kembalikan status permainan (apakah game over)
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver; // Set status permainan (apakah game over)
    }
}