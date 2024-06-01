package utils;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HashingTest {

    @Test
    void testHashPasswordConsistency() throws NoSuchAlgorithmException {
        String password = "password123";
        String hash1 = Hashing.hashPassword(password);
        String hash2 = Hashing.hashPassword(password);

        assertEquals(hash1, hash2, "Hashes of the same password should be equal");
    }

    @Test
    void testHashPasswordDifferentInputs() throws NoSuchAlgorithmException {
        String password1 = "password123";
        String password2 = "password124";
        String hash1 = Hashing.hashPassword(password1);
        String hash2 = Hashing.hashPassword(password2);

        assertNotEquals(hash1, hash2, "Hashes of different passwords should not be equal");
    }

    @Test
    void testHashPasswordLength() throws NoSuchAlgorithmException {
        String password = "password123";
        String hash = Hashing.hashPassword(password);

        assertEquals(64, hash.length(), "SHA-256 hash should be 64 characters long");
    }
}
