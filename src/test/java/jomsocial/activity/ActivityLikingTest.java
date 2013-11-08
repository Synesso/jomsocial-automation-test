package jomsocial.activity;

import jomsocial.pages.FrontPage;
import jomsocial.navigation.NavigateTo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static jomsocial.users.Users.*;

public class ActivityLikingTest {

    /*
    User A shares a message on his profile
    User B shares a message on his profile
    Login with user A, go to the JomSocial front-page.
        Assert like links shown for all activities
    Click like on each activity
        Assert like link turns to unlike
     */

    @Test
    public void userCanLikeAnActivity() {
        // todo - before all precondition (and teardown?)

        String userAMessage = UUID.randomUUID().toString();
        String userBMessage = UUID.randomUUID().toString();

        // pre-conditions
        FrontPage frontPage = NavigateTo.frontPage();
        frontPage.loginAs(USER_A);
        frontPage.shareStatus(userAMessage);
       frontPage.loginAs(USER_B);
        frontPage.shareStatus(userBMessage);

        // testing user A's ability to like and unlike all activities.
        frontPage.loginAs(USER_A);
        List<StatusActivity> activities = frontPage.statusActivities();
        List<String> messages = Arrays.asList(userAMessage, userBMessage);
        for (StatusActivity activity : activities) {
            if (messages.contains(activity.text())) {
                activity.mustBeLikeable()
                        .like()
                        .mustBeUnlikeable()
                        .unlike()
                        .mustBeLikeable();
            }
        }
    }
}
