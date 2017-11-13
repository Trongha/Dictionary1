package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Group;
import data.Word;
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
import org.apache.xmlbeans.impl.jam.mutable.MElement;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class AddWordController {
    @FXML
    private JFXTextField englishInp;

    @FXML
    private JFXTextField vietNamInp;

    @FXML
    private ChoiceBox<String> groupChoice;
    ObservableList<String> listGroupName = FXCollections.observableArrayList();

    @FXML
    private JFXButton imgChoice;

    @FXML
    private JFXButton add;

    @FXML
    private ImageView img;
    @FXML
    private Label linkImg;

    private Stage stage = new Stage();

    private String english = "";
    private String vietnam = "";
    private String pathImg = "";

    public void setListChoice() {
        for (Group group : AppManager.getGroups()) {
            groupChoice.getItems().add(group.getName());
        }
        groupChoice.setValue(AppManager.getGroups().get(0).getName());
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
            MessageBox.show("\nInput is Empty", "");
        } else {
            AppManager manager = new AppManager();
            manager.addWord(new Word(english, vietnam), groupChoice.getValue());
            MessageBox.show("\nAdd Complete!", "");
            stage.close();
            ((Node)event.getSource()).getScene().getWindow().hide();
        }
    }

    public void show() throws Exception {

        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = new FXMLLoader(getClass().getResource("fxml/AddWord.fxml")).load();
        stage.setTitle("Add Word");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void initialize() {
        setListChoice();
    }
}


