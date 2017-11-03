
package view;


import data.Word;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import manager.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.stage.Stage;

import java.util.*;


/**
 * Created by Trong on 2/11/2017.
 */
public class SearchController {
    private AppManager data = new AppManager();

    @FXML
    private Button searchButton;

    @FXML
    private TextField input;

    @FXML
    private TextArea output2;

    @FXML
    private ListView<Word> listWord;
    private ObservableList<Word> listWordData = FXCollections.observableArrayList();


    public SearchController() {
        HashMap<String, Word> list = manager.AppManager.getGroups().get(0).getListWords();
        Collection c1 = list.values();

        Set set = list.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
        // Hien thi cac phan tu
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            listWordData.add((Word) me.getValue());
        }

    }

    public void search(ActionEvent e) {
        String s = String.format("%s", data.search(input.getText()));
        output2.appendText(s);
        System.out.println(s);
    }
    @FXML
    private void initialize(){
        listWord.setItems(listWordData);
    }
}
