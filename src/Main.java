import consoleView.Console;
import data.Group;
import data.Level;
import data.Word;
import manager.AppManager;
import manager.FlashcardsManager;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import view.FlashcardController;
import view.GUI;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File folder = new File ("src\\data\\dataFile\\xlsx");

        for (File file : folder.listFiles()){
            if (!file.isDirectory()){
                AppManager appManager = new AppManager();
                appManager.addGroup(new Group(file.getPath()));
            }
        }
       /*for (String patch : patchs){
           AppManager appManager = new AppManager();
           appManager.addGroup(new Group(patch));
       }*/
        GUI gui = new GUI();
        gui.main(args);

       /* for (Group group : AppManager.getGroups()){
            for (Word word : group.getListWords().values()){
                System.out.println(word);
            }
            System.out.println("");
        }*/

    }
}
