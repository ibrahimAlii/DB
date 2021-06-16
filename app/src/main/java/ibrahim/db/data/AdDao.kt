package ibrahim.db.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the Ad class.
 */
@Dao
interface AdDao {
    @Query("SELECT * FROM ad ORDER BY created_at")
    fun getAds(): LiveData<List<Ad>>

    @Query("SELECT * FROM ad WHERE uid = :adId")
    fun getAd(adId: String): LiveData<Ad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAds(ads: List<Ad>)

    @Query("DELETE FROM ad")
    fun clearAds()
}
