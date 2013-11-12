package jomsocial.activity;

import jomsocial.pages.FrontPage;
import jomsocial.navigation.NavigateTo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static jomsocial.users.Users.*;
import static org.junit.Assert.assertEquals;

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
        // pre-conditions
        FrontPage frontPage = NavigateTo.frontPage();
        frontPage.loginAs(USER_A);
        String userAMessage = frontPage.shareStatus().text();
        frontPage.loginAs(USER_B);
        String userBMessage = frontPage.shareStatus().text();

        // testing user A's ability to like and unlike all activities.
        frontPage.loginAs(USER_A);
        List<StatusActivity> activities = frontPage.statusActivitiesWithText(userAMessage, userBMessage);
        assertEquals("Expected 2 activities to be found", 2, activities.size());
        for (StatusActivity activity : activities) {
            activity.mustBeLikeable()
                    .like()
                    .mustBeUnlikeable()
                    .unlike()
                    .mustBeLikeable();
        }
    }
}
