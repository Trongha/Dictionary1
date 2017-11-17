package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import data.Group;
import data.Level;
import data.Word;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.FlashcardsManager;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class FlashcardController {
    @FXML
    private JFXButton next;

    @FXML
    private JFXComboBox<String> levels = new JFXComboBox<String>();

    @FXML
    private JFXButton rotate;

    @FXML
    private HBox vnFace, enFace, waiting;

    @FXML
    private ImageView imgVN;

    @FXML
    private Label vnText;

    @FXML
    private Label enText;

    @FXML
    private ImageView icon;

    @FXML
    private JFXButton close;

    private Stage stage = new Stage();

    private enum FrontPane{
        vnFace,
        enFece
    }

    private FrontPane frontPane = FrontPane.enFece;

    private Group group= new Group();
    private int numCard = 0;
    private int indexCard = 1;
    private static FlashcardsManager flashcardsManager = new FlashcardsManager();


    @FXML
    void closeFlashcard(ActionEvent event) {
        System.out.println("Flashcard is Exit");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void next(ActionEvent event) {
        System.out.println(">>>>>>>>>>>> next Flashcard!");
        waiting.toFront();
        newCard(flashcardsManager.newWordToCard());
        enFace.toFront();
        frontPane = FrontPane.enFece;
    }

    @FXML
    void rotate(ActionEvent event) {
        if (frontPane == FrontPane.vnFace){
            frontPane = FrontPane.enFece;
            enFace.toFront();
        }else{
            frontPane = FrontPane.vnFace;
            vnFace.toFront();
        }
    }


    public void newCard(Word word){

        System.out.println("dkmdkdm" + word.getVietNam());

        System.out.println(word.getEnglish());
        System.out.println(word.getVietNam());

        enText.setText(word.getEnglish());
        vnText.setText(word.getVietNam());

        if(!word.getPathImage().equals("")){
            File f = new File(word.getPathImage());
            imgVN.setImage(new Image(f.toURI().toString()));
        }
        if (word.getLevel() != Level.nothing){
            levels.setValue(word.getLevel().toString());
        }
    }

    public void loadLevels(){
        for (Level level : Level.values()){
            levels.getItems().add(level.toString());
        }
    }

    public void show(Group _group, int _numCard) throws Exception {
        System.out.println(_group.getName());
        this.numCard = _numCard;

        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = new FXMLLoader(getClass().getResource("fxml/Flashcard.fxml")).load();
        GUI gui = new GUI();
        gui.setMyStyle(stage, root);
        stage.setTitle("xxx");
        stage.setScene(new Scene(root));

        flashcardsManager = new FlashcardsManager(_group);


        stage.showAndWait();
    }

    public void initialize() {
        enFace.toFront();
        loadLevels();
        newCard(flashcardsManager.newWordToCard());
        levels.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (levels.getItems().toString().equals(Level.nothing.toString())){
                    next.setDisable(true);
                }
                else next.setDisable(false);
            }

        });
    }

}
