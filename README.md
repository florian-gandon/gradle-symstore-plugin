# Gradle symstore plugin

This plugin allows to execute symstore provided by Windows Software Development Kit.

## Samples usage

    buildscript {
        dependencies {
            classpath group: 'com.ullink.gradle', name: 'gradle-symstore-plugin', version: '0.1'
        }
    }
    
    apply plugin: 'symstore'
    
    addSymbol {
        // Product name, project.name by default
        product = 'my_product'
        // Path to symstore.exe
        symstorePath = /C:\Program Files (x86)\Windows Kits\8.1\Debuggers\x64\symstore.exe/
        // Path of files or directories to add
        file = 'bin/Release/'
        // Root directory for the symbol store
        share = '\\\\sharing\\symbols'
        // Symbols will be compressed
        compress = true
    }


#See also

## Microsoft documentation

[SymStore Command-Line Options](https://msdn.microsoft.com/fr-fr/library/windows/desktop/ms681378(v=vs.85).aspx) - Full command line documentation

[Using SymStore](https://msdn.microsoft.com/en-us/library/windows/desktop/ms681417(v=vs.85).aspx) - Examples and other information about the usage of Symstore.exe

[Windows SDK for Windows 8.1](https://developer.microsoft.com/en-us/windows/downloads/windows-8-1-sdk) - Page to download the SDK

# License

Plugin licensed under the Apache License, Version 2.0 with no warranty (expressed or implied) for any purpose.