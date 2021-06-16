package ibrahim.db.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import ibrahim.db.data.MainRepository

class AdDetailsViewModel constructor(private val mainRepository: MainRepository) : ViewModel(),
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

    private val TAG = AdDetailsViewModel::class.java.simpleName


    var adLoader: Boolean = true
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.adLoader)
        }

    fun getAdById(adId: String) = mainRepository.getAdById(adId)

}