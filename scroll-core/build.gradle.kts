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
    jvmToolchain(20)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = "scroll-core" // 원하는 artifact 이름
            version = project.version.toString()
        }
    }
}
