/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

/**
 *
 * @author USER
 */
public class StockKeluar {
    

    public Integer getId_barang_keluar() {
        return id_barang_keluar;
    }

    public void setId_barang_keluar(Integer id_barang_keluar) {
        this.id_barang_keluar = id_barang_keluar;
    }

    public String getKode_barang_keluar() {
        return kode_barang_keluar;
    }

    public void setKode_barang_keluar(String kode_barang_keluar) {
        this.kode_barang_keluar = kode_barang_keluar;
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

    public String getTanggal_barang_keluar() {
        return tanggal_barang_keluar;
    }

    public void setTanggal_barang_keluar(String tanggal_barang_keluar) {
        this.tanggal_barang_keluar = tanggal_barang_keluar;
    }

    public String getJumlah_barang_keluar() {
        return jumlah_barang_keluar;
    }

    public void setJumlah_barang_keluar(String jumlah_barang_keluar) {
        this.jumlah_barang_keluar = jumlah_barang_keluar;
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
    Integer id_barang_keluar;
    String kode_barang_keluar;
    String nama_barang;
    String id_barang;
    String tanggal_barang_keluar;
    String jumlah_barang_keluar;
    String satuan;
    String stock;
    String pt;

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public StockKeluar(Integer id_barang_keluar, String kode_barang_keluar, String nama_barang, String id_barang, String tanggal_barang_keluar, String jumlah_barang_keluar, String satuan, String stock,String pt) {
        this.id_barang_keluar = id_barang_keluar;
        this.kode_barang_keluar = kode_barang_keluar;
        this.nama_barang = nama_barang;
        this.id_barang = id_barang;
        this.tanggal_barang_keluar = tanggal_barang_keluar;
        this.jumlah_barang_keluar = jumlah_barang_keluar;
        this.satuan = satuan;
        this.stock = stock;
        this.pt=pt;
    }
   
}
