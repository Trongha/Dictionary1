package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import data.Group;
import data.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.AppManager;
import manager.TestsManager;

import javafx.event.ActionEvent;

public class TestingController {

    @FXML
    private AnchorPane prepare;

    @FXML
    private AnchorPane learning;

    @FXML
    private AnchorPane finish;

    @FXML
    private Label keyAsk;

    @FXML
    private VBox choiceDapAn;

    @FXML
    private RadioButton radio1;

    @FXML
    private ToggleGroup DapAn;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private Button check;

    @FXML
    private Button next;

    @FXML
    private ImageView img;

    @FXML
    private Pane trueIcon;

    @FXML
    private Pane falseIcon;

    @FXML
    private Pane waiting;

    @FXML
    private JFXCheckBox ready;

    @FXML
    private JFXButton start;

    @FXML
    private Label scores;

    @FXML
    private Label finishLabel;

    @FXML
    private JFXButton reLearn;

    @FXML
    private JFXComboBox<Integer> numTest;

    @FXML
    private JFXComboBox<String> groupChoice;


    @FXML
    private JFXButton close;

    private Stage stage = new Stage();


    private int maxNumTest;

    private TestsManager learnManager;
    private AppManager manager = new AppManager();
    private int nowTest = 0;
    private Group groupTest;
    private int numTestWasChoose;

    public void setListChoice() {
        for (Group group : AppManager.getGroups()) {
            groupChoice.getItems().add(group.getName());
        }
        //groupChoice.setValue(AppManager.getGroups().get(0).getName());
    }

    public void setListNumber() {
        numTest.getItems().clear();
        for (int num = 1; num < maxNumTest; num++) {
            numTest.getItems().add(num);
        }
    }

    public void refresh(){
        ready.setSelected(false);
        start.setDisable(true);
        groupChoice.setDisable(false);
        numTest.setDisable(false);
        prepare.toFront();
        nowTest = 0;
    }

    @FXML
    void userReady(ActionEvent event) {
        if (ready.isSelected()) {
            groupChoice.setDisable(true);
            numTest.setDisable(true);
            start.setDisable(false);
        } else {
            start.setDisable(true);
            groupChoice.setDisable(false);
            numTest.setDisable(false);
        }
    }

    public void LearnLearn() {

    }

    @FXML
    void startLearn(ActionEvent event) {
        if (groupChoice.getValue() == null) {
            MessageBox.show("Chưa chọn bộ từ", "");
        } else if (numTest.getValue() == null) {
            MessageBox.show("Chưa chọn số lượng test", "");
        } else {
            numTestWasChoose = numTest.getValue();
            groupTest = manager.getGroup(groupChoice.getValue());
            learnManager = new TestsManager(groupTest.getListWords(), numTestWasChoose);
            learnManager.sinhTests();
            learning.toFront();
            waiting.toFront();
            setAsk();
        }
    }

    public void unCheck() {
        if (radio4.isSelected()) {
            radio4.setSelected(false);
        } else if (radio3.isSelected()) {
            radio3.setSelected(false);
        } else if (radio2.isSelected()) {
            radio2.setSelected(false);
        } else if (radio1.isSelected()) {
            radio1.setSelected(false);
        }
    }

    public void setAsk() {
        unCheck();
        waiting.toFront();
        Test test = new Test(learnManager.getTests()[nowTest]);
        keyAsk.setText((nowTest + 1) + ". " + test.getKeyAsk().getEnglish());
        radio1.setText(test.getDapAn()[0]);
        radio2.setText(test.getDapAn()[1]);
        radio3.setText(test.getDapAn()[2]);
        radio4.setText(test.getDapAn()[3]);
        choiceDapAn.setDisable(false);
        scores.setText(String.format("Scores:\n%d/%d", learnManager.getScores(), learnManager.getMaxScores()));
    }

    public void setKetQua(boolean rightOrWrong) {
        choiceDapAn.setDisable(true);
        if (rightOrWrong == true) {
            trueIcon.toFront();
            learnManager.trueIncrease();
        } else {
            falseIcon.toFront();
        }

    }

    public void check(ActionEvent event) {
        String dapan = "";
        if (radio1.isSelected()) {
            dapan += radio1.getText();
        } else if (radio2.isSelected()) {
            dapan += radio2.getText();
        } else if (radio3.isSelected()) {
            dapan += radio3.getText();
        } else if (radio4.isSelected()) {
            dapan += radio4.getText();
        }
        setKetQua(learnManager.getTests()[nowTest].checkDapAn(dapan));
//        ketQua.setText(learn.getTests()[nowTest].checkDapAn(dapan) ? "Đúng" : "Sai");
        /*for (RadioButton chose : radio){
            if (chose.isSelected()){
                dapan += chose.getText();
                System.out.println(dapan);
            }
        }*/
    }

    public void setFinishLabel(){
        finishLabel.setText(String.format("Điểm số của bạn là:\n%d/%d", learnManager.getScores(), learnManager.getMaxScores()));
    }

    public void next(ActionEvent event) {
        if (nowTest < numTestWasChoose-1){
            nowTest++;
            setAsk();
        }else{
            setFinishLabel();
            finish.toFront();
        }
    }

    @FXML
    void reLearn(ActionEvent event) {
        this.refresh();
    }

    @FXML
    void changeGroup(ActionEvent event) throws Exception {
        System.out.println("change");
        maxNumTest = manager.getGroup(groupChoice.getValue()).getListWords().size();
        System.out.println(maxNumTest);
        setListNumber();
    }

    public void show() throws Exception {

        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = new FXMLLoader(getClass().getResource("fxml/Testing.fxml")).load();

        GUI gui = new GUI();
        gui.setMyStyle(stage, root);
        stage.setTitle("Kiểm Tra");
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }

    public void close(ActionEvent e){
        System.out.println("Testing is Exit");
        ((Node)e.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        prepare.toFront();
        setListChoice();
        groupChoice.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                maxNumTest = manager.getGroup(groupChoice.getValue()).getListWords().size();
                System.out.println(maxNumTest);
                setListNumber();
            }
        });

        DapAn.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
                    if(newVal!=null){
                        String[] dapAnSelected = newVal.toString().split("\'");
                        System.out.println(newVal + " was selected");
                        setKetQua(learnManager.getTests()[nowTest].checkDapAn(dapAnSelected[1]));
                    }
                }
        );



    }
}
