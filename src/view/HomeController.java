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

import java.io.IOException;

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
    private AnchorPane homeView;

    @FXML
    private AnchorPane paneView;


    public void setPaneView(String path) {
        try {
            Parent root = new FXMLLoader(getClass().getResource(path)).load();
            paneView.getChildren().setAll(root);
            paneView.toFront();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    @FXML
    public void setDictionary(ActionEvent e) throws Exception {
        this.setPaneView("fxml/Search.fxml");
    }

    @FXML
    public void setHome(ActionEvent e) throws Exception {
        homeView.toFront();
    }

    @FXML
    public void setWordManager(ActionEvent e) throws Exception {
        this.setPaneView("fxml/GroupManager.fxml");
    }

    @FXML
    void setLearn(ActionEvent event) throws Exception {
        this.setPaneView("fxml/Learning.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
        /*Boolean save = ConfirmationBox.show("\n Lưu trước khi thoát ko?", "", "Yes", "No");
        if (save){
            System.out.println("Save");
        }else {
            System.out.println("Don't save");
        }*/
        GUI.myPrimaryStage.close();
    }

    @FXML
    private void initialize() {
        homeView.toFront();
    }
}
