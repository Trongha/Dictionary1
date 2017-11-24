package manager;

import com.sun.xml.internal.ws.addressing.W3CWsaClientTube;
import data.Group;
import data.Level;
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
    //List chứa tất cả các tù
    private static Group allGroup = new Group();
    //List chứa các tù đã học
    private static Group wordsStudied = new Group();
    //path folder chứa data mặc định
    private static String pathFileXlsx = "src\\data\\dataFile\\xlsx";

    private OldWord oldWord = new OldWord();

    public AppManager() {
    }

    static {
        allGroup.setName("All Topic");
        wordsStudied.setName("Words is studied");
    }

    /**
     * Tìm Group theo tên
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

    /**
     * Load lại tất cả các từ
     */
    public void reLoadAllGroup(){
        System.out.println("ReLoad group AllGroup");
        allGroup.getListWords().clear();
        for (Group group : groups){
            allGroup.getListWords().putAll(group.getListWords());
            add1GroupToWordsStudied(group);
        }
    }

    /**
     * Tất cả các từ
     * @return
     */
    public static Group getAllGroup() {
        return allGroup;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static void setGroups(ArrayList<Group> groups) {
        AppManager.groups = groups;
    }

    public static Group getWordsStudied() {
        return wordsStudied;
    }

    public static void add1GroupToWordsStudied(Group group){
        for (Word word : group.getListWords().values()){
            if (word.getLevel() != Level.nothing){
                wordsStudied.addWord(word);
            }
        }
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

    /**
     * Search trả về list từ có key ở đầu của từ tiếng anh
     * @param key
     * @param group
     * @return
     */
    public ArrayList<Word> search(String key, Group group){
        key = key.toLowerCase().trim();
        HashSet<Word> mapSearch = new HashSet<>();
        mapSearch.addAll(group.search2(key));
        ArrayList<Word> wordEnglishs = new ArrayList<Word>(mapSearch);
        Collections.sort(wordEnglishs);
        return wordEnglishs;
    }

    /**
     * Thêm một từ vào, hàm
     * @param newGroup
     * @return
     */
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
        add1GroupToWordsStudied(newGroup);

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

    /**
     * Xóa một từ khỏi tất cả các nhớm
     * @param wordDelete
     */
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

    /**
     * Load Data File
     */
    public void loadFile() {
        File folder = new File(pathFileXlsx);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                this.addGroup(new Group(file.getPath()));
            }
        }
    }

    public void outFile() {
        for (Group group : groups) {
            group.outFile();
        }
        oldWord.output();
    }
}
