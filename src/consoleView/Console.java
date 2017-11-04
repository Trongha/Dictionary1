package consoleView;

import data.Group;
import manager.AppManager;

import java.util.Scanner;

/**
 * Created by Trong on 30/10/2017.
 */
public class Console {
    private Scanner in = new Scanner(System.in);
    AppManager app = new AppManager();

    public Console(){}

    public void search(){
        System.out.println("Key search: ");
        String key = in.nextLine();
        System.out.println(app.search(key));
    }
    public void groupPrint(Group group){
        System.out.println(group);
    }
    public void addGroup(){
        System.out.println("name: ");
        String name = in.nextLine();
        System.out.println("patch: ");
        String patch = in.nextLine();
        AppManager.addGroup(new Group(name, patch));
    }
    public void printAllGroup(){
        for (Group group: AppManager.getGroups()){
            System.out.println(group);
        }
    }
}
