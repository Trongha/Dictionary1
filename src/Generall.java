import data.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Generall {
    public static String[] getKeyOfHashMap(HashMap<String, Word> map){
        String[] keyArray = new String[map.size()];

        Set<String> set = map.keySet();
        Iterator i = set.iterator();
        int i_key =0;
        while (i.hasNext()){
            keyArray[i_key++] =(String)i.next();
        }
        return keyArray;
    }
}
