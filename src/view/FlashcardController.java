package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlashcardController {
    @FXML
    private JFXButton next;

    @FXML
    private JFXComboBox<?> level;

    @FXML
    private JFXButton rotate;

    @FXML
    private HBox vnFace;

    @FXML
    private ImageView imgVN;

    @FXML
    private Label vnText;

    @FXML
    private HBox enFace;

    @FXML
    private ImageView icon;

    @FXML
    private Label enText;

    @FXML
    private JFXButton close;

    private Stage stage = new Stage();

    private enum FrontPane{
        vnFace,
        enFece
    }

    private FrontPane frontPane = FrontPane.enFece;

    @FXML
    void closeFlashcard(ActionEvent event) {
        System.out.println("Flashcard is Exit");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void next(ActionEvent event) {
        System.out.println(">>>>>>>>>>>> next Flashcard!");
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


    public void show() throws Exception {

        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = new FXMLLoader(getClass().getResource("fxml/Flashcard.fxml")).load();
        GUI gui = new GUI();
        gui.setMyStyle(stage, root);
        stage.setTitle("Add Word");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
