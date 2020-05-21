package roman.interview.bsc.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roman.interview.bsc.command.PostalPackage;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static roman.interview.bsc.utils.MathUtils.sum;

class MathUtilsTest {

    @Test
    @DisplayName("Summing valid data")
    void sumTest() {
        Collection<PostalPackage> postalPackages = List.of(new PostalPackage(5, "12305"),
                                                            new PostalPackage(5, "50321"));

        assertEquals(10, sum(postalPackages));
    }
}