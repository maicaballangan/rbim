/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import java.util.List;
import java.util.Map;

import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Util;

/**
 * @author Maica Ballangan
 * @since v1
 */
public class CustomCRUD extends CRUD {

    public static void list(int page,
                            String search,
                            String searchFields,
                            String orderBy,
                            String order,
                            Map<String, String> filter,
                            Map<String, Integer> min,
                            Map<String, Integer> max,
                            Map<String, String> match) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }

        String query = createQuery(filter, min, max, match);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, query.length() > 0 ? query : (String) request.args.get("where"));
        Long count = type.count(search, searchFields, query.length() > 0 ? query : (String) request.args.get("where"));

        try {
            render(type, objects, count, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, page, orderBy, order);
        }
    }

    @Util
    static String createQuery(
            Map<String, String> filter,
            Map<String, Integer> min,
            Map<String, Integer> max,
            Map<String, String> match) {
        StringBuilder fb = new StringBuilder();
        if (filter != null) filter.keySet()
                .stream()
                .filter(k -> filter.get(k) != null && !filter.get(k).isBlank())
                .forEach(k -> {
                    String equality = " = ";
                    if (filter.get(k).charAt(0) == '-') {
                        equality = " != ";
                        filter.put(k, filter.get(k).substring(1));
                    }

                    if (fb.length() > 0) fb.append(" and ");
                    fb.append(k + equality + "'" + filter.get(k) + "'");
                });

        if (match != null) match.keySet()
                .stream()
                .filter(k -> match.get(k) != null && !match.get(k).isBlank())
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");
                    fb.append(k + " like '%" + match.get(k) + "%'");
                });

        if (min != null) min.keySet()
                .stream()
                .filter(k -> min.get(k) != null)
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");
                    fb.append(k + " >= " + min.get(k));
                });

        if (max != null) max.keySet()
                .stream()
                .filter(k -> max.get(k) != null)
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");
                    fb.append(k + " <= " + max.get(k));
                });

        return fb.toString();
    }
}