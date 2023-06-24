plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}


dependencies {
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
//    implementation("net.jodah:failsafe:3.0.2")
    implementation ("net.jodah:failsafe:2.4.4")

    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.2.9")
}


tasks.test {
    useJUnitPlatform()
}
