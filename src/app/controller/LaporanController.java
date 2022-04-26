/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author USER
 */


public class LaporanController implements Initializable {
@FXML
    void BarangKeluar(MouseEvent event) {

    }

    @FXML
    void BarangMasuk(MouseEvent event) {

    }

    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void dashboardButton(ActionEvent event) {

    }

    @FXML
    void laporan(MouseEvent event) {

    }

    @FXML
    void stastisikbutton(ActionEvent event) {

    }
    @FXML
    void keluar(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/login.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @FXML
    void pindah_laporan(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/laporan.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @FXML
    void pindah_manage(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/manageadmin.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @FXML
    void pindah_home(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/kepalagudang.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    public void cetaksu(ActionEvent event) {
        
        try{
            HashMap parameter = new HashMap();
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql:"+"///gudangalami","root","");
            File file = new File("src/report/barangmasuk.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e){
            
        
        }
    }
    
        @FXML
    public void cetakjis(ActionEvent event) {
        try{
            HashMap parameter = new HashMap();
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql:"+"///gudangalami","root","");
            File file = new File("src/report/barangkeluar.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e){
            
        
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
