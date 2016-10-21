package com.ullink.gradle.symstore

import org.gradle.api.GradleException
import spock.lang.Specification

public class CommandBuilderSpecs extends Specification {
    def 'Given a file and a no value args when building a command then the command should be properly built.'(){
        expect:
        new CommandBuilder(path, transac)
                .withFileArg(name, value)
                .withNoValueArg(noValueName, toAdd)
                .build() == expected

        where:
        path  | transac | name  | value    | noValueName | toAdd || expected
        'cmd' | 'do'    | 'arg' | 'val1'   | 'arg2'      | false || 'cmd do /arg "val1"'
        'cmd' | 'do'    | 'arg' | 'val1'   | 'arg2'      | true  || 'cmd do /arg "val1" /arg2'
        'cmd' | 'do'    | 'arg' | 'val1'   | null        | false || 'cmd do /arg "val1"'
        'cmd' | 'do'    | 'arg' | 'val1\\' | 'arg2'      | false || 'cmd do /arg "val1\\\\"'
        'cmd' | 'do'    | 'arg' | null     | 'arg2'      | false || 'cmd do'
        'cmd' | 'do'    | 'arg' | ''       | 'arg2'      | false || 'cmd do /arg ""'
    }

    def 'Given invalid values when building a command then a exception is thrown.'(){
        when:
        new CommandBuilder(path, transac)
                .withFileArg(name, value)
                .withNoValueArg(noValueName, toAdd)

        then:
        thrown(GradleException)

        where:
        path  | transac | name  | value  | noValueName | toAdd
        null  | 'do'    | 'arg' | 'val1' | 'arg2'      | true
        'cmd' | null    | 'arg' | 'val1' | 'arg2'      | true
        'cmd' | 'do'    | null  | 'val1' | 'arg2'      | true
        'cmd' | 'do'    | 'arg' | 'val1' | null        | true
    }
}
