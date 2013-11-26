package jomsocial.users;

import jomsocial.config.Config;

public class Users {

    // todo - from configuration
    // todo - make unique for testing session
    public static final User USER_A = Users.user(Config.get("USER_A"), Config.get("USER_A_PASSWORD"));
    public static final User USER_B = Users.user(Config.get("USER_B"), Config.get("USER_B_PASSWORD"));

    /**
     * If a user has been created with the given name it will be returned. If the user has not yet been created it will
     * be created and then returned.
     */
    public static User user(String username, String password) {
        // todo - create if not already existing
        return new User(username, password);
    }
}