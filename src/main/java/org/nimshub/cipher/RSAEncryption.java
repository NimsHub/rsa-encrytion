package org.nimshub.cipher;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAEncryption {

    private final BigInteger privateKey;
    private final BigInteger publicKey;
    private final BigInteger modulus;

    public RSAEncryption(int bitLength) {
        SecureRandom random = new SecureRandom();

        // Generate two large prime numbers
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);

        modulus = p.multiply(q);

        // Calculate totient function (phi)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent
        publicKey = BigInteger.valueOf(65537); // Common choice for public exponent

        // Calculate private exponent using modular inverse
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        RSAEncryption rsa = new RSAEncryption(1024);

        BigInteger originalMessage = BigInteger.valueOf(1234567890L); // Message to be encrypted

        System.out.println("Original Message: " + originalMessage);

        // Encryption
        BigInteger encryptedMessage = rsa.encrypt(originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decryption
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
