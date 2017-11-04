package view;


import manager.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Button searchButton;

    @FXML
    private TextField input;

    @FXML
    private Label output;
    @FXML
    private Button movetoabc;


    public void search(ActionEvent e) {
        AppManager data = new AppManager();

        String s = String.format("%s", data.search(input.getText()));
        output.setText(s);
        System.out.println(s);
    }

    public void Moveabc(ActionEvent e) throws Exception{
        Stage abc = new Stage();
        Parent root = new FXMLLoader(getClass().getResource("Search.fxml")).load();
        abc.setTitle("Hello World");
        abc.setScene(new Scene(root, 600, 275));

        abc.show();
    }
}