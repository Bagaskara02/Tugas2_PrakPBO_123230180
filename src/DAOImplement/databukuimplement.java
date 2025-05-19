/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.*;
/**
 *
 * @author Bagaskara
 */
public interface databukuimplement {
    public void insert(dataBuku b);
    public void update(dataBuku b);
    public void delete(int id_buku);
    public List<dataBuku> getAll();
    public List<dataBuku> cari(String kategori, String keyword);
}
