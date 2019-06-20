package graphics

import java.awt.Image
import java.io.File
import java.io.IOException
import java.util.HashMap

import javax.imageio.ImageIO

/**
 * class to load images into RAM to save disk read time
 * @author isaaczachmann
 */
class ImageList {


    //public static final String character = "characther" then map character to the images we want or something
    fun loadDefaultImages() {
        //loadImages...
    }

    companion object {

        private val images = HashMap<String, Image>()

        @Throws(IOException::class)
        fun loadImage(pathToImage: String, name: String) {
            images.put(name, ImageIO.read(File(pathToImage)))
        }

        fun getImage(name: String): Image? {
            return images.get(name)
        }

        operator fun get(name: String): Image? {
            return images.get(name)
        }

        fun unloadImage(name: String) {
            images.remove(name)
        }
    }
}
