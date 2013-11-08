package jomsocial.users;

public class Users {

    // todo - from configuration
    // todo - make unique for testing session
    public static final User USER_A = Users.user("user-a", "password");
    public static final User USER_B = Users.user("user-b", "password");

    /**
     * If a user has been created with the given name it will be returned. If the user has not yet been created it will
     * be created and then returned.
     */
    public static User user(String username, String password) {
        // todo - create if not already existing
        return new User(username, password);
    }
}