
package view;


import com.jfoenix.controls.JFXButton;
import data.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import manager.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.TextOutput.Text;

import java.io.File;
import java.util.*;


/**
 * Created by Trong on 2/11/2017.
 */
public class
SearchController {
    private AppManager manager = new AppManager();
    private GUI gui = new GUI();
    private AddWordController addWordController = new AddWordController();
    private static Word wordOuput = new Word();

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addWord, editWord, deleteWord;

    @FXML
    private TextField input;

    @FXML
    private ImageView image;

    @FXML
    private Label outputE, outVN;

    @FXML
    private ListView<String> listWord;

    private static ObservableList<String> listWordData = FXCollections.observableArrayList();
    public static Parent searchPane;

    public SearchController() {
    }

    public static Parent getSearchPane() {
        return searchPane;
    }

    void refresh() {
        search2();
        search(new ActionEvent());
    }

    /**
     * ghi ra màn hình từ trong ô search
     *
     * @param word
     */
    public void reOutput(Word word) {
        if (word != null){
            wordOuput.clone(word);
            outputE.setText(word.getEnglish());
            outVN.setText(word.getVietNam());
            File f = new File(word.getPathImage());
            image.setImage(new Image(f.toURI().toString()));
        }

        if (listWordData.size() == 1) {
            deleteWord.setDisable(false);
            editWord.setDisable(false);
        } else {
            deleteWord.setDisable(true);
            editWord.setDisable(true);
        }
    }

    //Search chính xác 1 từ
    public void search(ActionEvent e) {
        if (!input.getText().trim().equals("") && input.getText() != null) {
            Word word = manager.search(input.getText());
            String s = String.format("%s", word.toString());
            reOutput(word);
        }
       /*
        System.out.println(s);*/
    }

    /**
     * Search ra list, in vào list view
     * Nếu list có 1 từ thì in ra từ đó luôn
     * Nếu list có khác 1 từ thì ko in gì
     */
    public void search2() {
        listWordData.clear();
        ArrayList<Word> wordEnglishsSearch = manager.search2(input.getText(), AppManager.getAllGroup().getName());
        for (Word word : wordEnglishsSearch) {
            listWordData.add(word.getEnglish());
        }

        listWord.setItems(listWordData);
        if (wordEnglishsSearch.size() == 1) {
            reOutput(wordEnglishsSearch.get(0));
        } else {
            reOutput(new Word("", ""));
        }
    }

    public void search2(ActionEvent e) {
        search2();
    }

    public void setAddWord(ActionEvent e) throws Exception {

        String groupName = "";
        Word newWord = addWordController.setAddWordWindow(Text.getTexts().get("addBtn"), groupName, (Word) null);

        if (newWord != null) {
            String message = "";
            if (AppManager.getAllGroup().getListWords().containsKey(newWord.getEnglish())){
                message = "Từ đã tồn tại";
            }else {
                manager.addWord(newWord, newWord.getWordGroup());
                message = Text.getTexts().get("updated");
            }
            MessageBox.show(message, "");
        }

        refresh();
    }

    public void deleteWord(Word wordDelete){
        if (ConfirmationBox.showConfirmation(String.format("Xóa %s ?", wordDelete.getEnglish()), "Delete Word", "Yes", "No")){
            manager.deleteWord(wordDelete);
        };
    }

    @FXML
    void setDeleteWord(ActionEvent event) {
        deleteWord(wordOuput);
        refresh();
    }

    @FXML
    void setEditWord(ActionEvent event) throws Exception {
        System.out.println(wordOuput);
        Word newWord = addWordController.setAddWordWindow(Text.getTexts().get("editBtn"), "",wordOuput );
        if (newWord!=null){
            manager.editWord(newWord);
            MessageBox.show(Text.getTexts().get("updated"), "");
            refresh();
        }
    }

    @FXML
    private void initialize() {
        refresh();
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            search2();
        });
        listWord.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                reOutput(manager.search(newValue));

                deleteWord.setDisable(false);
                editWord.setDisable(false);
            }
        });
    }
}
