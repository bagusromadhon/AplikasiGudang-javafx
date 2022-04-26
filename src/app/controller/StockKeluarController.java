/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import static app.controller.StockmasukController.explodeStringUsingCoreJava;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StockKeluarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField total_asli;
    @FXML
    private TextField cari;

    @FXML
    private TableView<StockKeluar> table_view;

    @FXML
    private Button hapus;

    @FXML
    private TextField id_barang_keluar;

    @FXML
    private Button insert;

    @FXML
    private TextField jumlah_keluar;

    @FXML
    private TextField kode_barang;

    @FXML
    private ComboBox<String> nama_barang;

    @FXML
    private TableColumn<StockKeluar, Integer> id_keluar;
    
    
    @FXML
    private TableColumn<StockKeluar, String> kolum_pt;

    @FXML
    private TableColumn<StockKeluar, String> kolum_JumlahBarangKeluar;

    @FXML
    private TableColumn<StockKeluar, String> kolum_barang;

    @FXML
    private TableColumn<StockKeluar, String> kolum_kode;

    @FXML
    private TableColumn<StockKeluar, String> kolum_satuan;

    @FXML
    private TableColumn<StockKeluar, String> kolum_stock;

    @FXML
    private TableColumn<StockKeluar, String> kolum_tanggal_keluar;

    @FXML
    private TextField satuan;

    @FXML
    private TextField stock;
     @FXML
    private TextField pt;
    

    @FXML
    private DatePicker tanggal_keluar;

    @FXML
    private Button ubah;

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/home.fxml"));
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
        stage.setMaximized(true);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE from barang_keluar WHERE `id_barang_keluar` = '" + id_barang_keluar.getText() + "'";
        connect = connectDb();

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

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
        cari();
    }
    ObservableList<StockKeluar> dataList;

    @FXML
    void cari() {

        kolum_kode.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("kode_barang_keluar"));
        kolum_barang.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("nama_barang"));
        kolum_JumlahBarangKeluar.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("jumlah_barang_keluar"));
        kolum_tanggal_keluar.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("tanggal_barang_keluar"));
        kolum_satuan.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("satuan"));
        kolum_stock.setCellValueFactory(new PropertyValueFactory<StockKeluar, String>("stock"));

        dataList = dataList();
        table_view.setItems(dataList);
        System.out.println(table_view);
        FilteredList<StockKeluar> filteredData = new FilteredList<>(dataList, b -> true);

        cari.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(StockMasuk -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (StockMasuk.getNama_barang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                }if(StockMasuk.getPt().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                
                }if (StockMasuk.getNama_barang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<StockKeluar> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());

        table_view.setItems(sortedData);
    }

    @FXML
    void hapusButton(ActionEvent event) {

    }

    @FXML
    void simpan(ActionEvent event) throws SQLException {
        Connection conn = connectDb();
        Statement st;
        st = connect.createStatement();
//         System.out.println(result.getString(""));
        String student = nama_barang.getValue();
        String student_id = student.substring(student.indexOf("(") + 1, student.indexOf(")"));
        String kodebarang = kode_barang.getText();
        String dateStr = tanggal_keluar.getValue().toString();
        String jumlahmasuk = jumlah_keluar.getText();
        String total_asli1 = total_asli.getText();
        String perusahan = pt.getText();
//        String stock_barang_tekotabel =result.getString("stock");
//        String stock_total=stock_barang_tekotabel + jumlahmasuk;
        String query = "INSERT INTO barang_keluar(kode_barang_keluar,id_barang,tanggal_barang_keluar,jumlah_barang_keluar,pt) VALUES('" + kodebarang + "','" + student_id + "','" + dateStr + "','" + jumlahmasuk + "','"+ perusahan +"')";
//        String query1 = "INSERT INTO barang(stock) VALUES('" +stock_total+"')";

        st.executeUpdate(query);
//                System.out.println(result.getString("stock"));
        try {

            st = conn.createStatement();

            String querynjopok = "SELECT * FROM barang";
            ResultSet rs = st.executeQuery(querynjopok);
            while (rs.next()) {

                // sek ndelok fxml
//             String Stock = rs.getString("stock");
              String Stock = rs.getString("stock");
                int stok_barang = Integer.parseInt(Stock);
                int jumlah_masuk = Integer.parseInt(jumlahmasuk);
                int total_asli2 = Integer.parseInt(total_asli1);
                int total = total_asli2 - Integer.parseInt(jumlahmasuk);
//                String stock_barang =  rs.getString("stock");
//                String stock_total = stock_barang+jumlahmasuk;
//                int nyoba = integer.parseInt(stock_total);

                String query1 = "UPDATE barang SET stock = '" + total_asli1 + "' WHERE id_barang = '" + student_id + "'";
                st.executeUpdate(query1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information ");
                alert.setHeaderText(null);
                alert.setContentText("Data Berhasil Di simpan!");

                alert.showAndWait();

            }
//            st.executeUpdate(query1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showData();
        clear();
        cari();

    }

    @FXML
    void Update(ActionEvent event) throws SQLException {
        Connection conn = koneksidatabase.koneksi();
        Statement st;
        String kode_keluar = kode_barang.getText();
        String dateStr = tanggal_keluar.getValue().toString();
        String jumlah = jumlah_keluar.getText();
        String perusahan = pt.getText();
        String id_keluar = id_barang_keluar.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("Data Berhasil Di Ubah!");

        alert.showAndWait();
        String query = "UPDATE barang_keluar SET kode_barang_keluar = '" + kode_keluar + "',tanggal_barang_keluar='" + dateStr + "',jumlah_barang_keluar='" + jumlah + "',pt='"+perusahan+"' WHERE id_barang_keluar= '" + id_keluar + "'";

        try {

            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
//       cari();
        clear();
        showData();
        cari();

    }

    public void BarangKeluar() {
    }

    public void laporan() {
    }

    public void hapusButton() {

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
    java.sql.Connection conn = koneksidatabase.koneksi();

    public ObservableList<StockKeluar> dataList() {
        connect = connectDb();

        ObservableList<StockKeluar> dataList = FXCollections.observableArrayList();

        String sql = "SELECT id_barang_keluar,kode_barang_keluar,nama_barang,barang.stock,jumlah_barang_keluar,tanggal_barang_keluar,satuan,pt,barang_keluar.id_barang FROM barang_keluar JOIN barang on barang_keluar.id_barang = barang.id_barang";
        try {

            Statement st;
            st = connect.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {

                dataList.add(new StockKeluar(
                        result.getInt("id_barang_keluar"),
                        result.getString("kode_barang_keluar"),
                        result.getString("nama_barang"),
                        result.getString("id_barang"),
                        result.getString("tanggal_barang_keluar"),
                        result.getString("jumlah_barang_keluar"),
                        result.getString("satuan"),
                        result.getString("stock"),
                        result.getString("pt")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;

    }

    public void showData() {

        ObservableList<StockKeluar> showList = dataList();
        kolum_kode.setCellValueFactory(new PropertyValueFactory<>("kode_barang_keluar"));
        kolum_barang.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
        kolum_JumlahBarangKeluar.setCellValueFactory(new PropertyValueFactory<>("jumlah_barang_keluar"));
        kolum_tanggal_keluar.setCellValueFactory(new PropertyValueFactory<>("tanggal_barang_keluar"));
        kolum_satuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        kolum_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        id_keluar.setCellValueFactory(new PropertyValueFactory<>("id_barang_keluar"));
        kolum_pt.setCellValueFactory(new PropertyValueFactory<>("pt"));

        table_view.setItems(showList);
        cari();
    }
    @FXML
    void total_harga(ActionEvent event){
    connect = connectDb();
    String total_barang_asli =  stock.getText();
    String total_barang_masuk_asli = jumlah_keluar.getText();
    int total_barang_asli1 = Integer.parseInt(total_barang_asli);
    int total_barang_masuk_asli1 = Integer.parseInt(total_barang_masuk_asli);
    int penambahan = total_barang_asli1 - total_barang_masuk_asli1;
    
     total_asli.setText(String.valueOf(penambahan));
     
    
    }

    @FXML
    void nampilnodata(ActionEvent event) {

        String student = nama_barang.getValue();
        String student_id = student.substring(student.indexOf("(") + 1, student.indexOf(")"));
        String Kontolgede = student.substring(student.indexOf(')') + 1, student.length());
        System.out.println(student);
        System.out.println(student_id);
        System.out.println(Kontolgede);

        String[] test = Kontolgede.split("\\.");
        System.out.println(Arrays.toString(test));
        String testis = test[1];
        System.out.println(test[1]);
        stock.setText(testis);

    }

//    combo box
    private void studentsInChoiceBox() {
        Connection conn = koneksidatabase.koneksi();
        Statement st;

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM barang");

            while (rs.next()) {
                String id_barang = rs.getString("id_barang");

                nama_barang.getItems().addAll("(" + id_barang.concat(")" + rs.getString("nama_barang") + "." + rs.getString("stock")));
                String stringToExplode = id_barang.concat("") + rs.getString("nama_barang") + "." + rs.getString("stock");
                String[] arrExploded = explodeStringUsingCoreJava(stringToExplode, ".");
                System.out.println(arrExploded);
//                if (nama_barang.getSelectionModel().isEmpty()) 
//                {
//                    
//                } else {
//                    System.out.println("satuan");
//                    System.out.println(result.getString("satuan"));
////                    ResultSet rr = st.executeQuery("SELECT * FROM barang WHERE id_barang = 'id_barang'");
////                    satuan_nyoba.setText(dataList().getSatuan());
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Select Data 
    public void selectData() {

        StockKeluar data = table_view.getSelectionModel().getSelectedItem();

        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
//        id_barang_masuk.setText(data.getId_barang_masuk());
        //id_barang_keluar = text field
        id_barang_keluar.setText(String.valueOf(data.getId_barang_keluar()));
        kode_barang.setText(data.getKode_barang_keluar());
        stock.setText(data.getStock());
        jumlah_keluar.setText(data.getJumlah_barang_keluar());
        satuan.setText(data.getSatuan());
        tanggal_keluar.setValue(LocalDate.parse(data.getTanggal_barang_keluar()));
        nama_barang.setValue(data.nama_barang);
        pt.setText(data.getPt());

//        public void showData() {
//
//        ObservableList<StockMasuk> showList = dataList();
//       kolum_kode.setCellValueFactory(new PropertyValueFactory<>("kode_barang_masuk"));
//        kolum_barang.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
//        
//       kolum_JumlahBarangMasuk.setCellValueFactory(new PropertyValueFactory<>("jumlah_barang_masuk"));
//        kolum_tanggal_masuk.setCellValueFactory(new PropertyValueFactory<>("tanggal_barang_masuk"));
//        kolum_satuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
//        kolum_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        id_masuk.setCellValueFactory(new PropertyValueFactory<>("id_barang_masuk"));
//        
//       
//        
//        
//
////        gaero
//        
//        table_view.setItems(showList);
//        cari();
//    }
    }

    public void clear() {

        id_barang_keluar.setText("");
        kode_barang.setText("");
        stock.setText("");
        satuan.setText("");
        jumlah_keluar.setText("");
        pt.setText("");

        nama_barang.getSelectionModel().clearSelection();
        cari();

    }
    @FXML
    public void randomKodeBarang(ActionEvent event) {
        kode_barang.setText(createRandomCode(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
    }
    
    public String createRandomCode(int codeLength, String id) {
        List<Character> temp = id.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toList());
        Collections.shuffle(temp, new SecureRandom());
        return temp.stream()
                .map(Object::toString)
                .limit(codeLength)
                .collect(Collectors.joining());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
        studentsInChoiceBox();
        selectData();
        cari();
    }

}
