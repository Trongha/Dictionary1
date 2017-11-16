package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StudyController {

    @FXML
    private JFXButton test;

    @FXML
    private JFXButton test1;


    private Parent testPane;
    private Parent studyPane;


    public void loadParent() {
        try {
            testPane = new FXMLLoader(getClass().getResource("fxml/Testing.fxml")).load();
            studyPane = new FXMLLoader(getClass().getResource("fxml/Flashcard.fxml")).load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void setTest(ActionEvent event) throws Exception{
        TestingController testingController = new TestingController();
        testingController.show();
    }

    @FXML
    void setStudy(ActionEvent event) {

    }



    @FXML
    public void initialize() {

    }
}