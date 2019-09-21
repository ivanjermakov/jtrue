# JTrue: Java object validation library

[![Build Status](https://img.shields.io/travis/com/ivanjermakov/jtrue/master)](https://travis-ci.com/ivanjermakov/jtrue)
[![Coverage Status](https://img.shields.io/coveralls/github/ivanjermakov/jtrue/master)](https://coveralls.io/github/ivanjermakov/jtrue?branch=master)
[![](https://jitpack.io/v/ivanjermakov/jtrue.svg)](https://jitpack.io/#ivanjermakov/jtrue)

JTrue - Java functional-style lazy object validation library.

## Getting started
### Setting up the dependency
The first step is to include JTrue into your project, for example, as a Gradle dependency:
1. Add it in your root build.gradle at the end of repositories

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
2. Add the dependency

        dependencies {
            implementation 'com.github.ivanjermakov:jtrue:Tag'
        }
        
### Hello world

Let's check if given string is blank:

    new Validator<String>()
            .check(new IsBlank())
            .validate("Hello, World!");

    //output: true

### Base classes

JTrue features several base classes you can use:

* `Validatable`: base interface for all validators
* `Validator`: lazy functional-style implementation of `Validatable`
* `Check`: smallest unit representing single validation check
* `CompleteCheck`: `Check`, but tested on specific instance

All of them are located in `core` package.

### Predefined common-use predicates

In Hello world example, line `new IsBlank()` may caught your eye. It is one of predefined predicates. All of them located in `predicate` package. There are such predicates like: `Equals`, `IsEmptyString`, `IsNull` and many more.

## Bugs and feedback
[GitHub issues](https://github.com/ivanjermakov/jtrue/issues)

## Documentation
<!--TODO: javadoc-->
[JavaDoc]()

## Binaries

Binaries and dependency information for Maven, Gradle and others can be found on [JitPack page](https://jitpack.io/#ivanjermakov/jtrue).

## Build

To build:

    $ git clone https://github.com/ivanjermakov/jtrue.git
    $ gradle build
    
LICENSE
<!--TODO: license-->