plugins {
    id("kotlin")
}

java {
    sourceSets.getByName("main").java.srcDir("src/")
}

dependencies {
    implementation(Depends.Gdx.core)
    implementation(Depends.Kotlin.stdlib)
}
