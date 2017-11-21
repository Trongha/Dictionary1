package view;

import com.jfoenix.controls.JFXButton;
import data.Group;
import data.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.AppManager;
import javafx.event.ActionEvent;
import view.TextOutput.Text;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GroupMangerController {
    private AppManager manager = new AppManager();
    private GUI gui = new GUI();
    private static String groupSelecting = "";
    private static Word wordSelecting  = new Word();

    private AddWordController addWordController = new AddWordController();
    private EditGroupNameController editGroupNameController = new EditGroupNameController();


    @FXML
    private ListView<String> listviewGroups;
    //Danh sách tất cả group đang chạy
    ObservableList<String> listGroupName = FXCollections.observableArrayList();
    ObservableList<String> listGroupSelected = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listWords;
    ObservableList<String> listWordData = FXCollections.observableArrayList();


    @FXML
    private JFXButton addGroupFX, editGroupFX, deleteGroupFX, merge;

    @FXML
    private Label groupName;

    @FXML
    private JFXButton addWord, editWord, deleteWord;

    @FXML
    private Button learn;

    @FXML
    private TableView<Word> table;
    ObservableList<Word> listWordOfGroupSelecting = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Word, String> enOutput;

    @FXML
    private TableColumn<Word, String> vnOutput;


    public void setListGroups() {
        listGroupName.clear();
        for (Group group : AppManager.getGroups()) {
            listGroupName.add(group.getName());
        }
        listviewGroups.setItems(listGroupName);
        System.out.println("Set List Group Complete!");
    }

    public void setTable(){
        groupName.setText(groupSelecting);
        listWordOfGroupSelecting.clear();
        System.out.println(groupSelecting);
        ArrayList<Word> wordInGroupSelecting = manager.search2("", groupSelecting);
        listWordOfGroupSelecting.addAll(wordInGroupSelecting);
        table.setItems(listWordOfGroupSelecting);
    }

    public void refresh(){
        setListGroups();
        setTable();
    }

    @FXML
    void setDeleteGroup(ActionEvent event) {
        for (String groupName : listGroupSelected){
            System.out.println(groupName);
            if(ConfirmationBox.showConfirmation(String.format("Xóa %s", groupName), "Delete", "Yes", "No")){
                manager.deleteGroup(groupName);
            }
        }
        refresh();
    }

    @FXML
    void setMerge(ActionEvent event) {
        Group newGroup = new Group();
        System.out.println("merge");
        String newName = "";
        try{
            newName = editGroupNameController.setEditNameGroupWindow("");
        }catch (Exception e){
            e.printStackTrace();
        }
        if (newName!=null && !newName.trim().equals("")){
            String[] allGroupSelecting = new String[listGroupSelected.size()];
            int i =0;
            for (String nameGroup : listGroupSelected){
                allGroupSelecting[i++] = nameGroup;
            }

            manager.mergeGroups(allGroupSelecting, newName);
        }


        refresh();
    }

    public void addGroupButton(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\data\\dataFile\\xlsx"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                Group newGroup = new Group(file.getPath());
                manager.addGroup(newGroup);
                MessageBox.show("Đã thêm " + newGroup.getName(), "Thêm nhóm từ");
            }

            setListGroups();
        } else {
            MessageBox.show("ERROR", "Thêm nhóm từ");
            System.out.println("File not found!");
        }
    }

    @FXML
    void setEditGroup(ActionEvent event) throws Exception {
        System.out.println(groupSelecting);
        String newName = editGroupNameController.setEditNameGroupWindow(groupSelecting);

        if (newName!=null && !newName.trim().equals("")){
            manager.getGroup(groupSelecting).reName(newName);
        }
        refresh();
    }

    public void moveLearn(ActionEvent e) throws Exception {
        Stage abc = new Stage();
        Parent root = new FXMLLoader(getClass().getResource("fxml/Testing.fxml")).load();
        abc.setTitle("Hello World");
        abc.setScene(new Scene(root));
        abc.show();
    }

    @FXML
    void setAddWord(ActionEvent event) throws Exception {
        Word newWord = addWordController.setAddWordWindow("Thêm", groupSelecting, null);

        if (newWord != null) {
            String message = "";
            if (manager.getGroup(groupSelecting).getListWords().containsKey(newWord.getEnglish())){
                message = "Từ đã tồn tại";
            }else {
                manager.addWord(newWord, newWord.getWordGroup());
                message = "Đã cập nhật";
            }
            MessageBox.show(message, "");
        }
    }

    @FXML
    void setDeleteWord(ActionEvent event) {
        SearchController searchController = new SearchController();
        searchController.deleteWord(wordSelecting);
    }

    @FXML
    void setEditWord(ActionEvent event) throws Exception{
        Word newWord = addWordController.setAddWordWindow(Text.getTexts().get("editBtn"),"", wordSelecting);
        if (newWord != null){
            manager.editWord(newWord);
            MessageBox.show(Text.getTexts().get("updated"), "");
            refresh();
        }
    }

    @FXML
    private void initialize() {
        setListGroups();
        enOutput.setCellValueFactory(new PropertyValueFactory<Word, String>("English"));
        vnOutput.setCellValueFactory(new PropertyValueFactory<Word, String>("VietNam"));

        listviewGroups.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listviewGroups.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            listGroupSelected = listviewGroups.getSelectionModel().getSelectedItems();
            System.out.println(listGroupSelected.size());
            if(listGroupSelected.size() == 0){
                merge.setDisable(true);
                editGroupFX.setDisable(true);
                deleteGroupFX.setDisable(true);
            }
            else if (listGroupSelected.size() == 1) {

                merge.setDisable(true);
                editGroupFX.setDisable(false);
                deleteGroupFX.setDisable(false);

                groupSelecting = newValue;
                setTable();
//                setListWords(manager.getGroup(newValue));

            } else if (listGroupSelected.size() > 1) {

                merge.setDisable(false);
                editGroupFX.setDisable(true);
                deleteGroupFX.setDisable(false);
            }
        });
        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue!=null){
                wordSelecting = newValue;
//                wordSelecting.setWordGroup(groupSelecting);
                System.out.println(wordSelecting);
                System.out.println(wordSelecting);
                editWord.setDisable(false);
                deleteWord.setDisable(false);
            }else {
                editWord.setDisable(true);
                deleteWord.setDisable(true);
            }
        }));
    }

}
