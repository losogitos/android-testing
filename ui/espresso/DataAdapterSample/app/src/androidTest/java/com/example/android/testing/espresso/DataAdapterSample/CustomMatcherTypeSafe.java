package com.example.android.testing.espresso.DataAdapterSample;


import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class CustomMatcherTypeSafe {


    static Matcher<Map<String, Object>> hasEntry(final String key, final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new TypeSafeMatcher<Map<String, Object>>() {

            @Override
            public boolean matchesSafely(Map<String, Object> entry) {
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
