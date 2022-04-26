/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.Data.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashboardController implements Initializable {

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Data, Integer> col_kode;

    @FXML
    private TableColumn<Data, String> col_nama;

    @FXML
    private TableColumn<Data, String> col_satuan;

    @FXML
    private TableColumn<Data, String> col_stock;

    @FXML
    private Button delete;

    @FXML
    private Button insert;

    @FXML
    private Label id;

    @FXML
    private Label numRows;

    @FXML
    private TextField kode;

    @FXML
    private TextField nama;

    @FXML
    private ComboBox<String> satuan;

    @FXML
    private TextField stock;

    @FXML
    private TableView<Data> table_view;

    @FXML
    private Button update;

    @FXML
    private Button simpan;

    @FXML
    private TextField satuan1;

    @FXML
    void addButton(ActionEvent event) {

    }


    
    
    @FXML
    void pindah_stockmasuk(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/stockmasuk.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setMaximized(true);
        stage.setScene(new Scene(root1));
        stage.show();
    }

     
    @FXML
    void keluar(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/login.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setMaximized(true);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void pindah_home(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/dashboard.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setMaximized(true);
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
//    private TableView<GudangProperty> gudangTable;
    /**
     * Initializes the controller class.
     */
//    private String[] combosatuan= {"Liter","KG","Lusing"};
//    
//    public void comboBox(){
//    
//           List<String> list = new ArrayList<>();
//           
//           for(String data: combosatuan){
//                list.add(data);
//           }
//           
//           ObservableList dataList = FXCollections.observableArrayList(list);
//           
//           satuan.setItems(dataList);
//    
//    }

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
    java.sql.Connection conn = koneksidatabase.koneksi();

    @FXML
    private void count(){
        connect = connectDb();
        String query = "SELECT COUNT(id_barang) FROM barang";
        
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            
            while (result.next()) {                
                numRows.setText(result.getString("COUNT(id_barang)"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void simpan(ActionEvent event) throws SQLException, IOException {
        connect = connectDb();
        String query = "INSERT INTO barang (nama_barang,stock,satuan) VALUES(?,?,?)";

        prepare = connect.prepareStatement(query);
        prepare.setString(1, nama.getText());
        prepare.setString(2, stock.getText());
        prepare.setString(3, satuan1.getText());
//        prepare.setString(3, (String) satuan.getSelectionModel().getSelectedItem());

        prepare.executeUpdate();
        Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information ");
alert.setHeaderText(null);
alert.setContentText("Data Berhasil Di simpan!");

alert.showAndWait();
        showData();
        clear();

    }

//    public void numRows() throws SQLException {
//        connect = connectDb();
//        String query = "SELECT count(*) FROM barang";
//
//        prepare = connect.prepareStatement(query);
////        prepare.setString(3, (String) satuan.getSelectionModel().getSelectedItem());
//
//        ResultSet rs = prepare.executeQuery();
//        while (rs.next()) {
//            
//        }
//    }

    public ObservableList<Data> dataList() {

        connect = connectDb();

        ObservableList<Data> dataList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM barang ";
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Data data;

            while (result.next()) {

                data = new Data(result.getInt("id_barang"),
                        result.getString("nama_barang"),
                        result.getString("satuan"),
                        result.getString("stock"));

                dataList.add(data);
            }

        } catch (Exception e) {
        }
        return dataList;
    }

    public void showData() {

        ObservableList<Data> showList = dataList();

        col_kode.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
        col_satuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table_view.setItems(showList);
    }

    public void selectData() {

        Data data = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        id.setText(String.valueOf(data.getId_barang()));
        nama.setText(data.getNama_barang());
        stock.setText(data.getStock());
        satuan1.setText(data.getSatuan());
        satuan.getSelectionModel().clearSelection();

    }

    public void update() throws SQLException {
        connect = connectDb();

        String sql = "UPDATE barang SET `nama_barang` = '"
                + nama.getText() + "', `satuan` = '"
                + satuan1.getText() + "', `stock` = '"
                + stock.getText() + "' WHERE id_barang = '" + id.getText() + "'";

        statement = connect.createStatement();
        statement.executeUpdate(sql);

        try {
            if (id.getText().isEmpty()
                    | nama.getText().isEmpty()
                    | satuan1.getText().isEmpty()
                    | stock.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Eror Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields");
                alert.showAndWait();

            } else {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Marcoman Message");
                alert.setHeaderText(null);
                alert.setContentText("Succesfuly update data");
                alert.showAndWait();

                showData();
                clear();
            }

        } catch (Exception e) {

        }
    }

    public void clear() {

        id.setText("");
        nama.setText("");
        stock.setText("");
        satuan.getSelectionModel().clearSelection();
        satuan1.setText("");

    }

    public void delete() {
        String sql = "DELETE from barang WHERE `id_barang` = '" + id.getText() + "'";
        connect = connectDb();

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Kamu Yakin Ingin Menghapus?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

            } else {
                return;
            }

        } catch (Exception e) {
        }
        showData();
        clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
    
            // TODO
            // for combo box
//        comboBox();
//        numRows();
       count();
        showData();
        populateCategories();
        clear();

    }

    public void BarangKeluar(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/app/view/stockkeluar.fxml"));
//        Node node = (Node) mouseEvent.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.centerOnScreen();

    }

    public void BarangMasuk(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/stockmasuk.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();

    }

    public void laporan(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/laporan.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();

    }

    private void populateCategories() {
        java.sql.Connection conn = koneksidatabase.koneksi();
        Statement st;
        connect = connectDb();
        ObservableList<String> list = FXCollections.observableArrayList();

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM kategori");
            while (rs.next()) {
                list.add(rs.getString("kategori"));

            }
        } catch (Exception ex) {

        }

        satuan.setItems(null);
        satuan.setItems(list);

        //To change body of generated methods, choose Tools | Templates.
    }

    private void comboBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @FXML
//    void home(ActionEvent event) throws IOException {
//         ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
//         
//         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app/view/home.fxml"));
//                Parent root = fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.setMaximized(true);
//                stage.setScene(new Scene(root));
//                stage.show();
//    }
    // pindah halaman
    public void home(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app/view/home.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();

    }

}
