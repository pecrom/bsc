package roman.interview.bsc.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roman.interview.bsc.command.parser.impl.PostalPackageParserImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static roman.interview.bsc.utils.ValidateUtils.isInvalidPostCode;
import static roman.interview.bsc.utils.ValidateUtils.isValidNumberOfDecimalPlaces;

class ValidateUtilsTest {

    @Test
    @DisplayName("Validate decimal number with valid parameters")
    void isValidNumberOfDecimalPlacesValidParametersTest() {
        boolean isValid = isValidNumberOfDecimalPlaces("5.28", PostalPackageParserImpl.DECIMAL_SEPARATOR, PostalPackageParserImpl.NUMBER_OF_DECIMAL_PLACES);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Validate decimal number with invalid decimal number count")
    void isValidNumberOfDecimalPlacesInvalidDecimalNumberCountTest() {
        boolean isValid = isValidNumberOfDecimalPlaces("5.2800", PostalPackageParserImpl.DECIMAL_SEPARATOR, PostalPackageParserImpl.NUMBER_OF_DECIMAL_PLACES);

        assertFalse(isValid);
    }

    @Test
    @DisplayName("Validate decimal number with zero decimal number count")
    void isValidNumberOfDecimalPlacesZeroDecimalNumberCountTest() {
        boolean isValid = isValidNumberOfDecimalPlaces("5", PostalPackageParserImpl.DECIMAL_SEPARATOR, PostalPackageParserImpl.NUMBER_OF_DECIMAL_PLACES);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Validate valid postcode")
    void isPostcodeInvalidValidTest() {
        assertFalse(isInvalidPostCode("12345"));
    }

    @Test
    @DisplayName("Validate invalid postcode")
    void isPostcodeInvalidInvalidTest() {
        assertTrue(isInvalidPostCode("123456"));
    }

    @Test
    @DisplayName("Validate not numbers postcode")
    void isPostcodeInvalidNotNumbersTest() {
        assertTrue(isInvalidPostCode("123a6"));
    }
}