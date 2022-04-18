package blockchain;

import blockchain.service.MiningFarmService;

public class Main {
    public static void main(String[] args) {

        MiningFarmService miningFarm = new MiningFarmService();

        miningFarm.startMining();

    }
}
