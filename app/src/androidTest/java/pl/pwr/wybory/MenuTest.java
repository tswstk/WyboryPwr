package pl.pwr.wybory;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void menuTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.login_editText),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.login_editText),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("log1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.login_editText), withText("log1"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Zaloguj"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.show_elections_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.questionary_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button10 = onView(
                allOf(withId(R.id.show_profile_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        button10.check(doesNotExist());

        pressBack();

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.login_editText), withText("log1"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("log3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.password_editText), withText("log1"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("log3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.password_editText), withText("log3"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText9.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login_button), withText("Zaloguj"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.show_profile_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.questionary_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.show_elections_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.login_editText), withText("log3"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("log8"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.login_editText), withText("log8"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.password_editText), withText("log3"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("log8"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.password_editText), withText("log8"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText13.perform(pressImeActionButton());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.login_button), withText("Zaloguj"),
                        withParent(allOf(withId(R.id.activity_login),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.show_elections_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.questionary_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction button8 = onView(
                allOf(withId(R.id.show_profile_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_menu),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        button10.check(doesNotExist());

    }
//doesNotExist()
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
