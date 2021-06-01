package com.backbase.assignment

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.backbase.assignment.ui.movie.MoviesAdapter
import com.backbase.assignment.ui.view.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.backbase.assignment", appContext.packageName)

        // To test the onClick view id of recyclerview
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<MoviesAdapter.ViewHolder>(1,clickItemWithId(R.id.layout)))

        // To test the textView with the desired text
        onView(allOf(withId(R.id.txt_most_popular), withText(R.string.most_popular)))

    }


    fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }
}
