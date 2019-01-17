plugins {
    id("kotlin")
}

java {
    sourceSets.getByName("main").java.srcDir("src/")
}

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${rootProject.extra["gdxVersion"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlinVersion"]}")
}
