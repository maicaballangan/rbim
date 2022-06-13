/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Util;
import play.mvc.With;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

/**
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public abstract class CustomCRUD extends CRUD {

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

        final StringBuilder urlBuilder = new StringBuilder("/v1/admin/" + type.toString().toLowerCase() + "s?")
                .append("search=")
                .append(search != null ? search.replaceAll(" ", "&nbsp") : "")
                .append("&searchFields=")
                .append(searchFields != null ? searchFields.replaceAll(" ", "+") : "");
			
        String query = createQuery(filter, min, max, match, urlBuilder);
        List<Model> objects = type.findPage(page, search, searchFields, "age".equals(orderBy) ? "monthofbirth,yearofBirth" : orderBy, order, query.length() > 0 ? query : (String) request.args.get("where"));
        Long count = type.count(search, searchFields, query.length() > 0 ? query : (String) request.args.get("where"));
        String url = urlBuilder.toString();

        try {
            render(type, objects, count, page, orderBy, order, url);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, page, orderBy, order, url);
        }
    }

    @Util
    static String createQuery(
            Map<String, String> filter,
            Map<String, Integer> min,
            Map<String, Integer> max,
            Map<String, String> match,
            StringBuilder urlBuilder) {
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
                    urlBuilder.append("&filter." + k + "=" + filter.get(k));
                });

        if (match != null) match.keySet()
                .stream()
                .filter(k -> match.get(k) != null && !match.get(k).isBlank())
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");

                    fb.append(k + " like '%" + match.get(k) + "%'");
                    urlBuilder.append("&match." + k + "=" + match.get(k));
                });
		
		// Custom age query
		if ((min != null && min.get("age") != null) || (max != null && max.get("age") != null)) {
			if (fb.length() > 0) fb.append(" and ");
			
			fb.append("date_part('year', age(current_date, cast(concat(yearofbirth, '-', case when monthofbirth between 1 and 12 then monthofbirth ELSE '01' end, '-01') as timestamp))) BETWEEN ");
			if (min != null && min.get("age") != null) {
				fb.append(min.get("age") + " and ");
				urlBuilder.append("&min.age=" + min.get("age"));
				min.remove("age");
			} else { 	
				fb.append("0 and ");
			}
			
			if (max != null && max.get("age") != null) {
				fb.append(max.get("age"));
				urlBuilder.append("&max.age=" + max.get("age"));
				max.remove("age");
			} else { 
				fb.append("999");
			}
		}

        if (min != null) min.keySet()
                .stream()
                .filter(k -> min.get(k) != null)
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");

                    fb.append(k + " >= " + min.get(k));
                    urlBuilder.append("&min." + k + "=" + min.get(k));
                });

        if (max != null) max.keySet()
                .stream()
                .filter(k -> max.get(k) != null)
                .forEach(k -> {
                    if (fb.length() > 0) fb.append(" and ");
                    fb.append(k + " <= " + max.get(k));
                    urlBuilder.append("&max." + k + "=" + max.get(k));
                });

        return fb.toString();
    }

    public static void save(String id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/show.html", type, object);
            }
        }
        try {
            object._save();
            flash.success(play.i18n.Messages.get("crud.saved", type.modelName));
            if (params.get("_save") != null) {
                redirect(request.controller + ".list");
            }
            redirect(request.controller + ".show", object._key());
        }  catch (Exception e) {
            flash.error(play.i18n.Messages.get("crud.save.error", type.modelName));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, object);
            } catch (TemplateNotFoundException ex) {
                render("CRUD/show.html", type, object);
            }
        }
    }

    public static void create() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/blank.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/blank.html", type, object);
            }
        }

        try {
            object._save();
            flash.success(play.i18n.Messages.get("crud.created", type.modelName));
            if (params.get("_save") != null) {
                redirect(request.controller + ".list");
            }
            if (params.get("_saveAndAddAnother") != null) {
                redirect(request.controller + ".blank");
            }
            redirect(request.controller + ".show", object._key());
        }  catch (Exception e) {
            flash.error(play.i18n.Messages.get("crud.save.error", type.modelName));
            try {
                render(request.controller.replace(".", "/") + "/blank.html", type, object);
            } catch (TemplateNotFoundException ex) {
                render("CRUD/blank.html", type, object);
            }
        }
    }
}