package blockchain.service;

import blockchain.entity.Block;
import blockchain.entity.BlockChain;
import blockchain.entity.Miner;
import blockchain.util.Constant;

import java.util.*;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class MiningFarmService {

    private final BlockChainService blockChainService;
    private final Scanner scanner;
    private final List<Miner> miners;
    private int prefix;
    private boolean run = true;


    public MiningFarmService() {
        blockChainService = new BlockChainService(new BlockChain());
        miners = new ArrayList<>();
        prefix = Constant.DEFAULT_PREFIX;
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
        if (blockChainService.getBlockchainSize() == Constant.MAX_BLOCKCHAIN_SIZE) {
            run = false;
            miners.forEach(miner -> {
                miner.setRun(false);
                miner.setWallet(blockChainService.getBlockChain().getBlockList());
                System.out.printf("Miner #%d wallet: %d\n", miner.getMinerId(), miner.getWallet());
            });
        }
    }

    public void startMining() {
        System.out.println(Constant.TYPE_NUMBER_OF_MINERS);
        int numberMiners = scanner.nextInt();
        while (numberMiners > ZERO.intValue()) {
            addMiner(new Miner(this));
            numberMiners--;
        }
        System.out.println(Constant.TYPE_WALLET_COURSE);
        blockChainService.getBlockChain().setWalletCourse(scanner.nextInt());
        miners.forEach(Miner::start);
    }

    private void addMiner(Miner miner) {
        miner.setMinerId(miners.size() + ONE.intValue());
        miner.setBlockChainService(blockChainService);
        miners.add(miner);
    }

    private void calculatePrefix(Block block) {
        int secondsUpperLimit = 60;
        int secondsLowerLimit = 10;
        if (block.getMiningTime() >= secondsUpperLimit && prefix != ZERO.intValue()) {
            System.out.println(Constant.N_DECREASED);
        } else if (block.getMiningTime() <= secondsLowerLimit) {
            System.out.println(Constant.N_INCREASED);
        } else {
            System.out.println(Constant.N_STAYS_THE_SAME);
        }
    }

}
