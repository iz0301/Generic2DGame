package physics

import math.Point
import math.Vector

class PhysicsBody {

    var position = Point.ORIGIN

    var velocity = Vector.ZERO_VECTOR

    var acceleration = Vector.ZERO_VECTOR

    var width :Double = 0.0

    var height :Double = 0.0
}