plugins {
    `maven-publish`
}

val gdxVersion: String by project

group = "com.github.ityeri"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    api("com.badlogicgames.gdx:gdx:$gdxVersion")
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = "scroll-core"
            version = project.version.toString()
        }
    }
}
