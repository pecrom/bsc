package roman.interview.bsc.utils;

import roman.interview.bsc.command.PostalPackage;

import java.util.Collection;

public class MathUtils {

    /**
     * Summarize weight of {@link Collection<PostalPackage>}
     * @param postalPackages {@link Collection<PostalPackage>} packages
     * @return total weight of all packages
     */
    public static double sum(Collection<PostalPackage> postalPackages) {
        return postalPackages.parallelStream()
                                .mapToDouble(PostalPackage::getWeight)
                                .sum();
    }
}
