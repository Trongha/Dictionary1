package manager;

import data.Word;

import java.util.HashMap;

/**
 * Created by Trong on 28/10/2017.
 */

public class appManager {
    private HashMap<String, Word> data = new HashMap();

    public appManager(){}

    public appManager(HashMap data) {
        this.data = data;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
    public void loadData(){
        data.put("hello", new Word("hello", "xin chào"));
        data.put("one", new Word("one", "1"));
        data.put("popular", new Word("popular", "Phổ biến"));
        data.put("image", new Word("image", "ảnh"));
        data.put("manage", new Word("manage", "Quản lý"));
    }
    public Word searchE(String english){
        /*if (data.isEmpty())
            return "data is Empty";

        if (!data.containsKey(english))
            return "chua update";*/
        english = english.toLowerCase();
        return data.get(english);
    }
}
