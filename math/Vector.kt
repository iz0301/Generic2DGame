package math

import kotlin.math.pow
import kotlin.math.roundToInt


/**
 * A class for Vectors and vector math
 * @author Isaac Zachmann
 */
class Vector {

    /**
     * The x-component of the vector
     */
    var x: Double = 0.0

    /**
     * The y-component of the vector
     */
    var y: Double = 0.0

    /**
     * Gets the magnitude of the vector.
     * @return the magnitude of the vector
     */
    val magnitude
        get() = (x.pow(2) + y.pow(n = 2)).pow(0.5)

    /**
     * Creates and returns a vector with the same direction, but a magnitude of one.
     * If the vector is a zero vector, returns a zero vector
     * @return a unit vector if possible, otherwise a zero vector
     */
    val unitVector: Vector
        get() = if (this.magnitude != 0.0) {
            Vector.multiplyVectorByScalar(this, 1.0 / this.magnitude)
        } else {
            ZERO_VECTOR
        }


    /**
     * Creates a vector with the specified components.
     * @param x the x-component of the vector
     * @param y the y-component of the vector
     * @param z the z-component of the vector
     */
    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    /**
     * Creates a vector going from pointA to pointB.
     * Note: Vectors do not actually have location, so this vector will have the direction and magnitude, but not the location
     * @param pointA The start position of the vector
     * @param pointB The ending position of the vector
     */
    constructor(pointA: Point, pointB: Point) {
        x = pointB.x - pointA.x
        y = pointB.y - pointA.y
    }

    /**
     * Creates a vector with a direction identical to dir, but with the specified magnitude.
     * @param dir the direction vector of the new vector
     * @param magnitude the magnitude of the new vector
     */
    constructor(dir: Vector, magnitude: Float) {
        x = dir.x * (magnitude / dir.magnitude)
        y = dir.y * (magnitude / dir.magnitude)
    }

    /**
     * Tests if one vector has the same value as the other
     * @param other The other vector to compare against
     * @return true if the vectors have the same components, false if otherwise
     */
    override fun equals(other : Any?): Boolean {
        return if(other == null || (other !is Vector) ){
            false
        } else {
            (this.x == other.x && other.y == other.y)
        }
    }

    /**
     * Returns the hashCode generated from the compoents of the vector
     */
    override fun hashCode(): Int {
        return (x*1000 + y*1000).roundToInt()
    }

    /**
     * Generates a new vector that is the same magnitude but opposite direction as this one.
     * Does not modify this vector, simply generates a new one.
     * @return The negative of this vector
     */
    fun invert(): Vector {
        return Vector.multiplyVectorByScalar(this, -1.0)
    }

    /**
     * Does subtraction of this vector and v and returns the result
     * @param v
     * @return
     */
    operator fun minus(v: Vector): Vector {
        return Vector.subtractVectors(this, v)
    }


    override fun toString(): String {
        return "<$x, $y>"
    }

    /**
     * Creates a new point with the same components as this vector
     * @return A new point with the components of this vector as x, y, and z
     */
    fun toPoint(): Point {
        return Point(x, y)
    }

    /**
     * @return This vector multiplied by a scalar value
     */
    fun timesScalar(scalar: Double) : Vector {
        return Vector.multiplyVectorByScalar(this, scalar)
    }

    /**
     * @return This vector dot multiplied by a vector value
     */
    fun dotTimes(vecA: Vector) : Double {
        return Vector.dotMultiply(this, vecA)
    }

    companion object {

        /**
         * Defines a vector with zero as all components
         */
        val ZERO_VECTOR = Vector(0.0, 0.0)

        /**
         * Returns a new vector with the specified components.
         * @param x the x-component of the vector
         * @param y the y-component of the vector
         * @param z the z-component of the vector
         * @return a new Vector
         */
        @Deprecated("vector constructor instead", replaceWith = ReplaceWith("Vector(x=x, y=y)"))
        fun makeVector(x: Double, y: Double): Vector {
            return Vector(x, y)
        }

        /**
         * Adds the two specified vectors and returns the result.
         * @param vecA the fist vector to add
         * @param vecB the second vector to add
         * @return the result vector (vecA+vecB)
         */
        fun addVectors(vecA: Vector, vecB: Vector): Vector {
            return Vector(vecA.x + vecB.x, vecA.y + vecB.y)
        }

        /**
         * Subtracts vecB from vecA and returns the result.
         * @param vecA the minuend vector
         * @param vecB the subtrahend vector
         * @return the result vector (vecA-vecB)
         */
        fun subtractVectors(vecA: Vector, vecB: Vector): Vector {
            return Vector(vecA.x - vecB.x, vecA.y - vecB.y)
        }

        /**
         * Multiplies vecA by scalar and returns the outcome.
         * @param vecA the vector to be multiplied by scalar
         * @param scalar the quantity to multiply vecA by
         * @return the result vector (vecA*scalar)
         */
        fun multiplyVectorByScalar(vecA: Vector, scalar: Double): Vector {
            return Vector(vecA.x * scalar, vecA.y * scalar)
        }

        /**
         * Element multiplies (vector product) vecA by vecB and returns the product. Elemental multiplication simply
         * multiplies each element by the corresponding element in the other vector. It multiplies both the first elements
         * to get the resulting first element, then does thing same thing for the other two elements.
         * @param vecA The first vector to multiply elements of
         * @param vecB The second vector to multiply elements of
         * @return the resulting vector (vecA.x*vecB.x, vecA.y*vecB.y, vecA.z*vecB.z)
         */
        fun elementMultiply(vecA: Vector, vecB: Vector): Vector {
            return Vector(vecA.x * vecB.x, vecA.y * vecB.y)
        }

        /**
         * Element divides (vector out) vecA by vecB and returns the quotient. Elemental division simply
         * divides each element by the corresponding element in the other vector. It divides both the first elements
         * to get the resulting first element, then does thing same thing for the other two elements.
         * @param vecA The first vector to divide elements of
         * @param vecB The second vector to divide elements of
         * @return the resulting vector (vecA.x/vecB.x, vecA.y/vecB.y, vecA.z/vecB.z)
         */
        fun elementDivide(vecA: Vector, vecB: Vector): Vector {
            return Vector(vecA.x / vecB.x, vecA.y / vecB.y)
        }

        /**
         * Dot multiplies (scalar product) vecA by vecB and returns the product. Dot vector multiplication returns a scalar
         * quantity. You can look up dot product multiplication for more info.
         * @param vecA the first vector to multiply
         * @param vecB the second vector to multiply
         * @return the result scalar (vecA * vecB)
         */
        fun dotMultiply(vecA: Vector, vecB: Vector): Double {
            //a*b = ax * bx + ay * by + az *bz
            return vecA.x * vecB.x + vecA.y * vecB.y
        }

        /**
         * Checks if the two vectors are equal. Vectors are equal if they have the same direction and magnitude.
         * @param vecA the first vector to check
         * @param vecB the second vector to check
         * @return true if the vectors are equal; false if otherwise
         */
        fun areVectorsEqual(vecA: Vector, vecB: Vector): Boolean {
            return (vecA.x == vecB.x && vecA.y == vecB.y)
        }
    }
}
