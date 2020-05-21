package roman.interview.bsc.utils;

public class ValidateUtils {
    /**
     * RegEx to validate post code
     */
    private static final String POST_CODE_REGEX = "\\d{5}";

    /**
     * Validate {@link String} number
     * @param number {@link String} number to be validated
     * @param decimalSeparator {@link String} decimal separator
     * @param numberOfDecimalPlaces number of decimal places
     * @return true / false
     */
    public static boolean isValidNumberOfDecimalPlaces(String number, String decimalSeparator, int numberOfDecimalPlaces) {
        boolean isValid = true;

        // find index of decimal separator
        int decimalSeparatorIdx = number.indexOf(decimalSeparator);

        if (decimalSeparatorIdx >= 0) {
            isValid = number.substring(decimalSeparatorIdx + 1).length() <= numberOfDecimalPlaces;
        }

        return isValid;
    }

    /**
     * Validate post code
     * @param postCode {@link String} post code
     * @return true / false
     */
    public static boolean isInvalidPostCode(String postCode) {
        return !postCode.matches(POST_CODE_REGEX);
    }
}
