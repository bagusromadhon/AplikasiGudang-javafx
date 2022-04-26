/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import app.controller.koneksidatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import app.controller.session;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button btn_login;

    @FXML
    private Label alert_pass;

    @FXML
    private Label alert_username;

    @FXML
    void login(MouseEvent event) {

    }
    java.sql.Connection conn = koneksidatabase.koneksi();

    @FXML
    private void btn_login(ActionEvent event) throws SQLException, IOException {
        String query = "SELECT * FROM login WHERE username = ?";

        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username.getText());
        ResultSet result = st.executeQuery();

        if (result.next()) {
            if (password.getText().equals(result.getString("password"))) {
                if ("admin".equals(result.getString("level"))) {
                     Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMASI LOGIN ");
                    alert.setHeaderText(null);
                    alert.setContentText("KAMU  LOGIN SEBAGAI ADMIN");

                    alert.showAndWait();
                    session.setGetId(result.getString("user_id"));
                    session.setGetUsername(result.getString("username"));
                    session.setGetLevel(result.getString("level"));
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/dashboard.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setMaximized(true);
                    stage.setScene(new Scene(root1));
                    stage.show();
                   
                } else {
                    session.setGetId(result.getString("user_id"));
                    session.setGetUsername(result.getString("username"));
                    session.setGetLevel(result.getString("level"));
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/view/kepalagudang.fxml"));
                    
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setMaximized(true);
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
            } else {
                 Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("INFORMASI LOGIN ");
                    alert.setHeaderText(null);
                    alert.setContentText("PASSWORD KAMU SALAH!");

                    alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    
//    double x = 0,y=0; 
//    public void dragged(MouseEvent mouseEvent){
//    Node node = (Node) mouseEvent.getSource();
//    Stage stage = (Stage) node.getScene().getWindow();
//    stage.setX(mouseEvent.getScreenX() - x);
//    stage.setY(mouseEvent.getScreenY()- y);
//    }
//    
//    public void pressed(MouseEvent mouseEvent){
//    x=mouseEvent.getSceneX();
//    y=mouseEvent.getSceneY();
//    }
//    public void login(MouseEvent mouseEvent) throws IOException{
//    Parent root = FXMLLoader.load(getClass().getResource("/app/view/dashboard.fxml"));
//    Node node = (Node) mouseEvent.getSource();
//    Stage stage = (Stage) node.getScene().getWindow();
//    stage.setScene(new Scene(root));
//    stage.centerOnScreen();
//    
//    }
}
