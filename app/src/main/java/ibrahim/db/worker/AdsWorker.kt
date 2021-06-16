package ibrahim.db.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import ibrahim.db.data.Ad
import ibrahim.db.data.AppDatabase
import ibrahim.db.network.AdsApi
import ibrahim.db.network.Client
import kotlinx.coroutines.coroutineScope

class AdsWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val database = AppDatabase.getInstance(applicationContext)
        val data = Data.Builder()
        try {
            val ads = getAds()
            database.mainDao().insertAds(ads)
            data.putInt(STATE, SUCCESS)
            data.putString(MESSAGE, "Success call")
            data.putBoolean(EMPTY_ADS, ads.isEmpty())
            Result.success(data.build())
        } catch (ex: Exception) {
            Log.e(TAG, "Error database", ex)
            Log.d(TAG, ex.message!!)
            data.putInt(STATE, FAIL)
            data.putString(MESSAGE, ex.localizedMessage)
            Result.failure(data.build())
        }
    }


    private suspend fun getAds(): List<Ad> {
        val data = Client.retrofit.create(AdsApi::class.java).getAdsAsync().await().results


        return kotlin.runCatching { data }.getOrDefault(emptyList())
    }



    companion object {
        private val TAG = AdsWorker::class.java.simpleName
        const val STATE = "state"
        const val SUCCESS = 200
        const val FAIL = 401
        const val EMPTY_ADS = "empty_ads"
        const val MESSAGE = "message"
    }
}