package blockchain.dao;

import blockchain.entity.Block;
import blockchain.service.BlockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blockchain {

    private List<Block> blocks;
    private BlockService blockService;

    public Blockchain () {
        blocks = new ArrayList<>();
        blockService = new BlockService();
    }


    public void addBlock () {
        int id = blocks.size()+1;
        String previousHash;
        if(blocks.isEmpty()){
            previousHash = "0";
        } else {
            previousHash = blocks.get(blocks.size()-1).getHash();
        }
        blocks.add(blockService.generateBlock(id, previousHash));
    }

    public Block getBlock (int id) {
        return this.blocks.get(id-1);
    }

    public List<Block> getAllBlocks () {
        return blocks;
    }

    public boolean checkBlockchain () {
        boolean checked = true;
        for (int i=1; i<blocks.size(); i++) {
            if(!Objects.equals(blocks.get(i).getPreviousHash(), blocks.get(i - 1).getHash())) {
                checked = false;
                break;
            }
        } return checked;
    }

}
