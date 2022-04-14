package blockchain.service;

import blockchain.entity.Block;

import java.security.MessageDigest;
import java.util.Date;

public class BlockService {

    private static final String CRYPT = "SHA-256";

    public Block mineBlock(int prefix, String previousHash, int minerId) {
        long startTime = new Date().getTime();
        Block block = new Block(previousHash, minerId);
        block.setBlockHash(calculateBlockHash(block));
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!block.getBlockHash().substring(0, prefix).equals(prefixString)) {
            block.setMagicNumber(block.getMagicNumber() + 1);
            block.setBlockHash(calculateBlockHash(block));
        }
        block.setMiningTime((int) (System.currentTimeMillis() - startTime));
        return block;
    }

    private String calculateBlockHash(Block block) {
        String input = block.getPreviousBlockHash() + block.getTimeStamp() + block.getMagicNumber();
        try {
            MessageDigest digest = MessageDigest.getInstance(CRYPT);
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
