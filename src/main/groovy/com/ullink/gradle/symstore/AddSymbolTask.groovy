package com.ullink.gradle.symstore

import org.gradle.api.GradleException
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction

class AddSymbolTask extends ConventionTask {

    String symstorePath

    File file

    String share

    String product

    @TaskAction
    void run(){
        def command = new CommandBuilder(symstorePath, 'add')
            .withFileArg('f', file?.path)
            .withFileArg('s', share)
            .withArg('t', product)
            .build()
        logger.info "Symstore command: $command"
        def symstore = command.execute()
        def info = new StringBuilder()
        def error = new StringBuffer()
        symstore.consumeProcessOutput(info, error)
        int result = symstore.waitFor()
        logger.info info.toString()
        logger.error error.toString()
        if(result != 0)
            throw new GradleException("$symstorePath exits with code $result.")
    }
}
