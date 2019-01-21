object Depends {
    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    }

    object Gdx {
        val core = "com.badlogicgames.gdx:gdx:${Versions.gdx}"
        val android = "com.badlogicgames.gdx:gdx-backend-android:${Versions.gdx}"
        val nativesArmeabi = "com.badlogicgames.gdx:gdx-platform:${Versions.gdx}:natives-armeabi"
        val nativesArmeabiV7a =
            "com.badlogicgames.gdx:gdx-platform:${Versions.gdx}:natives-armeabi-v7a"
        val nativesArm64V8a = "com.badlogicgames.gdx:gdx-platform:${Versions.gdx}:natives-arm64-v8a"
        val nativesX86 = "com.badlogicgames.gdx:gdx-platform:${Versions.gdx}:natives-x86"
        val nativesX86_64 = "com.badlogicgames.gdx:gdx-platform:${Versions.gdx}:natives-x86_64"
    }
}

