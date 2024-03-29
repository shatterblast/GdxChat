package com.chaomao.lala

import com.badlogic.gdx.backends.iosrobovm.IOSApplication
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration

import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication

class IOSLauncher : IOSApplication.Delegate() {

    override fun createApplication(): IOSApplication {
        val config = IOSApplicationConfiguration()
        return IOSApplication(Lala(), config)
    }

    companion object {
        @JvmStatic
        fun main(argv: Array<String>) {
            val pool = NSAutoreleasePool()
            UIApplication.main<UIApplication, IOSLauncher>(argv, null, IOSLauncher::class.java)
            pool.close()
        }
    }
}