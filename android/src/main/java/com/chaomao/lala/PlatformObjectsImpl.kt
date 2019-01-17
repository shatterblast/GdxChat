package com.chaomao.lala

import android.app.Activity
import com.chaomao.lala.nativelabel.NativeLabelRenderer
import com.chaomao.lala.nativelabel.PlatformNativeLabelRenderer
import com.chaomao.lala.nativetextinput.NativeTextInputManager
import com.chaomao.lala.nativetextinput.PlatformTextInputManager

class PlatformObjectsImpl(private val activity: Activity) : PlatformObjects {
    override val nativeLabelRenderer: NativeLabelRenderer by lazy { PlatformNativeLabelRenderer() }
    override val nativeTextInputManager: NativeTextInputManager by lazy { PlatformTextInputManager(activity) }
}