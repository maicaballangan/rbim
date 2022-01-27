package controllers;

import models.Staff;

/**
 * @author Maica Ballangan
 * @since v1
 */
public class Security extends Secure.Security {
	
    static boolean authenticate(String username, String password) {
        Staff staff =  Staff.findById(username.toUpperCase());
        if (staff != null) {
            return staff.checkPassword(password);
        }

        return false;
    }

    static void onAuthenticated() {
        CRUD.index();
    }
}