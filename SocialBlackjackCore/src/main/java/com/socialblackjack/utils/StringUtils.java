package com.socialblackjack.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtils {
	private final static Logger log = Logger.getLogger(StringUtils.class);
	
	public static final String HASHING_SHA256 = "SHA-256";
	   
	   /**
	    * Alphabet consisting of upper and lower case letters A-Z and
	    * the digits 0-9.
	    */
	   public static final char[] NUMBERS_AND_LOWER_LETTERS_ALPHABET = {
	       'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
	       'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
	       'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
	       'y', 'z', '0', '1', '2', '3', '4', '5',
	       '6', '7', '8', '9'
	   };
	   
	   /**
	    * Alphabet consisting of upper and lower case letters A-Z and
	    * the digits 0-9.
	    */
	   public static final char[] NUMBERS_AND_LETTERS_ALPHABET = {
	       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	       'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
	       'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	       'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
	       'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	       'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	       'w', 'x', 'y', 'z', '0', '1', '2', '3',
	       '4', '5', '6', '7', '8', '9'
	   };
	   
	   /**
	    * Alphabet consisting of upper and lower case letters A-Z and
	    * the digits 0-9 but with characters that are often mistaken
	    * for each other when typed removed. (I,L,O,U,V,i,l,o,u,v,0,1)
	    */
	   public static final char[] NONCONFUSING_ALPHABET = {
	       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	       'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
	       'T', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
	       'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
	       'n', 'p', 'q', 'r', 's', 't', 'w', 'x',
	       'y', 'z', '2', '3', '4', '5', '6', '7',
	       '8', '9'
	   };

	   /**
	    * Encode a string using the specified algorithm and return the
	    * resulting encrypted password. If exception, the plain credentials
	    * string is returned
	    *
	    * @param string Password or other credentials to use in authenticating this username
	    * @param algorithm Algorithm used to do the digest
	    *
	    * @return encypted password based on the algorithm.
	    */
	   public static String encodeString(String string, String algorithm) {
	       byte[] byteArray = string.getBytes();
	       return encodeBytes(byteArray, algorithm);
	   }

	   public static String encodeBytes(byte[] bytes, String algorithm) {

	       MessageDigest md = null;

	       try {
	           // first create an instance, given the provider
	           md = MessageDigest.getInstance(algorithm);
	       } catch (Exception e) {
	           log.error("Exception: " + e);
	           return null;
	       }

	       md.reset();

	       // call the update method one or more times
	       // (useful when you don't know the size of your data, eg. stream)
	       md.update(bytes);

	       // now calculate the hash
	       byte[] encodedString = md.digest();

	       StringBuffer buf = new StringBuffer();

	       for (int i = 0; i < encodedString.length; i++) {
	           if ((encodedString[i] & 0xff) < 0x10) {
	               buf.append("0");
	           }

	           buf.append(Long.toString(encodedString[i] & 0xff, 16));
	       }

	       return buf.toString();
	   }

	   /**
	    * Validates an e-mail address
	    *
	    * @param email Email to validate
	    * @return boolean wheter the email is valid or not
	    */
	   public static boolean isValidEmail(String email) {
	       Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

	       //Match the given string with the pattern
	       Matcher m = p.matcher(email);

	       //Check whether match is found
	       boolean matchFound = m.matches();

	       return matchFound;
	   }
	   
	   
	   public static String generateRandomString(int lenght) {
	       return generateRandomString(lenght, NONCONFUSING_ALPHABET);
	   }
	   
	   public static String generateRandomString(int lenght, char [] alphabet) {
	       StringBuffer randomString = new StringBuffer();
	       SecureRandom rand = new SecureRandom();
	       int alphSize = alphabet.length;
	       for(int i = 0; i < lenght; i++) {
	           randomString.append(alphabet[rand.nextInt(alphSize)]);
	       }
	       return randomString.toString();
	   }
}
