
package view;


import javafx.scene.control.TextArea;
import manager.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Created by Trong on 2/11/2017.
 */
public class SearchController {
    @FXML
    private Button searchButton;

    @FXML
    private TextField input;



    @FXML
    private TextArea output2;

    public void search(ActionEvent e) {
        AppManager data = new AppManager();
        String s = String.format("%s", data.search(input.getText()));
        output2.appendText(s);
        System.out.println(s);
    }
    
}
