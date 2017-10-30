package data;

/**
 * Created by Trong on 28/10/2017.
 */
public class Word {
    private String English = "";
    private String VietNam = "";

    public Word(){}
    public Word(String english, String vietNam) {
        English = english;
        VietNam = vietNam;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getVietNam() {
        return VietNam;
    }

    public void setVietNam(String vietNam) {
        VietNam = vietNam;
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s", getEnglish(), getVietNam());
    }
}
