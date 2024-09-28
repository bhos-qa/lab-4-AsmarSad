plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube") version "3.3"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)  // Generates XML report required by SonarCloud
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))  // Generates HTML for manual viewing
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "bhos-qa_lab-4-AsmarSad")  // Replace with your SonarCloud project key
        property("sonar.organization", "bhos-qa")               // Replace with your SonarCloud organization key
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))   // Uses SONAR_TOKEN from environment variables
    }
}

tasks.wrapper {
    gradleVersion = "7.2"
}
