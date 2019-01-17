package com.chaomao.lala

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.chaomao.lala.nativetextinput.NativeTextInputManager

class ChatScreen : Screen {
    private val contextTable = Table()
    private val inputBounds: Rectangle = Rectangle()
    private val stage = Stage()
    private val padding = 8 * Gdx.graphics.density

    init {
        contextTable.add().expand().row()
        val rootTable = Table()
        rootTable.setFillParent(true)
        stage.addActor(rootTable)

        val blankPixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        blankPixmap.setColor(Color.ROYAL)
        blankPixmap.fill()
        val blankDrawable = TextureRegionDrawable(TextureRegion(Texture(blankPixmap)))
        blankPixmap.dispose()
        val barHeight = 56 * Gdx.graphics.density

        val toolbar = Table()
        toolbar.background = blankDrawable
        val backButtonStyle = ImageButton.ImageButtonStyle()
        backButtonStyle.imageUp = TextureRegionDrawable(
            Texture(Gdx.files.internal("round-arrow_back_ios-48px.png"))
        ).tint(Color.BLACK)
        val backButton = ImageButton(backButtonStyle)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
            }
        })
        val trivialActor = Actor()

        toolbar.add(backButton)
        toolbar.add(trivialActor).left().top().expand()

        rootTable.add(contextTable).expand().fill().row()
        rootTable.add(toolbar).size(Gdx.graphics.width.toFloat(), barHeight).row()
        rootTable.pack()

        val stageCoordinate = trivialActor.localToStageCoordinates(Vector2(0f, 0f))
        inputBounds.set(stageCoordinate.x, Gdx.graphics.height.toFloat() - stageCoordinate.y, Gdx.graphics.width.toFloat() - backButton.width - padding,
            barHeight
        )
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
        Gdx.input.isCatchBackKey = true
        val searchEditTextListener = object : NativeTextInputManager.OnTextChangedListener {
            override fun onEnter(str: String) {
                Gdx.app.postRunnable{
                    contextTable.add(MessageActor(str)).padLeft(padding).row()
                }
            }

            override fun onTextChanged(str: String) {
            }
        }
        platformObjects.nativeTextInputManager.show(inputBounds.x.toInt(), inputBounds.y.toInt(),
            inputBounds.width.toInt(), inputBounds.height.toInt(), searchEditTextListener,
            "Input here")
    }

    override fun hide() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }

    override fun dispose() {
    }
}
