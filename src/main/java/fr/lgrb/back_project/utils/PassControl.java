package fr.lgrb.back_project.utils;

import fr.lgrb.back_project.entity.Consumer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PassControl {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    // Générer un mot de passe avec PBKDF2
    public Consumer generatePassword(Consumer user) throws Exception {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();

        user.setPassword(Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword));
        return user;
    }
    // Vérifier le mot de passe
    public boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedPasswordHash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] newPasswordHash = factory.generateSecret(spec).getEncoded();

        return java.util.Arrays.equals(newPasswordHash, storedPasswordHash);
    }
}
