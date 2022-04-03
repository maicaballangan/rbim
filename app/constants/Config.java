package constants;

import play.Play;

public class Config {

    private Config() {
        throw new IllegalStateException("Utility class");
    }

    public static final String APPLICATION_NAME = Play.configuration.getProperty("application.name").toLowerCase();
    public static final String ATTACHMENTS_PATH = Play.configuration.getProperty("attachments.path");
    public static final String AUTH_ID = Play.configuration.getProperty("auth.id");
    public static final String AUTH_PASS = Play.configuration.getProperty("auth.password");
    public static final String TIMEZONE = Play.configuration.getProperty("application.timezone");
    public static final String COUNTRY = Play.configuration.getProperty("application.country");
    public static final String VERSION = Play.configuration.getProperty("application.version");
    public static final String API_VERSION = Play.configuration.getProperty("api.version");
    public static final String API_URL = Play.configuration.getProperty("api.address");
    public static final String API_PORT = Play.configuration.getProperty("http.port");
    public static final String API_URL_SCHEME = Play.configuration.getProperty("api.address.scheme");
    public static final String WS_URL_SCHEME = Play.configuration.getProperty("api.address.ws");
    public static final String BASE_URL = String.format("%s/%s", API_URL, VERSION);
    public static final String BASE_URL_SCHEME = String.format("%s%s", API_URL_SCHEME, BASE_URL);
    public static final String WS_URL = String.format("%s%s", WS_URL_SCHEME, BASE_URL);
    public static final boolean IS_SECURE = !"http://".equals(API_URL_SCHEME)
        || !API_URL.startsWith("localhost");
}
