package data;

/**
 * Created by Trong on 28/10/2017.
 */
public class Word {
    private String English = "";
    private String VietNam = "";

    public Word(){}
    public Word(String english, String vietNam) {
        setVietNam(vietNam);
        setEnglish(english);
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        english = english.trim();
        english = english.toLowerCase();
        this.English = english;
    }

    public String getVietNam() {
        return VietNam;
    }

    public void setVietNam(String vietNam) {
        vietNam = vietNam.trim();
        vietNam = vietNam.toLowerCase();
        this.VietNam = vietNam;
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s", getEnglish(), getVietNam());
    }
}
