package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trong on 29/10/2017.
 */
public class Group {
    private HashMap<String, Word> listWords = new HashMap<>();
    private String name = "";
    public Group(){}

    public HashMap<String, Word> getListWords() {
        return listWords;
    }

    public void setListWords(HashMap<String, Word> listWords) {
        this.listWords = listWords;
    }
    public Group clone(Group o){
        Group newgroup = new Group();
        newgroup.setName(o.name);
        newgroup.setListWords(o.listWords);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String s  = "";
        ArrayList<Word> listWord = new ArrayList<>(listWords.values());
        for (int i=0 ; i<listWord.size() ; i++){
            s+= String.format("%s%n", listWord.get(i));
        }
    }
}

