package roman.interview.bsc;

import roman.interview.bsc.command.CommandReader;
import roman.interview.bsc.command.PostalPackage;
import roman.interview.bsc.command.parser.PostalPackageParser;
import roman.interview.bsc.command.parser.impl.PostalPackageParserImpl;
import roman.interview.bsc.publisher.Publisher;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * File containing initial data for publisher
     */
    private static final int FILENAME_IDX = 0;

    /**
     * Number of program parameters
     */
    private static final int PROGRAM_PARAMETERS_COUNT = 1;

    /**
     * How often to process packages
     */
    private static final long FREQUENCY = 1;

    /**
     * Delay before the first procession of packages
     */
    private static final long INITIAL_DELAY = FREQUENCY;

    public static void main(String[] args) {
        PostalPackageParser parser = new PostalPackageParserImpl();
        Publisher publisher = new Publisher(new HashMap<>());

        loadPackages(args, parser, publisher);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        CommandReader reader = new CommandReader(System.in, publisher, parser, executor);

        executor.scheduleAtFixedRate(publisher::processPackages, INITIAL_DELAY, FREQUENCY, TimeUnit.MINUTES);

        reader.start();
    }

    /**
     * Load packages from file
     * @param args {@link String[]} file name in arguments
     * @param postalPackageParser {@link PostalPackageParser} parser
     * @param publisher {@link Publisher} postal package storage
     */
    private static void loadPackages(String[] args, PostalPackageParser postalPackageParser, Publisher publisher) {

        if (args.length == PROGRAM_PARAMETERS_COUNT) {
            loadFromFile(args[FILENAME_IDX], postalPackageParser, publisher);
        }
    }


    /**
     * Load initial data from file
     * @param filename {@link String} file name
     * @param packageParser {@link PostalPackageParser} parser
     * @param publisher {@link Publisher} postal package storage
     */
    private static void loadFromFile(String filename, PostalPackageParser packageParser, Publisher publisher) {

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {

            // import all postal packages from the file
            String line;
            while ((line = fileReader.readLine()) != null) {
                parsePackage(line, packageParser)
                        .ifPresent(publisher::addPackage);

            }

        } catch (IOException e) {
            System.out.println("Can not read file " + filename);
        }
    }

    /**
     * Parse package from initial file
     * @param line {@link String} string containing package info
     * @param parser {@link PostalPackageParser} parser for parsing packages
     * @return {@link Optional<PostalPackage>} parsed package
     */
    private static Optional<PostalPackage> parsePackage(String line, PostalPackageParser parser) {
        Optional<PostalPackage> postalPackage = Optional.empty();

        try {
            postalPackage = Optional.of(parser.parsePostalPackage(line));
        } catch (InvalidParameterException e) {
            // ignore, skip this line
        }

        return postalPackage;
    }
}
