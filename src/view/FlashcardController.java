package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
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

    @FXML
    private JFXSlider slider;

    private Stage stage = new Stage();

    private enum FrontPane{
        vnFace,
        enFece
    }

    private FrontPane frontPane = FrontPane.enFece;

    private Group group= new Group();
    private static int numCard = 0;
    private int indexCard = 1;
    private static FlashcardsManager flashcardsManager = new FlashcardsManager();
    private Word nowWord = new Word();

    @FXML
    void closeFlashcard(ActionEvent event) {
        System.out.println("Flashcard is Exit");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void next(ActionEvent event) {
        System.out.println(">>>>>>>>>>>> next Flashcard!");
        nowWord.setLevel(levels.getValue().toString());
        flashcardsManager.add1Word(nowWord);
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

    void setNumCardSeen(){
        System.out.println((flashcardsManager.getNumWordSelected() *100 / numCard));
        slider.setValue(flashcardsManager.getNumWordSelected()*100/numCard);
        System.out.println("Set Value");
    }

    public void setDisableNextBtn(){
        if (nowWord.getLevel() == Level.nothing){
            next.setDisable(true);
        }
        else {
            next.setDisable(false);
        }
    }

    public void newCard(Word word){
        nowWord = word;
        System.out.println(word.getVietNam());

        enText.setText(word.getEnglish());
        vnText.setText(word.getVietNam());

        if(!word.getPathImage().equals("")){
            File f = new File(word.getPathImage());
            imgVN.setImage(new Image(f.toURI().toString()));
        }

        levels.setValue(word.getLevel().toString());
        System.out.println(word.getLevel());
        setNumCardSeen();
    }

    public void loadLevels(){
        for (Level level : Level.values()){
            if (level != Level.nothing){
                levels.getItems().add(level.toString());
            }
        }
    }

    public void show(Group _group, int _numCard) throws Exception {
        System.out.println(_group.getName());
        this.numCard = _numCard;
        flashcardsManager = new FlashcardsManager(_group, _numCard);

        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = new FXMLLoader(getClass().getResource("fxml/Flashcard.fxml")).load();
        GUI gui = new GUI();
        gui.setMyStyle(stage, root);
        stage.setTitle("xxx");
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }

    public void initialize() {
        enFace.toFront();
        loadLevels();
        newCard(flashcardsManager.newWordToCard());
        levels.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                nowWord.setLevel(newValue);
                setDisableNextBtn();
            }

        });
    }

}
