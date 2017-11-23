package manager;

import data.Group;
import data.OldWord;
import data.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.java2d.opengl.WGLSurfaceData;

import java.io.File;
import java.util.*;


/**
 * Created by Trong on 28/10/2017.
 */

public class AppManager {
    private static ArrayList<Group> groups = new ArrayList<>();
    private static Group allGroup = new Group();
    private static String pathFileXlsx = "src\\data\\dataFile\\xlsx";
    private OldWord oldWord = new OldWord();


    public AppManager() {
    }

    static {
        allGroup.setName("All Topic");
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
            if (nameGroupSearch.equals("")) {
                for (int i = 0; i < groups.size(); i++) {
                    mapSearch.addAll(groups.get(i).search2(key));
                }
            } else {
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

    public boolean addGroup(Group newGroup) {
        System.out.println("add Group");
        boolean add = true;
        for (Group oldGroup : groups) {
            if (oldGroup.getName().equals(newGroup.getName())) {
                System.out.println("Trùng tên");
                return false;
            }
        }

        groups.add(newGroup);
        allGroup.addGroup(newGroup);

        Collections.sort(groups, new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return add;
    }

    public void mergeGroups(String[] grName, String newName) {

        Group newGroup = new Group(String.format("%s & %s", grName[0], grName[1]), this.getGroup(grName[0]), this.getGroup(grName[1]));

        for (int i = 2; i < grName.length; i++) {
            newGroup = new Group(String.format("%s & %s", newGroup.getName(), grName[i]), newGroup, this.getGroup(grName[i]));
        }

        if (!newName.trim().equals("")) {
            newGroup.setName(newName);
        }
        newGroup.outFile();
        this.addGroup(newGroup);
    }

    public void deleteGroup(String name) {
        for (int i = 0; i < groups.size(); i++) {
            if (name.equals(groups.get(i).getName())) {
                groups.get(i).delete();
                groups.remove(i);
            }
        }
    }

    public void addWord(Word word, String nameGroup) {
        Group group = this.getGroup(nameGroup);
        group.addWord(word);
        allGroup.addWord(word);
        System.out.println(String.format("add %s %s to %s Complete!", word.getEnglish(), word.getVietNam(), group.getName()));
    }

    public void editWord(Word word) {
        this.addWord(word, word.getWordGroup());
    }

    public void deleteWord(Word wordDelete) {
        System.out.println("Delete Word");
        this.getGroup(wordDelete.getWordGroup()).deleteWord(wordDelete);
        System.out.println("Delete Word Complete!");
    }

    public void deleteWordInAllGroup(Word wordDelete) {
        System.out.println("Delete Word");
        for (Group group : groups) {
            if (group.getListWords().containsKey(wordDelete.getEnglish())) {
                System.out.println("Delete in " + group.getName());
                group.delete(wordDelete.getEnglish());
            }
        }
        allGroup.delete(wordDelete.getEnglish());
        System.out.println("Delete Word Complete!");
    }


    public void loadFile() {
        File folder = new File(pathFileXlsx);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                this.addGroup(new Group(file.getPath()));
            }
        }

    }

    public void groupOutFile() {
        for (Group group : groups) {
            group.outFile();
        }
        oldWord.output();
    }
}
