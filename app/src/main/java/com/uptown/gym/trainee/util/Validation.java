package com.uptown.gym.trainee.util;

import java.util.regex.Pattern;

public final class Validation {
    private static final String STRING_REGEX = "^[^0-9]*$";
    private static final String PHONE_NUMBER_REGEX = "(0)?[0-9]{10}";
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_.-]*$";

    private Validation() {
        throw new IllegalStateException("Validation class");
    }

    /**
     * Validate String Text contains Characters only
     *
     * @param string
     * @return
     */
    public static boolean isStringInputValid(String string) {
        return Pattern.matches(STRING_REGEX, string);
    }

    /**
     * Validate Phone Numbers Contains 11 Number Starts with 0
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneInputValid(String phone) {
        return Pattern.matches(PHONE_NUMBER_REGEX, phone);
    }

    /**
     * Validate Username Contains letters (upper or lowercase)
     * numbers (0-9)
     * underscore (_)
     * dash (-)
     * point (.)
     * no spaces! or other characters
     *
     * @param username
     * @return
     */
    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }
}
