/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.sql.ResultSet;

/**
 *
 * @author USER
 */
public class StockMasuk {
    Integer id_barang_masuk;
    String kode_barang_masuk;
    String nama_barang;
    String id_barang;
    String tanggal_barang_masuk;
    String jumlah_barang_masuk;
    String satuan;
    String stock;
    String pt;

    

   // sek bi 

    public StockMasuk(Integer id_barang_masuk, String kode_barang_masuk, String nama_barang, String id_barang, String tanggal_barang_masuk, String jumlah_barang_masuk, String satuan, String stock,String pt) {
        this.id_barang_masuk = id_barang_masuk;
        this.kode_barang_masuk = kode_barang_masuk;
        this.nama_barang = nama_barang;
        this.id_barang = id_barang;
        this.tanggal_barang_masuk = tanggal_barang_masuk;
        this.jumlah_barang_masuk = jumlah_barang_masuk;
        this.satuan = satuan;
        this.stock = stock;
        this.pt=pt;
      
    }

    public Integer getId_barang_masuk() {
        return id_barang_masuk;
    }

    public void setId_barang_masuk(Integer id_barang_masuk) {
        this.id_barang_masuk = id_barang_masuk;
    }

    public String getKode_barang_masuk() {
        return kode_barang_masuk;
    }

    public void setKode_barang_masuk(String kode_barang_masuk) {
        this.kode_barang_masuk = kode_barang_masuk;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getTanggal_barang_masuk() {
        return tanggal_barang_masuk;
    }

    public void setTanggal_barang_masuk(String tanggal_barang_masuk) {
        this.tanggal_barang_masuk = tanggal_barang_masuk;
    }

    public String getJumlah_barang_masuk() {
        return jumlah_barang_masuk;
    }

    public void setJumlah_barang_masuk(String jumlah_barang_masuk) {
        this.jumlah_barang_masuk = jumlah_barang_masuk;
    }
    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    public String getPt() {
        return pt;
    }

    
    

    
    
    
  
}
