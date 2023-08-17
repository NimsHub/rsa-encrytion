package org.nimshub;

import org.nimshub.cipher.RSA;
import org.nimshub.utils.Converters;
import org.nimshub.utils.Printer;

import static org.nimshub.utils.Constants.STUDENT_NAME;

public class Main {
    public static void main(String[] args) {
        testRSAEncryption();
    }
    /**
     * Test method for illustrate the functionality of RSA encryption
     */
    private static void testRSAEncryption() {

        RSA rsa = new RSA();

        Printer.print("Original Message  " , STUDENT_NAME);
        Printer.print("Original Message in Bytes " , Converters.bytesToString(STUDENT_NAME.getBytes()));

        byte[] encryptedMessage = rsa.encrypt(STUDENT_NAME.getBytes());
        Printer.print("Encrypted message " , new String(encryptedMessage));
        byte[] decryptedMessage = rsa.decrypt(encryptedMessage);
        Printer.print("Decrypted message " , new String(decryptedMessage));
    }
}