import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("io.ktor.plugin") version "2.1.0"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "me.kacper"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("com.pi4j:pi4j-core:2.1.1")
	implementation("org.springframework:spring-web:5.3.22")
	implementation("org.springframework.boot:spring-boot-starter-web:2.7.2")
	implementation("com.pi4j:pi4j-plugin-raspberrypi:2.1.1")
	implementation("com.pi4j:pi4j-plugin-pigpio:2.1.1")
	implementation("com.pi4j:pi4j-library-pigpio:2.1.1")
	implementation("com.pi4j:pi4j-core:2.1.1")
	implementation("com.diozero:diozero-ws281x-java:1.3.3")

}

application {
	mainClass.set("me.kacper.service.ServiceApplicationKt")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}



//tasks.register("copyDistribution", Copy::class.java) {
//	from(configurations.default)
//	from(tasks.named("jar"))
//	into(layout.buildDirectory.dir("distribution"))
//}
//tasks.named("build") {
//	dependsOn("copyDistribution")
//	dependsOn("fatJar")
//}
//
//val fatJar = task("fatJar", type = Jar::class) {
//	baseName = "${project.name}-fat"
//	manifest {
//		attributes["Implementation-Title"] = "Gradle Jar File Example"
//		attributes["Implementation-Version"] = version
//		attributes["Main-Class"] = "org.springframework.boot.loader.JarLauncher"
//		attributes["Start-Class"] = "me.kacper.service.ServiceApplicationKt"
//	}
//	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//	from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
//	with(tasks.jar.get() as CopySpec)
//}