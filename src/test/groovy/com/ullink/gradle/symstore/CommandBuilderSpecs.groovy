package com.ullink.gradle.symstore

import org.gradle.api.GradleException
import spock.lang.Specification

public class CommandBuilderSpecs extends Specification {
    def 'Given a file arg when building a command then the command should be properly built.'(){
        expect:
        new CommandBuilder(path, transac).withFileArg(name, value).build() == expected

        where:
        path  | transac | name  | value  || expected
        'cmd' | 'do'    | 'arg' | 'val1' || 'cmd do /arg "val1"'
        'cmd' | 'do'    | 'arg' | 'val1\\' || 'cmd do /arg "val1\\\\"'
        'cmd' | 'do'    | 'arg' | null   || 'cmd do'
        'cmd' | 'do'    | 'arg' | ''     || 'cmd do /arg ""'
    }

    def 'Given invalid values when building a command then a exception is thrown.'(){
        when:
        new CommandBuilder(path, transac).withFileArg(name, value)

        then:
        thrown(GradleException)

        where:
        path  | transac | name  | value
        null  | 'do'    | 'arg' | 'val1'
        'cmd' | null    | 'arg' | 'val1'
        'cmd' | 'do'    | null  | 'val1'
    }
}
