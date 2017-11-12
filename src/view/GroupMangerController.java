package view;

import com.jfoenix.controls.JFXButton;
import data.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.AppManager;
import javafx.event.ActionEvent;
import manager.Learning;
import org.apache.poi.util.Beta;


import java.io.File;
import java.util.List;

public class GroupMangerController {
    private AppManager manager = new AppManager();
    private GUI gui = new GUI();

    @FXML
    private ListView<String> listviewGroups;
    //Danh sách tất cả group đang chạy
    ObservableList<String> listGroupName = FXCollections.observableArrayList();
    ObservableList<String> listGroupSelected = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listWords;
    ObservableList<String> listWordData = FXCollections.observableArrayList();
    @FXML
    private JFXButton addGroupFX;

    @FXML
    private JFXButton editGroupFX;

    @FXML
    private JFXButton deleteGroupFX;
    @FXML
    private Label groupName;

    @FXML
    private JFXButton addWord;

    @FXML
    private JFXButton editWord;

    @FXML
    private JFXButton deleteWord;

    @FXML
    private Button learn;
    @FXML
    private Button back;


    public void setListGroups(){
        listGroupName.clear();
        for (Group group : AppManager.getGroups()){
            listGroupName.add(group.getName());
        }
        listviewGroups.setItems(listGroupName);
        System.out.println("Set List Group Complete!");
    }

    public void setListWords(Group group){
        groupName.setText(group.getName());
        listWordData.clear();
        listWordData.addAll(group.getKeyOfGroup());
        listWords.setItems(listWordData);
        System.out.println("Set List Word Complete!");
    }
    public void deleteGroup(){

    }
    public void addGroupButton(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("E:\\java\\Dictionary\\src\\resrc\\xlsx\\"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null){
            for(File file:selectedFiles){
                Group newGroup = new Group(file.getPath());
                manager.addGroup(newGroup);
                MessageBox.show( "Đã thêm " + newGroup.getName(), "Thêm nhóm từ");
            }

        setListGroups();
        } else {
            MessageBox.show("ERROR", "Thêm nhóm từ");
            System.out.println("File not found!");
        }
    }

    public void moveLearn(ActionEvent e) throws Exception{
        Stage abc = new Stage();
        Parent root = new FXMLLoader(getClass().getResource("fxml/Learning.fxml")).load();
        abc.setTitle("Hello World");
        abc.setScene(new Scene(root));
        abc.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

    }

    public void back(ActionEvent e){
        gui.backHome();
    }
    @FXML
    void setAddWord(ActionEvent event) {

    }

    @FXML
    void setDeleteWord(ActionEvent event) {

    }

    @FXML
    void setEditWord(ActionEvent event) {

    }

    @FXML
    private void initialize(){
           setListGroups();
        listviewGroups.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listviewGroups.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            listGroupSelected = listviewGroups.getSelectionModel().getSelectedItems();
            if (listGroupSelected.size() == 1){
                setListWords(manager.getGroup(newValue));
                Learning learn = new Learning(manager.getGroup(newValue).getListWords());
                this.learn.setDisable(false);
            }

        });
    }

}
