package tartan.combination;

/**
 * Created by HomeM5 on 01/12/2014.
 */
public enum OPERATION_BINARY {
    COMBINE_CONCATENATION("Concatenation");

    private final String form;

    private OPERATION_BINARY(String form) {
        this.form = form;
    }

    @Override public String toString() {
        return form;
    }

}
