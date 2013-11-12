package jomsocial.activity;

import jomsocial.navigation.NavigateTo;
import jomsocial.pages.FrontPage;
import org.junit.BeforeClass;
import org.junit.Test;

import static jomsocial.users.Users.USER_A;

/**
 * As per JS-1696, verify the activity feature works as expected on the frontpage.
 * Not tested because it is covered elsewhere:
 *   - liking tested by ActivityLikingTest
 *
 */
public class FrontPageActivityTest {

    private static FrontPage page;

    @BeforeClass
    public static void becomeUserAOnTheFrontPage() {
        page = NavigateTo.frontPage();
        page.loginAs(USER_A);
    }

    @Test
    public void canCreateAndDeleteStatusMessage() {
        StatusActivity activity = page.shareStatus();
        activity.mustBeVisible();
        // todo - Jem WIP - comment on it & delete
    }

}
