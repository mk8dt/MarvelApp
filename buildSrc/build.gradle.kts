// Top-level build file where you can add configuration options common to all sub-projects/modules.
repositories {
    jcenter()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}