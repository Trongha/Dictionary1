
package view;


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
import java.util.*;


/**
 * Created by Trong on 2/11/2017.
 */
public class
SearchController {
    private AppManager manager = new AppManager();
    private GUI gui = new GUI();

    @FXML
    private Button searchButton;

    @FXML
    private Button back;

    @FXML
    private Button addWord;

    @FXML
    private TextField input;

    @FXML
    private TextArea output2;

    @FXML
    private ImageView image;

    @FXML
    private ListView<String> listWord;
    private ObservableList<String> listWordData = FXCollections.observableArrayList();


    public SearchController() {    }

    public void reOutput2(Word word){
        output2.clear();
        output2.appendText(word.toString());
        File f = new File(word.getPathImage());
        image.setImage(new Image(f.toURI().toString()));
    }


    public void search(ActionEvent e) {
        Word word = manager.search(input.getText());
        String s = String.format("%s", word.toString());
        reOutput2(word);/*
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
    public void search2(ActionEvent e) {
        search2();
    }

    public void moveAddWord(ActionEvent e) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = new FXMLLoader(getClass().getResource("fxml/AddWord.fxml")).load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
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
