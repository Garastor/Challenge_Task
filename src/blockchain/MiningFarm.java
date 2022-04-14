package blockchain;

import blockchain.entity.Block;
import blockchain.entity.BlockChain;
import blockchain.entity.Miner;
import blockchain.service.BlockChainService;

import java.util.*;

import static java.math.BigInteger.ONE;

public class MiningFarm {

    private final BlockChainService blockChainService;
    private final Scanner scanner;
    private final List<Miner> miners;
    private int prefix;
    private boolean run = true;


    public MiningFarm() {
        blockChainService = new BlockChainService(new BlockChain());
        miners = new ArrayList<>();
        prefix = 0;
        scanner = new Scanner(System.in);
    }

    public int getPrefix() {
        return prefix;
    }

    public synchronized void addBlock(Block block) {
        if (!run) return;
        if (!blockChainService.checkBlock(block)) return;
        blockChainService.addBlock(block);
        miners.forEach(miner -> miner.setBlockChainService(blockChainService));
        System.out.println(block);
        calculatePrefix(block);
        if (blockChainService.getBlockchainSize() == 15) {
            run = false;
            miners.forEach(miner -> {
                miner.setRun(false);
                miner.updateWallet(blockChainService.getBlockChain().getBlockList());
                System.out.printf("Miner #%d wallet: %d\n", miner.getMinerId(), miner.getWallet());
            });
        }
    }

    public void startMining() {
        System.out.println("Type number of miners: ");
        int numberMiners = scanner.nextInt();
        while (numberMiners > 0) {
            addMiner(new Miner(this));
            numberMiners--;
        }
        System.out.println("Type wallet course: ");
        blockChainService.getBlockChain().setWalletCourse(scanner.nextInt());
        miners.forEach(Miner::start);
    }

    private void addMiner(Miner miner) {
        miner.setMinerId(miners.size() + ONE.intValue());
        miner.setBlockChainService(blockChainService);
        miners.add(miner);
    }

    private void calculatePrefix(Block block) {
        if (block.getMiningTime() >= 60 && prefix != 0) {
            System.out.println("N was decreased by " + --prefix + "\n");
        } else if (block.getMiningTime() <= 10) {
            System.out.println("N was increased to " + ++prefix + "\n");
        } else {
            System.out.println("N stays the same\n");
        }
    }

}
