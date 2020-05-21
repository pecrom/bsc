package roman.interview.bsc.command.parser.impl;

import roman.interview.bsc.command.PostalPackage;
import roman.interview.bsc.command.parser.PostalPackageParser;

import java.security.InvalidParameterException;

import static java.lang.Float.parseFloat;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static roman.interview.bsc.utils.ValidateUtils.isInvalidPostCode;
import static roman.interview.bsc.utils.ValidateUtils.isValidNumberOfDecimalPlaces;

/**
 * {@inheritDoc}
 */
public class PostalPackageParserImpl implements PostalPackageParser {

    /**
     * Number of expected parameters
     */
    private static final int NUMBER_OF_PARAMETERS = 2;

    /**
     * Index of weight parameter
     */
    private static final int WEIGHT_IDX = 0;

    /**
     * Index of postal code parameter
     */
    private static final int POSTAL_CODE_IDX = 1;

    /**
     * Number of decimal places x.xxx
     */
    public static final int NUMBER_OF_DECIMAL_PLACES = 3;

    /**
     * Decimal separator
     */
    public static final String DECIMAL_SEPARATOR = ".";


    /**
     * {@inheritDoc}
     */
    public PostalPackage parsePostalPackage(String toParse) throws InvalidParameterException {
        String[] parameters = toParse.trim().split(SPACE);

        validateInput(parameters);

        return PostalPackage.builder()
                            .postalCode(parameters[POSTAL_CODE_IDX])
                            .weight(parseFloat(parameters[WEIGHT_IDX]))
                            .build();
    }

    /**
     * Validation of input line
     * @param input {@link String[]} array of parameters
     */
    private void validateInput(String[] input) {
        if (input.length != NUMBER_OF_PARAMETERS) {
            throw new InvalidParameterException("Invalid parameter count, should be " + NUMBER_OF_PARAMETERS + " and is " + input.length);
        }

        if (isInvalidWeight(input[WEIGHT_IDX])) {
            throw new InvalidParameterException("Invalid weight parameter: " + input[WEIGHT_IDX]);
        }

        if (isInvalidPostCode(input[POSTAL_CODE_IDX])) {
            throw new InvalidParameterException("Invalid postcode parameter: " + input[POSTAL_CODE_IDX]);
        }
    }

    /**
     * Check if {@link String} representation of weight is a valid number
     * @param stringWeight {@link String} weight
     * @return true / false
     */
    private boolean isInvalidWeight(String stringWeight) {
        boolean isInvalid;

        try {
            parseFloat(stringWeight);
            isInvalid = !isValidNumberOfDecimalPlaces(stringWeight, DECIMAL_SEPARATOR, NUMBER_OF_DECIMAL_PLACES);
        } catch (NumberFormatException e) {
            isInvalid = true;
        }

        return isInvalid;
    }

}
