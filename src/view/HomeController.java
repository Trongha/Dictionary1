package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

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

    private Parent searchPane;
    private Parent managePane;
    private Parent studyPane;


    public void loadParent() {
        try {
            searchPane = new FXMLLoader(getClass().getResource("fxml/Search.fxml")).load();
            managePane = new FXMLLoader(getClass().getResource("fxml/GroupManager.fxml")).load();
            studyPane = new FXMLLoader(getClass().getResource("fxml/Study.fxml")).load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void setDictionary(ActionEvent e) throws Exception {
        paneView.getChildren().setAll(searchPane);
        paneView.toFront();
    }

    @FXML
    public void setHome(ActionEvent e) throws Exception {
        homeView.toFront();
    }

    @FXML
    public void setWordManager(ActionEvent e) throws Exception {
        paneView.getChildren().setAll(managePane);
        paneView.toFront();
    }

    @FXML
    void setLearn(ActionEvent event) throws Exception {
        paneView.getChildren().setAll(studyPane);
        paneView.toFront();
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
        loadParent();
        homeView.toFront();
    }
}
