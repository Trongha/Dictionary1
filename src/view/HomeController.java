package view;

import com.jfoenix.controls.JFXButton;
import data.Group;
import data.Level;
import data.OldWord;
import data.Word;
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
        desktop.setText(dictionary.getText());
        paneView.getChildren().setAll(searchPane);
        paneView.toFront();
        imgInHome.setVisible(false);
    }

    @FXML
    public void setHome(ActionEvent e) throws Exception {
        desktop.setText(home.getText());
        homeView.toFront();
        imgInHome.setVisible(true);
    }

    @FXML
    void setNewWords(ActionEvent event) throws Exception {
        Group newWordsGroup = new Group();
        int num = 0;
        for (Word word : AppManager.getAllGroup().getListWords().values()){
            if (word.getLevel() == Level.nothing){
                newWordsGroup.addWord(word);
                if (++num == 20)
                    break;
            }
        }

        if (num == 0){
            MessageBox.show("Không có từ mới", ":))");
        }else {
            InputIntegerController inpInt = new InputIntegerController();
            int n = inpInt.setAddIntegerWindow(num);
            if (n > 0){
                FlashcardController flashcardController = new FlashcardController();
                flashcardController.show(newWordsGroup, n);
                unNewWords.toFront();
            }
        }
    }

    @FXML
    void setOldWords(ActionEvent event) throws Exception{
        OldWord oldWord = new OldWord();
        oldWord.input();

        InputIntegerController inputInteger = new InputIntegerController();
        int n = inputInteger.setAddIntegerWindow();

        if (n > 0){
            if (OldWord.getSize() < n){
                MessageBox.show("Không có đủ từ cũ :))", "ERROR");
            }else{
                FlashcardController flashcardController = new FlashcardController();
                flashcardController.show(oldWord.getGroupOldestWord(5), n);
                unOldWords.toFront();
            }
        }

    }

    @FXML
    public void setWordManager(ActionEvent e) throws Exception {
        desktop.setText(wordManager.getText());
        paneView.getChildren().setAll(managePane);
        paneView.toFront();
        imgInHome.setVisible(false);
    }

    @FXML
    void setLearn(ActionEvent event) throws Exception {
        desktop.setText(learn.getText());
        paneView.getChildren().setAll(studyPane);
        paneView.toFront();
        imgInHome.setVisible(false);
    }

    @FXML
    void setStatistical(ActionEvent event) throws Exception {
        desktop.setText(statistical.getText());
        Parent statisticalPaneLocal = new FXMLLoader(getClass().getResource("fxml/Statistical.fxml")).load();
        paneView.getChildren().setAll(statisticalPaneLocal);
        paneView.toFront();
        imgInHome.setVisible(false);
    }

    @FXML
    void exit(ActionEvent event) {
        Boolean save = ConfirmationBox.showConfirmation("\n Lưu trước khi thoát ko?", "", "Yes", "No");
        if (save){
            System.out.println("Save");
            manager.OutFile();
        }else {
            System.out.println("Don't save");
        }
        GUI.myPrimaryStage.close();
    }

    @FXML
    private void initialize() {
        loadParent();
        imgInHome.setImage(new Image((new File(Text.getPaths().get("imgInHome"))).toURI().toString()));

        homeView.toFront();
    }
}
