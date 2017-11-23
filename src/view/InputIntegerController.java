package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.TextOutput.Text;

import static view.ConfirmationBox.stage;

public class InputIntegerController {
    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton ok;

    @FXML
    private JFXComboBox<Integer> inputInt;

    private static int intInp = 0;
    private GUI gui = new GUI();

    @FXML
    void cancel(ActionEvent event) {
        intInp = 0;
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void setOk(ActionEvent event) {

        if (inputInt.getValue() == null || inputInt.getValue() == 0){
            MessageBox.show(Text.getTexts().get("inputEmpty"), "");
        }else {
            intInp = inputInt.getValue();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    public void show() throws Exception {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = new FXMLLoader(getClass().getResource("fxml/InputInteger.fxml")).load();
        gui.setMyStyle(stage, root);
        stage.setTitle("add Integer");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public int setAddIntegerWindow() throws Exception{
        this.show();
        return intInp;
    }

    @FXML
    private void initialize(){
        for (int i=1 ; i<20 ; i++){
            inputInt.getItems().add(i);
        }
    }

}
