import consoleView.Console;
import data.Group;
import manager.AppManager;
import view.GUI;

public class Main {

    public static void main(String[] args) {
        AppManager.addGroup(new Group("Family", "E:\\Java\\Dictionary\\src\\resrc\\family.xlsx"));
        GUI gui = new GUI();
        gui.main(args);
/*        String patch = "E:\\Java\\Dictionary\\src\\resrc\\family.xlsx";
        Group group = new Group();
        group.loadFile(patch);
        System.out.println(group);*/

        Console consol = new Console();

        //consol.groupPrint(AppManager.getGroups().get(0));

        /*String s = "   asdfasdf s adf sadf sd fsdfsds    ";
        System.out.println(s.trim() + ".");*/
        consol.search();

    }
}
