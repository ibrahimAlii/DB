package ibrahim.db

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import ibrahim.db.adapter.AdsAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailsActivityTest {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun stubAllExternalIntents() {
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    @Test
    fun test_open_details_activity() {
        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AdsAdapter.AdViewHolder>(
                    0, click()
                )
            )
    }

    @Test
    fun test_intent_ad_id_in_details() {
        val position = 3
        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AdsAdapter.AdViewHolder>(
                    position, click()
                )
            )

        val intent = Intents.getIntents().first()
        assertThat(intent, hasExtraWithKey(DetailsActivity.AD_ID))
    }

}

