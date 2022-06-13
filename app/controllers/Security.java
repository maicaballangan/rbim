package controllers;

import models.Staff;
import play.cache.Cache;

/**
 * @author Maica Ballangan
 * @since v1
 */
public class Security extends Secure.Security {
    private static final String FAILED_ATTEMPTS = "failedAttempts:";
    private static final String ROLES = "roles:";
	
    static boolean authenticate(String username, String password) {
        String user = username.toUpperCase();

        Integer failedAttemptCnt = (Integer) Cache.get(FAILED_ATTEMPTS + user);
        if (failedAttemptCnt != null && failedAttemptCnt > 4) {
            flash.error("secure.rateLimit");
            params.flash();
            render("Secure/login.html");
        }

        Staff staff =  Staff.findById(username);
        if (staff != null) {
            if (staff.checkPassword(password)) {
                Cache.set(ROLES + username, staff.getRole().toString(), "1d");
                return true;
            }
        }

        Cache.set(FAILED_ATTEMPTS + user, failedAttemptCnt != null ? failedAttemptCnt + 1 : 1, "1h");
        return false;
    }

    static void onAuthenticated() {
        CRUD.index();
    }

    static void onLogout() {
        Cache.delete(FAILED_ATTEMPTS + connected());
    }

    static boolean check(String profile) {
        String role = Cache.get(ROLES + connected()).toString();
        if ("ADMIN".equals(role) || profile.equals(role)){
            return true;
        } else {
            return false;
        }
    }
}