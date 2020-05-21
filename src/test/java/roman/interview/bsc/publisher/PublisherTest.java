package roman.interview.bsc.publisher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roman.interview.bsc.command.PostalPackage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PublisherTest {

    @Test
    @DisplayName("Add package")
    void addPackageTest() {
        String postalCode = "12345";
        float weight = 5f;
        PostalPackage expectedPackage = new PostalPackage(weight, postalCode);

        Map<String, Collection<PostalPackage>> storage = new HashMap<>();
        Publisher publisher = new Publisher(storage);

        publisher.addPackage(expectedPackage);

        // assertion
        PostalPackage insertedPackage = storage.get(postalCode)
                                                .stream()
                                                .findFirst()
                                                .get();

        assertEquals(expectedPackage, insertedPackage);
    }
}