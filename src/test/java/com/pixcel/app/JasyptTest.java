package com.pixcel.app;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class JasyptTest {
@Autowired
StringEncryptor jasypt;
@Test
void test() {
	String plainText = ""; // 암호화할 실제 원본값
	String encryptedText = jasypt.encrypt(plainText);
	System.out.println("Encrypted Result: ");
	System.out.println(encryptedText);
    }
}