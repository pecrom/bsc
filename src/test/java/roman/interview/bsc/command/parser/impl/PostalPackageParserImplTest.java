package roman.interview.bsc.command.parser.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roman.interview.bsc.command.PostalPackage;
import roman.interview.bsc.command.parser.PostalPackageParser;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class PostalPackageParserImplTest {

    @Test
    @DisplayName("Parse valid input")
    void parsePostalPackageValidTest() {
        PostalPackageParser packageParser = new PostalPackageParserImpl();

        PostalPackage postalPackage = packageParser.parsePostalPackage("5.123 12345");

        // assertion
        assertEquals(5.123f, postalPackage.getWeight());
        assertEquals("12345", postalPackage.getPostalCode());
    }

    @Test
    @DisplayName("Parse invalid weight")
    void parsePostalPackageInvalidWeightTest() {
        PostalPackageParser packageParser = new PostalPackageParserImpl();

        // assertion
        assertThrows(InvalidParameterException.class, () -> packageParser.parsePostalPackage("5,123 12345"));
    }
}