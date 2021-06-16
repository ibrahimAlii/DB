package ibrahim.db.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun imageArrayToSingleImage(images: Array<String>): String = images[0]

    @TypeConverter fun singleImageToArray(image: String): Array<String> = arrayOf(image)
}