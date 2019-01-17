package com.chaomao.lala.nativelabel

import com.badlogic.gdx.graphics.Texture

interface NativeLabelRenderer {
    fun getTexture(str: String, style: NativeLabel.NativeLabelStyle): Texture
}
