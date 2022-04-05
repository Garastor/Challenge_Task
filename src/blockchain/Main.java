package blockchain;

import blockchain.dao.Blockchain;
import blockchain.entity.Block;

public class Main {
    public static void main(String[] args) {

        Blockchain blockchain = new Blockchain();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();


    }
}
