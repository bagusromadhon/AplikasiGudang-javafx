 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.controller.koneksidatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class TugaAkhir extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        stage.setTitle("APP");
        Scene scene = new Scene(root);
        


        stage.setScene(scene);
       stage.setMaximized(true);
        stage.centerOnScreen();

        stage.show();
    }
//opo bi  ndek total
    /**
     * @param args the command line ar
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
