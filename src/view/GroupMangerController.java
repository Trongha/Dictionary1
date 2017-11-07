package view;

import data.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.AppManager;
import javafx.event.ActionEvent;
import manager.Learning;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GroupMangerController {
    private AppManager manager = new AppManager();
    @FXML
    private ListView<String> listGroups;
    ObservableList<String> listGroupName = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listWords;
    ObservableList<String> listWordData = FXCollections.observableArrayList();
    @FXML
    private Button editGroup;
    @FXML
    private Button deleteGroup;
    @FXML
    private Button show;
    @FXML
    private Button addGroup;
    @FXML
    private Button learn;


    public void setListGroups(){
        listGroupName.clear();
        for (Group group : AppManager.getGroups()){
            listGroupName.add(group.getName());
        }
        listGroups.setItems(listGroupName);
        System.out.println("Set List Group Complete!");
    }

    public void setListWords(Group group){
        listWordData.clear();
        listWordData.addAll(group.getKeyOfGroup());
        listWords.setItems(listWordData);
        System.out.println("Set List Word Complete!");
    }

    public void addGroupButton(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null){
            for(File file:selectedFiles){
               manager.addGroup(new Group(file.getPath()));
            }
        setListGroups();
        } else {
            System.out.println("File not found!");
        }

    }

    public void moveLearn(ActionEvent e) throws Exception{
        Stage abc = new Stage();
        Parent root = new FXMLLoader(getClass().getResource("Learning.fxml")).load();
        abc.setTitle("Hello World");
        abc.setScene(new Scene(root, 600, 275));
        abc.show();
    }

    @FXML
    private void initialize(){
               setListGroups();
        listGroups.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setListWords(manager.getGroup(newValue));
            Learning learn = new Learning(manager.getGroup(newValue).getListWords());
            editGroup.setDisable(false);
            deleteGroup.setDisable(false);
            this.learn.setDisable(false);
        });

    }

}
