package manager;

import data.Group;
import data.Test;
import data.Word;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Trong on 4/11/2017.
 */
public class Learning {
    private static HashMap<String, Word> listWords;
    private Test[] tests;
    private int numOfTest = 5;
    private static HashSet<String> keyAsks;
    private int scores = 0;
    private final int scorePerTest = 10;
    private int maxScores;

    public Learning(){}
    public Learning(HashMap<String, Word> listWords, int numOfTest) {
        setListWords(listWords);
        this.numOfTest = numOfTest;
        keyAsks = new HashSet<String>();
        this.scores = 0;
        this.maxScores = numOfTest*scorePerTest;
    }
    public Learning(HashMap<String, Word> listWords) {
        setListWords(listWords);
        this.numOfTest = this.listWords.size()-1;
        keyAsks = new HashSet<String>();
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getScorePerTest() {
        return scorePerTest;
    }

    public int getMaxScores() {
        return maxScores;
    }

    public void setMaxScores(int maxScores) {
        this.maxScores = maxScores;
    }

    public int getNumOfTest() {
        return numOfTest;
    }

    public void setNumOfTest(int numOfTest) {
        this.numOfTest = numOfTest;
    }

    public static HashMap<String, Word> getListWords() {
        return listWords;
    }


    public static void setListWords(HashMap<String, Word> listWords) {
        Learning.listWords = listWords;
        System.out.println("_______________Set List Words to Learn COmplete!");
    }

    public Test[] getTests() {
        return tests;
    }

    public Test sinh1Test( String[] listKey){
        int numOfGroup = listKey.length-1;
        Test newtest = new Test();
        int indexOfListKey=0;
        //Sinh key hỏi
        do{
            indexOfListKey = (int)Math.round(Math.random() * (numOfGroup-1));
            System.out.println(listKey[indexOfListKey]);
          /*  for (String s : this.keyAsks){
                System.out.println(s);
            }*/
        }while (keyAsks.contains(listKey[indexOfListKey]));
        keyAsks.add(listKey[indexOfListKey]);
        newtest.setKeyAsk(listWords.get(listKey[indexOfListKey]));

        //Sinh đáp án
        HashSet<String> dapAnArray = new HashSet<String>();
        dapAnArray.add(listWords.get(listKey[indexOfListKey]).getVietNam());
        for (int i=1 ; i<Test.getNumDapAn() ; i++){
            do{
                indexOfListKey = (int)Math.round(Math.random() * (numOfGroup-1));
//                System.out.println(indexOfListKey);
            }while (dapAnArray.contains(listWords.get(listKey[indexOfListKey]).getVietNam()));
            dapAnArray.add(listWords.get(listKey[indexOfListKey]).getVietNam());
        }
        newtest.setDapAn(dapAnArray.toArray(new String[dapAnArray.size()]));
        return newtest;
    }

    public void sinhTests() {
        String[] listKey = Group.getKeyOfHashMap(listWords);    //Lấy tất cả các từ tiếng anh
        System.out.println("Size list sinh test: " + listWords.size());
        tests = new Test[numOfTest];
        for (int i=0 ; i<numOfTest ; i++){
            tests[i] = new Test(this.sinh1Test(listKey));
        }

    }

    public void trueIncrease(){
        this.scores+= this.scorePerTest;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0 ; i<numOfTest ; i++){
            s+= String.format("%d: %s\n", i, tests[i]);
        }
        return s;
    }
}
