package graphics

import java.awt.Color
import java.awt.Image

/**
 * This class creates a generic graphics item that can be loaded on a graphics window
 * @author isaaczachmann
 */
class GraphicsItem {


    /**
     * The type, should be TEXT IMAGE RECT or HIDDEN
     */
    var type = RECT

    /**
     * The image to show on the screen for the graphics item, or null to draw a different shape
     */
    var texture: Image? = null

    /**
     * If showText is true, draw this text as the graphics item
     */
    var text: String? = null

    /**
     * The color to fill for this graphics item if it is rect
     * or text color if its text
     */
    var fillColor = Color.BLUE

    /**
     * The color to do edged for this graphics item if a texture isn't set
     */
    var edgeColor = Color.BLACK

    /**
     * Line width in px if its a generic rectangle
     */
    var lineWidth = 1

    /**
     * The position of the (upper left) corner of the object x coordinate
     */
    var x: Int = 0

    /**
     * The position of the (upper left) corner of the object x coordinate
     */
    var y: Int = 0

    /**
     * The "z" location for the Graphics Item, items with larger z will be drawn in front of objects with smaller z.
     * For objects with the same it cannot be certain which will be drawn on top
     */
    var z: Int = 0;

    /**
     * The width of the object
     */
    var width: Int = 0

    /**
     * the height of the object
     */
    var height: Int = 0

    companion object {

        /**
         * type to represent should be text
         */
        val TEXT = 1

        /**
         * type to represent should be image with texture
         */
        val IMAGE = 2

        /**
         * type to represent should be rectangular
         */
        val RECT = 3

        /**
         * type to represent should be hidden
         */
        val HIDDEN = 4
    }

}
