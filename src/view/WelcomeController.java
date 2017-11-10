package view;


import javafx.scene.Node;
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


public class WelcomeController {
    private GUI gui = new GUI();
    @FXML
    private Button searchButton;

    @FXML
    private TextField input;

    @FXML
    private Label output;
    @FXML
    private Button search;
    @FXML
    private Button groupManager;


    public void search(ActionEvent e) {
        AppManager data = new AppManager();

        String s = String.format("%s", data.search(input.getText()));
        output.setText(s);
        System.out.println(s);
    }

    public void moveSearch(ActionEvent e) throws Exception{
        gui.move("fxml\\Search.fxml", "Search");
    }

    public void moveGroupManager(ActionEvent e){
        gui.move("fxml\\GroupManager.fxml", "Group Manager");
    }

}