# File Integrity Checker

This project includes two Java utilities for file integrity verification using SHA-256 hashing.

## Components

1. **SaveFileHash.java**
    - Generates SHA-256 hash for a file
    - Saves the hash to a specified output file
    - Usage: `java SaveFileHash <inputFilePath> <outputFilePath>`

2. **CheckIntegrity.java**
    - Verifies file integrity by comparing current hash with stored hash
    - Usage: `java CheckIntegrity <filename> <hashfile>`

## Technical Details

- Uses Java's MessageDigest class for SHA-256 hashing
- Implements NIO Files API for file operations
- Handles files as byte arrays
- Outputs 64-character hexadecimal hash strings

## Requirements

- Java Runtime Environment (JRE)
- Sufficient memory to read input files

## Usage Example

```bash
# Generate hash for a file
java SaveFileHash document.pdf document_hash.txt

# Verify file integrity later
java CheckIntegrity document.pdf document_hash.txt
```

## Author

Hangxi Xiang

## Version

1.0