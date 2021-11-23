package unit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import models.User;
import play.test.UnitTest;

/**
 * User Test
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest extends UnitTest {

    private static User user;

    @BeforeClass
    public static void setUp() {
        user = new User.Builder("jamaicaballangan")
                .name("Jamaica Ballangan")
                .password("maicanotfound")
                .status(User.Status.Active)
                .build();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        user.delete();
        //assertFalse(user.exists());
        //assertFalse(user.lookup().isPresent());
    }

    @Test
    public void stage01_saveUser() throws Exception {
        user.validateAndCreate();
        assertTrue(User.findById(user.getId()) != null);
    }

    @Test
    public void stage02_removeUser() throws Exception {
        user.delete();
        assertFalse(User.findById(user.getId()) != null);
    }
}