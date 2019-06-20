package controls

import java.awt.event.KeyEvent


object KeyControls : ArrayList<KeyboardControl>() {

    /**
     * Declasre all the keys to use and assign their bindings
     * (val makes them constants)
     */
    val UP = KeyboardControl(keyCode = KeyEvent.VK_W)
    val DOWN = KeyboardControl(keyCode = KeyEvent.VK_S)
    val LEFT = KeyboardControl(keyCode = KeyEvent.VK_A)
    val RIGHT = KeyboardControl(keyCode = KeyEvent.VK_D)
    val ZOOM_IN = KeyboardControl(keyCode = KeyEvent.VK_EQUALS)
    val ZOOM_OUT = KeyboardControl(keyCode = KeyEvent.VK_MINUS)

    init {
        this.add(UP)
        this.add(DOWN)
        this.add(LEFT)
        this.add(RIGHT)
        this.add(ZOOM_IN)
        this.add(ZOOM_OUT)
    }
}

/**
 * A data structure for a keybard control
 * @property keyCode the KeyEvent key code for the key
 * @property isPressed the state of the keybaord control if its pressed or released at the time
 */
data class KeyboardControl(var keyCode : Int, var isPressed: Boolean = false)
