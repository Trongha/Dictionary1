
package view;


import com.jfoenix.controls.JFXButton;
import data.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import manager.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by Trong on 2/11/2017.
 */
public class
SearchController {
    private AppManager manager = new AppManager();
    private GUI gui = new GUI();

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addWord;

    @FXML
    private JFXButton editWord;

    @FXML
    private JFXButton deleteWord;

    @FXML
    private TextField input;

    @FXML
    private TextArea output2;

    @FXML
    private ImageView image;


    @FXML
    private Label outputE;


    @FXML
    private Label outVN;

    @FXML
    private ListView<String> listWord;
    private ObservableList<String> listWordData = FXCollections.observableArrayList();
    public static Parent searchPane;
    public SearchController() {    }

    public static Parent getSearchPane() {
        return searchPane;
    }



    public void reOutput2(Word word){
        outputE.setText(word.getEnglish());
        outVN.setText(word.getVietNam());
        if (AppManager.getAllGroup().getListWords().containsKey(word.getEnglish())){
            deleteWord.setDisable(false);
            editWord.setDisable(false);
        }else {
            deleteWord.setDisable(true);
            editWord.setDisable(true);
        }
        File f = new File(word.getPathImage());
        image.setImage(new Image(f.toURI().toString()));
    }
//Search chính xác 1 từ
    public void search(ActionEvent e) {
        if(!input.getText().trim().equals("") && input.getText()!=null){
            Word word = manager.search(input.getText());
            String s = String.format("%s", word.toString());
            reOutput2(word);
        }
       /*
        System.out.println(s);*/
    }

    public void back(ActionEvent e){
        gui.backHome();
    }

    public void search2() {
        listWordData.clear();
        ArrayList<String> wordEnglishs = manager.search2(input.getText());
        listWordData.addAll(wordEnglishs);
        listWord.setItems(listWordData);
    }
    public void search2(ActionEvent e)
    {
            search2();
    }

    public void moveAddWord(ActionEvent e) throws Exception{
        AddWordController addWord = new AddWordController();
        addWord.show();
        search2();
        search(e);
    }
    @FXML
    void setDeleteWord(ActionEvent event) {

    }

    @FXML
    void setEditWord(ActionEvent event) {

    }
    @FXML
    private void initialize(){
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            search2();
        });
        listWord.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            reOutput2(manager.search(newValue));
        });
    }
}
