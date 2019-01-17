package com.chaomao.lala

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.chaomao.lala.nativelabel.NativeLabel


class MessageActor(message: String) : Actor() {
    private val bodyBounds: Rectangle = Rectangle()
    private val bodyLabel: NativeLabel

    init {
        val itemHeight = 56 * Gdx.graphics.density
        bodyBounds.set(0f, 0f, Gdx.graphics.width.toFloat(), itemHeight)
        val style = NativeLabel.NativeLabelStyle()
        style.textSize = (16 * Gdx.graphics.density).toInt()
        bodyLabel = NativeLabel(message, style)
        val max = Math.max(bodyLabel.height, itemHeight)
        bodyBounds.height = max
        setSize(Gdx.graphics.width.toFloat(), max)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        bodyLabel.setBounds(x + bodyBounds.x, y + bodyBounds.y, bodyBounds.width, bodyBounds.height)
        bodyLabel.draw(batch, parentAlpha)
    }
}
