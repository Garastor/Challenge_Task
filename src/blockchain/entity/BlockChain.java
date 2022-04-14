package blockchain.entity;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private final List<Block> blockList;
    private int walletCourse;

    public BlockChain () {
        blockList = new ArrayList<>();
    }

    public void setWalletCourse(int walletCourse) {
        this.walletCourse = walletCourse;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void addBlock (Block block) {
        block.setValue(walletCourse);
        blockList.add(block);
    }
}
