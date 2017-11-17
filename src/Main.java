import consoleView.Console;
import data.Group;
import data.Level;
import data.Word;
import manager.AppManager;
import manager.FlashcardsManager;
import view.FlashcardController;
import view.GUI;

public class Main {
    private static String[] patchs =
            {"src\\data\\dataFile\\xlsx\\family.xlsx",
            "src\\data\\dataFile\\xlsx\\Steel.xlsx" ,
            /*"src\\data\\dataFile\\xlsx\\Place.xlsx" ,
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



       /* FlashcardsManager flashcardsManager = new FlashcardsManager(AppManager.getGroups().get(0));

        for (int i =0 ; i<10 ; i++){
            System.out.println(flashcardsManager.newWordToCard().getEnglish());
            System.out.println("");
        }*/

       /* for (Group group : AppManager.getGroups()){
            group.outFile();
        }*/
      //  consol.search();

    }
}
