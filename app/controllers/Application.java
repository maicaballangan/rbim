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
import java.util.Map;

import enums.APIErrorCode;
import enums.Relation;
import exceptions.HttpException;
import models.Record;
import play.Logger;
import play.Play;
import play.data.validation.Error;
import play.modules.excel.RenderExcel;
import play.mvc.Controller;
import play.mvc.Finally;
import play.mvc.Http;
import play.mvc.Util;
import play.mvc.With;
import utils.ExcelUtils;

/**
 * Main application entry point - used to define basic authentication and documentation interface
 *
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public class Application extends Controller {

    //default time zone
    public static final ZoneId zoneId = ZoneId.systemDefault();
    public static final String ERROR_PAGE = "Application/error.json";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Popcom\"";

    /*@Before(unless = {"notFound", "ping", "heartbeat", "info"})
    static void authenticate() {
    }*/

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
        CRUD.index();
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

    public static void upload() {
        render();
    }

    /**
     * POST     /import
     */
    public static void importFile(final File file) throws IOException {
        if (file == null) {
            flash.error("Please select a file to import");
            render(request.controller + "/upload.html");
        }

        Logger.info("============Start Import process=============");
        Logger.info("File name: %s", file.getName());
        try {
            List<Record> records = ExcelUtils.importExcel(file, validation);
            if (validation.hasErrors()) {
                Logger.error("There has been errors on parsing the document, please make sure the fields are properly formatted");
                flash.error(play.i18n.Messages.get("crud.hasErrors"));
                //renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
                Map<String, List<Error>> errors = validation.errorsMap();
                render(request.controller + "/upload.html", errors);
            }

            // Save head residents first
            records.stream()
                    .filter(r -> Relation.HEAD.equals(r.getResident().getRelationshipToHead()))
                    .forEach(r -> r.create(validation));

            records.stream()
                    .filter(r -> !Relation.HEAD.equals(r.getResident().getRelationshipToHead()))
                    .forEach(r -> r.create(validation));
            Logger.info("============End Import process===============");

        } catch (Exception e) {
            Logger.fatal(e, "Import failed with file name %s", file.getName());
            flash.error("Import failed. Please try again.");
            render(request.controller + "/upload.html");
        }

        if (validation.hasErrors()) {
            flash.success(play.i18n.Messages.get("Successfully imported file"));
            flash.error(play.i18n.Messages.get("crud.hasErrors"));
            Map<String, List<Error>> errors = validation.errorsMap();
            render(request.controller + "/upload.html", errors);
        }

        flash.success(play.i18n.Messages.get("Successfully imported file"));
        render("CRUD/index.html");
    }

    @Util
    public static void generateReport(String table, final String fileName, final List<?> records) {
        final var total = records.size();
        request.format = "xlsx";
        response.contentType = MediaType.OOXML_SHEET.toString();
        response.headers.put("Content-Disposition", new Http.Header("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\""));
        renderArgs.put(RenderExcel.RA_ASYNC, false);
        renderArgs.put(RenderExcel.RA_FILENAME, fileName + ".xslxs");
        render("Reports/" + table + ".xlsx", total, records);
    }
}