package game

import controls.KeyControls
import controls.KeyboardActionMap
import controls.KeyboardInputMap
import graphics.GraphicsItem
import graphics.GraphicsWindow
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.Timer

fun main(args: Array<String>) {
    // TODO Auto-generated method stub
    val frame = JFrame()
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(800, 500)
    val panel = GraphicsWindow()
    val test = GraphicsItem()
    test.width = 200
    test.height = 200
    test.x = 100
    test.y = 100
    test.lineWidth = 10

    val test2 = GraphicsItem()

    panel.add(test2)

    test2.width = -100
    test2.height = -100
    test2.x = 400
    test2.y = 400
    test2.lineWidth = 10
    test2.type = GraphicsItem.IMAGE
    try {
        test2.texture = ImageIO.read(File(
                "/Users/isaaczachmann/Library/Mobile Documents/com~apple~CloudDocs/IMG_0155.JPG"))
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }


    val game = GameEngine()

    panel.add(test)
    frame.add(panel)

    var inputMap = KeyboardInputMap
    panel.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap)

    var actionMap = KeyboardActionMap
    panel.actionMap = actionMap

    test2.z = 10
    panel.reorder()
    //panel.getActionMap().put(KeyStroke.getAWTKeyStroke(KeyboardMapping.UP, 0, false), );
    frame.revalidate()
    panel.requestFocusInWindow()
    val loop = Timer(10, object : ActionListener {

        override fun actionPerformed(e: ActionEvent) {
            if (KeyControls.UP.isPressed) {
                test2.y--;
            }
            if (KeyControls.DOWN.isPressed) {
                test2.y++;
            }
            if (KeyControls.LEFT.isPressed) {
                test2.x--;
            }
            if (KeyControls.RIGHT.isPressed) {
                test2.x++;
            }
            if (KeyControls.ZOOM_IN.isPressed){
                test2.z++
                panel.reorder()
            }
            if (KeyControls.ZOOM_OUT.isPressed){
                test2.z--
                panel.reorder()
            }
            frame.repaint()
        }
    })
    loop.start()
}