package org.nimshub;

import org.nimshub.cipher.RSA;
import org.nimshub.utils.Converters;
import org.nimshub.utils.Printer;

import static org.nimshub.utils.Constants.STUDENT_NAME;

public class Main {
    public static void main(String[] args)
    {
        RSA rsa = new RSA();
        String teststring = STUDENT_NAME;

        Printer.print("String in Bytes: "
                + Converters.bytesToString(teststring.getBytes()));
        // encrypt
        byte[] encrypted = rsa.encrypt(teststring.getBytes());
        // decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        Printer.print("Decrypting Bytes: " + Converters.bytesToString(decrypted));
        Printer.print("Decrypted String: " + new String(decrypted));
    }
}