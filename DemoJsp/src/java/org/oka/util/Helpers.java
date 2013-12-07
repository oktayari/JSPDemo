package org.oka.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


    public final class Helpers 
    {
          
        public static int size(String s) {
            return s.trim().length();
        }

        public static  String encrypt(String password) {
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                System.err.println(ex.getMessage());
            }
            m.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, m.digest()).toString(16);
        }

    }

