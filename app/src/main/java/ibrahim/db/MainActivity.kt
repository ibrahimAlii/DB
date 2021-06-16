package ibrahim.db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ibrahim.db.adapter.AdsAdapter
import ibrahim.db.databinding.ActivityMainBinding
import ibrahim.db.utils.InjectorUtils
import ibrahim.db.viewmodel.AdsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adsViewModel: AdsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        adsViewModel = ViewModelProvider(this, InjectorUtils.provideAdsViewModel(this))
                .get(AdsViewModel::class.java)
        super.onCreate(savedInstanceState)
        val view = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            model = adsViewModel
        }


        val adapter = AdsAdapter(this)
        view.recycler.adapter = adapter
        adsViewModel.loadAds().observe(this, {
            adapter.submitList(it)
        })

        view.refresher.setOnRefreshListener {
            adsViewModel.refresherVisibility = true
            adsViewModel.loadAds()
        }
    }
}