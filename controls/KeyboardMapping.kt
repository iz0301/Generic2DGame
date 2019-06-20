package controls

import java.awt.event.ActionEvent
import java.awt.event.InputEvent
import javax.swing.AbstractAction
import javax.swing.ActionMap
import javax.swing.InputMap
import javax.swing.KeyStroke


/**
 * Generates an InputMap for all the keys in the KeyControls array (Controls.kt)
 * the actions that are mapped to the key press/release are the KeybaordControl
 * object's hashCode convrted to a string and " press" or " release"
 */
val KeyboardInputMap: InputMap
    get() {
        val inputMap = InputMap()

        val modifiers = intArrayOf(
                0,
                InputEvent.ALT_DOWN_MASK,
                InputEvent.ALT_GRAPH_DOWN_MASK,
                InputEvent.SHIFT_DOWN_MASK,
                InputEvent.CTRL_DOWN_MASK,
                InputEvent.META_DOWN_MASK)

        /**
         * Gets all the combinations of the modifiers and returns them as an array. Combinations are determined by
         * addition. (ex: input 1 2 4, output 1 2 3 4 5 6 7)
         */
        fun getAllCombosFor(modifier : IntArray) : IntArray {
            if (modifier.size == 1){
                return intArrayOf(modifier.first())
            }

            val combos = mutableListOf<Int>()
            for (i in modifier.indices){
                val theRest = modifier.copyOfRange(i+1, modifier.size)
                for (c in getAllCombosFor(theRest)){
                    combos.add(modifier[i] + c)
                }
                combos.add(modifier[i])
            }
            return combos.toIntArray()
        }

        // Gets a list of all possible modifiers
        val allModifiers = getAllCombosFor(modifiers)

        println(allModifiers)
        println(allModifiers.size)
        for (kc in KeyControls) {
            // Make sure we add the action for any possible modifiers that are pressed
            for(mod in allModifiers){
                inputMap.put(KeyStroke.getKeyStroke(kc.keyCode, mod, false),
                        kc.hashCode().toString() + " press")
                inputMap.put(KeyStroke.getKeyStroke(kc.keyCode, mod, true),
                        kc.hashCode().toString() + " release")
            }
        }
        return inputMap
    }

val KeyboardActionMap: ActionMap
    get() {
        val actionMap = ActionMap()
        for (kc in KeyControls) {
            actionMap.put(kc.hashCode().toString() + " press", object : AbstractAction() {
                override fun actionPerformed(e: ActionEvent) {
                    kc.isPressed = true
                }
            })
            actionMap.put(kc.hashCode().toString() + " release", object : AbstractAction() {
                override fun actionPerformed(e: ActionEvent) {
                    kc.isPressed = false
                }
            })
        }
        return actionMap
    }

