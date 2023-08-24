# NBA Management Game
NBA game with GUI created using Java and JavaFX

## Description
This is an application created on NetBeans using Java and JavaFX to simulate the management of an NBA season.

The classes found in the "model" folder contain the methods and functions required to complete the tasks of the application.

The fxml files in the "view" folder create the visual aspects of the application. This includes the table and list views of data as well as the logos and backgrounds of each screen. 

The controller files found in the "controller" folder tie the classes and fxml files together to create a functional application. 

## How to Run

To run the application, you will need Java installed on your computer. After installing Java, simply head into the "dist" directory and open the "nbafx.jar" file. 

## How to Use

Upon opening the file, there are two main options - explore teams or arrange new season. 

Exploring teams allows the viewing and editing of certain data such as name of team, adding more teams or removing teams. You can also manage each player in a team and edit their attributes such as name, age, number, or credit.

Arranging a new season allows the organisation of each matchup by adding teams to each round. The winner of a round is determined by the average credit of each team. The team with the higher average credit will win. After a team loses, that team gets eliminated until there is one winner. 