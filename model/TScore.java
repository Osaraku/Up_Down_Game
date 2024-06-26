package model;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class TScore extends DB {

    // Konstruktor untuk kelas TScore
    public TScore() throws Exception, SQLException {
        super(); // Memanggil konstruktor dari superclass DB
    }

    // Metode untuk mengambil skor dari username tertentu dari tabel tscore
    public void getDataScore(String username) {
        try {
            // Query SQL untuk memilih skor berdasarkan username tertentu
            String query = "SELECT * from tscore WHERE username='" + username + "'";
            createQuery(query); // Menjalankan query
        } catch (Exception e) {
            // Menampilkan semua exception yang terjadi
            System.err.println(e.toString());
        }
    }

    // Metode untuk memasukkan atau memperbarui skor pengguna dalam tabel tscore
    public void insertOrUpdateScore(String username, String score, String up, String down) {
        boolean update = false; // Flag untuk menentukan apakah akan memperbarui atau memasukkan
        int highScore = 0; // Variabel untuk menyimpan skor tertinggi saat ini

        try {
            TScore user = new TScore(); // Membuat instance baru dari TScore
            user.getDataScore(username); // Mengambil data untuk username saat ini

            // Memeriksa apakah ada catatan yang ada untuk username tersebut
            if (user.getResult().next()) {
                update = true; // Set flag update menjadi true
                highScore = user.getResult().getInt(3); // Mendapatkan skor tertinggi saat ini
            }
        } catch (Exception e) {
            // Menampilkan semua exception yang terjadi
            System.out.println(e.getMessage());
        }

        if (!update) {
            // Jika tidak ada catatan yang ada, masukkan catatan baru
            try {
                // Query SQL untuk memasukkan catatan baru
                String query = "INSERT INTO tscore VALUES(null, '" + username + "', " + score + ", " + up + ", " + down
                        + ")";
                createUpdate(query); // Menjalankan update
            } catch (Exception e) {
                // Menampilkan semua exception yang terjadi
                System.out.println(e.toString());
            }
        } else if (update && Integer.valueOf(score) >= highScore) {
            // Jika catatan ada dan score baru lebih besar atau sama dengan skor tertinggi
            // saat ini, perbarui catatan
            try {
                // Query SQL untuk memperbarui catatan yang ada
                String query = "UPDATE tscore SET score=" + score + ", up=" + up + ", down=" + down
                        + " WHERE username='" + username + "'";
                createUpdate(query); // Menjalankan update
            } catch (Exception e) {
                // Menampilkan semua exception yang terjadi
                System.out.println(e.getMessage());
            }
        }
    }

    // Metode untuk mengambil data skor sebagai DefaultTableModel untuk ditampilkan
    // dalam tabel
    public DefaultTableModel getTableData() {
        DefaultTableModel tableData = null;
        try {
            // Mendefinisikan header kolom untuk tabel
            Object[] column = { "Rank", "Username", "Score", "Up", "Down" };
            tableData = new DefaultTableModel(null, column); // Inisialisasi model tabel dengan header kolom

            // Query SQL untuk memilih semua skor diurutkan berdasarkan score secara menurun
            String query = "SELECT * FROM tscore ORDER BY score DESC";
            this.createQuery(query); // Menjalankan query

            int i = 0; // Counter untuk ranking
            while (this.getResult().next()) {
                // Membuat baris baru untuk setiap catatan
                Object[] row = new Object[5];
                row[0] = i + 1; // Rank (indeks berbasis 1)
                row[1] = this.getResult().getString(2); // Username
                row[2] = this.getResult().getString(3); // Score
                row[3] = this.getResult().getString(4); // Up
                row[4] = this.getResult().getString(5); // Down
                tableData.addRow(row); // Menambahkan baris ke model tabel
                i++; // Meningkatkan counter rank
            }
        } catch (Exception e) {
            // Menampilkan semua exception yang terjadi
            System.err.println(e.getMessage());
        }
        return tableData; // Mengembalikan model tabel yang telah diisi
    }
}