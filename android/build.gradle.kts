plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

val natives by configurations.creating

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.chaomao.lala"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 5
        versionName = "1.0.0"
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/main/AndroidManifest.xml")
            java.srcDirs("src/main/java")
            aidl.srcDirs("src/main")
            renderscript.srcDirs("src/main")
            res.srcDirs("'src/main/res")
            assets.srcDirs("assets")
            jniLibs.srcDirs("libs")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isShrinkResources = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    lintOptions {
        isAbortOnError = false
    }
}

kapt {
    correctErrorTypes = true
}

configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(Depends.Gdx.android)
    natives(Depends.Gdx.nativesArmeabi)
    natives(Depends.Gdx.nativesArmeabiV7a)
    natives(Depends.Gdx.nativesArm64V8a)
    natives(Depends.Gdx.nativesX86)
    natives(Depends.Gdx.nativesX86_64)
    implementation(Depends.Kotlin.stdlib)
}

task("copyAndroidNatives") {
    doFirst {
        file("libs/arm64-v8a/").mkdirs()
        file("libs/armeabi-v7a/").mkdirs()
        file("libs/armeabi/").mkdirs()
        file("libs/x86_64/").mkdirs()
        file("libs/x86/").mkdirs()
        natives.files.forEach { jar ->
            var outputDir: File? = null
            if (jar.name.endsWith("natives-arm64-v8a.jar")) outputDir = file("libs/arm64-v8a")
            if (jar.name.endsWith("natives-armeabi-v7a.jar")) outputDir = file("libs/armeabi-v7a")
            if (jar.name.endsWith("natives-armeabi.jar")) outputDir = file("libs/armeabi")
            if (jar.name.endsWith("natives-x86_64.jar")) outputDir = file("libs/x86_64")
            if (jar.name.endsWith("natives-x86.jar")) outputDir = file("libs/x86")
            if (outputDir != null) {
                copy {
                    from(zipTree(jar))
                    into(outputDir)
                    include("*.so")
                }
            }
        }
    }
}

tasks.whenTaskAdded {
    if (name.contains("package")) {
        setDependsOn(listOf(tasks.getByName("copyAndroidNatives")))
    }
}
