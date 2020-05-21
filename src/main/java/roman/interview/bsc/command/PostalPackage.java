package roman.interview.bsc.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class PostalPackage {

    /**
     * Weight of package
     */
    private final float weight;

    /**
     * Post code
     */
    private final String postalCode;
}
