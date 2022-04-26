/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import app.controller.koneksidatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import app.controller.ManagaAdmin;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */

public class ManageadminController implements Initializable {
   @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private TextField id_admin;
    
    @FXML
    private TableView<ManagaAdmin> table_view;

    @FXML
    private TableColumn<ManagaAdmin, Integer> kolum_id;

    @FXML
    private TableColumn<ManagaAdmin, String> kolum_level;

    @FXML
    private TableColumn<ManagaAdmin, String> kolum_password;

    @FXML
    private TableColumn<ManagaAdmin, String> kolum_username;
     @FXML
    private TableColumn<ManagaAdmin, String> kolum_email;

    @FXML
    private PasswordField password;

    @FXML
    private Button simpan;

    @FXML
    private Button update;

    @FXML
    private TextField username;
    
     @FXML
    private TextField email;
      @FXML
    private TextField level;
        @FXML
    private ComboBox<?> level1;

   public void dashboardButton(){
   }
   
      private String[] comboGender = {"admin", "Kepala Gudang"};
    
    public void comboBox(){
        
        List<String> list = new ArrayList<>();
        
        for(String data: comboGender){
            
            list.add(data);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list);
        
        level1.setItems(dataList);
        
    }
   
@FXML
    void laporan(ActionEvent event) {

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
    void pindah_home(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/kepalagudang.fxml"));
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
    void keluar(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/login.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    void BarangKeluar(){
    
    }
    

    @FXML
    void delete(ActionEvent event) {
String sql = "DELETE from login WHERE `user_id` = '" + id_admin.getText()+ "'";
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
//        cari();
    }

    @FXML
    void s(ActionEvent event) {

    }

    @FXML
    void simpan(ActionEvent event) throws SQLException {
        Connection conn = connectDb();
        Statement st;
        st=connect.createStatement();
//         System.out.println(result.getString(""));
        String id =id_admin.getText() ;
        String username_boy = username.getText();
        String  password_boy= password.getText();
        String  email_boy= email.getText();
        String  level_boy= level.getText();
        String level_s = (String) level1.getSelectionModel().getSelectedItem();
        
//        String stock_barang_tekotabel =result.getString("stock");
//        String stock_total=stock_barang_tekotabel + jumlahmasuk;
        String query = "INSERT INTO login(username,password,email,level) VALUES('" + username_boy + "','" + password_boy + "','" + email_boy + "','"+level_s+"')";
//        String query1 = "INSERT INTO barang(stock) VALUES('" +stock_total+"')";

        st.executeUpdate(query);
//                System.out.println(result.getString("stock"));
        try {
            
           
          
            
//            st.executeUpdate(query1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showData();
        clear();
//        cari();

    }

    @FXML
    void update(ActionEvent event) {
   Connection conn = koneksidatabase.koneksi();
        Statement st;
              String username_update = username.getText();
        String password_update = password.getText();
        String email_update = email.getText();
        String level_update = level.getText();
        String id_update = id_admin.getText();
        String level_ = (String) level1.getSelectionModel().getSelectedItem();
       
        
       
        String query = "UPDATE login SET username = '"+username_update+"',password='"+password_update+"',email='"+email_update+"',level='"+level_+"' WHERE user_id= '"+id_update+"'" ;
                                                           
        try {
            
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
//       cari();
       clear();
       showData();
//       cari();
    }
    /**
     * Initializes the controller class.
     */
    
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

     public ObservableList<ManagaAdmin> dataList() {
        connect = connectDb();

        ObservableList<ManagaAdmin> dataList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM login";
        try {

            Statement st;
            st = connect.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {
                
                dataList.add(new ManagaAdmin(
                        result.getInt("user_id"),
                        result.getString("username"), 
                        result.getString("password"), 
                        result.getString("email"), 
                        result.getString("level")));
                        
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
        
    }
     
        public void showData() {

        ObservableList<ManagaAdmin> showList = dataList();
        kolum_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        kolum_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        kolum_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        kolum_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        kolum_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        
        table_view.setItems(showList);
//        cari();
    }
     
        
        // select data
             public void selectData() {

        ManagaAdmin data = table_view.getSelectionModel().getSelectedItem();

        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
//        id_barang_masuk.setText(data.getId_barang_masuk());
        //id_barang_keluar = text field
        id_admin.setText(String.valueOf(data.getUser_id()));
        username.setText(data.getUsername());
        password.setText(data.getPassword());
        email.setText(data.getEmail());
        level.setText(data.getLevel());
    }
             
              public void clear() {

        username.setText("");
        password.setText("");
        level.setText("");
        email.setText("");
        
      
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox();
        showData();
        selectData();
    }    
    
}
