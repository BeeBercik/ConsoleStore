package com.shop.validators;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidator {
    public boolean hashAndValidatePasswords(String password, String repPassword) {
        String hashedPasswordOne = BCrypt.hashpw(password, BCrypt.gensalt());
        String hashedPasswordTwo = BCrypt.hashpw(repPassword, BCrypt.gensalt());

        return hashedPasswordOne.equals(hashedPasswordTwo);
    }
}
