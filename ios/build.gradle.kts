plugins {
    id("kotlin")
    id("robovm")
}

java {
    sourceSets.getByName("main").java.srcDir("src/")
}
dependencies {
    implementation(project(":core"))
    implementation(Depends.Gdx.roboVMRT)
    implementation(Depends.Gdx.roboVMCocoatouch)
    implementation(Depends.Gdx.ios)
    implementation(Depends.Gdx.iosNatives)
    implementation(Depends.Kotlin.stdlib)
}

ext {
    set("mainClassName", "com.chaomao.lala.IOSLauncher")
    set("executable", "GdxChat")
}

tasks.getByName("launchIPhoneSimulator") {
    setDependsOn(listOf(tasks.getByName("build")))
}
tasks.getByName("launchIPadSimulator") {
    setDependsOn(listOf(tasks.getByName("build")))
}
tasks.getByName("launchIOSDevice") {
    setDependsOn(listOf(tasks.getByName("build")))
}
tasks.getByName("createIPA") {
    setDependsOn(listOf(tasks.getByName("build")))
}

robovm {
    isIosSkipSigning = true
    archs = "thumbv7:arm64"
}
