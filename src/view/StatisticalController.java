package view;

import data.Group;
import data.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.AppManager;

import java.util.ArrayList;

public class StatisticalController {

    @FXML
    private TableView<Group> table;

    @FXML
    private TableColumn<Group, String> nameGroup;

    @FXML
    private TableColumn<Group, Integer> numWords;

    @FXML
    private TableColumn<Group, Integer> numStudied;

    @FXML
    private TableColumn<Group, Integer> numEsasy;

    ObservableList<Group> listGroup = FXCollections.observableArrayList();

    public void setTable(){

        listGroup.clear();
//        ArrayList<Group> listGrouppp =
        listGroup.addAll(AppManager.getGroups());
        table.setItems(listGroup);
    }

    @FXML
    private void initialize() {
        nameGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        numStudied.setCellValueFactory(new PropertyValueFactory<Group, Integer>("numWordStudied"));
        numEsasy.setCellValueFactory(new PropertyValueFactory<Group, Integer>("numWordEsasy"));
        numWords.setCellValueFactory(new PropertyValueFactory<Group, Integer>("size"));
        setTable();
    }

}
