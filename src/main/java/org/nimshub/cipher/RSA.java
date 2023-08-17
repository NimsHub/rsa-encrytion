package org.nimshub.cipher;

import org.nimshub.utils.Logger;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.nimshub.utils.Constants.BIT_LENGTH;

/**
 * Implementation of RSA Encryption
 *
 * @author Sabaragamuwa S.B.N.M
 * @regNo EG/2018/3443
 */
public class RSA {
    Logger logger = new Logger();
    private final BigInteger modulus;
    private final BigInteger publicKey;
    private final BigInteger privateKey;

    public RSA() {
        SecureRandom random = new SecureRandom();

        logger.info("generating two large prime numbers p and q");
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH, random);
        logger.info(String.format("randomly assigned value for p : %s %n",p));
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH, random);
        logger.info(String.format("randomly assigned value for q : %s %n",q));

        modulus = p.multiply(q);
        logger.info(String.format("calculated value of modulus from p and q : %s %n",modulus));

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        logger.info(String.format("calculated totient function : %s %n",phi));

        publicKey = BigInteger.probablePrime(BIT_LENGTH / 2, random);

        privateKey = publicKey.modInverse(phi);
    }


    /**
     * Encrypt a given message
     *
     * @param message : byte[]
     * @return byte[]
     */
    public byte[] encrypt(byte[] message) {
        logger.info("encrypting message ...");
        return (new BigInteger(message)).modPow(publicKey, modulus).toByteArray();

    }

    /**
     * This method decrypt encrypted message
     *
     * @param message : byte[]
     * @return byte[]
     */
    public byte[] decrypt(byte[] message) {
        logger.info("decrypting message ...");
        return (new BigInteger(message)).modPow(privateKey, modulus).toByteArray();
    }
}
