package jomsocial;

import jomsocial.activity.Activity;
import jomsocial.resources.Photo;
import jomsocial.users.User;

import java.util.ArrayList;
import java.util.List;

public class FrontPage {
    public FrontPage loginAs(User user) {
        // todo
        return this;
    }

    public FrontPage shareStatus(String status) {
        // todo
        return this;
    }

    public FrontPage sharePhoto(Photo photo) {
        // todo
        return this;
    }

    public List<Activity> activities() {
        return new ArrayList<Activity>();
    }
}
