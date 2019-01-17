package com.chaomao.lala.nativelabel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.opengl.GLES20
import android.opengl.GLUtils
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

class PlatformNativeLabelRenderer : NativeLabelRenderer {
    override fun getTexture(str: String, style: NativeLabel.NativeLabelStyle): Texture {
        val alignment = Layout.Alignment.ALIGN_NORMAL
        val textPaint = TextPaint()
        textPaint.textSize = style.textSize.toFloat()
        textPaint.isAntiAlias = true
        textPaint.color = android.graphics.Color.BLACK
        val staticLayout: StaticLayout
        val outerWidth = 1048576 // 1Mb
        val spacingMult = 1f
        val spacingAdd = 0f
        staticLayout = when {
            style.multiline -> if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain(str, 0, str.length, textPaint, style.maxWidth).build()
            } else {
                @Suppress("DEPRECATION")
                StaticLayout(str, textPaint, style.maxWidth, alignment, spacingMult, spacingAdd, true)
            }
            else -> if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain(str, 0, str.length, textPaint, outerWidth).setEllipsize(TextUtils.TruncateAt.END).setEllipsizedWidth(style.maxWidth).setLineSpacing(spacingAdd, spacingMult).build()
            } else {
                @Suppress("DEPRECATION")
                StaticLayout(str, 0, str.length, textPaint, outerWidth, alignment, spacingMult, spacingAdd, true, TextUtils.TruncateAt.END, style.maxWidth)
            }
        }
        val bitmap = Bitmap.createBitmap(Math.min(textPaint.measureText(str).toInt(), style.maxWidth),
                staticLayout.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.save()
        staticLayout.draw(canvas)
        canvas.restore()
        val texture = Texture(bitmap.width, bitmap.height, Pixmap.Format.RGBA8888)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.textureObjectHandle)
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
        bitmap.recycle()
        return texture
    }
}
