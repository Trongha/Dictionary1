package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
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
import view.TextOutput.Text;

import java.io.IOException;

public class StudyController {

    @FXML
    private AnchorPane prepare;

    @FXML
    private AnchorPane preparePane;

    @FXML
    private AnchorPane learning;

    @FXML
    private AnchorPane finish;

    @FXML
    private Label keyAsk;

    @FXML
    private VBox choiceDapAn;

    @FXML
    private RadioButton radio1, radio2, radio3, radio4;

    @FXML
    private ToggleGroup DapAn;

    @FXML
    private Button check, next;

    @FXML
    private ImageView img;

    @FXML
    private Pane falseIcon, waiting, trueIcon;

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
    private String numTestPromptText = "";

    @FXML
    private JFXComboBox<String> groupChoice;
    private String groupChoicePromptText = "";

    @FXML
    private JFXRadioButton checkToTest, checkToFlash;

    @FXML
    private ToggleGroup action;

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
            if(group.getListWords().size() > 0)
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
        userReady( new ActionEvent());
        start.setDisable(true);

        groupChoice.setPromptText(groupChoicePromptText);
        numTest.setPromptText(numTestPromptText);


        prepare.toFront();
        nowTest = 0;
    }

    @FXML
    void userReady(ActionEvent event) {
        if (ready.isSelected()) {
//            HomeController.setMenuPaneDisable(true);
            preparePane.setDisable(true);
            start.setDisable(false);
        } else {
            start.setDisable(true);
//HomeController.setMenuPaneDisable(false);
            preparePane.setDisable(false);

        }
    }

    @FXML
    void startLearn(ActionEvent event) {
        if (groupChoice.getValue() == null) {
            MessageBox.show(Text.getTexts().get("ChuaChonBoTu"), "");
        } else if (numTest.getValue() == null) {
            MessageBox.show(Text.getTexts().get("ChuaChonSoLuong"), "");
        } else{
            numTestWasChoose = numTest.getValue();
            groupTest = manager.getGroup(groupChoice.getValue());
            if (checkToTest.isSelected()){
                System.out.println("----->goto Test");
                learnManager = new TestsManager(groupTest.getListWords(), numTestWasChoose);
                learnManager.sinhTests();
                learning.toFront();
                waiting.toFront();
                setAsk();
            }else {
                System.out.println("----->goto Fashcard");
                FlashcardController flashcardController = new FlashcardController();
                try{
                    flashcardController.show(groupTest, numTestWasChoose);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

    /**
     * Lạp dữ liệu cho câu hỏi mới
     */
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
        scores.setText(String.format("%s:\n%d/%d",Text.getTexts().get("Scores"), learnManager.getScores(), learnManager.getMaxScores()));
    }

    /**
     * Hiện icon đúng/sai
     * @param rightOrWrong
     */
    public void setKetQua(boolean rightOrWrong) {
        choiceDapAn.setDisable(true);
        if (rightOrWrong == true) {
            trueIcon.toFront();
            learnManager.trueIncrease();
        } else {
            falseIcon.toFront();
        }

    }

    /**
     * Check đáp án
     * @param event
     */
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
    }

    /**
     * Thông báo điểm số
     */
    public void setFinishLabel(){
        finishLabel.setText(String.format("Điểm số của bạn là:\n%d/%d", learnManager.getScores(), learnManager.getMaxScores()));
    }

    /**
     * Next câu hỏi
     * @param event
     */
    public void next(ActionEvent event) {
        if (nowTest < numTestWasChoose-1){
            nowTest++;
            setAsk();
        }else{
            setFinishLabel();
            finish.toFront();
        }
    }

    /**
     * Nhấn vào reLearn --> Thi lại
     * @param event
     */
    @FXML
    void reLearn(ActionEvent event) {
        this.refresh();
    }

    /**
     * SHOW
     * @throws Exception
     */
    public void show() throws Exception {

        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = new FXMLLoader(getClass().getResource("fxml/Testing.fxml")).load();

        GUI gui = new GUI();
        gui.setMyStyle(stage, root);
        stage.setTitle("Kiểm Tra");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void initialize() {
        prepare.toFront();


        action.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if(newVal != null){
                String ifo = newVal.toString();
                if (ifo.indexOf(checkToTest.getId()) > 0){
                    System.out.println("choice Test");
                    groupChoicePromptText = "Nhóm muốn kiểm tra: ";
                    numTestPromptText = "Số câu hỏi: ";
                    refresh();
                }else if(ifo.indexOf(checkToFlash.getId()) > 0){
                    System.out.println("choice Flashcard");
                    groupChoicePromptText = "Nhóm muốn học: ";
                    numTestPromptText = "Số thẻ: ";
                    refresh();
                }
            }
        });
        setListChoice();

        /**
         * Xem sự thay đổi của việc chọn nhóm
         */
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
                        System.out.println(newVal.toString());
                        String[] dapAnSelected = newVal.toString().split("\'");
                        System.out.println(newVal + " was selected");
                        setKetQua(learnManager.getTests()[nowTest].checkDapAn(dapAnSelected[1]));
                    }
                }
        );

    }
}
