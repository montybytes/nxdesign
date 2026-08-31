import java.util.Properties

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) file.inputStream().use(::load)
}

val githubUser = requireNotNull(localProperties.getProperty("github.username")) {
    "Missing github.username in local.properties"
}
val githubToken = requireNotNull(localProperties.getProperty("github.token")) {
    "Missing github.token in local.properties"
}

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)

    id("maven-publish")
}

android {
    namespace = "com.nxtended.design"

    buildFeatures {
        compose = true
    }

    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        singleVariant("release")
    }

}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.nxtended"
            artifactId = "design"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/montybytes/nxdesign")
            credentials {
                username = githubUser
                password = githubToken
            }
        }
    }
}