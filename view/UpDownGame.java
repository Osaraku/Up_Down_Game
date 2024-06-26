package view;

import viewmodel.*;
import javax.swing.*;
import java.awt.*;

public class UpDownGame extends JPanel {
    // Deklarasi variabel untuk komponen-komponen game
    private Game game;
    private GroundManager groundManager;
    private PlatformManager platformManager; // Tambahkan variabel untuk PlatformManager
    private Input input;
    private Collision collision;
    private int frameWidth = 960; // Lebar frame
    private int frameHeight = 540; // Tinggi frame
    private String username; // Username pemain

    // Atribut untuk image
    private Image backgroundImage;
    private Image frogImage;
    private Image lowerGroundImage;
    private Image platformImage;

    // Konstruktor untuk UpDownGame
    public UpDownGame() {
        // Mengatur ukuran dan fokus panel
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);

        // Memuat gambar dari file
        backgroundImage = new ImageIcon(getClass().getResource("../assets/background.png")).getImage();
        frogImage = new ImageIcon(getClass().getResource("../assets/frog.png")).getImage();
        lowerGroundImage = new ImageIcon(getClass().getResource("../assets/ground.png")).getImage();
        platformImage = new ImageIcon(getClass().getResource("../assets/platform.png")).getImage();

        // Inisialisasi objek-objek yang digunakan dalam game
        game = new Game(this);
        groundManager = new GroundManager(this);
        platformManager = new PlatformManager(this);
        input = new Input(this);
        collision = new Collision(this);
        username = getUsername(); // Mengambil username
    }

    // Metode untuk menggambar komponen pada panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g); // Memanggil metode draw pada objek game
    }

    // Getter untuk lebar frame
    public int getFrameWidth() {
        return frameWidth;
    }
    // Getter untuk tinggi frame
    public int getFrameHeight() {
        return frameHeight;
    }
    // Getter untuk objek Game
    public Game getGame() {
        return game;
    }
    // Getter untuk GroundManager
    public GroundManager getGroundManager() {
        return groundManager;
    }
    // Getter untuk PlatformManager
    public PlatformManager getPlatformManager() {
        return platformManager;
    }
    // Getter untuk Collision
    public Collision getCollision() {
        return collision;
    }
    // Getter untuk gambar background
    public Image getBackgroundImage() {
        return backgroundImage;
    }
    // Getter untuk gambar frog
    public Image getFrogImage() {
        return frogImage;
    }
    // Getter untuk gambar lower ground
    public Image getLowerGroundImage() {
        return lowerGroundImage;
    }
    // Getter untuk gambar platform
    public Image getPlatformImage() {
        return platformImage;
    }
    // Getter untuk username
    public String getUsername() {
        return username;
    }
    // Setter untuk username
    public void setUsername(String username) {
        this.username = username;
    }
}