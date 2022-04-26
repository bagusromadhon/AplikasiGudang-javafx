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
public class Data {
   private final Integer id_barang;
 
   private final String nama_barang;
   private final String satuan;
   private final String stock;
   
   
   public Data(Integer id_barang,  String nama_barang, String satuan, String stock){
       this.id_barang=id_barang;
       this.nama_barang=nama_barang;
       this.satuan=satuan;
       this.stock=stock;
       
   }
   public Integer getId_barang(){
       
        return id_barang;
        }
   
   public String getNama_barang(){
       
        return nama_barang;
        }
   
   public String getSatuan(){
       
        return satuan;
        }
   
   public String getStock(){
       
        return stock;
        }
   
  
}   

