/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import models.Resident;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

/**
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public class Residents extends CustomCRUD {

    private static Pattern pattern = Pattern.compile("H[0-9]{6,6}", Pattern.CASE_INSENSITIVE);

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

    public static void list(int page,
                            String search,
                            String searchFields,
                            String orderBy,
                            String order,
                            Map<String, String> filter,
                            Map<String, Integer> min,
                            Map<String, Integer> max,
                            Map<String, String> match) {
        ObjectType type = ObjectType.get(Residents.class);
        if (page < 1) {
            page = 1;
        }

        List<Model> objects;
        Long count;
        if (search != null && pattern.matcher(search).matches()) {
            objects = Resident.findByHouseholdId(search);
            count = Long.valueOf(objects.size());
        } else {
            String query = createQuery(filter, min, max, match);
            objects = type.findPage(page, search, searchFields, orderBy, order, query.length() > 0 ? query : (String) request.args.get("where"));
            count = type.count(search, searchFields, query.length() > 0 ? query : (String) request.args.get("where"));
        }

        try {
            render(type, objects, count, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, page, orderBy, order);
        }
    }
}