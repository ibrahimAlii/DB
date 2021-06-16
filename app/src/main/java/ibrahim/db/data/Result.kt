package ibrahim.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Result(
    val results: List<Ad>
)

@Entity(tableName = "ad")
data class Ad(
    @PrimaryKey val uid: String,
    val created_at: String,
    val price: String,
    val name: String,
    val image_ids: Array<String>,
    val image_urls: Array<String>,
    val image_urls_thumbnails: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ad

        if (uid != other.uid) return false
        if (created_at != other.created_at) return false
        if (price != other.price) return false
        if (name != other.name) return false
        if (!image_ids.contentEquals(other.image_ids)) return false
        if (!image_urls.contentEquals(other.image_urls)) return false
        if (!image_urls_thumbnails.contentEquals(other.image_urls_thumbnails)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + created_at.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + image_ids.contentHashCode()
        result = 31 * result + image_urls.contentHashCode()
        result = 31 * result + image_urls_thumbnails.contentHashCode()
        return result
    }
}
