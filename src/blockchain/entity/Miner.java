package blockchain.entity;

import blockchain.MiningFarm;
import blockchain.service.BlockChainService;
import blockchain.service.BlockService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Miner extends Thread {

    private final BlockService blockService;
    private int minerId;
    private int wallet;
    private boolean run;
    private MiningFarm miningFarm;
    private BlockChainService blockChainService;

    public Miner(MiningFarm miningFarm) {
        run = true;
        blockService = new BlockService();
        this.miningFarm = miningFarm;
        wallet = 0;
    }

    public int getWallet() {
        return wallet;
    }

    public void setMinerId(int id) {
        minerId = id;
    }

    public int getMinerId() {
        return minerId;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void setBlockChainService(BlockChainService bcs) {
        blockChainService = bcs;
    }

    @Override
    public void run() {
        while (run) {
            Block block = blockService.mineBlock(miningFarm.getPrefix(),
                    blockChainService.getLastBlockHash(),
                    minerId);
            miningFarm.addBlock(block);
        }
    }

    public void updateWallet(List<Block> blockList) {
        blockList.stream().filter(block -> block.getMinerId() == minerId)
                .forEach(block -> wallet += block.getValue());
    }

}
