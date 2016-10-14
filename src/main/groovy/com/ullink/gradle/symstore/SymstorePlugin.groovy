package com.ullink.gradle.symstore

import org.gradle.api.Plugin
import org.gradle.api.Project

class SymstorePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task('addSymbol', type: AddSymbolTask) { task ->
            task.product = project.name
        }
    }
}
