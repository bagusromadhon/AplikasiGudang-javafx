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
public class NamaBarang {
    int id_barang;
    String nama_barang;
    String satuan;

    public NamaBarang(int id_barang, String nama_barang, String satuan, String stock) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.satuan = satuan;
        this.stock = stock;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
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
    String stock;
}
