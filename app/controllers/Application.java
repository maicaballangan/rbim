/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import com.google.common.collect.Maps;
import com.google.common.net.MediaType;
import enums.APIErrorCode;
import exceptions.HttpException;
import play.Logger;
import play.Play;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.modules.excel.RenderExcel;
import play.mvc.*;
import utils.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main application entry point - used to define basic authentication and documentation interface
 *
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public class Application extends Controller {

    //default time zone
    public static final String ERROR_PAGE = "Application/error.json";

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
     * GET      /ping
     */
    public static void ping() {
        ok();
    }

    public static void upload() {
        render();
    }

    public static void reports() {
        render();
    }

    /**
     * POST     /import
     */
    public static void importFile(final File[] files) throws IOException {
        for (File file : files) {
            if (file == null) {
                flash.error("Please select a file to import");
                render(request.controller + "/upload.html");
            }

            Logger.info("============Start Import process=============");
            Logger.info("File name: %s", file.getName());
            try {
                // Save household heads first
                ExcelUtils.importExcel(file, true);
                // Save non-household heads
                ExcelUtils.importExcel(file, false);
                Logger.info("============End Import process===============");
            } catch (Exception e) {
                Logger.fatal(e, "Import failed with file name %s", file.getName());
                flash.error("Import failed. Please try again");
                render(request.controller + "/upload.html");
            } catch (OutOfMemoryError e) {
                Logger.fatal(e, "Make sure that each file contains less that 10,000 records. [File: %s]", file.getName());
                flash.error("Make sure that each file contains less that 10,000 records. [File: %s]", file.getName());
                render(request.controller + "/upload.html");
            }
        }

        if (Validation.hasErrors()) {
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