/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.StockMasuk;
import app.controller.koneksidatabase;
import static app.controller.koneksidatabase.koneksi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;
import app.controller.NamaBarang;
import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class StockmasukController implements Initializable {

    @FXML
    private TextField catat;
    @FXML
    private TextField id_barang_masuk;
    @FXML
    private Button ubah;
    @FXML
    private TextField cari;

    @FXML
    private Button insert;

    @FXML
    private TableView<?> table_barang;
    @FXML
    private TableColumn<?, ?> id_barang_tabel;
    @FXML
    private TableColumn<?, ?> nama_barang_tabel;

    @FXML
    private TableColumn<?, ?> satuan_barang_tabel;

    @FXML
    private TableColumn<?, ?> stock_barang_tabel;

    @FXML
    private ComboBox<String> nama_barang;
    @FXML
    private TableColumn<StockMasuk, String> kolum_JumlahBarangMasuk;
    @FXML
    private TableColumn<StockMasuk, String> catatan_masuk;
    @FXML
    private TableColumn<StockMasuk, String> kolum_barang;

    @FXML
    private TableColumn<StockMasuk, String> kolum_kode;

    @FXML
    private TableColumn<StockMasuk, String> kolum_satuan;

    @FXML
    private TableColumn<StockMasuk, String> kolum_stock;

    @FXML
    private TableColumn<StockMasuk, Integer> id_masuk;

    @FXML
    private TableColumn<StockMasuk, String> kolum_tanggal_masuk;
      @FXML
    private TableColumn<StockMasuk, String> kolum_pt;

    @FXML
    private TableView<StockMasuk> table_view;

    @FXML
    private TextField satuan;

    @FXML
    private TextField stock;
     @FXML
    private TextField pt;

    @FXML
    private DatePicker tanggal_masuk;

    @FXML
    private TextField jumlah_masuk;

    @FXML
    private TextField kode_barang;
    @FXML
    private TextField njajal_total;
    @FXML
    private Button hapus;
    @FXML
    private TextField total_asli;

    @FXML
    void njajal(ActionEvent event) {
    }
    @FXML
    void total_harga(ActionEvent event){
    connect = connectDb();
    String total_barang_asli =  stock.getText();
    String total_barang_masuk_asli = jumlah_masuk.getText();
    int total_barang_asli1 = Integer.parseInt(total_barang_asli);
    int total_barang_masuk_asli1 = Integer.parseInt(total_barang_masuk_asli);
    int penambahan = total_barang_asli1 + total_barang_masuk_asli1;
    
     total_asli.setText(String.valueOf(penambahan));
     
    
    }

    @FXML
    public void cetaksu(ActionEvent event) {
        try {
            HashMap parameter = new HashMap();
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///gudangalami", "root", "");
            File file = new File("src/report/masuk.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {

        }
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
    void pindah_stockkeluar(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/StockKeluar.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setMaximized(true);
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
    java.sql.Connection conn = koneksidatabase.koneksi();

    public ObservableList<StockMasuk> dataList() {
        connect = connectDb();

        ObservableList<StockMasuk> dataList = FXCollections.observableArrayList();

        String sql = "SELECT id_barang_masuk,kode_barang_masuk,nama_barang,barang.stock,jumlah_barang_masuk,tanggal_barang_masuk,satuan,pt,barang_masuk.id_barang FROM barang_masuk JOIN barang on barang_masuk.id_barang = barang.id_barang";
        try {

            Statement st;
            st = connect.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {

                dataList.add(new StockMasuk(
                        result.getInt("id_barang_masuk"),
                        result.getString("kode_barang_masuk"),
                        result.getString("nama_barang"),
                        result.getString("id_barang"),
                        result.getString("tanggal_barang_masuk"),
                        result.getString("jumlah_barang_masuk"),
                        result.getString("satuan"),
                        result.getString("stock"),
                        result.getString("pt")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
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

    public static String[] explodeStringUsingCoreJava(String stringToExplode, String separator) {
        return stringToExplode.split(separator);
    }

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
    ObservableList<StockMasuk> dataList;

    @FXML
    void cari() {

        kolum_kode.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("kode_barang_masuk"));
        kolum_barang.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("nama_barang"));
        kolum_JumlahBarangMasuk.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("jumlah_barang_masuk"));
        kolum_tanggal_masuk.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("tanggal_barang_masuk"));
        kolum_satuan.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("satuan"));
        kolum_stock.setCellValueFactory(new PropertyValueFactory<StockMasuk, String>("stock"));

        dataList = dataList();
        table_view.setItems(dataList);
        System.out.println(table_view);
        FilteredList<StockMasuk> filteredData = new FilteredList<>(dataList, b -> true);

        cari.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(StockMasuk -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (StockMasuk.getNama_barang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                }
                if (StockMasuk.getPt().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<StockMasuk> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());

        table_view.setItems(sortedData);

    }

    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE from barang_masuk WHERE `id_barang_masuk` = '" + id_barang_masuk.getText() + "'";
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
    }

    public void delete() {

    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        Connection conn = koneksidatabase.koneksi();
        Statement st;
        String kode_masuk = kode_barang.getText();
        String perusahaan = pt.getText();
        String dateStr = tanggal_masuk.getValue().toString();
        String jumlah = jumlah_masuk.getText();
        String id_masuk = id_barang_masuk.getText();
        System.out.println("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("Data Berhasil Di Ubah!");

        alert.showAndWait();

        String query = "UPDATE barang_masuk SET kode_barang_masuk = '" + kode_masuk + "',tanggal_barang_masuk='" + dateStr + "',jumlah_barang_masuk='" + jumlah + "',pt='"+perusahaan+"' WHERE id_barang_masuk= '" + id_masuk + "'";

        try {

            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cari();
        clear();
    }

    @FXML
    private void simpan(ActionEvent event) throws SQLException {
        Connection conn = connectDb();
        Statement st;
        st = connect.createStatement();
//         System.out.println(result.getString(""));

        String student = nama_barang.getValue();
        String student_id = student.substring(student.indexOf("(") + 1, student.indexOf(")"));
        String kodebarang = kode_barang.getText();
        String dateStr = tanggal_masuk.getValue().toString();
        String jumlahmasuk = jumlah_masuk.getText();
        String total_asli1 = total_asli.getText();
        String perusahaan = pt.getText();
//        String stock_barang_tekotabel =result.getString("stock");
//        String stock_total=stock_barang_tekotabel + jumlahmasuk;
        String query = "INSERT INTO barang_masuk(kode_barang_masuk,id_barang,tanggal_barang_masuk,jumlah_barang_masuk,pt) VALUES('" + kodebarang + "','" + student_id + "','" + dateStr + "','" + jumlahmasuk + "','"+perusahaan+"')";
//        String query1 = "INSERT INTO barang(stock) VALUES('" +stock_total+"')";

        st.executeUpdate(query);
//                System.out.println(result.getString("stock"));
        try {

            st = conn.createStatement();

            String querynjopok = "SELECT * FROM barang";
            ResultSet rs = st.executeQuery(querynjopok);

            while (rs.next()) {

//                String stock_total = rs.getString("stock");
                String Stock = rs.getString("stock");
                int stok_barang = Integer.parseInt(Stock);
                int jumlah_masuk = Integer.parseInt(jumlahmasuk);
                int total_asli2 = Integer.parseInt(total_asli1);
                int total = total_asli2 + Integer.parseInt(jumlahmasuk);
//                String stock_barang =  rs.getString("stock");
//                String stock_total = stock_barang+jumlahmasuk;
//                int nyoba = integer.parseInt(stock_total);
//                String query2 = "UPDATE barang_masuk SET jumlah_stock = '" + total + "' WHERE kode_barang = '" + kodebarang + "'";
                String query1 = "UPDATE barang SET stock = '" + total_asli1 + "' WHERE id_barang = '" + student_id + "'";

//                String query2="UPDATE barang_masuk SET = '"+total+"' WHERE jumlah_stock = '"+student_id+"'";
//                st = conn.createStatement();
//                ResultSet rsp = st.executeQuery(query2);
//                
//               st.executeUpdate(query2);
//                
                st.executeUpdate(query1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information ");
                alert.setHeaderText(null);
                alert.setContentText("Data Berhasil Di simpan!");

                alert.showAndWait();
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/stockmasuk.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }
//            st.executeUpdate(query1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showData();
        clear();

    }
//     public ObservableList<StockMasuk> dataList() {
//        connect = connectDb();
//
//        ObservableList<StockMasuk> dataList = FXCollections.observableArrayList();
//
//        String sql = "SELECT kode_barang_Masuk,nama_barang,barang.stock,jumlah_barang_masuk,tanggal_barang_masuk,satuan,barang_masuk.id_barang FROM barang_masuk JOIN barang on barang_masuk.id_barang = barang.id_barang";
//        try {
//// maeng isok di tampil tapi tampil e gak sesuai iki
//            Statement st;
//            st = connect.createStatement();
//            result = st.executeQuery(sql);
//
////           stok e disout kenek tapi dek tabel e gakenek tapi wes urut
//            while (result.next()) {
//                System.out.println(result.getString("stock"));
//                dataList.add(new StockMasuk(result.getString("kode_barang_masuk"), result.getString("nama_barang"), result.getString("stock"), result.getString("jumlah_barang_masuk"), result.getString("tanggal_barang_masuk"), result.getString("satuan"), result.getString("id_barang")));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataList;
//    }

//    private void simpan(ActionEvent event) throws SQLException, IOException {
//        connect = connectDb();
//        String query = "INSERT INTO barang_masuk (kode_barang_masuk,id_barang,tanggal_barang_masuk,jumlah_barang_masuk) VALUES(?,?,?,?)";
//
//        prepare = connect.prepareStatement(query);
//        prepare.setString(1, kode_barang.getText());
//          prepare.setString(2, (String)nama_barang.getSelectionModel().getSelectedItem()    );
//        prepare.setDate(3, java.sql.Date.valueOf(tanggal_masuk.getValue()));
//        prepare.setString(4, jumlah_masuk.getText());
////        prepare.setString(5, nama_barang.getValue(););
////        
//        
//        
////        var student = choice_name.getValue();
////        var student_id = student.substring(student.indexOf("(")+1, student.indexOf(")"));
//        
//
//
//        prepare.executeUpdate(query);
//
//        showData();
////        clear();
//
//    }
    public void showData() {

        ObservableList<StockMasuk> showList = dataList();
        kolum_kode.setCellValueFactory(new PropertyValueFactory<>("kode_barang_masuk"));
        kolum_barang.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
        kolum_JumlahBarangMasuk.setCellValueFactory(new PropertyValueFactory<>("jumlah_barang_masuk"));
        kolum_tanggal_masuk.setCellValueFactory(new PropertyValueFactory<>("tanggal_barang_masuk"));
        kolum_satuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        kolum_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        id_masuk.setCellValueFactory(new PropertyValueFactory<>("id_barang_masuk"));
        kolum_pt.setCellValueFactory(new PropertyValueFactory<>("pt"));

//        gaero
        table_view.setItems(showList);
        cari();
    }

    public void selectData() {

        StockMasuk data = table_view.getSelectionModel().getSelectedItem();
//        NamaBarang databarang = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
//        id_barang_masuk.setText(data.getId_barang_masuk());
        id_barang_masuk.setText(String.valueOf(data.getId_barang_masuk()));
        kode_barang.setText(data.getKode_barang_masuk());
        stock.setText(data.getStock());
        jumlah_masuk.setText(data.getJumlah_barang_masuk());
        satuan.setText(data.getSatuan());
        tanggal_masuk.setValue(LocalDate.parse(data.getTanggal_barang_masuk()));
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

//   public void update()  throws SQLException {
//        connect = connectDb();
////`id_barang` = '"+ satuan.getSelectionModel().getSelectedItem()+"'
//        String sql = "UPDATE barang_masuk SET `kode_barang_masuk` = '"
//                + kode_barang.getText() + "', `stock` = '"
//                + stock.getText()+"' WHERE id_barang = '" + id.getText()+"'";
//        
//            statement = connect.createStatement();
//            statement.executeUpdate(sql);
//            
//          
//        try{
//            if (id.getText().isEmpty() 
//                | nama.getText().isEmpty() 
//                | satuan.getSelectionModel().isEmpty()
//                | stock.getText().isEmpty()) {
//                
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                
//                alert.setTitle("Eror Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Enter all blank fields");
//                alert.showAndWait();
//                
//            }else{
//                
//             statement = connect.createStatement();
//             statement.executeUpdate(sql);
//             
//                
//                
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            
//            alert.setTitle("Marcoman Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Succesfuly update data");
//            alert.showAndWait();
//            
//            showData(); 
//            }
//            
//        }catch(Exception e){
//           
//        }                 
//    }
    public void hapusButton() {

    }

    /**
     * Initializes the controller class.
     */
    public void BarangKeluar(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/StockKeluar.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    public void laporan(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/StockKeluar.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }
//    
//    
//    private void populateCategories() {
//        java.sql.Connection conn = koneksidatabase.koneksi();
//        Statement st;
//        connect = connectDb();
//        ObservableList<String> list = FXCollections.observableArrayList();
//
//        try {
//            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM barang");
//            while (rs.next()) {
//                list.add(rs.getString("nama_barang"));
//            }
//        } catch (Exception ex) {
//
//        }
//
//        nama_barang1.setItems(null);
//        nama_barang1.setItems(list);
//
//        //To change body of generated methods, choose Tools | Templates.
//    }

    public void clear() {

        id_barang_masuk.setText("");
        kode_barang.setText("");
        stock.setText("");
        satuan.setText("");
        jumlah_masuk.setText("");
        pt.setText("");

//        tanggal_masuk.setValue(LocalDate.parse(""));
        nama_barang.getSelectionModel().clearSelection();

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

    @FXML
    public void randomKodeBarang(ActionEvent event) {
        kode_barang.setText(createRandomCode(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cari();
        showData();
        studentsInChoiceBox();
    }
}
