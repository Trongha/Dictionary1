import consoleView.Console;
import data.Group;
import manager.AppManager;
import manager.Learning;
import view.GUI;

public class Main {
    private static String[] patchs =
            {"E:\\Java\\Dictionary\\src\\resrc\\xlsx\\family.xlsx",
            "E:\\Java\\Dictionary\\src\\resrc\\xlsx\\Region Of The World.xlsx",
            "E:\\Java\\Dictionary\\src\\resrc\\xlsx\\Region Of Viet Nam.xlsx",
            "E:\\java\\Dictionary\\src\\resrc\\xlsx\\MINISTRIES AND MINISTRY-LEVEL AGENCIES.xlsx",
            "E:\\java\\Dictionary\\src\\resrc\\xlsx\\Place.xlsx"  ,
            /*"E:\\java\\Dictionary\\src\\resrc\\xlsx\\Steel.xlsx"*/
    };

    public static void main(String[] args) {
       for (String patch : patchs){
           AppManager.addGroup(new Group(patch));
       }
        GUI gui = new GUI();
        gui.main(args);

        Learning learn = new Learning(AppManager.getGroups().get(0).getListWords(), 5);
        learn.sinhTests();
        System.out.println(learn);
        Console consol = new Console();

        //consol.printAllGroup();

        /*for (Group group : AppManager.getGroups()){
            group.outFile();
        }*/
      //  consol.search();

    }
}
