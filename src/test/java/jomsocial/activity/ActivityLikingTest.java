package jomsocial.activity;

import jomsocial.FrontPage;
import jomsocial.login.Login;
import jomsocial.navigation.NavigateTo;
import jomsocial.resources.Photos;
import jomsocial.users.User;
import jomsocial.users.Users;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static jomsocial.resources.Photos.PHOTO_A;
import static jomsocial.users.Users.*;
import static org.junit.Assert.*;

public class ActivityLikingTest {

    // todo - setup and tear down?
    // keep in mind the abilty to execute this against a live site.
    // can I delete the activities that have been tested?


    // pre-conditions
    /*
    User A shares a message on his profile
    User A shares a photo via the share box
    User B shares a message on his profile

    Login with user A, go to the JomSocial front-page.
        Assert like links shown for all activities
    Click like on the photo sharing activity
        Assert like link turns to unlike
    Refresh the browser
        Assert like link is not present, unlike link is present.

     */

    @Test
    public void userCanLikeAnActivity() {
        // todo - before all precondition (and teardown?)

        // pre-conditions
        FrontPage frontPage = NavigateTo.frontPage();
        frontPage.loginAs(USER_A);
        frontPage.shareStatus("please like my status message");
        frontPage.sharePhoto(PHOTO_A);
        frontPage.loginAs(USER_B);
        frontPage.shareStatus("something relevant to user b");

        // testing user A's ability to like and unlike all activities.
        frontPage.loginAs(USER_A);
        List<Activity> activities = frontPage.activities();
        for (Activity activity : activities) {
            assertTrue(activity.isLikeable());
            activity.like();
            assertTrue(activity.isUnlikeable());
            activity.unlike();
            assertTrue(activity.isLikeable());
        }
    }
}
