# TyperEditText
[![](https://jitpack.io/v/andyxialm/TyperEditText.svg)](https://jitpack.io/#andyxialm/TyperEditText)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TyperEditText-brightgreen.svg?style=flat)]()
<a href="http://www.methodscount.com/?lib=com.github.andyxialm%3ATyperEditText%3A1.0.2"><img src="https://img.shields.io/badge/Methods and size-57 | 7 KB-e91e63.svg"/></a>

Typerwriter

![](https://github.com/andyxialm/TyperEditText/blob/master/art/screenshot.gif?raw=true)
### Usage

#### Gradle
##### Step 1. Add the JitPack repository to your build file
~~~ xml
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
~~~

##### Step 2. Add the dependency
~~~ xml
dependencies {
    compile 'com.github.andyxialm:TyperEditText:1.0.2'
}
~~~

#### Maven
##### Step 1. Add the JitPack repository to your build file
~~~ xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
~~~

##### Step 2. Add the dependency
~~~ xml
<dependency>
    <groupId>com.github.andyxialm</groupId>
    <artifactId>TyperEditText</artifactId>
    <version>1.0.2</version>
</dependency>
~~~

##### Edit your layout XML:

~~~ xml
<cn.refactor.typer.TyperEditText
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    typer:characterWriteInterval="200"
    typer:punctuationWriteInterval="600"
    typer:touchable="false"/>
~~~

### License

    Copyright 2016 andy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
