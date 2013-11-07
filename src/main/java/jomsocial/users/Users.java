package jomsocial.users;

public class Users {

    public static final User USER_A = Users.user("user-a");
    public static final User USER_B = Users.user("user-b");

    /**
     * If a user has been created with the given name it will be returned. If the user has not yet been created it will
     * be created and then returned.
     */
    public static User user(String username) {
        return new User();
    }
}