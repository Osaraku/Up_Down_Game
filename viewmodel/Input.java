package viewmodel;

import view.UpDownGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.Clip;

public class Input implements KeyListener {
    private UpDownGame updowngame;
    private Clip jumpSoundClip;

    // Konstruktor untuk kelas Input
    public Input(UpDownGame updowngame) {
        this.updowngame = updowngame;

        // Menambahkan KeyListener ke permainan
        updowngame.addKeyListener(this);
    }

    // Metode ini akan dipanggil ketika tombol diketik
    @Override
    public void keyTyped(KeyEvent e) {
        // Tidak ada implementasi yang diperlukan di sini
    }

    // Metode ini akan dipanggil ketika tombol ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        // Memeriksa apakah tombol panah atas atau W ditekan
        boolean isUpPressed = e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W;
        // Memeriksa apakah tombol panah kiri atau A ditekan
        boolean isLeftPressed = e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A;
        // Memeriksa apakah tombol panah kanan atau D ditekan
        boolean isRightPressed = e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D;
        // Memeriksa apakah tombol panah bawah atau S ditekan
        boolean isDownPressed = e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S;

        int velocityX = 0;
        int velocityY = 0;

        // Jika tombol atas ditekan, setel kecepatan Y ke -21 dan mainkan suara lompat
        if (isUpPressed) {
            velocityY = -21;
            this.jumpSoundClip = Sound.playSound(this.jumpSoundClip, "jump.wav", false);
        }
        // Jika tombol kiri ditekan, setel kecepatan X ke -6
        if (isLeftPressed) {
            velocityX = -6;
        }
        // Jika tombol kanan ditekan, setel kecepatan X ke 6
        if (isRightPressed) {
            velocityX = 6;
        }
        // Jika tombol bawah ditekan, setel kecepatan Y ke 6
        if (isDownPressed) {
            velocityY = 6;
        }

        // Mengatur kecepatan pemain berdasarkan input pengguna
        updowngame.getGame().getPlayer().setVelocityX(velocityX);
        updowngame.getGame().getPlayer().setVelocityY(velocityY);

        // Jika tombol R ditekan dan permainan berakhir, mulai ulang permainan
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (updowngame.getGame().getIsGameOver()) {
                updowngame.getGame().restart();
            }
        }
        // Jika tombol spasi ditekan, setel permainan menjadi berakhir
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            updowngame.getGame().setIsGameOver(true);
        }
    }

    // Metode ini akan dipanggil ketika tombol dilepas
    @Override
    public void keyReleased(KeyEvent e) {
        // Jika tombol gerakan dilepas, setel kecepatan pemain ke 0
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            updowngame.getGame().getPlayer().setVelocityX(0);
            updowngame.getGame().getPlayer().setVelocityY(0);
        }
    }
}