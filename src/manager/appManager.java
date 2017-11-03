package manager;

import data.Group;
import data.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


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

    /**
     * Search Chính xác
     * @param key
     * @return
     */
    public Word search(String key){
        key = key.toLowerCase().trim();
        if (!groups.isEmpty()){
            for (int i=0 ; i<groups.size() ; i++){
                return groups.get(i).search(key);
            }
        }
        Word error = new Word(key, "");
        if (groups.isEmpty()){
            error.setVietNam("groups is empty");
        }
        return error;
    }
    public ArrayList<String> search2(String key){
        key = key.toLowerCase().trim();
        HashSet<String> mapSearch = new HashSet<>();
        if (!groups.isEmpty()){
            for (int i=0 ; i<groups.size() ; i++){
                mapSearch.addAll(groups.get(i).search2(key));
            }
        }
        return  new ArrayList<String>(mapSearch);
    }

    public void deleteGroup(int index){
        groups.remove(index);
    }

}
