# Transaction Statistics - N26 Exercise 

<h6>Restful API for our transactions and statistics</h6>

## Goal of this project

*The goal of our API is to
calculate realtime statistic from the last 60 seconds. There will be two APIs, one of them is
called every time a transaction is made. It is also the sole input of this rest API. The other one
returns the statistic based of the transactions of the last 60 seconds.*

### Specs

**1. POST /transactions**

*Every Time a new transaction happened, this endpoint will be called*

Body:
```
{
"amount": 12.3,
"timestamp": 1478192204000
}
```
●  **amount** - *transaction amount*
●  **timestamp** - *transaction time in epoch in millis in UTC time zone (this is not current timestamp)*

*Returns: Empty body with either 201 or 204.*
●  **201** - in case of success
●  **204** - if transaction is older than 60 seconds

**2.  GET /statistics**

*It returns the statistic based on the transactions which happened in the last 60seconds.*

```
{
"sum": 1000,
"avg": 100,
"max": 200,
"min": 50,
"count": 10
}
```
Where:
● **sum** *is a double specifying the total sum of transaction value in the last 60 seconds*
● **avg** *is a double specifying the average amount of transaction value in the last 60seconds*
● **max** *is a double specifying single highest transaction value in the last 60 seconds*
● **min** *is a double specifying single lowest transaction value in the last 60 seconds*
● **count** *is a long specifying the total number of transactions happened in the last 60seconds*


## Getting Started

*Below are the steps to set up the project in local development environment.*

### Prerequisites

* **IDE - Eclipse / IntelliJ**
* **JDK1.8**
* **Maven**

  *And all paths set in environment variables*

### Importing the project

*Please import the java sources [src folder] using maven import from existing sources in the File-> Import options of IDE.
Point the import folder to the folder ( TransactionStatistics ) containing the pom.xml*


##### Folder Structure

```
TransactionStatistics
 
 |--src
 |   |-main
 |   | |-java
 |   |     |-ApplicationClasses.java
 |   |
 |   |-test
 |       |-java
 |	      |-TestClasses.java
 |--pom.xml
```

## Logic of the API

*The Main Logic I have used is whenever a transaction comes in check whether it is within last 60 secons if so add it to transactionMaps and RightAway calculate the statistics for the next sixty seconds.*

*So whenever a /getstatistics is called withinin next sixty seconds it will return whatever stats calculated while addind transactions*

## Building the Project

*Right click pom.xml and select Run as Maven Build and execute the goals **"clean package install"** in eclipse

*As we are using **spring-boot-maven-plugin** to package we will get a single stand-alone jar **TransactionStatistics-1.0.0-SNAPSHOT.jar** in src/target folder*

## Executing the program

*Execute the below command in command prompt*

```
JarPath>java -jar TransactionStatistics-1.0.0-SNAPSHOT.jar

```
*If you are in Windows run or execute batch script*  **startApplication.bat**
*If you are in Unix run or execute shell script*  **startApplication.sh**


## Authors

* *Selvakumar Kamatchinathan*

## Thanks To

* *Java 8 Streams which made the code easy*
* *N26 Team for Providing the opportunity to code and learn*

