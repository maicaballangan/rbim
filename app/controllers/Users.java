package controllers;

import play.data.validation.Max;
import play.data.validation.Min;

/**
 * Users
 *
 * @author Maica Ballangan
 * @since v1
 */
public class Users extends CRUD {

    /**
     * Referer-based Authentication from HTTP Header
     */
    /*@Before
    static void authenticate() throws HttpException {
        APIHelpers.authenticate();
    }*/

    /**
     * GET      /users
     *
     * @param email path
     * @param after path
     * @param size  path
     * @param page  path
     */
    public static void getAll(final String username,
                              final String email,
                              final String after,
                              @Min(0) @Max(100) final Long size,
                              @Min(1) final Integer page) {
        /* var response = User.Builder(username)
            .setEmail(email)
            .setLimit(size != null ? size : Constants.RESULTS_SIZE)
            .setStartingAfter(after)
            .build());*/
        //render(APIHelpers.GET_ALL_TEMPLATE, page, hasMore, results);
    }

    /**
     * GET      /users/{username}
     *
     * @param username path
     */
    /*public static void get(@Required final String username) {
        renderJSON(User.findById(username));
    }

    public static void create(@Required String username,
                              @Required String name,
                              @Password String password) {
        var user = new User.Builder(username)
                .name(name)
                .password(password)
                .build();
        user.create();
        //response.status = 201;
        renderTemplate("views/users.html", user);
    }*/
}
