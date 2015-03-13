package tartan.combination;

/**
 * Created by HomeM5 on 01/12/2014.
 */
public enum OPERATION_UNARY {
    DARKER_COLOUR("Darker Colours"),
    BRIGHTER_COLOUR("Brighter Colours");

    private final String form;

    private OPERATION_UNARY(String form) {
        this.form = form;
    }

    @Override public String toString() {
        return form;
    }
}
