package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class HomeController {
    @FXML
    private JFXButton home;

    @FXML
    private JFXButton dictionary;

    @FXML
    private JFXButton wordManager;

    @FXML
    private JFXButton learn;

    @FXML
    private JFXButton statistical;

    @FXML
    private JFXButton exit;

    @FXML
    private AnchorPane paneView;

    @FXML
    private AnchorPane homeView;



    public void setDictionary(ActionEvent e) throws Exception{
        Parent root = new FXMLLoader(getClass().getResource("fxml/Search.fxml")).load();
        paneView.getChildren().setAll(root);
        paneView.toFront();
    }

    public void setHome(ActionEvent e) throws Exception {
        homeView.toFront();
        homeView.setVisible(true);
    }

    public void setWordManager(ActionEvent e) throws Exception {
        Parent root = new FXMLLoader(getClass().getResource("fxml/GroupManager.fxml")).load();
        paneView.getChildren().setAll(root);
    }

    private void initialize(){


    }

}
