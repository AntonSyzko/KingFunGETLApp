package com.king.fung.services;

import com.king.fung.services.impl.UserNameHashingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserNameHashingServiceImplTest {

    @InjectMocks
    private UserNameHashingServiceImpl underTest;

    @Test
    public void encodeUserNameTest() throws NoSuchAlgorithmException {
        String sha256Encoded = underTest.encodeUserName("jane", UserNameHashingService.encoding);
        assertNotNull(sha256Encoded);
        assertEquals(sha256Encoded, "81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2");
    }

    @Test
    public void digestToSHA() {
        byte[] encodedBytes = underTest.digestToSHA("jane", UserNameHashingService.encoding);
        assertNotNull(encodedBytes);
        assertEquals(encodedBytes.length, 32);
    }

    @Test
    public void bytesToHex() {
        Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
        byte[] encodedBytes = underTest.digestToSHA("jane", UserNameHashingService.encoding);
        assertNotNull(encodedBytes);

        String hex = underTest.bytesToHex(encodedBytes);
        final Matcher matcher = HEXADECIMAL_PATTERN.matcher(hex);
        assertNotNull(hex);
        assertTrue(matcher.matches());
    }
}