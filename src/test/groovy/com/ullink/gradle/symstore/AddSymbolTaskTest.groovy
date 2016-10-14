package com.ullink.gradle.symstore

import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

public class AddSymbolTaskTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static AddSymbolTask ApplyPluginAndGetTask(String commandStub) {
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'symstore'
        def task = (AddSymbolTask) project.tasks['addSymbol']
        def stubCmd = File.createTempFile('symstore', '.bat')
        stubCmd.write(commandStub)
        task.symstorePath = stubCmd
        task
    }

    @Test
    void 'Given echo command when running the task then parameters should be run without exception.'() {
        AddSymbolTask task = ApplyPluginAndGetTask('echo %*')
        task.run()
    }

    @Test
    void 'Given "exit 1" command when running the task then exception should be thrown.'() {
        AddSymbolTask task = ApplyPluginAndGetTask('exit 1')
        exception.expect(GradleException)
        task.run()
    }
}
