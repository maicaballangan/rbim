package controllers;

import models.Staff;
import play.cache.Cache;

/**
 * @author Maica Ballangan
 * @since v1
 */
public class Security extends Secure.Security {
    private static final String BUCKET = "security/";
	
    static boolean authenticate(String username, String password) {
        String user = username.toUpperCase();
        String key = BUCKET + user;

        Integer failedAttemptCnt = (Integer) Cache.get(key);
        if (failedAttemptCnt != null && failedAttemptCnt > 4) {
            flash.error("secure.rateLimit");
            params.flash();
            render("Secure/login.html");
        }

        Staff staff =  Staff.findById(username);
        if (staff != null) {
            if (staff.checkPassword(password)) {
                return true;
            }
        }

        Cache.set(key, failedAttemptCnt != null ? failedAttemptCnt + 1 : 1, "1h");
        return false;
    }

    static void onAuthenticated() {
        CRUD.index();
    }
}