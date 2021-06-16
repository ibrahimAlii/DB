package ibrahim.db.network


import ibrahim.db.data.Result
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AdsApi {

    @GET("default/dynamodb-writer/")
    fun getAdsAsync(): Deferred<Result>
}