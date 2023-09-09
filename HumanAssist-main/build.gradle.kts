plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("jacoco")
}

group = "com.student"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	//implementation("org.springframework.boot:spring-boot-starter-security") //this is to enforce login page
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.5") // Core Jackson functionality
	implementation ("com.fasterxml.jackson.core:jackson-core:2.12.5") // Core Jackson functionality
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.5") // Jackson annotations (optional)
	implementation("org.codehaus.jackson:jackson-core-asl:1.9.13") // Jackson core functionality (optional)
	implementation("org.codehaus.jackson:jackson-mapper-asl:1.9.13")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.1.1") //for validation annotation like @NotBlank etc
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")//for Postgres db
	implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
	implementation("org.flywaydb:flyway-core") // for db migration
	implementation("org.apache.tomcat:tomcat-servlet-api:9.0.37")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.postgresql:r2dbc-postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
}

jacoco {
	toolVersion = "0.8.9" // Use the latest version
	reportsDirectory.set(layout.buildDirectory.dir("$buildDir/reports/jacoco"))
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)  // tests are required to run before generating the report
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = 90.0.toBigDecimal()
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
