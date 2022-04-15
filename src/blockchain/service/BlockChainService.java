package blockchain.service;

import blockchain.entity.Block;
import blockchain.entity.BlockChain;

import java.util.List;
import java.util.Objects;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class BlockChainService {

    private final BlockChain blockChain;

    public BlockChainService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public int getBlockchainSize() {
        return blockChain.getBlockList().size();
    }

    public int getNewBlockId() {
        return getBlockchainSize() + ONE.intValue();
    }

    public BlockChain getBlockChain() {
        return blockChain;
    }

    public String getLastBlockHash() {
        List<Block> blockList = blockChain.getBlockList();
        if (blockList.size() == ZERO.intValue()) {
            return "0";
        } else {
            return blockList.get(blockList.size() - ONE.intValue()).getBlockHash();
        }
    }

    public void addBlock(Block block) {
        block.setBlockId(getNewBlockId());
        blockChain.addBlock(block);
    }

    public boolean checkBlock(Block block) {
        List<Block> blockList = blockChain.getBlockList();
        if (blockList.size() == ZERO.intValue()) {
            return true;
        } else {
            return Objects.equals(block.getPreviousBlockHash(),
                    blockList.get(blockList.size() - ONE.intValue()).getBlockHash());
        }
    }

}
