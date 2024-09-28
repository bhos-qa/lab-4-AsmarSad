plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube") version "4.0.0.2929"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

jacoco {
    toolVersion = "0.8.7" // Make sure the Jacoco version is correct
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)  // Generate XML report for SonarCloud
        html.required.set(true) // Generate HTML report for local browsing
    }
}

// Ensure Jacoco reports are generated after tests
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // Run jacocoTestReport after test
}
sonarqube {
    properties {
        property ("sonar.projectKey", "bhos-qa_lab-3-AsmarSad")
        property( "sonar.organization", "bhos-qa")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", project.findProperty("sonar.login") ?: "")
    }
}