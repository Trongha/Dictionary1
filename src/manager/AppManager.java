package manager;

import data.Group;
import data.Word;
import sun.java2d.opengl.WGLSurfaceData;

import java.util.*;


/**
 * Created by Trong on 28/10/2017.
 */

public class AppManager {
    private static ArrayList<Group> groups = new ArrayList<>();
    private static Group allGroup = new Group();

    public AppManager() {
    }

    static {
        allGroup.setName("All Group");
    }

    /**
     * Tìm Group theo tên
     *
     * @param nameGroup
     * @return
     */
    public Group getGroup(String nameGroup) {
        for (Group group : groups) {
            if (nameGroup.equals(group.getName()))
                return group;
        }
        return groups.get(0);
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
     *
     * @param key
     * @return
     */
    public Word search(String key) {
        key = key.toLowerCase().trim();
        return allGroup.search(key);
    }

    public ArrayList<Word> search2(String key, String nameGroupSearch) {
        key = key.toLowerCase().trim();
        HashSet<Word> mapSearch = new HashSet<>();
        if (!groups.isEmpty()) {
            if (nameGroupSearch.equals("")){
                for (int i = 0; i < groups.size(); i++) {
                    mapSearch.addAll(groups.get(i).search2(key));
                }
            }else {
                mapSearch.addAll(this.getGroup(nameGroupSearch).search2(key));
            }
        }
        ArrayList<Word> wordEnglishs = new ArrayList<Word>(mapSearch);

        Collections.sort(wordEnglishs, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getEnglish().compareToIgnoreCase(o2.getEnglish());
            }
        });
        return wordEnglishs;
    }

    public void addGroup(Group group) {
        groups.add(group);
        allGroup.addGroup(group);
        Collections.sort(groups, new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    public void mergeGroups(String[] grName){

        Group newGroup = new Group(String.format("%s & %s", grName[0], grName[1]), this.getGroup(grName[0]),this.getGroup(grName[1]));

        for (int i=2 ; i<grName.length ; i++){
            newGroup = new Group(String.format("%s & %s", newGroup.getName(), grName[i]), newGroup,this.getGroup(grName[i]));
        }

        this.addGroup(newGroup);
    }

    public void editGroup(){}

    public void deleteGroup(String name) {
        for (int i = 0; i < groups.size(); i++) {
            if (name.equals(groups.get(i).getName()))
                groups.remove(i);
        }
    }

    public void addWord(Word word, String nameGroup) {
        Group group = this.getGroup(nameGroup);
        group.addWord(word);
        allGroup.addWord(word);
        System.out.println(String.format("add %s %s to %s Complete!", word.getEnglish(), word.getVietNam(), group.getName()));
    }

    public void editWord(Word word){
        this.addWord(word, word.getWordGroup());
    }

    public void deleteWord(Word wordDelete){
        System.out.println("Delete Word");
        this.getGroup(wordDelete.getWordGroup()).deleteWord(wordDelete);
        System.out.println("Delete Word Complete!");
    }

    public void groupOutFile(){
        for (Group group : groups){
            group.outFile();
        }
    }
}
