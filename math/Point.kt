package math

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * A floating point x,y
 * @author isaaczachmann
 */
class Point {

    /**
     * The x-coordinate of the point
     */
    var x: Double = 0.0

    /**
     * The y-coordinate of the point
     */
    var y: Double = 0.0


    /**
     * Creates a point at the specified coordinates
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    /**
     * Creates a new point at the end of the vector. The initial point argument is for the start of the vector.
     * @param point the start point
     * @param vec the vector to go from the start point.
     */
    constructor(point: Point, vec: Vector) {
        x = point.x + vec.x
        y = point.y + vec.y
    }


    override fun toString(): String {
        return "($Double, $Double)"
    }

    /**
     * Returns true if this point is at the same location as Point other
     */
    override fun equals(other: Any?): Boolean {
        return if (other is Point) {
            ( ((other.x - this.x).absoluteValue < ERROR) && ((other.y - this.y).absoluteValue < ERROR) )
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        val a = (this.x*ERROR + this.y*ERROR)
        return (a*10000).roundToInt()
    }

    /**
     * Creates a vector with an x, y, and z component the same as the x, y, and z components of this point.
     * Useful for doing math with points.
     * Another way to think about it is creating a vector from the origin to this point.
     * @return the vector with the same x, y, z of this point
     */
    fun toVector(): Vector {
        return Vector(x, y)
    }

    companion object {

        /**
         * Just a point at the origin, (0,0)
         */
        val ORIGIN = Point(0.0, 0.0)

        /**
         * Finds and returns the midpoint between two points
         * @param pointA the first point
         * @param pointB the second point
         * @return the midpoint
         */
        fun midpoint(pointA: Point, pointB: Point): Point {
            return Point((pointA.x + pointB.x) / 2, (pointA.y + pointB.y) / 2)
        }

        /**
         * Finds and returns the distance between two points
         * @param pointA the first point
         * @param pointB the second point
         * @return the distance
         */
        fun distance(pointA: Point, pointB: Point): Double {
            return ((pointA.x - pointB.x).pow(2.0) + (pointA.y - pointB.y.pow(2.0))).pow(0.5)
        }

        /**
         * Fraction of error to assume a point is still collinear with the other two in the isPointBetween function.
         */
        private const val ERROR = 0.000001

        /**
         * Calculates if p3 is collinear and lies on the segment created by p1 and p2. If it is, return true, if not
         * return false. Returns true if testPoint is the same as either p1 or p2.
         * NOTE: Rounding problems made it so that instead of testing exactly it will still return true even if the
         * point is a little bit off this error amount of error is percent off for each of the components of vectors
         * generated.
         * NOTE: new way of doing face intersections may not need this rounding.
         * @param p1 The first point of the line segment
         * @param p2 The second point of the line segment
         * @param testPoint to point to test if it lies on the line segment
         * @return True if testPoint is between p1 and p2, false if otherwise.
         */
        fun isPointBetween(p1: Point, p2: Point, testPoint: Point): Boolean {
            val segment = Vector(p1, p2)
            val test = Vector(p1, testPoint)
            val segmentAtLength = Vector.multiplyVectorByScalar(segment, test.magnitude / segment.magnitude)
            /*The reason for this: originally looked
				at unit vectors of both segment and test to check if they are the same direction, BUT ran into
				rounding errors when test was significantly small, this caused the rounding errors when expanding it
				to unit vector size. Instead, shrink the segment vector to test vector size. This should work because
				 segment vector will always be longer than test vector if the point	is between. <--- Also that didn't
				  fix it completely because there are still small rounding errors so this ERROR thing is a work
				  around that would be good to fix*/
            return if (Math.abs(segmentAtLength.x - test.x) < ERROR &&
                    Math.abs(segmentAtLength.y - test.y) < ERROR &&
                    test.magnitude <= segment.magnitude) {
                true
            } else {
                return (p1 == testPoint || p2 == testPoint)
            }
        }
    }

}
