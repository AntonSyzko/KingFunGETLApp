package com.king.fung.services;

public interface UserNameHashingService {

    String encoding = "SHA-256";

    String encodeUserName(String userName, String encoding);
}
