package ibrahim.db.utils

import android.content.Context
import ibrahim.db.data.AppDatabase
import ibrahim.db.data.MainRepository
import ibrahim.db.viewmodel.AdDetailsVMFactory
import ibrahim.db.viewmodel.AdsViewModelFactory

object InjectorUtils {

    private fun getMainRepository(context: Context): MainRepository {
        return MainRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).mainDao()
        )
    }

    fun provideAdsViewModel(
        context: Context
    ): AdsViewModelFactory {
        return AdsViewModelFactory(getMainRepository(context))
    }


    fun provideAdDetailsViewModel(
        context: Context
    ): AdDetailsVMFactory {
        return AdDetailsVMFactory(getMainRepository(context))
    }


}