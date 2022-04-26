/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class titlebarcontroller implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    public void min (MouseEvent mouseEvent){
    Node node = (Node) mouseEvent.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.setIconified(true);
    }
    
    public void max (MouseEvent mouseEvent){
    Node node = (Node) mouseEvent.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.setFullScreen(true);
    }
    
    public void close (MouseEvent mouseEvent){
    Node node = (Node) mouseEvent.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.close();
    }
}
