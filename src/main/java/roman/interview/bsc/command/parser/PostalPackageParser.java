package roman.interview.bsc.command.parser;

import roman.interview.bsc.command.PostalPackage;

import java.security.InvalidParameterException;

/**
 * Parser for converting {@link String} to {@link PostalPackage}
 */
public interface PostalPackageParser {

    /**
     * Convert {@link String} to {@link PostalPackage}
     * @param toParse {@link String} string representation of postal package
     * @return {@link PostalPackage} converted package
     */
    PostalPackage parsePostalPackage(String toParse) throws InvalidParameterException;
}
