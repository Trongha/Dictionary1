package manager;

import data.Group;
import data.Level;
import data.Word;

import java.util.LinkedList;
import java.util.Queue;

public class FlashcardsManager {
    private Queue<Word> hardQueue = new LinkedList<Word>();
    private Queue<Word> moderateQueue = new LinkedList<Word>();
    private Queue<Word> esasyQueue = new LinkedList<Word>();
    int numOfCard = 0;
    private static Double level1 = 0.5;
    private static Double level2 = 0.8;

    public FlashcardsManager() {
    }

    public FlashcardsManager(Group _group) {
        set3Queue(_group);
    }

    public void add1Word(Word word) {
        System.out.println(word);
        switch (word.getLevel()) {
            case hard: {
                hardQueue.add(word);
                break;
            }
            case nothing:
            case esasy: {
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

    public void set3Queue(Group group) {
        System.out.println("---->  set Group to Flashcard : " + group.getName());
        for (Word word : group.getListWords().values()) {
            this.add1Word(word);
        }
        System.out.println("size hard    : " + hardQueue.size());
        System.out.println("size moretate: " + moderateQueue.size());
        System.out.println("size esasy   : " + esasyQueue.size());
        System.out.println(esasyQueuePoll());
    }

    public int getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }

    public Word hardQueuePoll() {
        if (!hardQueue.isEmpty()) {
            System.out.println("----> return Word in hard");
            return hardQueue.poll();
        } else
            return  new Word("", "");
    }

    public Word moderateQueuePoll() {
        if (!moderateQueue.isEmpty()) {
            System.out.println("----> return Word in moderate");
            return moderateQueue.poll();
        } else
            return  new Word("", "");
    }

    public Word esasyQueuePoll() {
        if (!esasyQueue.isEmpty()) {
            System.out.println("----> return Word in esasy");
            return esasyQueue.poll();
        } else
            return new Word("", "");
    }

    public Word newWordToCard() {
        if (hardQueue.size() == 0) {
            this.level1 = 0.0;
        }
        if (moderateQueue.size() == 0) {
            this.level2 = 0.0;
        }

        Double random = Math.random();
        System.out.println(random);

        if (random <= this.level1) {
            return hardQueuePoll();
        } else if (random <= this.level2) {
            return moderateQueuePoll();
        } else {
            return esasyQueuePoll();
        }
    }

    public Queue<Word> getHardQueue() {
        return hardQueue;
    }

    public void setHardQueue(Queue<Word> hardQueue) {
        this.hardQueue = hardQueue;
    }

    public Queue<Word> getModerateQueue() {
        return moderateQueue;
    }

    public void setModerateQueue(Queue<Word> moderateQueue) {
        this.moderateQueue = moderateQueue;
    }

    public Queue<Word> getEsasyQueue() {
        return esasyQueue;
    }

    public void setEsasyQueue(Queue<Word> esasyQueue) {
        this.esasyQueue = esasyQueue;
    }
}
