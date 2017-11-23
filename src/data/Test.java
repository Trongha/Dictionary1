package data;

/**
 * Created by Trong on 4/11/2017.
 */
public class Test {
    private Word keyAsk;
    private String keyAskString = "";
    private boolean typeAnhViet = true;
    private String[] dapAn;
    private static int numDapAn = 4;


    public Test(){}
    public Test(Test test){
        setTypeAnhViet(test.typeAnhViet);
        setKeyAsk(test.getKeyAsk());
        dapAn = test.getDapAn();
    }

    public Word getKeyAsk() {
        return keyAsk;
    }

    public void setKeyAsk(Word _keyAsk) {
        this.keyAsk = new Word();
        this.keyAsk.clone(_keyAsk);
        this.setKeyAskString();
    }

    public String[] getDapAn() {
        return dapAn;
    }

    public void setDapAn(Word[] _dapAn) {
        this.dapAn = new String[4];
        int i=0;
        if (typeAnhViet){
            for (Word word : _dapAn){
                this.dapAn[i++] = word.getVietNam();
            }
        }else {
            for (Word word : _dapAn){
                this.dapAn[i++] = word.getEnglish();
            }
        }
    }

    public static void setNumDapAn(int numDapAn) {
        Test.numDapAn = numDapAn;
    }

    public static int getNumDapAn() {
        return numDapAn;
    }

    public String getKeyAskString() {
        return keyAskString;
    }

    public void setKeyAskString() {
        if (typeAnhViet){
            this.keyAskString = this.keyAsk.getEnglish();
        }else {
            this.keyAskString = this.keyAsk.getVietNam();
        }

    }

    public boolean checkDapAn(String chose){
        if (this.typeAnhViet){
            return  chose.equals(this.keyAsk.getVietNam());
        }else  {
            return chose.equals(this.keyAsk.getEnglish());
        }
    }

    public boolean isTypeAnhViet() {
        return typeAnhViet;
    }

    public void setTypeAnhViet(boolean typeAnhViet) {
        this.typeAnhViet = typeAnhViet;
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
