package view;

import com.jfoenix.controls.JFXButton;
import data.OldWord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import manager.AppManager;
import view.TextOutput.Text;

import java.io.File;
import java.io.IOException;

public class HomeController {

    @FXML
    private static VBox menuPane;

    @FXML
    private JFXButton home, dictionary, wordManager, learn, statistical, exit;

    @FXML
    private Label desktop;

    @FXML
    private AnchorPane homeView;

    @FXML
    private AnchorPane paneView;

    @FXML
    private ImageView imgInHome;

    @FXML
    private HBox unOldWords;

    @FXML
    private JFXButton oldWords;

    @FXML
    private HBox unNewWords;

    @FXML
    private JFXButton newWords;

    private Parent searchPane;
    private Parent managePane;
    private Parent studyPane;
    private StudyController studyController = new StudyController();
    private Parent statisticalPane;

    private AppManager manager = new AppManager();


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
        desktop.setText("Dictionary");
        paneView.getChildren().setAll(searchPane);
        paneView.toFront();

    }

    @FXML
    public void setHome(ActionEvent e) throws Exception {
        desktop.setText("Home");
        homeView.toFront();
    }

    @FXML
    void setNewWords(ActionEvent event) {

        unNewWords.toFront();
    }

    @FXML
    void setOldWords(ActionEvent event) throws Exception{
        OldWord oldWord = new OldWord();
        oldWord.input();

        FlashcardController flashcardController = new FlashcardController();
        InputIntegerController inputInteger = new InputIntegerController();

        int n = inputInteger.setAddIntegerWindow();
        if (n > 0){
            if (OldWord.getSize() < n){
                MessageBox.show("Không có đủ từ cũ :))", "ERROR");
            }else{
                flashcardController.show(oldWord.getGroupOldestWord(5), 5);
                unOldWords.toFront();
            }
        }

    }

    @FXML
    public void setWordManager(ActionEvent e) throws Exception {
        desktop.setText("Words Manager");
        paneView.getChildren().setAll(managePane);
        paneView.toFront();
    }

    @FXML
    void setLearn(ActionEvent event) throws Exception {
        desktop.setText("Study");
        paneView.getChildren().setAll(studyPane);
        paneView.toFront();
    }

    @FXML
    void setStatistical(ActionEvent event) throws Exception {
        desktop.setText("Statistical");
        statisticalPane = new FXMLLoader(getClass().getResource("fxml/Statistical.fxml")).load();
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
        imgInHome.setImage(new Image((new File(Text.getPaths().get("imgInHome"))).toURI().toString()));

        homeView.toFront();
    }
}
