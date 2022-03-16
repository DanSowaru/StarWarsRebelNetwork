
# Star Wars Rebel Network API

![](https://img.shields.io/github/repo-size/dan-sowaru/StarWarsRebelNetwork)
![](https://img.shields.io/github/commit-activity/m/dan-sowaru/StarWarsRebelNetwork)
![](https://img.shields.io/badge/test%20covered-yes-success)
![](https://img.shields.io/badge/project%20members-4-blueviolet)
[![Visits Badge](https://badges.pufler.dev/visits/dan-sowaru/StarWarsRebelNetwork)](https://badges.pufler.dev)
![]()
![]()
![]()
![]()
![]()
![]()
![]()
![]()
![]()

## Table of Contents
1. [About](#about)
2. [Project Team](#project-team)
3. [IDEs/Editors](#ides/editors)
4. [Language](#language)
5. [Frameworks, Platforms and Libraries](#frameworks,-platforms-and-libraries)
6. [How to Run the Project](#how-to-run-the-project)
7. [How to Use the project](#how-to-use-the-project)
8. [Credits](#credits)


## About

This is an API of a fictional network trade and comunication system for members of the Rebel Alliance faction of the Star Wars franchise. This interface is responsible for basic CRUD operations of rebel soldiers in a database and providing some interactions between them, while also providing reports about their activity stats.

This API was made with Spring Web with Thymeleaf for the quick and clean meeting with the MVC standard, Maven with Lombok for the straight implementation of the constructors, getters, setters and other regular functions of classes, and Wrapper for the testing.

## Project Team
 * [Amanda Amabili](https://github.com/amandaamabili)
 * [Aramiz Moura](https://github.com/aramiz-moura) 
 * [Danilo Soares](https://github.com/dan-sowaru) 
 * [Rubens Souza](https://github.com/RubensPS)

## IDEs/Editors
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)

## Language
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

## Frameworks, Platforms and Libraries

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)


## How to Run the Project

1. Run the API on a server.
2. Use an API platform like Postman, Insomnia or the Thunder Client extension of VsCode to make the requests;


## How to Use the Project

After running it, you can use HTTP requests to consume the database.

>`http://localhost:8080/rebels`
>
>A Post request with a new Rebel will create a new entry in theRebel repository

> `http://localhost:8080/rebels/all`
>
>This request returns the current rebel lis stored in the database

>`http://localhost:8080/rebels/${id}`
> 
> This get request returns the rebel corresponding with the id provided as parameter

>`http://localhost:8080/rebels/location/${id}`
>
> A patch request on this Id will allow to update the last location the soldier was at

>`http://localhost:8080/rebels/traitors`
> 
> This get request will return a list of all rebel soldiers that were accused of being traitors 3 times or more by other soldiers, thus acquiring the "traitor" label

>`http://localhost:8080/rebels/allies`
> 
> This get request returns the list of the current active rebels, without any rebel that was labeled as traitor

>`http://localhost:8080/rebels/resources`
>
>This get request returns a report with the average resources (Weapon, Ammo, Water and Food) per active rebel in the army. It also returns the point (cost of resources)
>lost because of the rebels labeled as traitors

>`http://localhost:8080/rebels/trade/${firstId}/${secondId}`
>
>A Post request that executes a trade between two rebels. The trade will only be completed if the ids are different (two different rebels), if any of them are traitors and if
>the resource total value from each rebel offer are the same



