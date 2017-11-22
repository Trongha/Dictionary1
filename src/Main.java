import data.Group;
import data.OldWord;
import data.Word;
import manager.AppManager;
import view.GUI;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        GUI gui = new GUI();

        appManager.loadFile();

        gui.main(args);


    }
}
