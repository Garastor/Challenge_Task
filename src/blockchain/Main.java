package blockchain;

import blockchain.entity.Miner;

public class Main {
    public static void main(String[] args) {

        MiningFarm miningFarm = new MiningFarm();

        miningFarm.startMining();

    }
}
