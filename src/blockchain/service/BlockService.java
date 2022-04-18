package blockchain.service;

import blockchain.entity.Block;
import blockchain.util.Constant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class BlockService {

    public Block mineBlock(int prefix, String previousHash, int minerId) {
        long startTime = new Date().getTime();
        Block block = new Block(previousHash, minerId);
        block.setBlockHash(calculateBlockHash(block));
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!block.getBlockHash().substring(ZERO.intValue(), prefix).equals(prefixString)) {
            block.setMagicNumber(block.getMagicNumber() + ONE.intValue());
            block.setBlockHash(calculateBlockHash(block));
        }
        block.setMiningTime((int) (System.currentTimeMillis() - startTime));
        return block;
    }

    private String calculateBlockHash(Block block) {
        String input = new StringBuilder()
                .append(block.getPreviousBlockHash())
                .append(block.getTimeStamp())
                .append(block.getMagicNumber())
                .toString();
        try {
            MessageDigest digest = MessageDigest.getInstance(Constant.SHA_256);
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(Constant.MAX_BINARY_NUMBER & elem);
                if (hex.length() == ONE.intValue()) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
