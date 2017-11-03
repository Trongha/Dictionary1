package data;

import java.io.IOException;
import java.util.*;


/**
 * Created by Trong on 29/10/2017.
 */
public class Group {
    private HashMap<String, Word> listWords = new HashMap<>();
    private String name = "";
    private String patch = "";
    public Group(){}

    public Group(String name, String patch) {
        this.name = name;
        this.patch = patch;
        this.loadFile();
    }

    /**
     * Lấy tất cả key của map
     * @param map
     * @return
     */
    public static String[] getKeyOfHashMap(HashMap<String, Word> map){
        String[] keyArray = new String[map.size()];

        Set<String> set = map.keySet();
        Iterator i = set.iterator();
        int i_key =0;
        while (i.hasNext()){
            keyArray[i_key++] = (String)i.next();
        }
        return keyArray;
    }
    public HashMap<String, Word> getListWords() {
        return listWords;
    }

    public void setListWords(HashMap<String, Word> listWords) {
        this.listWords = listWords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
    public void addWord(Word newWord){
        this.listWords.put(newWord.getEnglish(), newWord);
    }
    public void deleteWord(String name){
        if (this.listWords.containsKey(name))
            this.listWords.remove(name);
    }
    public void editWord(String name, String newEnglish, String newVietNam){
        if (this.listWords.containsKey(name)){
            this.listWords.remove(name);
            this.listWords.put(newEnglish, new Word(newEnglish, newVietNam));
            System.out.println("delete Complete!");
        }
    }
    public Word search(String key){
        System.out.println("List dang search: " + this);
        if (!listWords.isEmpty()){
            System.out.println("key: " + key);
            if (this.listWords.containsKey(key))
                return this.listWords.get(key);
        }
        Word error = new Word(key, "");
        if (listWords.isEmpty()){
            error.setVietNam("groups is empty");
        }
        else error.setVietNam("ko tim thay");
        return error;
    }

    /**
     * Search English
     * @param key
     * @return    hashmap Các từ có chứa key
     */
    public HashSet<String> search2(String key){
        HashSet<String> mapSearch = new HashSet<>();
        if (!listWords.isEmpty()){
            for(String x: getKeyOfHashMap(this.listWords)){
                if (x.indexOf(key)==0){
                    mapSearch.add(x);
                }
            }
        }
        return mapSearch;
    }

    @Override
    public String toString() {
        String s  = "";
        ArrayList<Word> listWord = new ArrayList<>(listWords.values());
        for (int i=0 ; i<listWord.size() ; i++){
            s+= String.format("%d. %s%n",i,  listWord.get(i));
        }
        return s;
    }

    /**
     * loadfile
     * @return
     */
    public Group loadFile(){
        InputFile inputFile = new InputFile(patch);
        this.listWords = inputFile.loadMap();
        return this;
    }
}

