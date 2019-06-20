package game

import graphics.GraphicsItem
import math.Point
import math.Vector
import physics.PhysicsBody
import kotlin.math.roundToInt

/**
 * A game item combines a physics and graphics item so it can exist together
 * @author isaaczachmann
 */
class GameItem {
    private var physicsBody = PhysicsBody()
    private var graphicsItem = GraphicsItem()

    /**
     * syncs the graphics item so that it matches the physics item
     * @param center
     * @param zoom
     */
    fun sync(zoom: Vector, center: Point) {
        val gCoords = convertToScreenCoords(physicsBody.position, zoom, center)
        graphicsItem.x = gCoords[0]
        graphicsItem.y = gCoords[1]
        graphicsItem.width = (physicsBody.width * zoom.x).roundToInt()
        graphicsItem.height = (physicsBody.height * zoom.y).roundToInt()
    }

    /**
     * Converts the point to screen coordinates and
     * returns x,y through int array size 2
     * @param p
     * @param center
     * @param zoom
     * @return int array with first element as x and second element as y
     */
    private fun convertToScreenCoords(p: Point, zoom: Vector, center: Point): IntArray {
        val out = IntArray(2)
        out[0] = ((p.x + center.x) * zoom.x).roundToInt()
        out[1] = ((p.y + center.y) * zoom.y).roundToInt()
        return out
    }
}
