package roman.interview.bsc.publisher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostcodeWeight {

    /**
     * Postcode
     */
    private final String postcode;

    /**
     * Total weight for the postcode
     */
    private final double weight;
}
