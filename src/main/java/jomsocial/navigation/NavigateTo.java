package jomsocial.navigation;

import jomsocial.pages.FrontPage;

public class NavigateTo {

    public static FrontPage frontPage() {
        return new FrontPage().open();
    }
}
