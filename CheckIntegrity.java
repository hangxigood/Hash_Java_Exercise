/**
 * CheckIntegrity - A utility class to verify file integrity using SHA-256 hashes
 * 
 * This program compares a file's current SHA-256 hash with a previously saved hash
 * to verify if the file has been modified.
 * 
 * Usage:
 *     java CheckIntegrity <filename> <hashfile>
 * 
 * Parameters:
 *     filename - Path to the file to be verified
 *     hashfile - Path to the file containing the original hash
 * 
 * Example:
 *     java CheckIntegrity document.pdf document_hash.txt
 * 
 * Technical Details:
 * - Uses Java's MessageDigest class for SHA-256 hashing
 * - Reads entire file into memory (not suitable for very large files)
 * - Compares 64-character hexadecimal hash strings
 * - Uses NIO Files API for file operations
 * 
 * Return Values:
 * - Exits with status 0 if hashes match
 * - Exits with status 1 if hashes don't match or on error
 * 
 * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
 * @throws IOException if there are errors reading files
 * 
 * @author Hangxi Xiang
 * @version 1.0
 */

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.*;

public class CheckIntegrity {
    public static void main(String[] args) {
        // Check if correct number of arguments are provided
        if (args.length != 2) {
            System.out.println("Usage: java CheckIntegrity <filename> <preexisting hash file name>");
            System.exit(1);
        }

        try {
            // Read the file to check
            byte[] fileContent = Files.readAllBytes(Paths.get(args[0]));
            
            // Calculate SHA-256 hash of the file
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] calculatedHash = digest.digest(fileContent);
            
            // Read the pre-existing hash from file
            String storedHash = new String(Files.readAllBytes(Paths.get(args[1]))).trim();
            
            // Convert calculated hash to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : calculatedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            // Compare the hashes
            if (hexString.toString().equalsIgnoreCase(storedHash)) {
                System.out.println("File integrity confirmed");
            } else {
                System.out.println("File integrity check failed");
            }
            
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hash algorithm not found");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}