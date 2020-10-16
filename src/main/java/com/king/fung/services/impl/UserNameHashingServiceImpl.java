package com.king.fung.services.impl;

import com.king.fung.services.UserNameHashingService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserNameHashingServiceImpl implements UserNameHashingService {

    @Override
    public String encodeUserName(String userName, String encoding) {
        final byte[] shaed = digestToSHA(userName, encoding);
        return bytesToHex(shaed);
    }

    public byte[] digestToSHA(String uuid, String encoding) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(encoding);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(uuid.getBytes(StandardCharsets.UTF_8));

        return hash;

    }

    public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
