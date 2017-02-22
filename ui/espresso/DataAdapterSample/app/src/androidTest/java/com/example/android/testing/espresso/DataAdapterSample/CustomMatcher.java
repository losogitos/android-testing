package com.example.android.testing.espresso.DataAdapterSample;


import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * It's probably a not good implementation because I'm not using typing of Map here.
 * Maybe better is to use TypeSafeMatcher<Map<? extends K, ? extends V>> like in Hamcrest implementation.
 */
public class CustomMatcher {

    static Matcher<Object> hasEntry(final String key, final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<Object, Map>(Map.class) {

            @Override
            public boolean matchesSafely(Map entry) {
//                Map<String, Object> entryMap = (Map<String, Object>) entry;
                if (!entry.containsKey(key)) return false;
                String value = (String) entry.get(key);
                return value != null && stringMatcher.matches(value);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with value: ");
                stringMatcher.describeTo(description);
            }
        };
    }
}
