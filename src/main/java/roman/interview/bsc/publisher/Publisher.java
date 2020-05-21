package roman.interview.bsc.publisher;

import lombok.RequiredArgsConstructor;
import roman.interview.bsc.command.PostalPackage;

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.String.format;
import static java.util.Comparator.comparingDouble;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static roman.interview.bsc.utils.MathUtils.sum;

@RequiredArgsConstructor
public class Publisher {

    /**
     * Formatter used when the total weight is written to the screen
     */
    private static final String SUM_FORMATTER = "%.3f";

    /**
     * Map<PostalCode, Collection<PostalPackage>>
     */
    private final Map<String, Collection<PostalPackage>> packages;

    /**
     * Add package to storage
     * @param postalPackage {@link PostalPackage} package to be added
     */
    public synchronized void addPackage(PostalPackage postalPackage) {
        Collection<PostalPackage> postcodePackages = packages.getOrDefault(postalPackage.getPostalCode(), new ConcurrentLinkedQueue<>());
        postcodePackages.add(postalPackage);

        packages.put(postalPackage.getPostalCode(), postcodePackages);
    }

    /**
     * Process packages
     */
    public synchronized void processPackages() {
        packages.entrySet().stream()
                            .map(entry -> new PostcodeWeight(entry.getKey(), sum(entry.getValue())))
                            .sorted(comparingDouble(PostcodeWeight::getWeight).reversed())
                            .forEachOrdered(this::printPostcodeWeight);
    }

    /**
     * Print {@link PostcodeWeight} to the screen
     * @param postcodeWeight {@link PostcodeWeight} data to be printed
     */
    private void printPostcodeWeight(PostcodeWeight postcodeWeight) {
        System.out.println(postcodeWeight.getPostcode() + SPACE + format(Locale.US, SUM_FORMATTER, postcodeWeight.getWeight()));
    }

}
