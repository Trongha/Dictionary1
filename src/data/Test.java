package data;

/**
 * Created by Trong on 4/11/2017.
 */
public class Test {
    private Word keyAsk;
    private String[] dapAn;
    private static int numDapAn = 4;

    public Test(){}
    public Test(Test test){
        setKeyAsk(test.getKeyAsk());
        setDapAn(test.getDapAn());
    }
    public Test(Word keyAsk, String[] dapAn) {
        this.keyAsk = keyAsk;
        this.dapAn = dapAn;
    }

    public Word getKeyAsk() {
        return keyAsk;
    }

    public void setKeyAsk(Word keyAsk) {
        this.keyAsk = keyAsk;
    }

    public String[] getDapAn() {
        return dapAn;
    }

    public void setDapAn(String[] dapAn) {
        this.dapAn = dapAn;
    }

    public static void setNumDapAn(int numDapAn) {
        Test.numDapAn = numDapAn;
    }

    public static int getNumDapAn() {
        return numDapAn;
    }

    @Override
    public String toString() {
        String s = this.getKeyAsk().getEnglish();
        for (String x:this.getDapAn()){
            s+= "    " + x;
        }
        return s;
    }
}
