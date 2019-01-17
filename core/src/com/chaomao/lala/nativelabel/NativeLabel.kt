package com.chaomao.lala.nativelabel

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.chaomao.lala.platformObjects

class NativeLabel(str: String, private val style: NativeLabelStyle) : Widget(), Disposable {
    private var texture: Texture
    private val align = style.align
    private var imageX: Float = 0f
    private var imageY: Float = 0f

    init {
        texture = platformObjects.nativeLabelRenderer.getTexture(str, style)
        setSize(prefWidth, prefHeight)
    }

    override fun layout() {
        val imageWidth = texture.width
        val imageHeight = texture.height

        imageX = when {
            align and Align.left != 0 -> 0f
            // x is Float but need to convert to Int first, integer pixel or something like that
            align and Align.right != 0 -> (width - imageWidth).toInt().toFloat()
            else -> (width / 2 - imageWidth / 2).toInt().toFloat()
        }

        imageY = when {
            align and Align.top != 0 -> (height - imageHeight).toInt().toFloat()
            align and Align.bottom != 0 -> 0f
            else -> (height / 2 - imageHeight / 2).toInt().toFloat()
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        validate()
        batch.draw(texture, x + imageX, y + imageY)
    }

    override fun getPrefWidth(): Float {
        return style.maxWidth.toFloat()
    }

    override fun getPrefHeight(): Float {
        return texture.height.toFloat()
    }

    override fun dispose() {
        texture.dispose()
    }

    fun setText(text: String) {
        texture.dispose()
        texture = platformObjects.nativeLabelRenderer.getTexture(text, style)
        this.width = texture.width.toFloat()
        invalidateHierarchy()
        setSize(prefWidth, prefHeight)
    }

    class NativeLabelStyle {
        var textSize = Int.MAX_VALUE
        var maxWidth: Int = Int.MAX_VALUE
        var align = Align.left
        var multiline: Boolean = false
    }
}