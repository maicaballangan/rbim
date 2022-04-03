package constants;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Default date formats
    public static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    public static final String MILITARY_TIME_FORMAT = "HH:mm";
    public static final String DATE_OUTPUT_FORMAT = "MMM d, yyyy";
    public static final String TIME_OUTPUT_FORMAT = "h:mm a";
    public static final String TIME_OUTPUT_FULL_FORMAT = "h:mm:ss a";
    public static final String DATE_TIME_OUTPUT_FORMAT = DATE_OUTPUT_FORMAT + " " + TIME_OUTPUT_FULL_FORMAT + " z";
    public static final String DATE_FORMAT_NO_DASH = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT_NO_DASH = "yyyyMMddHHmmss";
    public static final String GIT_TIME_FORMAT = "EEE MMM d HH:mm:ss yyyy Z";
}
