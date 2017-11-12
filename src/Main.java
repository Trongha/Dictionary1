import consoleView.Console;
import data.Group;
import manager.AppManager;
import manager.Learning;
import view.GUI;

public class Main {
    private static String[] patchs =
            {"src\\data\\dataFile\\xlsx\\family.xlsx",
            "src\\data\\dataFile\\xlsx\\Steel.xlsx" ,
            "src\\data\\dataFile\\xlsx\\Place.xlsx" ,
            "src\\data\\dataFile\\xlsx\\Region Of Viet Nam.xlsx" ,
            "src\\data\\dataFile\\xlsx\\MINISTRIES AND MINISTRY-LEVEL AGENCIES.xlsx" ,
            "src\\data\\dataFile\\xlsx\\building dictionary.xlsx",
            "src\\data\\dataFile\\xlsx\\Region Of The World.xlsx"
    };

    public static void main(String[] args) {
       for (String patch : patchs){
           AppManager.addGroup(new Group(patch));
       }
        GUI gui = new GUI();
        gui.main(args);


        Console consol = new Console();

        //consol.printAllGroup();

        /*for (Group group : AppManager.getGroups()){
            group.outFile();
        }*/
      //  consol.search();

    }
}
