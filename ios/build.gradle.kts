plugins {
    id("kotlin")
    id("robovm")
}

java {
    sourceSets.getByName("main").java.srcDir("src/")
}
dependencies {
    implementation(project(":core"))
    implementation("com.mobidevelop.robovm:robovm-rt:2.3.5")
    implementation("com.mobidevelop.robovm:robovm-cocoatouch:2.3.5")
    implementation("com.badlogicgames.gdx:gdx-backend-robovm:1.9.9")
    implementation("com.badlogicgames.gdx:gdx-platform:1.9.9:natives-ios")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.21")
}

ext {
    set("mainClassName", "com.chaomao.lala.IOSLauncher")
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
