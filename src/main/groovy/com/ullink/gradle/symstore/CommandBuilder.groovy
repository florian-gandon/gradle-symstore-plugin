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

    public CommandBuilder withFileArg(String name, String value) {
        if (value != null && value.length() > 0 && value[-1] == '\\') value += '\\'
        withArg(name, value)
    }

    public CommandBuilder withArg(String name, String value) {
        if(value != null) addArg(name, value)
        return this
    }

    private void addArg(String name, String optionalValue = null) {
        if(name == null)
            throw new GradleException('name is not set')
        command += " /$name"
        if(optionalValue != null) command += " \"$optionalValue\""
    }

    public CommandBuilder withNoValueArg(String name, boolean toAdd) {
        if(toAdd) addArg(name)
        return this
    }
}
