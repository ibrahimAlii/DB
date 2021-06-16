package ibrahim.db.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ibrahim.db.data.MainRepository
import ibrahim.db.worker.AdsWorker

class AdsViewModel constructor(private val mainRepository: MainRepository) : ViewModel(),
    Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    @Suppress("unused")
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    private val TAG = AdsViewModel::class.java.simpleName


    var refresherVisibility: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.refresherVisibility)
        }

    var adsLoader : Boolean = true
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.adsLoader)
        }

    fun loadAds() = mainRepository.getAds().also {
        getAds()
    }

    fun reload() {
        mainRepository.clearAds()
        getAds()
    }


    private fun getAds() {
        if (!refresherVisibility)
            adsLoader = true

        val data = Data.Builder().build()
        val request = OneTimeWorkRequestBuilder<AdsWorker>()
            .setInputData(data).build()

        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observeForever { workInfo ->
            if (workInfo.state.isFinished) {
                if (workInfo.outputData.getInt(AdsWorker.STATE, 0) == AdsWorker.SUCCESS) {
                    //errorVisibility = true
                    //kotlin.runCatching { recycler!!.scrollToPosition(0) }
                } else if (workInfo.outputData.getInt(
                        AdsWorker.STATE,
                        0
                    ) == AdsWorker.FAIL
                ) {
                    //errorVisibility = false
                    //errorText = workInfo.outputData.getString(AdsWorker.MESSAGE)!!
                }

                adsLoader = false
                refresherVisibility = false


                if (workInfo.outputData.getBoolean(AdsWorker.EMPTY_ADS, false)) {
                    //handle empty visibility
                    //emptyVisibility = false
                }
            }
        }
        WorkManager.getInstance().enqueue(request)
    }
}