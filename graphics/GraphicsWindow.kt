package graphics

import java.awt.Graphics
import javax.swing.JPanel

/**
 * A class to make a graphics window based on a JFrame
 * @author isaaczachmann
 */
class GraphicsWindow : JPanel() {

    /**
     * This array list holds all the graphics items to paint on the panel
     */
    private val graphicsItems = mutableListOf<GraphicsItem>()

    /**
     * Reorders the graphicsItems so that all of the "z" are drawn directly, this is more computationally heavy so it
     * isnt done every graphics update, just do it when a z value changes or new objects are added`
     */
    fun reorder() : Unit{
        graphicsItems.sortByDescending { it.z }
        println("ho yo")
    }

    /**
     * The custom painting method for the game
     */
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        for (gi in graphicsItems) {
            when (gi.type) {
                GraphicsItem.RECT -> {
                    g.color = gi.edgeColor
                    g.fillRect(gi.x, gi.y, gi.width, gi.height)
                    g.color = gi.fillColor
                    g.fillRect(gi.x + gi.lineWidth, gi.y + gi.lineWidth, gi.width - 2 * gi.lineWidth,
                            gi.height - 2 * gi.lineWidth)
                }
                GraphicsItem.IMAGE -> g.drawImage(gi.texture, gi.x, gi.y, gi.width, gi.height, null)
                GraphicsItem.TEXT -> {
                    g.color = gi.fillColor
                    g.drawString(gi.text, gi.x, gi.y)
                }
                GraphicsItem.HIDDEN -> {}
            }

        }
    }

    /**
     * Adds the graphics item to the panel
     * @return the added item
     */
    fun add(gi: GraphicsItem): GraphicsItem {
        graphicsItems.add(gi)
        reorder()
        return gi
    }

    /**
     * remove the graphics item from the panel
     */
    fun remove(gi: GraphicsItem) {
        graphicsItems.remove(gi)
    }

}
