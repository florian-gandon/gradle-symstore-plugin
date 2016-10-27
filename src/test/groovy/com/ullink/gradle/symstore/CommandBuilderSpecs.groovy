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
        path   | transac | name  | value    | noValueName | toAdd || expected
        'cmd1' | 'do'    | 'arg' | 'val1'   | 'arg2'      | false || 'cmd1 do /arg "val1"'
        'cmd2' | 'do'    | 'arg' | 'val1'   | 'arg2'      | true  || 'cmd2 do /arg "val1" /arg2'
        'cmd3' | 'do'    | 'arg' | 'val1'   | null        | false || 'cmd3 do /arg "val1"'
        'cmd4' | 'do'    | 'arg' | 'val1\\' | 'arg2'      | false || 'cmd4 do /arg "val1\\\\"'
        'cmd5' | 'do'    | 'arg' | null     | 'arg2'      | false || 'cmd5 do'
        'cmd6' | 'do'    | 'arg' | ''       | 'arg2'      | false || 'cmd6 do /arg ""'
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
