package best2ask.services;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Criptare {

    private static int iteratii=11;
    public static String hashPassword(String password)
    {
        String salt= BCrypt.gensalt(iteratii);
        return BCrypt.hashpw(password,salt);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
