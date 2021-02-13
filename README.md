# SimplTranslator
Program to translate words sent by REST request basing on a dictionary JSON file.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
The purpose of the project is to gain practice on creating simple REST points to check how it is working.

## Technologies
* Java 8 or above is required for using this program.
* JUnit 4 for testing purposes
* GSON 2.8 Google's JSON parser
* REST

## Setup
Prerequisites

You will need Postman software in order to check how the program works.
Exemplary Rest put requests are provided in "SampleData" folder.

In order to use it, it is recommended to import them in the Postman software.

Files Required

The software requires the JSON file in order to work. The required file
is provided under the "resources" folder.

JSON path

dictionaryFromJson\src\main\resources\programData

## Code Examples
Send text to translation with quotes:
`http://localhost:8080/v1/trans/transentopl/kota walcz ma/true`

Send request for dictionary usage:
`http://localhost:8080/v1/trans/getelementcount`

## Features
List of features ready and TODOs for future development
* text translation PL to EN and reverse
* text translation returned with every word in quotes
* counter for amount of word usage

To-do list:
* web interface

## Status
Project is: _in progress_

## Inspiration
Project wos inspired by programmers test task.
## Contact
Created by [@AdamSobieraj](https://github.com/AdamSobieraj) - feel free to contact me!

