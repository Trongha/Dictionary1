import consoleView.Console;
import data.Group;
import data.Level;
import data.Word;
import manager.AppManager;
import manager.FlashcardsManager;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import view.FlashcardController;
import view.GUI;

public class Main {
    private static String[] patchs =
            {"src\\data\\dataFile\\xlsx\\family.xlsx",
            "src\\data\\dataFile\\xlsx\\Steel.xlsx" ,
           /* "src\\data\\dataFile\\xlsx\\Place.xlsx" ,
            "src\\data\\dataFile\\xlsx\\Region Of Viet Nam.xlsx" ,
            "src\\data\\dataFile\\xlsx\\MINISTRIES AND MINISTRY-LEVEL AGENCIES.xlsx" ,
            "src\\data\\dataFile\\xlsx\\building dictionary.xlsx",
            "src\\data\\dataFile\\xlsx\\Region Of The World.xlsx"*/
    };

    public static void main(String[] args) {
       for (String patch : patchs){
           AppManager appManager = new AppManager();
           appManager.addGroup(new Group(patch));
       }
        GUI gui = new GUI();
        gui.main(args);

     /*   for (Group group : AppManager.getGroups()){
            for (Word word : group.getListWords().values()){
                System.out.println(word);
            }
            System.out.println("");
        }*/

    }
}
