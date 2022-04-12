package blockchain.entity;

import blockchain.service.BlockService;

public class Miner implements Runnable {

    private int minerId;
    private final BlockService blockService;
    private Blockchain blockchain;
    private boolean run;

    public Miner (Blockchain blockchain) {
        blockService = new BlockService();
        this.blockchain = blockchain;
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            Block block = blockService.mineBlock(blockchain.getPrefix(), blockchain.getPreviousHash(), minerId);
            blockchain.putBlock(block);
        }
    }

    public void setMinerId(int minerId) {
        this.minerId = minerId;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void setBlockChain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public boolean isRun() {
        return run;
    }

    public int getMinerId() {
        return minerId;
    }
}
