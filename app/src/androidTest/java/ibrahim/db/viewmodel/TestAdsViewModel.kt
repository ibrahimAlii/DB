package ibrahim.db.viewmodel

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import ibrahim.db.MainActivity
import ibrahim.db.data.AppDatabase
import ibrahim.db.data.MainRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class TestAdsViewModel {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_ads_list() {
        val mainRepository = MainRepository.getInstance(
            AppDatabase.getInstance(getApplicationContext()).mainDao()
        )

        val adsViewModel = AdsViewModel(mainRepository)

        activityRule.scenario.onActivity {
            adsViewModel.loadAds().observe(it, { ads ->
                assert(!ads.isNullOrEmpty())
            })
        }

    }

}