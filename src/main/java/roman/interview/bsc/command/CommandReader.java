package roman.interview.bsc.command;

import lombok.RequiredArgsConstructor;
import roman.interview.bsc.command.parser.PostalPackageParser;
import roman.interview.bsc.publisher.Publisher;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

@RequiredArgsConstructor
public class CommandReader {

    private static final String QUIT_COMMAND = "quit";

    /**
     * Stream to read the packages from
     */
    private final InputStream input;

    /**
     * Where to store the packages
     */
    private final Publisher publisher;

    /**
     * Postal packages parser
     */
    private final PostalPackageParser parser;

    /**
     * Service responsible for package processing
     */
    private final ScheduledExecutorService executorService;

    /**
     * Start reading command from {@link InputStream}
     */
    public void start() {
        boolean keepScanning = true;
        Scanner scanner = new Scanner(input);

        String line;
        do {
            // get the command
            line = scanner.nextLine();

            if (QUIT_COMMAND.equals(line)) {
                keepScanning = false;
            } else {
                // let's process the package
                parsePostalPackage(line).ifPresent(publisher::addPackage);
            }

        } while(keepScanning);

        // shut down executor responsible for outputting postal packages
        executorService.shutdown();
    }

    /**
     * Parse package from provided line
     * @param line {@link String} line representing package
     * @return {@link Optional< PostalPackage >} line converted to postal package
     */
    private Optional<PostalPackage> parsePostalPackage(String line) {
        Optional<PostalPackage> postalPackage = Optional.empty();

        try {
            postalPackage = Optional.of(parser.parsePostalPackage(line));
        } catch (Exception e) {
            // package can not be parsed
            System.out.println("Can not parse package: " + e.getMessage());
        }

        return postalPackage;
    }
}
