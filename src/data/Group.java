package data;

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
        this.patch = patch;
        this.setName(name);
        this.loadFile();
    }
    public Group(String patch){
        this.patch = patch;
        this.setName("");
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
        else this.name = _name;
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
    public void deleteWord(String name){
        if (this.listWords.containsKey(name))
            this.listWords.remove(name);
    }

/*    public void editWord(String name, String newEnglish, String newVietNam){
        if (this.listWords.containsKey(name)){
            this.listWords.remove(name);
            this.listWords.put(newEnglish, new Word(newEnglish, newVietNam));
            System.out.println("delete Complete!");
        }
    }*/

    public boolean delete(String englishKey){
        if (this.getListWords().containsKey(englishKey)){
            this.getListWords().remove(englishKey);
            System.out.println(">>>>>>>>>>Delete complete!");
            return  true;
        }
        System.out.println(">>>>>>>>>>Delete failed!");
        return  true;
    }

    public Word search(String key){
        System.out.println("List dang search: " + this.getName());
        if (!listWords.isEmpty()){
            System.out.println("key: " + key);
            if (this.listWords.containsKey(key))
                return this.listWords.get(key);
        }
        Word error = new Word(key, "", "");
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
        return this;
    }
    public void outFile(){
        Output outputFile = new Output(this.getName());
        outputFile.outFile(this.getListWords());
        System.out.println("OutFile Complete!");
    }
}

