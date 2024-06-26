// Saya [Muhamad Tio Ariyanto 2201718] mengerjakan evaluasi Tugas Masa Depan
// dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya
// saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

import view.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Buat frame menu dan tampilkan ke layar
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}