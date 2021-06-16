package ibrahim.db

import ibrahim.db.data.Converters
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    private val images = arrayOf("IMAGE_URL_DUMMY")

    @Test fun singleImageToArray() {
        assertArrayEquals(images, Converters().singleImageToArray(images[0]))
    }

    @Test fun imageArrayToSingleImage() {
        assertEquals(Converters().imageArrayToSingleImage(images), images[0])
    }
}
