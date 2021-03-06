package tartan.combination;

/**
 * Created by HomeM5 on 01/12/2014.
 */
public enum OPERATION_UNARY {
    Tartanise("Tartanise"),
    LeftThinner("Left Thinnernise"),
    RightThinner("Right Thinnernise"),
    RandomiseColoursWithThreadCounts("Randomise Colours with Thread Counts");

    private final String form;

    private OPERATION_UNARY(String form) {
        this.form = form;
    }

    @Override public String toString() {
        return form;
    }
}
