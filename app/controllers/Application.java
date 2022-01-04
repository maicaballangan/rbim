/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import com.google.common.collect.Maps;
import com.google.common.net.MediaType;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.List;

import enums.APIErrorCode;
import enums.Relation;
import exceptions.HttpException;
import models.Record;
import play.Logger;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;
import utils.ExcelUtils;

/**
 * Main application entry point - used to define basic authentication and documentation interface
 *
 * @author Maica Ballangan
 * @since v1
 */
public class Application extends Controller {

    //default time zone
    public static final ZoneId zoneId = ZoneId.systemDefault();
    public static final String ERROR_PAGE = "Application/error.json";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Popcom\"";

    @Before(unless = {"notFound", "ping", "heartbeat", "info"})
    static void authenticate() {
        /*if (!(Config.AUTH_ID.equals(request.user) && Config.AUTH_PASS.equals(request.password))) {
            response.setHeader(WWW_AUTHENTICATE, REALM);
            request.format = MediaType.JSON_UTF_8.toString();
            HttpException e = new HttpException(APIErrorCode.BASIC_AUTHENTICATION_FAILED);
            response.status = e.getHttpStatusCode();
            render(ERROR_PAGE, e);
        }*/
    }

    @Finally
    protected static void removeCookies() {
        boolean cookiesEnabled = Boolean.parseBoolean(Play.configuration.getProperty("cookies.enabled"));
        if (!cookiesEnabled) {
            response.cookies = Maps.newHashMap();
        } else {
            // Remove play cookies
            response.cookies.remove("PLAY_ERRORS");
            response.cookies.remove("PLAY_FLASH");
            response.cookies.remove("PLAY_SESSION");
        }
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

    /**
     * POST     /import
     */
    public static void importFile(final File file) throws IOException {
        Logger.info("============Start Import process=============");
        Logger.info("File name: %s", file.getName());

        List<Record> records = ExcelUtils.importExcel(file);

        // Save head residents first
        records.stream()
                .filter(r -> Relation.Head.equals(r.getResident().getRelationshipToHead()))
                .forEach(Record::create);

        records.stream()
                .filter(r -> !Relation.Head.equals(r.getResident().getRelationshipToHead()))
                .forEach(Record::create);
        Logger.info("============End Import process===============");
    }
}