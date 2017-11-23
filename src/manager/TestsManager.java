package manager;

import data.Group;
import data.Test;
import data.Word;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Trong on 4/11/2017.
 */

/**
 *
 */
public class TestsManager {
    //Map lưu các từ để hỏi, tài nguyên để hỏi
    private static HashMap<String, Word> listWords;
    //List các Tét sinh ra
    private Test[] tests;
    private int numOfTest = 5;
    // HashSet lưu các từ đã được hỏi trong lần test này. mục đích ko hỏi trùng
    private static HashSet<String> wordAsks;
    private int scores = 0;
    private final int scorePerTest = 10;
    private int maxScores;

    public TestsManager(){}

    /**
     * Thường dùng contructor này
     * @param listWords
     * @param numOfTest
     */
    public TestsManager(HashMap<String, Word> listWords, int numOfTest) {
        setListWords(listWords);
        this.numOfTest = numOfTest;
        wordAsks = new HashSet<String>();
        this.scores = 0;
        this.maxScores = numOfTest*scorePerTest;
    }

    public TestsManager(HashMap<String, Word> listWords) {
        setListWords(listWords);
        this.numOfTest = this.listWords.size()-1;
        wordAsks = new HashSet<String>();
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
        TestsManager.listWords = listWords;
        System.out.println("_______________Set List Words to Learn COmplete!");
    }

    public Test[] getTests() {
        return tests;
    }

    /**
     * Hàm sinh 1 test
     * @param listKeyEnglish List các từ để hỏi
     * @return
     */
    public Test sinh1Test(String[] listKeyEnglish){

        int numKeyE = listKeyEnglish.length-1;
        Test newtest = new Test();

        //Set Kiểu câu hỏi (Anh -Việt hay Việt Anh)
        Boolean typeAnhViet = (Math.random() >= 0.5) ? true : false;
        newtest.setTypeAnhViet(typeAnhViet);
        System.out.println("typeAnhViet : " + typeAnhViet);

        int indexOfListKey=0;
        //Sinh key hỏi
        do{
            indexOfListKey = (int)Math.round(Math.random() * (numKeyE-1));
            System.out.println(listKeyEnglish[indexOfListKey]);
        }while (wordAsks.contains(listKeyEnglish[indexOfListKey]));
        wordAsks.add(listKeyEnglish[indexOfListKey]);
        newtest.setKeyAsk(listWords.get(listKeyEnglish[indexOfListKey]));

        //Sinh đáp án
        HashSet<Word> dapAnArray = new HashSet<Word>();

        dapAnArray.add(listWords.get(listKeyEnglish[indexOfListKey]));

        for (int i=1 ; i<Test.getNumDapAn() ; i++){
            do{
                indexOfListKey = (int)Math.round(Math.random() * (numKeyE-1));
            }while (dapAnArray.contains(listWords.get(listKeyEnglish[indexOfListKey])));
            dapAnArray.add(listWords.get(listKeyEnglish[indexOfListKey]));
        }

        newtest.setDapAn(dapAnArray.toArray(new Word[dapAnArray.size()]));
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
