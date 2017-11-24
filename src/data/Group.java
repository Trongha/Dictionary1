package data;

import java.io.File;
import java.util.*;


/**
 * Created by Trong on 29/10/2017.
 */
public class Group{
    private HashMap<String, Word> listWords = new HashMap<>();
    private String name = "";
    private String patch = "";
    private Integer size = 0;
    private Integer numWordStudied = 0;
    private Integer numWordEsasy = 0;

    public Group(){}
    public Group(String _name, Group gr1, Group gr2){
        this.setName(_name);
        this.listWords.putAll(gr1.listWords);
        this.listWords.putAll(gr2.listWords);
    }
    public Group(String name, String patch) {
        this.patch = patch;
        this.setName(name);
        this.loadFile();
        this.size = this.listWords.size();
    }
    public Group(String patch){
        this.patch = patch;
        this.setName("");
        this.loadFile();
        this.size = this.listWords.size();
        System.out.println(size);
//        this.outFile();
    }

    public void refresh(){
        size = this.listWords.size();
        numWordStudied = 0;
        numWordEsasy = 0;
        for (Word word : this.listWords.values()){
            if (word.getLevel() != Level.nothing){
                numWordStudied++;
                if (word.getLevel() == Level.easy){
                    numWordEsasy++;
                }
            }
        }
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
        Arrays.sort(keyArray);
        return keyArray;
    }

    /**
     * Get Key of this group
     * @return
     */
    public String[] getKeyOfGroup(){
        return getKeyOfHashMap(this.listWords);
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

    public void setName(String _name) {
        _name = _name.trim();
        if (_name.equals(""))
            this.setName(patch.substring(patch.lastIndexOf('\\')+1, patch.lastIndexOf('.')));
        else {
            this.name = _name;
        }
    }

    public void reName(String _name){

        this.setName(_name);

        File oldName = new File(patch);
        patch = patch.substring(patch.lastIndexOf('\\')) + _name + ".xlsx";
        File newName = new File(patch);

        if(oldName.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
    }

    public void delete(){
        System.out.println("delete " + patch);
        File file = new File(this.patch);
        if (file.delete()){
            System.out.println("Delete file complete!");
        }else {
            System.out.println("Can't Delete File");
        }
    }

    public String getPatch() {
        return patch;
    }

    /**
     * Gộp thêm một group nữa
     * @param x
     */
    public void addGroup(Group x){
        this.listWords.putAll(x.getListWords());
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public void addWord(Word newWord){
        this.listWords.put(newWord.getEnglish(), newWord);
    }
/*

    public void deleteWord(Word wordDelete){
        if (this.listWords.containsKey(wordDelete.getEnglish()))
            this.listWords.remove(wordDelete.getEnglish());
    }
*/

    public boolean delete(String englishKey){
        if (this.getListWords().containsKey(englishKey)){
            this.getListWords().remove(englishKey);
            System.out.println(">>>>>>>>>>Delete complete!");
            return  true;
        }
        System.out.println(">>>>>>>>>>Delete failed!");
        return  true;
    }

    /**
     * Search chính xác một từ trong nhớm
     * @param key
     * @return
     */
    public Word search(String key){
        System.out.println("List dang search: " + this.getName());
        if (!listWords.isEmpty()){
            System.out.println("key: " + key);
            if (this.listWords.containsKey(key))
                return this.listWords.get(key);
        }
        Word error = new Word(key, "", "", "");
        if (listWords.isEmpty()){
            error.setVietNam("groups is empty");
        }
        else error.setVietNam("Khong tim thay");
        return error;
    }

    public void setNum(){
        for (Word word : this.listWords.values()){
            if (word.getLevel() != Level.nothing){
                numWordStudied ++;
                if (word.getLevel() == Level.easy){
                    numWordEsasy++;
                }
            }
        }
    }

    /**
     * Search English
     * @param key
     * @return    hashmap Các từ có chứa key, Kiểu trả về là word.
     */
    public HashSet<Word> search2(String key){
        HashSet<Word> mapSearch = new HashSet<>();
        if (!listWords.isEmpty()){
            for(Word x: this.listWords.values()){
                if (x.getEnglish().indexOf(key)==0){
                    mapSearch.add(x);
                }
            }
        }
        return mapSearch;
    }

    @Override
    public String toString() {
        String s  ="  * " + this.name + " :\n";
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
        Input inputFile = new Input(patch);
        this.listWords = inputFile.loadMap();
        this.setNum();
        return this;
    }
    public void outFile(){
        Output outputFile = new Output(this.getName());
        outputFile.outFile(this.getListWords());
        System.out.println("OutFile Complete!");
    }

    public Integer getSize() {
        return size;
    }

    /**
     * Số lượng từ trong topic
     * @param size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumWordStudied() {
        return numWordStudied;
    }

    public void setNumWordStudied(Integer numWordStudied) {
        this.numWordStudied = numWordStudied;
    }

    public Integer getNumWordEsasy() {
        return numWordEsasy;
    }

    public void setNumWordEsasy(Integer numWordEsasy) {
        this.numWordEsasy = numWordEsasy;
    }
}

