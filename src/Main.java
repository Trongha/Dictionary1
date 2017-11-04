import consoleView.Console;
import data.Group;
import manager.AppManager;
import view.GUI;

public class Main {
    private static String[] patchs =    {"E:\\Java\\Dictionary\\src\\resrc\\xlsx\\family.xlsx",
                                        "E:\\Java\\Dictionary\\src\\resrc\\xlsx\\Region Of The World.xlsx",
                                        /*"E:\\Java\\Dictionary\\src\\resrc\\xlsx\\Region Of Viet Nam.xlsx"*/};

    public static void main(String[] args) {
       for (String patch : patchs){
           AppManager.addGroup(new Group(patch));
       }
        /*GUI gui = new GUI();
        gui.main(args);*/

        Console consol = new Console();

        consol.printAllGroup();
        /*String s = "   asdfasdf s adf sadf sd fsdfsds    ";
        System.out.println(s.trim() + ".");*/
        /*consol.addGroup();
        consol.printAllGroup();*/
        for (Group group : AppManager.getGroups()){
            group.outFile();
        }
        consol.search();

    }
}
