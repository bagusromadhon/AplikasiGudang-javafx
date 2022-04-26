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
public class combobox {
    private final String kategori;
    private final Integer id_kategori;
    
    
    
    public combobox(Integer id_kategori,String kategori){
    this.kategori=kategori;
    this.id_kategori=id_kategori;
    }
    
    
    
    public String getKategori(){
       
        return kategori;
        }
    public Integer getId_kategori(){
       
        return id_kategori;
        }
    
    
    
}
