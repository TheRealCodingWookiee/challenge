import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.9.10"

	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("kapt") version kotlinVersion

}

group = "de.patronus"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17
extra["snakeyaml.version"] = "1.33"


repositories {
	mavenCentral()
	maven {
		name = "github"
		url = uri("https://maven.pkg.github.com/objego/dummy")
	}
	mavenLocal()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.7")
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.27.0")
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
		arg("mapstruct.defaultInjectionStrategy", "constructor")
		arg("mapstruct.unmappedTargetPolicy", "ERROR")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs =
				listOf(
						"-Xjsr305=strict",
						"-Xjvm-default=all",
				)
		jvmTarget = "17"
	}
}

tasks.test {
	useJUnitPlatform()
}
