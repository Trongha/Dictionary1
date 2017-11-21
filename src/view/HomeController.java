package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import manager.AppManager;

import java.io.IOException;

public class HomeController {

    @FXML
    private static VBox menuPane;

    @FXML
    private JFXButton home, dictionary, wordManager, learn, statistical, exit;

    @FXML
    private AnchorPane homeView;

    @FXML
    private AnchorPane paneView;

    private Parent searchPane;
    private Parent managePane;
    private Parent studyPane;
    private Parent statisticalPane;

    private AppManager manager = new AppManager();


    public void loadParent() {
        try {
            searchPane = new FXMLLoader(getClass().getResource("fxml/Search.fxml")).load();
            managePane = new FXMLLoader(getClass().getResource("fxml/GroupManager.fxml")).load();
            studyPane = new FXMLLoader(getClass().getResource("fxml/Study.fxml")).load();
            statisticalPane = new FXMLLoader(getClass().getResource("fxml/Statistical.fxml")).load();
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
    void setStatistical(ActionEvent event) {
        paneView.getChildren().setAll(statisticalPane);
        paneView.toFront();
    }

    @FXML
    void exit(ActionEvent event) {
        Boolean save = ConfirmationBox.showConfirmation("\n Lưu trước khi thoát ko?", "", "Yes", "No");
        if (save){
            System.out.println("Save");
            manager.groupOutFile();
        }else {
            System.out.println("Don't save");
        }
        GUI.myPrimaryStage.close();
    }

    public VBox getMenuPane() {
        return menuPane;
    }

    public static void setMenuPaneDisable(boolean disable) {
        menuPane.setDisable(disable);
    }

    @FXML
    private void initialize() {
        loadParent();
        homeView.toFront();
    }
}
