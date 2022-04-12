package blockchain.entity;

import java.util.*;

import static java.math.BigInteger.ONE;

public class Blockchain {

    private final List<Block> blockchain;
    private final List<Miner> miners;
    private int prefix;
    private boolean run = true;

    public Blockchain() {
        blockchain = new ArrayList<>();
        miners = new ArrayList<>();
        prefix = 0;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(Block block) {
        if (block.getMiningTime() >= 60 && prefix != 0) {
            System.out.println("N was decreased by " + --prefix + "\n");
        } else if (block.getMiningTime() <= 10) {
            System.out.println("N was increased to " + ++prefix + "\n");
        } else {
            System.out.println("N stays the same\n");
        }
    }

    public String getPreviousHash() {
        if (blockchain.size() == 0) {
            return "0";
        } else {
            return blockchain.get(getPreviousBlockId()).getBlockHash();
        }
    }

    public int getPreviousBlockId() {
        return blockchain.size() - ONE.intValue();
    }

    public void addMiner(Miner miner) {
        miner.setMinerId(miners.size() + ONE.intValue());
        miners.add(miner);
    }

    public void start() {
        miners.forEach(Miner::run);
    }

    public synchronized void putBlock(Block block) {
        if (!run) return;
        if (!checkBlock(block)) return;
        block.setBlockId(blockchain.size());
        blockchain.add(block);
        miners.forEach(miner -> miner.setBlockChain(this));
        System.out.println(block);
        setPrefix(block);
        if (blockchain.size() == 5) {
            run = false;
            miners.forEach(miner -> miner.setRun(false));
        }
    }

    private boolean checkBlock(Block block) {
        if (blockchain.size() == 0) {
            return true;
        } else {
            return Objects.equals(block.getPreviousBlockHash(), blockchain.get(getPreviousBlockId()).getBlockHash());
        }
    }

}
