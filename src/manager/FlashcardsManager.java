package manager;

import data.Group;
import data.Word;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FlashcardsManager {
    private Queue<Word> hardQueue = new LinkedList<Word>();
    private Queue<Word> moderateQueue = new LinkedList<Word>();
    private Queue<Word> esasyQueue = new LinkedList<Word>();
    private HashSet<String> wordSelected = new HashSet<String>(); //Lưu các từ tiếng anh đã học
    private Word oldWord = new Word();
    private Word newWord = new Word();
    int numOfCard = 0;

    //Mật độ sinh thẻ theo cấp độ
    private static Double hard = 0.5;
    private static Double moderate = 0.8;

    public FlashcardsManager() {
    }

    public FlashcardsManager(Group _group, int _numOfCard) {
        setNumOfCard(_numOfCard);
        set3Queue(_group);

    }

    /**
     * Thêm 1 từ, hàm chọn queue thích hợp cho từ theo mức độ
     *
     * @param word
     */
    public void add1Word(Word word) {
        System.out.println(word);
        switch (word.getLevel()) {
            case hard: {
                hardQueue.add(word);
                break;
            }
            case nothing:
            case easy: {
                esasyQueue.add(word);
                break;
            }
            case moderate: {
                moderateQueue.add(word);
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * Chia các từ vào Queue theo mức độ từ lần học trước.
     *
     * @param group
     */
    public void set3Queue(Group group) {
        int i = 0;
        System.out.println("---->  set Group to Flashcard : " + group.getName());
        for (Word word : group.getListWords().values()) {
            i++;
            this.add1Word(word);
            if (i == numOfCard)
                break;
        }

        System.out.println("  size hard    : " + hardQueue.size());
        System.out.println("  size moretate: " + moderateQueue.size());
        System.out.println("  size easy   : " + esasyQueue.size());
    }

    public int getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }

    public int getNumWordSelected(){
        return wordSelected.size();
    }
    /**
     * Lấy ra 1 từ mức hard
     *
     * @return
     */
    public Word hardQueuePoll() {
        System.out.println("----> return Word in hard");
        return hardQueue.poll();
    }

    /**
     * Lấy ra 1 từ mức trung bình
     *
     * @return
     */
    public Word moderateQueuePoll() {
        System.out.println("----> return Word in moderate");
        return moderateQueue.poll();
    }

    /**
     * Lấy ra 1 từ mức easy
     *
     * @return
     */
    public Word esasyQueuePoll() {
        System.out.println("----> return Word in easy");
        return esasyQueue.poll();
    }

    /**
     * Sinh 1 từ trong bộ từ
     *
     * @return
     */
    public Word newWordToCard() {
        Double levelHard = this.hard;
        Double levelModerate = this.moderate;
        if (esasyQueue.size() == 0){
            levelModerate = 1.0;
            levelHard = this.moderate;
        }
        if (moderateQueue.size() == 0) {
            levelHard = this.moderate;
            levelModerate = 0.0;
        }
        if (hardQueue.size() == 0) {
            levelHard = 0.0;
        }
        oldWord = newWord;
        do{
            Double random = Math.random();
            System.out.println(random);

            if (random <= levelHard) {
                newWord = hardQueuePoll();
            } else if (random <= levelModerate) {
                newWord = moderateQueuePoll();
            } else {
                newWord = esasyQueuePoll();
            }
            if (newWord.equals(oldWord))
                this.add1Word(newWord);
        }while (newWord.equals(oldWord));
        wordSelected.add(newWord.getEnglish());
        return newWord;
    }
}
