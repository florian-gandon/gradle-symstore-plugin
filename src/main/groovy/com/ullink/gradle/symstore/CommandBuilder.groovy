package com.ullink.gradle.symstore

import org.gradle.api.GradleException

public class CommandBuilder {
    private String command

    public CommandBuilder(String symstorePath, String transaction) {
        if(symstorePath == null)
            throw new GradleException('symstorePath is not set.')
        if(transaction == null)
            throw new GradleException('transaction is not set')
        command = symstorePath + ' ' + transaction
    }

    public String build() { command }

    CommandBuilder withFileArg(String name, String value) {
        if (value != null && value.length() > 0 && value[-1] == '\\') value += '\\'
        withArg(name, value == null ? null : "\"$value\"")
    }

    CommandBuilder withArg(String name, String value) {
        if(name == null)
            throw new GradleException('name is not set')
        if(value) command += " /$name $value"
        return this
    }
}
