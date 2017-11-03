package manager;

import data.Group;
import data.Word;

import java.util.ArrayList;


/**
 * Created by Trong on 28/10/2017.
 */

public class AppManager {
    private static ArrayList<Group> groups = new ArrayList<>();

    public AppManager(){}


    public static void addGroup(Group group){
        groups.add(group);
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static void setGroups(ArrayList<Group> groups) {
        AppManager.groups = groups;
    }

    public Word search(String key){
        key.toLowerCase();
        if (!groups.isEmpty()){
            for (int i=0 ; i<groups.size() ; i++){
                return groups.get(i).search(key);
            }
        }
        Word error = new Word("error", "");
        if (groups.isEmpty()){
            error.setVietNam("groups is empty");
        }
        return error;
    }

    public void deleteGroup(int index){
        groups.remove(index);
    }

}
