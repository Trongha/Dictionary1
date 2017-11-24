
import manager.AppManager;
import view.GUI;

public class Main {

    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        GUI gui = new GUI();

        appManager.loadFile();
        gui.main(args);
    }
}
