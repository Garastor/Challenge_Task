package blockchain;

import blockchain.entity.Blockchain;
import blockchain.entity.Miner;

public class Main {
    public static void main(String[] args) {

        Blockchain blockchain = new Blockchain();

        for (int i = 0; i < 1000; i++) {
            blockchain.addMiner(new Miner(blockchain));
        }
        blockchain.start();
        
    }
}
