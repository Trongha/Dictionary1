package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.TextOutput.Text;

public class EditGroupNameController {
    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton ok;

    @FXML
    private JFXTextField inputName;

    private Stage stage = new Stage();
    private GUI gui = new GUI();
    private static String inpNameString = "";

    @FXML
    void cancel(ActionEvent event) {
        inpNameString = "";
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void setOk(ActionEvent event) {
        inpNameString = inputName.getText();
        if (inpNameString.trim().equals("")){
            MessageBox.show(Text.getTexts().get("inputEmpty"), "");
        }else {
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    public void show() throws Exception {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = new FXMLLoader(getClass().getResource("fxml/EditGroupName.fxml")).load();
        gui.setMyStyle(stage, root);
        stage.setTitle("Name Group");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public String setEditNameGroupWindow(String oldName) throws  Exception{
        System.out.println(oldName);
        inpNameString = oldName;
        this.show();
        return inpNameString;
    }

    @FXML
    private void initialize(){
        inputName.setText(inpNameString);
    }
}
