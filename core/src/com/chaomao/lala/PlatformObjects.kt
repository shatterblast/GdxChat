package com.chaomao.lala

import com.chaomao.lala.nativelabel.NativeLabelRenderer
import com.chaomao.lala.nativetextinput.NativeTextInputManager

lateinit var platformObjects: PlatformObjects

interface PlatformObjects {
    val nativeLabelRenderer: NativeLabelRenderer
    val nativeTextInputManager: NativeTextInputManager
}