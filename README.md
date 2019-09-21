# JTrue: Java object validation library

[![Build Status](https://img.shields.io/travis/com/ivanjermakov/jtrue/master)](https://travis-ci.com/ivanjermakov/jtrue)
[![Coverage Status](https://img.shields.io/coveralls/github/ivanjermakov/jtrue/master)](https://coveralls.io/github/ivanjermakov/jtrue?branch=master)
[![](https://jitpack.io/v/ivanjermakov/jtrue.svg)](https://jitpack.io/#ivanjermakov/jtrue)

JTrue - Java functional-style lazy object validation library.

## Getting started
### Setting up the dependency
The first step is to include JTrue into your project, for example, as a Gradle dependency:
1. Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
        
2. Add the dependency

```java
dependencies {
    implementation 'com.github.ivanjermakov:jtrue:Tag'
}
```
        
### Hello world

Let's check if given string is blank:

```java
new Validator<String>()
        .check(new IsBlank())
        .validate("Hello, World!");

//output: true
```

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
[JavaDoc](https://ivanjermakov.github.io/jtrue/docs/)

## Binaries

Binaries and dependency information for Maven, Gradle and others can be found on [JitPack page](https://jitpack.io/#ivanjermakov/jtrue).

## Build

To build:

```shell script
$ git clone https://github.com/ivanjermakov/jtrue.git
$ gradle build
```
    
## LICENSE
    
```
MIT License

Copyright (c) 2019 Ivan Ermakov

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```