package org.nimshub.cipher;

import org.nimshub.utils.Converters;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import static org.nimshub.utils.Constants.BIT_LENGTH;

public class RSA {

    private final BigInteger N;
    private final BigInteger e;
        private final BigInteger d;

    public RSA()
        {
            SecureRandom random = new SecureRandom();;
            BigInteger p = BigInteger.probablePrime(BIT_LENGTH, random);
            BigInteger q = BigInteger.probablePrime(BIT_LENGTH, random);
            N = p.multiply(q);
            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            e = BigInteger.probablePrime(BIT_LENGTH / 2, random);
            while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
            {
                e.add(BigInteger.ONE);
            }
            d = e.modInverse(phi);
        }

        public static void main(String[] args) throws IOException
        {
            RSA rsa = new RSA();
            DataInputStream in = new DataInputStream(System.in);
            String teststring;
            System.out.println("Enter the plain text:");
            teststring = in.readLine();
            System.out.println("Encrypting String: " + teststring);
            System.out.println("String in Bytes: "
                    + Converters.bytesToString(teststring.getBytes()));
            // encrypt
            byte[] encrypted = rsa.encrypt(teststring.getBytes());
            // decrypt
            byte[] decrypted = rsa.decrypt(encrypted);
            System.out.println("Decrypting Bytes: " + Converters.bytesToString(decrypted));
            System.out.println("Decrypted String: " + new String(decrypted));
        }



        // Encrypt message
        public byte[] encrypt(byte[] message)
        {
            return (new BigInteger(message)).modPow(e, N).toByteArray();
        }

        // Decrypt message
        public byte[] decrypt(byte[] message)
        {
            return (new BigInteger(message)).modPow(d, N).toByteArray();
        }
}
