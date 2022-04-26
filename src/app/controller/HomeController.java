/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.koneksidatabase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeController implements Initializable {

    java.sql.Connection conn = koneksidatabase.koneksi();

    
    @FXML
    private Label total_barang;
    @FXML
    private Label total_keluar;
    @FXML
    private Label total_masuk;
    
    @FXML
    void total_barang(ActionEvent event) {
        
    }
    
    
    @FXML
    void pindah_stockmasuk(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/stockmasuk.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void pindah_home(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/home.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void pindah_stockkeluar(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/StockKeluar.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
      @FXML
    void laporan(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/StockKeluar.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

     private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/gudangalami", "root", "");
            return connect;
        } catch (Exception e) {
        }
        return null;

    }
    
    @FXML
    private void count_masuk(){
        connect = connectDb();
        String query = "SELECT COUNT(id_barang_masuk) FROM barang_masuk";
        
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            
            while (result.next()) {                
                total_masuk.setText(result.getString("COUNT(id_barang_masuk)"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
       @FXML
    private void count_keluar(){
        connect = connectDb();
        String query = "SELECT COUNT(id_barang_keluar) FROM barang_keluar";
        
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            
            while (result.next()) {                
                total_keluar.setText(result.getString("COUNT(id_barang_keluar)"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       @FXML
    private void count(){
        connect = connectDb();
        String query = "SELECT COUNT(id_barang) FROM barang";
        
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            
            while (result.next()) {                
                total_barang.setText(result.getString("COUNT(id_barang)"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tampiltotalbarang(){
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        count_keluar();
        count_masuk();
         count();
    }

    public void s() {

    }

}
