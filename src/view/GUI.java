package view;


import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    private String myPath = "fxml/Welcome.fxml";
    private String myTitle = "Hello World";

    public String getMyPath() {
        return myPath;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public GUI(){}
    public static Stage myPrimaryStage;
    public void move(String pathFXML, String title){
        try{
            Parent root = new FXMLLoader(getClass().getResource(pathFXML)).load();
            GUI.myPrimaryStage.setTitle(title);
            GUI.myPrimaryStage.setScene(new Scene(root));
            System.out.println("Move " + pathFXML);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void backHome(){
        this.move(this.getMyPath(), this.myTitle);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        myPrimaryStage = primaryStage;
        Parent root = new FXMLLoader(getClass().getResource("fxml/Home.fxml")).load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
