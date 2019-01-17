package com.chaomao.lala.nativetextinput

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.badlogic.gdx.Input

class PlatformTextInputManager(private val activity: Activity) : NativeTextInputManager {
    private val parent = LinearLayout(activity)
    private val editText = EditText(activity)

    override fun clear() {
        activity.runOnUiThread {
            editText.setText("")
            editText.clearFocus()
        }
    }

    override fun show(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        listener: NativeTextInputManager.OnTextChangedListener,
        hint: String,
        initialText: String
    ) {
        activity.runOnUiThread {
            parent.setPadding(x, y, 0, 0)
            parent.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            parent.gravity = android.view.Gravity.TOP
            editText.layoutParams = LinearLayout.LayoutParams(width, height)
            editText.maxLines = 1
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            editText.setBackgroundResource(android.R.color.transparent)
            editText.hint = hint
            if (!initialText.isEmpty()) {
                editText.setText(initialText)
                editText.setSelection(editText.text.length)
            }
            editText.setOnKeyListener { _, _, p2 ->
                if (p2.keyCode == Input.Keys.BACK) {
                    hideKeyboard()
                } else if (p2.keyCode == Input.Keys.ENTER) {
                    hideKeyboard()
                    listener.onEnter(editText.text.toString())
                }
                false
            }
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    listener.onTextChanged(editable.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    showKeyboard(editText)
                }
            }

            if (editText.parent != null) {
                (editText.parent as ViewGroup).removeView(editText)
            }
            if (parent.parent != null) {
                (parent.parent as ViewGroup).removeView(parent)
            }
            parent.addView(editText)
            val mainLayout: FrameLayout = activity.findViewById(android.R.id.content)
            mainLayout.addView(parent)
        }
    }

    override fun hide() {
        activity.runOnUiThread {
            hideKeyboard()
            editText.text.clear()
            val mainLayout: FrameLayout = activity.findViewById(android.R.id.content)
            parent.removeView(editText)
            mainLayout.removeView(parent)
        }
    }

    private fun showKeyboard(target: EditText?) {
        if (target == null) return
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(target, InputMethodManager.SHOW_FORCED)
    }

    override fun hideKeyboard() {
        activity.runOnUiThread {
            val view = activity.currentFocus
            view?.clearFocus()
            if (view != null) {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}
