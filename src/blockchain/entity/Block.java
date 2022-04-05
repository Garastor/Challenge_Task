package blockchain.entity;

import java.util.Date;


public class Block {
    int id;
    private String hash;
    private String previousHash;
    private long timeStamp;

    public Block(int id, String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Block:\n" +
        "Id: " + id +
        "\nTimestamp: " + timeStamp +
        "\nHash of the previous block:\n" +
        previousHash +
        "\nHash of the block:\n" +
        hash + "\n";
    }
}
