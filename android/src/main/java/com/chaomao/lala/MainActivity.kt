package com.chaomao.lala

import android.graphics.Color
import android.os.Bundle
import android.view.SurfaceView
import android.widget.RelativeLayout
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class MainActivity : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        platformObjects = PlatformObjectsImpl(this)
        val config = AndroidApplicationConfiguration()
        val gdxView = initializeForView(Lala(), config)
        if (gdxView is SurfaceView) {
            /*
            https://issuetracker.google.com/issues/36922072
            Mixing EditText and SurfaceView results in strange clipping of Text box when editing text
            */
            gdxView.setBackgroundColor(Color.TRANSPARENT)
        }
        val mainLayout = RelativeLayout(this)
        mainLayout.addView(gdxView)
        setContentView(mainLayout)
    }
}
