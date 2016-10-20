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
        def share = File.createTempDir()
        task.share = share
        task.file = new File('the_file')
        task.product = 'the_product'
        task.run()
        share.deleteDir()
    }

    @Test
    void 'Given echo command and no share when running the task then exception should be thrown.'() {
        AddSymbolTask task = ApplyPluginAndGetTask('echo %*')
        task.file = new File('the_file')
        task.product = 'the_product'
        exception.expect(GradleException)
        task.run()
    }

    @Test
    void 'Given "exit 1" command when running the task then exception should be thrown.'() {
        AddSymbolTask task = ApplyPluginAndGetTask('exit 1')
        def share = File.createTempDir()
        task.share = share
        task.file = new File('the_file')
        task.product = 'the_product'
        exception.expect(GradleException)
        task.run()
        share.deleteDir()
    }
}
