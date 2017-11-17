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
    private JFXComboBox<String> levels;

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
    private FlashcardsManager flashcardsManager;


   /* private Queue<Word> hardQueue = new LinkedList<Word>();
    private Queue<Word> moderateQueue = new LinkedList<Word>();
    private Queue<Word> esasyQueue = new LinkedList<Word>();
    private static Double level1 = 0.5;
    private static Double level2 = 0.8;
    public void add1Word(Word _word) {
//        System.out.println(word);
        Word word = new Word();
        word.clone(_word);
        switch (word.getLevel()) {
            case hard: {
                hardQueue.add(word);
                break;
            }
            case nothing:
            case esasy: {
                esasyQueue.add(word);
                break;
            }
            case moderate: {
                moderateQueue.add(word);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void set3Queue(Group group) {
        System.out.println("---->  set Group to Flashcard : " + group.getName());
        for (Word word : group.getListWords().values()) {
            this.add1Word(word);
            System.out.println("add" + word.getEnglish());
        }
        System.out.println("size hard    : " + hardQueue.size());
        System.out.println("size moretate: " + moderateQueue.size());
        System.out.println("size esasy   : " + esasyQueue.size());
    }

    public Word hardQueuePoll() {
        if (!hardQueue.isEmpty()) {
            System.out.println("----> return Word in hard");
            return hardQueue.poll();
        } else
            return  new Word("", "");
    }

    public Word moderateQueuePoll() {
        if (!moderateQueue.isEmpty()) {
            System.out.println("----> return Word in moderate");
            return moderateQueue.poll();
        } else
            return  new Word("", "");
    }

    public Word esasyQueuePoll() {
        if (!esasyQueue.isEmpty()) {
            System.out.println("----> return Word in esasy");
            return esasyQueue.poll();
        } else{
            System.out.println(esasyQueue.size());
            return new Word("", "");
        }

    }

    public Word newWordToCard() {

        if (hardQueue.size() == 0) {
            this.level1 = 0.0;
        }
        if (moderateQueue.size() == 0) {
            this.level2 = 0.0;
        }

        Double random = Math.random();
        System.out.println(random);

        if (random <= this.level1) {
            return hardQueuePoll();
        } else if (random <= this.level2) {
            return moderateQueuePoll();
        } else {
            return esasyQueuePoll();
        }
    }
*/


    @FXML
    void closeFlashcard(ActionEvent event) {
        System.out.println("Flashcard is Exit");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void next(ActionEvent event) {
        System.out.println(">>>>>>>>>>>> next Flashcard!");
        waiting.toFront();

//        newCard(cardManager.newWordToCard());

        enText.setText(String.valueOf(indexCard++));
        vnText.setText(String.valueOf(indexCard++));
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
        System.out.println("dkmdkdm" + word.getEnglish());


        enText.setText((String)word.getEnglish());
        vnText.setText((String)word.getVietNam());

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

        flashcardsManager = new FlashcardsManager(_group);
        for (int i = 0; i < 5; i++) {
            System.out.println(i + "   " + flashcardsManager.newWordToCard().getEnglish());
//            enText.setText("adshnksd");

            System.out.println("");
        }

            Parent root = new FXMLLoader(getClass().getResource("fxml/Flashcard.fxml")).load();
            GUI gui = new GUI();
            gui.setMyStyle(stage, root);

            stage.setScene(new Scene(root));

            stage.showAndWait();

    }
    public void initialize() {
        enFace.toFront();
        Word word = flashcardsManager.newWordToCard();
        enText.setText("fdsg");
        loadLevels();

//        System.out.println(flashcardsManager.newWordToCard().getEnglish());

        levels.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (levels.getItems().equals(Level.nothing.toString())){
                    next.setDisable(true);
                }
                else next.setDisable(false);
            }
        });

    }

}
