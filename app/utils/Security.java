/*
package utils;

import models.Staff;

public class Security extends Secure.Security {

    static boolean authenticate(String id, String password) {
        Staff user = Staff.findByIdAndPassword(id, password);
        return user != null;
    }

    static boolean check(String profile) {
        if ("admin".equals(profile)) {
            Staff user = Staff.findById("byUser", connected()).first();
          if (user != null) {
            return user.isAdmin;
          }
        } else if ("user".equals(profile)) {
          return connected().equals("user");
        }
        return false;
    }
}*/
