package blockchain.service;

import blockchain.entity.Block;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BlockService {

    Scanner scanner = new Scanner(System.in);

    public Block mineBlock (int prefix, int id, String previousHash) {
        Block block = new Block(id, previousHash);
        block.setHash(calculateBlockHash(block));
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!block.getHash().substring(0, prefix).equals(prefixString)) {
            block.setNonce(block.getNonce()+1);
            block.setHash(calculateBlockHash(block));
        }
        return block;
    }

    private String calculateBlockHash (Block block){
        String input = block.getPreviousHash()+block.getTimeStamp()+block.getNonce();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


}
