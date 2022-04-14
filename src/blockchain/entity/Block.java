package blockchain.entity;

import java.util.Date;
import java.util.List;

public class Block {
    private final String previousBlockHash;
    private final long timeStamp;
    private final int minerId;
    private String blockHash;
    private int blockId;
    private int magicNumber;
    private int miningTime;
    private int value;

    public Block(String previousHash, int minerId) {
        this.previousBlockHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.minerId = minerId;
        this.magicNumber = 0;
    }

    public int getMiningTime() {
        return miningTime;
    }

    public void setMiningTime(int miningTime) {
        this.miningTime = miningTime;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMinerId() {
        return minerId;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\nBlock:\n" +
                "Created by miner #  " + minerId +
                "\nminer " + minerId + " gets " + value + " VC" +
                "\nId: " + blockId +
                "\nTimestamp: " + timeStamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" +
                previousBlockHash +
                "\nHash of the block:\n" +
                blockHash +
                "\nBlock was generating for " + miningTime + " seconds";
    }

}
