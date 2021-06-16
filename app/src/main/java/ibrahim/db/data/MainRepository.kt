package ibrahim.db.data


class MainRepository private constructor(private val adDao: AdDao) {

    fun getAds() = adDao.getAds()

    fun getAdById(adId: String) = adDao.getAd(adId)

    suspend fun insertAds(ads: List<Ad>) = adDao.insertAds(ads)

    fun clearAds() = adDao.clearAds()

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(adDao: AdDao) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(adDao).also { instance = it }
            }
    }
}