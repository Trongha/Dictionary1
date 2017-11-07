package view;

import data.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import manager.Learning;

import javafx.event.ActionEvent;

public class LearningController {
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private RadioButton radio4;

    @FXML
    private Label keyAsk;
    @FXML
    private Label ketQua;

    @FXML
    private Button check;
    @FXML
    private Button next;

    private Learning learn = new Learning();
    private int nowTest = 0;



    public void setAsk(){
        ketQua.setText("");
        Test test = new Test(learn.getTests()[nowTest]);
        keyAsk.setText((nowTest+1) + " " + test.getKeyAsk().getEnglish());
        radio1.setText(test.getDapAn()[0]);
        radio2.setText(test.getDapAn()[1]);
        radio3.setText(test.getDapAn()[2]);
        radio4.setText(test.getDapAn()[3]);
    }
    public void unCheck(){
        if (radio4.isSelected()){
            radio4.setSelected(false);
        }else if (radio3.isSelected()){
            radio3.setSelected(false);
        }else if (radio2.isSelected()){
            radio2.setSelected(false);
        }else if (radio1.isSelected()){
            radio1.setSelected(false);
        }
    }
    public void check(ActionEvent event){
        String dapan = "";
        if (radio1.isSelected()){
            dapan+=radio1.getText();
        }
        else if (radio2.isSelected()){
            dapan+=radio2.getText();
        }
        else if (radio3.isSelected()){
            dapan+=radio3.getText();
        }
        else if (radio4.isSelected()){
            dapan+=radio4.getText();
        }

        ketQua.setText(learn.getTests()[nowTest].checkDapAn(dapan) ? "Đúng" : "Sai");
        /*for (RadioButton chose : radio){
            if (chose.isSelected()){
                dapan += chose.getText();
                System.out.println(dapan);
            }
        }*/
    }

    public void next(ActionEvent event){
        if (nowTest<learn.getNumOfTest())
            nowTest++;
        unCheck();
        setAsk();
    }
    @FXML
    public void initialize(){
        System.out.println("Size List Learn : " + Learning.getListWords().size());
        learn.sinhTests();

        setAsk();
    }
}
