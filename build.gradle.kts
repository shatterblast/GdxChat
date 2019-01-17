buildscript {
    extra["gdxVersion"] = "1.9.9"
    extra["kotlinVersion"] = "1.3.11"

    repositories {
        jcenter()
        google()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinVersion"]}")
    }
}

allprojects {
    version = "1.0"
    repositories {
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
}
