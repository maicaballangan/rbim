package controllers;

import com.google.common.collect.Maps;
import com.google.common.net.MediaType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import constants.Config;
import enums.APIErrorCode;
import exceptions.HttpException;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;

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
    public static void importFile(final File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default :
                    }
                }

                // TODO
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}