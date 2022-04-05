package blockchain.dao;

import blockchain.entity.Block;
import blockchain.service.BlockService;

import java.util.*;

public class Blockchain {

    private List<Block> blocks;
    private BlockService blockService;
    private int prefix;

    Scanner scanner = new Scanner(System.in);

    public Blockchain () {
        System.out.println("Enter how many zeros the hash must start with: ");
        this.prefix = scanner.nextInt();
        blocks = new ArrayList<>();
        blockService = new BlockService();
    }


    public void addBlock () {
        Long start = new Date().getTime();
        int id = blocks.size()+1;
        String previousHash;
        if(blocks.isEmpty()){
            previousHash = "0";
        } else {
            previousHash = blocks.get(blocks.size()-1).getHash();
        }
        Block block = blockService.mineBlock(prefix, id, previousHash);
        Long finish = new Date().getTime();
        blocks.add(block);
        System.out.printf(block + "Block was generating for %d seconds\n", finish-start);
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
