import Versions.androidBuildToolsVersion
import Versions.androidConstraintLayoutVersion
import Versions.androidKtxVersion
import Versions.androidMaterialVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.mockitoKotlinVersion
import Versions.mockitoVersion
import Versions.rxAndroidVersion
import Versions.rxJavaVersion

object Dependencies {
    // Gradle
    val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    // Kotlin
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    // Android
    val androidMaterial = "com.google.android.material:material:$androidMaterialVersion"
    val androidConstraintLayout = "androidx.constraintlayout:constraintlayout:$androidConstraintLayoutVersion"
    val androidKtx = "androidx.core:core-ktx:$androidKtxVersion"

    // Rx
    val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"

    // Testing
    val junit = "junit:junit:$junitVersion"
    val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
    val mockitoInline = "org.mockito:mockito-inline:$mockitoVersion"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:$mockitoKotlinVersion"

    val testing = listOf(
        junit,
        mockitoCore,
        mockitoInline,
        mockitoKotlin
    )
}