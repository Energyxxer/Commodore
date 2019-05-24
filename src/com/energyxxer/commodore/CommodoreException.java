package com.energyxxer.commodore;

public class CommodoreException extends RuntimeException {
    public enum Source {
        ENTITY_ERROR,
        NBT_TYPE_ERROR,
        NUMBER_LIMIT_ERROR,
        NUMBER_RANGE_ERROR,
        FORMAT_ERROR,
        DUPLICATION_ERROR,
        TYPE_ERROR, COORDINATE_ERROR, SELECTOR_ERROR, IMPOSSIBLE, API_ARGUMENT_ERROR, VERSION_ERROR
    }

    private Source source;
    private Object cause;
    private String causeKey;

    public CommodoreException(Source source, String message) {
        this(source, message, null);
    }

    public CommodoreException(Source source, String message, Object cause) {
        this(source, message, cause, null);
    }

    public CommodoreException(Source source, String message, Object cause, String causeKey) {
        super(message);
        this.source = source;
        this.cause = cause;
        this.causeKey = causeKey;
    }

    public Source getSource() {
        return source;
    }

    public Object getCausedBy() {
        return cause;
    }

    public String getCauseKey() {
        return causeKey;
    }
}
