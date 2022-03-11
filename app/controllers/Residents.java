/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import java.util.List;
import java.util.Map;

import models.Resident;
import play.mvc.With;

/**
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public class Residents extends CustomCRUD {

    /**
     * POST     /export
     */
    public static void export(Map<String, String> filter,
                              Map<String, Integer> min,
                              Map<String, Integer> max,
                              Map<String, String> match) {
        List<Resident> records = Resident.find(createQuery(filter, min, max, match)).fetch();
        Application.generateReport("residents", "residents", records);
    }
}