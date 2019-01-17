package com.chaomao.lala.nativetextinput

interface NativeTextInputManager {
    fun show(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        listener: OnTextChangedListener,
        hint: String,
        initialText: String = ""
    )

    fun clear()
    fun hide()
    fun hideKeyboard()
    interface OnTextChangedListener {
        fun onTextChanged(str: String)
        fun onEnter(str: String)
    }
}
