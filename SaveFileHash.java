/**
 * SaveFileHash - A utility class to generate and save SHA-256 hashes of files
 * 
 * This program reads an input file, generates its SHA-256 hash, and saves the hash
 * in hexadecimal format to an output file.
 * 
 * Usage:
 *     java SaveFileHash <inputFilePath> <outputFilePath>
 * 
 * Parameters:
 *     inputFilePath  - Path to the file to be hashed
 *     outputFilePath - Path where the hash will be saved
 * 
 * Example:
 *     java SaveFileHash document.pdf hash.txt
 * 
 * Technical Details:
 * - Uses Java's MessageDigest class for SHA-256 hashing
 * - Reads entire file into memory (not suitable for very large files)
 * - Outputs hash as a 64-character hexadecimal string
 * - Uses NIO Files API for file operations
 * 
 * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
 * @throws IOException if there are errors reading/writing files
 * 
 * @author Hangxi Xiang
 * @version 1.0
 */

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaveFileHash {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SaveFileHash <inputFilePath> <outputFilePath>");
            return;
        }

        try {
            String inputFilePath = args[0];
            String outputFilePath = args[1];

            // Read the input file and compute SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] fileBytes = Files.readAllBytes(Paths.get(inputFilePath));
            byte[] hashBytes = digest.digest(fileBytes);

            // Convert hash bytes to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Write the hash to the output file
            Files.write(Paths.get(outputFilePath), hexString.toString().getBytes());
            System.out.println("SHA-256 hash has been written to: " + outputFilePath);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
        }
    }
}