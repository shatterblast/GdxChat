buildscript {
    repositories {
        jcenter()
        google()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradleBuildTool}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.mobidevelop.robovm:robovm-gradle-plugin:2.3.5")
    }
}

allprojects {
    version = Versions.versionName
    repositories {
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
}
