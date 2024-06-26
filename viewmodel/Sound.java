package viewmodel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Class untuk mengatur suara pada game
public class Sound {
    // Metode untuk memainkan suara
    public static Clip playSound(Clip clip, String filename, Boolean looping) {
        try {
            // Mengambil audio input dari file
            AudioInputStream audioIn = AudioSystem
                    .getAudioInputStream(new File("assets/" + filename).getAbsoluteFile());
            // Mendapatkan resource sound clip
            clip = AudioSystem.getClip();
            // Membuka audio clip dan memuat sampel dari audio input stream
            clip.open(audioIn);
            clip.start(); // Memulai audio
            if (looping) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Mengatur audio untuk loop terus-menerus
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace(); // Menangani kesalahan jika format audio tidak didukung
        } catch (IOException e) {
            e.printStackTrace(); // Menangani kesalahan I/O
        } catch (LineUnavailableException e) {
            e.printStackTrace(); // Menangani kesalahan jika line audio tidak tersedia
        }
        return clip; // Mengembalikan objek Clip
    }

    // Metode untuk menghentikan suara
    public static void stopSound(Clip clip) {
        clip.stop(); // Menghentikan audio
    }
}