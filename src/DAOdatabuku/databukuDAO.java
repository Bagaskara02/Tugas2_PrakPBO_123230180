/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatabuku;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import  DAOImplement.databukuimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bagaskara
 */
public class databukuDAO implements databukuimplement{
    Connection connection;
    
    final String select = "SELECT * FROM db_perpustakaan";
    final String insert = "INSERT INTO db_perpustakaan (judul, genre, penulis, penerbit, lokasi, stock) VALUES (?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE db_perpustakaan set judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stock=? where id_buku=?";
    final String delete = "DELETE FROM db_perpustakaan where id_buku=?";
    public databukuDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(dataBuku b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getGenre());
            statement.setString(3, b.getPenulis());
            statement.setString(4, b.getPenerbit());
            statement.setString(5, b.getLokasi());
            statement.setInt(6, b.getStock());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId_buku(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(dataBuku b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getGenre());
            statement.setString(3, b.getPenulis());
            statement.setString(4, b.getPenerbit());
            statement.setString(5, b.getLokasi());
            statement.setInt(6, b.getStock());
            statement.setInt(7, b.getId_buku());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id_buku) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id_buku);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
public List<dataBuku> getAll() {
    List<dataBuku> db = new ArrayList<>();
    try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            dataBuku bk = new dataBuku();
            bk.setId_buku(rs.getInt("id_buku"));
            bk.setJudul(rs.getString("judul"));
            bk.setGenre(rs.getString("genre"));
            bk.setPenulis(rs.getString("penulis"));
            bk.setPenerbit(rs.getString("penerbit"));
            bk.setLokasi(rs.getString("lokasi"));
            bk.setStock(rs.getInt("stock"));
            db.add(bk); 
        }
    } catch (SQLException ex) {
        Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return db;
}
    @Override
public List<dataBuku> cari(String kategori, String keyword) {
    List<dataBuku> list = new ArrayList<>();
    String sql = "SELECT * FROM db_perpustakaan WHERE " + kategori + " LIKE ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            dataBuku bk = new dataBuku();
            bk.setId_buku(rs.getInt("id_buku"));
            bk.setJudul(rs.getString("judul"));
            bk.setGenre(rs.getString("genre"));
            bk.setPenulis(rs.getString("penulis"));
            bk.setPenerbit(rs.getString("penerbit"));
            bk.setLokasi(rs.getString("lokasi"));
            bk.setStock(rs.getInt("stock"));
            list.add(bk);
        }
    } catch (SQLException ex) {
        Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

}
