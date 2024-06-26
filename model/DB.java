// Saya [Muhamad Tio Ariyanto 2201718] mengerjakan evaluasi Tugas Masa Depan
// dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya
// saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    // URL untuk koneksi ke basis data MySQL
    private String url ="jdbc:mysql://localhost/db_updown";
    // Username untuk koneksi ke basis data
    private String user="root";
    // Password untuk koneksi ke basis data
    private String pass="";

    // Statement untuk menjalankan query
    private Statement stmt = null;
    // ResultSet untuk menyimpan hasil query
    private ResultSet rs = null;
    // Connection untuk mengelola koneksi ke basis data
    private Connection conn = null;

    // Konstruktor untuk kelas DB
    public DB() throws Exception, SQLException {
        try {
            // Memuat driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Membuat koneksi ke basis data
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException es) {
            // Mengeluarkan pesan error jika koneksi gagal
            throw es;
        }
    }

    // Metode untuk membuat dan mengeksekusi query SELECT pada database
    public void createQuery(String Query) throws Exception, SQLException {
        // Menjalankan query SELECT
        try {
            // Membuat statement
            stmt = conn.createStatement();
            // Menjalankan query
            rs = stmt.executeQuery(Query);
            if (stmt.execute(Query)) {
                // Jika query berhasil dijalankan, simpan hasil ke ResultSet
                rs = stmt.getResultSet();
            }
        } catch (SQLException es) {
            // Mengeluarkan pesan error jika query gagal
            throw es;
        }
    }

    // Metode untuk membuat dan mengeksekusi query yang mengubah data (UPDATE, INSERT, DELETE)
    public void createUpdate(String Query) throws Exception, SQLException {
        //Menjalankan query UPDATE, INSERT, DELETE
        try {
            // Membuat statement
            stmt = conn.createStatement();
            // Menjalankan query dan mendapatkan jumlah baris yang terpengaruh
            int hasil = stmt.executeUpdate(Query);
        } catch (SQLException es) {
            // Mengeluarkan pesan error jika query gagal
            throw es;
        }
    }

    // Metode untuk mendapatkan hasil query
    public ResultSet getResult() throws Exception {
        // Mengembalikan hasil query dalam bentuk ResultSet
        ResultSet Temp = null;
        try {
            return rs; // Mengembalikan hasil query
        } catch (Exception ex) {
            // Eksepsi jika hasil tidak dapat dikembalikan
            return Temp;
        }
    }
}