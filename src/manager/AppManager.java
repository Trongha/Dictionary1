package manager;

import data.Group;
import data.Word;
import sun.java2d.opengl.WGLSurfaceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;



/**
 * Created by Trong on 28/10/2017.
 */

public class AppManager {
    private static ArrayList<Group> groups = new ArrayList<>();
    private static Group allGroup = new Group();
    public AppManager(){}

    public static void addGroup(Group group){


        groups.add(group);
        allGroup.addGroup(group);
        allGroup.setName("allGroup");
    }

    /**
     * Tìm Group theo tên
     * @param nameGroup
     * @return
     */
    public Group getGroup(String nameGroup){
        for (Group group : groups){
            if (nameGroup.equals(group.getName()))
                return group;
        }
        return groups.get(0);
    }
    public void addWord(Word word, String nameGroup){
        Group group = this.getGroup(nameGroup);
        group.addWord(word);
        allGroup.addWord(word);
        System.out.println(String.format("add %s %s to %s Complete!", word.getEnglish(), word.getVietNam(), group.getName()));
    }
    public static Group getAllGroup() {
        return allGroup;
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

        return allGroup.search(key);
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

    public void deleteGroup(String name) {
        for (int i=0 ; i<groups.size() ; i++){
            if (name.equals(groups.get(i).getName()))
                groups.remove(i);
        }
    }

}
