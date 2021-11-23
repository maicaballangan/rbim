package controllers;

import com.google.common.net.MediaType;

import constants.Config;
import enums.APIErrorCode;
import exceptions.HttpException;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;
import play.mvc.Util;

/**
 * Main application entry point - used to define basic authentication and documentation interface
 *
 * @author Maica Ballangan
 * @since v1
 */
public class Application extends Controller {

    public static final String ERROR_PAGE = "Application/error.json";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Popcom\"";

    @Before(unless = {"notFound", "ping", "heartbeat", "info"})
    static void authenticate() {
        if (!(Config.AUTH_ID.equals(request.user) && Config.AUTH_PASS.equals(request.password))) {
            response.setHeader(WWW_AUTHENTICATE, REALM);
            request.format = MediaType.JSON_UTF_8.toString();
            HttpException e = new HttpException(APIErrorCode.BASIC_AUTHENTICATION_FAILED);
            response.status = e.getHttpStatusCode();
            render(ERROR_PAGE, e);
        }
    }

    @Finally
    protected static void removeCookies() {
        APIHelpers.adjustCookies();
    }

    @Util
    public static String getServiceRegistryUrl() {
        return APIHelpers.getLocalIP() + "_" + Config.API_PORT;
    }

    /**
     * *       /.*
     */
    public static void notFound() {
        request.format = MediaType.JSON_UTF_8.toString();
        HttpException e = new HttpException(APIErrorCode.RESOURCE_NOT_FOUND, request.method + " " + request.path);
        response.status = e.getHttpStatusCode();
        render(Application.ERROR_PAGE, e);
    }

    /**
     * GET /
     */
    public static void index() {
        render();
    }

    /**
     * GET /login
     */
    public static void login() {
        render();
    }

    /**
     * GET     /resources.json
     */
    public static void resources() {
        render("Application/resources.json");
    }

    /**
     * GET     /resources/{api}
     *
     * @param api path
     */
    public static void resource(final String api) {
        render("Application/resources/" + api);
    }

    /**
     * GET      /ping
     */
    public static void ping() {
        ok();
    }
}