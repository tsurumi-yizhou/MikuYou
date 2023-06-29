buildscript {
    val compose_version by extra("1.2.0")
}
plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.8.10" apply false
}