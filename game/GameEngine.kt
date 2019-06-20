package game

import math.Point
import math.Vector

/**
 * A class to handle graphics and world things for the game
 */
class GameEngine {

    /**
     *  A list to hold all of the game items in this world
     */
    private val gameItems = mutableListOf<GameItem>()

    /**
     * The zoom to relate pixel size to screen size, X zoom and Y zoom in one vector
     * Relates world coordinates (physics) to graphics equival
     */
    val zoom = Vector(1.0, 1.0)

    /**
     * The location in the game world to center the screen
     */
    private val center = Point.ORIGIN


    fun gameUpdate() {
        graphicsUpdate()
    }

    /**
     * Updates all of the world itmes so that their graphics are synchronized with their corresponding physics items.
     * Refresh the panel
     */
    private fun graphicsUpdate() {
        for (gmi in gameItems) {
            gmi.sync(zoom, center)
        }
        //panel.update
    }


}
