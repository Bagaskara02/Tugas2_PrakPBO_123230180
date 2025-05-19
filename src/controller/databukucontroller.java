/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAOdatabuku.databukuDAO;
import DAOImplement.databukuimplement;
import model.*;
import view.MainView;
import javax.swing.JOptionPane;

public class databukucontroller {
    MainView frame;
    databukuimplement impldatabuku;
    List<dataBuku> db;
    
    public databukucontroller(MainView frame){
        this.frame = frame;
        impldatabuku = new databukuDAO();
    }

    public void isitabel(){
        db = impldatabuku.getAll();
        modeltabeldataBuku mb = new modeltabeldataBuku(db);
        frame.getTabelDataBuku().setModel(mb);
    }
    
    public void insert() {
    try {
        String judul = frame.getJTxtJudul().getText().trim();
        String genre = frame.getJTxtGenre().getText().trim();
        String penulis = frame.getJTxtPenulis().getText().trim();
        String penerbit = frame.getJTxtPenerbit().getText().trim();
        String lokasi = frame.getJTxtLokasi().getText().trim();
        String stockText = frame.getJTxStock().getText().trim();

        if (judul.isEmpty() || genre.isEmpty() || penulis.isEmpty() || penerbit.isEmpty() || lokasi.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Stok harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        dataBuku db = new dataBuku();
        db.setJudul(judul);
        db.setGenre(genre);
        db.setPenulis(penulis);
        db.setPenerbit(penerbit);
        db.setLokasi(lokasi);
        db.setStock(stock);

        impldatabuku.insert(db);
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void update (){
        dataBuku db = new dataBuku();
        db.setJudul(frame.getJTxtJudul().getText());
        db.setGenre(frame.getJTxtGenre().getText());
        db.setPenulis(frame.getJTxtPenulis().getText());
        db.setPenerbit(frame.getJTxtPenerbit().getText());
        db.setLokasi(frame.getJTxtLokasi().getText());
        db.setStock(Integer.parseInt(frame.getJTxStock().getText()));
        db.setId_buku(Integer.parseInt(frame.getJTxtid().getText()));
         

        impldatabuku.update(db);
    }
    
    public void delete(){
        int id_buku = Integer.parseInt(frame.getJTxtid().getText());
        impldatabuku.delete(id_buku);
    }
    
    public void caribuku(String kategori, String keyword) {
    try {
            if (keyword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Silakan masukkan kata kunci pencarian.");
            } else {
                List<dataBuku> hasil = impldatabuku.cari(kategori, keyword);
                if (hasil.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data dengan kata kunci tersebut tidak ditemukan.");
                } else {
                    modeltabeldataBuku model = new modeltabeldataBuku(hasil);
                    frame.getTabelDataBuku().setModel(model);
                }
            } 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari data: " + ex.getMessage());
        }
    }
}

