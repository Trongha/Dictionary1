package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import data.Group;
import data.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import manager.AppManager;
import manager.Learning;

import javafx.event.ActionEvent;

public class LearningController {

    @FXML
    private AnchorPane learning;

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
    private AnchorPane prepare;

    @FXML
    private JFXCheckBox ready;

    @FXML
    private JFXButton start;

    @FXML
    private Label scores;

    @FXML
    private JFXComboBox<Integer> numTest;

    @FXML
    private JFXComboBox<String> groupChoice;

    private int maxNumTest;

    private Learning learnManager;
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
            learnManager = new Learning(groupTest.getListWords(), numTestWasChoose);
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

    public void next(ActionEvent event) {
        if (nowTest < learnManager.getNumOfTest())
            nowTest++;
//        unCheck();
        setAsk();
    }

    @FXML
    void changeGroup(ActionEvent event) throws Exception {
        System.out.println("change");
        maxNumTest = manager.getGroup(groupChoice.getValue()).getListWords().size();
        System.out.println(maxNumTest);
        setListNumber();
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
