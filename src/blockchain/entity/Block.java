package blockchain.entity;

import java.util.Date;

public class Block {
    private final int minerId;
    private int blockId;
    private String blockHash;
    private final String previousBlockHash;
    private final long timeStamp;
    private int magicNumber;
    private int miningTime;

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

    @Override
    public String toString() {
        return "\nBlock:\n" +
                "Created by miner #  " + minerId +
                "\nId: " + blockId +
                "\nTimestamp: " + timeStamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" +
                previousBlockHash +
                "\nHash of the block:\n" +
                blockHash + "\n" +
                "Block was generating for " + miningTime + " seconds";
    }
}
