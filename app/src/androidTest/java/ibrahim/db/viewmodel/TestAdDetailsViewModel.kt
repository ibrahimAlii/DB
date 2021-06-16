package ibrahim.db.viewmodel


import androidx.test.core.app.ApplicationProvider
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
class TestAdDetailsViewModel {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_ad_details() {
        val mainRepository = MainRepository.getInstance(
            AppDatabase.getInstance(ApplicationProvider.getApplicationContext()).mainDao()
        )

        val adDetailViewModel = AdDetailsViewModel(mainRepository)
        val adsViewModel = AdsViewModel(mainRepository)


        activityRule.scenario.onActivity { activity ->
            adsViewModel.loadAds().observe(activity, { ads->
                adDetailViewModel.getAdById(ads.first().uid).observe(activity, { ad ->
                    assert(ad.name == ads.first().name)
                    assert(ad.created_at == ads.first().created_at)
                    assert(ad.uid == ads.first().uid)
                })
            })

        }

    }

}