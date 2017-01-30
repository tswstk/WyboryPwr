package pl.pwr.wybory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("pl.pwr.wybory", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<LoginActivity> rule  = new  ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureListViewIsPresent() throws Exception {
        onView(withId(R.id.login_editText)).perform(typeText(""));
        onView(withId(R.id.password_editText)).perform(typeText(""));
        onView(withId(R.id.login_button)).perform(click());
        onView(withText(startsWith("N"))).
                inRoot(withDecorView(
                        not(is(rule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }



//    @Rule
//    // third parameter is set to false which means the activity is not started automatically
//    public ActivityTestRule<ElectionsActivity> rule =
//            new ActivityTestRule(AddElectionsDialog.class, true, false);
//
//    @Test
//    public void demonstrateIntentPrep() {
//        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), AddElectionsDialog.class);
//        rule.launchActivity(intent);
//        onView(withId(R.id.show_elections_button)).check(matches(wi));
//    }
}
