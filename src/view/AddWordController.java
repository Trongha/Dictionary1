package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import data.Group;
import data.Level;
import data.Word;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.AppManager;
import view.TextOutput.Text;

import java.io.File;


public class AddWordController {

    @FXML
    private JFXTextField englishInp;

    @FXML
    private JFXTextField vietNamInp;

    @FXML
    private JFXComboBox<String> groupChoice;
    ObservableList<String> listGroupName = FXCollections.observableArrayList();

    @FXML
    private JFXButton imgChoice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView img;
    @FXML
    private Label linkImg;

    private static String btnOKText = "";
    private Stage stage = new Stage();
    private GUI gui = new GUI();
    private String english = "";
    private String vietnam = "";
    private String pathImg = "";
    private static String valueOfListGroup = "";
    private static Word wordInput;
    private static String groupChoiceName = "";


    public void setListChoice() {
        for (Group group : AppManager.getGroups()) {
            groupChoice.getItems().add(group.getName());
        }

    }

    private void setPropertices(){

        if (wordInput != null){
            englishInp.setText(wordInput.getEnglish());
            englishInp.setDisable(true);
            vietNamInp.setText(wordInput.getVietNam());

            valueOfListGroup = wordInput.getWordGroup();

            File f;
            if(!wordInput.getPathImage().equals("")){
                f = new File(wordInput.getPathImage());
            }else{
                f = new File(Text.getPaths().get("imgDefual"));
            }
            img.setImage(new Image(f.toURI().toString()));
            linkImg.setText(wordInput.getPathImage());
        }

        setListChoice();

        if (valueOfListGroup == null|| valueOfListGroup.equals("") ) {
            groupChoice.setValue(AppManager.getGroups().get(0).getName());
        } else {
            System.out.println("abc");
            groupChoice.setValue(valueOfListGroup);
            groupChoice.setDisable(true);
        }

        if (!btnOKText.equals("")) {
            System.out.println(btnOKText);
            btnAdd.setText(btnOKText);
        }


    }

    private void refresh() {
        setListChoice();
    }

    @FXML
    void choiceImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\resrc\\img"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            img.setImage(new Image(selectedFile.toURI().toString()));
            pathImg = (String) selectedFile.getPath();
            linkImg.setText(pathImg);
        } else {
            MessageBox.show("\nFILE ERROR", "ERROR");
            System.out.println("File not found!");
        }
    }

    @FXML
    void addWord(ActionEvent event) {
        english = englishInp.getText().trim();
        vietnam = vietNamInp.getText().trim();
        if (english.equals("") || vietnam.equals("")) {
            MessageBox.show(Text.getTexts().get("inputEmpty"), "");
        } else {
            wordInput = new Word(english, vietnam, pathImg, groupChoice.getValue());
            wordInput.setWordGroup(groupChoice.getValue());
            stage.close();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        wordInput = null;
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void show() throws Exception {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = new FXMLLoader(getClass().getResource("fxml/AddWord.fxml")).load();
        gui.setMyStyle(stage, root);
        stage.setTitle("Add Word");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    /**
     * Show có điều kiện :))
     *
     * @param btnOk
     * @param groupName
     * @return
     * @throws Exception
     */
    public Word setAddWordWindow(String btnOk, String groupName, Word word) throws Exception {
        wordInput = null;
        btnOKText = btnOk;

        valueOfListGroup = new String(groupName);

        if (word != null){
            wordInput = new Word();
            wordInput.clone(word);
        }

        this.show();
        return wordInput;
    }

    @FXML
    private void initialize() {
        setPropertices();
    }
}


