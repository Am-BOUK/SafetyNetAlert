# SafetyNet Alerts

## Description

A command line app for managing the information persons in case of disaster. This app uses Java to run and stores the data in JSON file.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Prerequisites

What things you need to install the software and how to install them

Java 1.8

Maven 3.6.3


###Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html


###Running App

* JSON file is present under the resources folder in the code base.

* To have the jar file in the target folder :
	-mvn clean package

* launch the application with the java -jar "path to the .jar" command
	-java -jar "path to the .jar"

###Testing

The app has unit tests and integration tests written. The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file. 

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command:

JUNIT et Jacoco
- mvn test

Reports
To obtains tests reports execute the below command.

- mvn site



## Endpoints

The API accepts JSON-encoded request bodies and returns JSON-encoded files as well. It uses standard HTTP response codes and verbs.

### Main endpoints are :

#### To manage the persons:

- Read-GET /person?firstName=<firstName>&lastName=<lastName> to get a specific person information
- Create-POST /person to create/add a new person
- Modify-PUT /person?firstName=<firstName>&lastName=<lastName> to update a specific person
- Remove-DELETE /person?firstName=<firstName>&lastName=<lastName> to delete a specific person
- Read/GET /persons to get a list of all persons


#### To manage the medical records:

- Read-GET /medicalRecord?firstName=<firstName>&lastName=<lastName> to get a specific medical record information
- Create-POST /medicalRecord to create/add a new medical record
- Modify-PUT /medicalRecord?firstName=<firstName>&lastName=<lastName> to update a specific medical record
- Remove-DELETE /medicalRecord?firstName=<firstName>&lastName=<lastName> to delete a specified medical record
- Read/GET /medicalRecords to get a list of all medical records


### To manage the fire stations:

- Read-GET /fireStation?address=<address> to get a specific fire station information
- Create-POST /fireStation to add a new fire station
- Modify-PUT /fireStation?address=<address> to update a specified fire station
- Remove-DELETE /fireStation?address=<address> to delete the fire station of the specified address
- Read/GET /fireStations to get a list of all fire stations


### To retrieve information

-  /firestation?stationNumber=<station_number> 
	To get a list of persons covered by the corresponding fire station in the given station number.The list must include the following specific information: first name, last name, address, telephone number. In addition, it must provide a count of the number of adults and the number of children (aged 18 or under) in the area served.

- /childAlert?address=<address>
	To get a list of children (aged 18 or under) living at the given address. The list should include each child's first and last name, age, and a list of other household members.

- /phoneAlert?firestation=<firestation_number>
	To get a list of the phone numbers of the residents served by the fire station.

- /fire?address=<address> 
	To get a list of persons living at the given address as well as the number of the fire station serving it. The list should include the name, phone number, age, and medical record (medications, dosage, and allergies) of each person.

- /flood/stations?stations=<a list of station_numbers> 
	To get a list of all the addresses served by the list of fire station numbers. This list should group person by address. It should also include the name, phone number and age of residents, and include their medical history (medications, dosage, and allergies) next to each name.

- /personInfo?firstName=<firstName>&lastName=<lastName> 
	To get a list of person information of a specific person. The list should include the name, address, age, email address and medical record (medications, dosage, allergies) of each inhabitant. If more than one person has the same name, they must all appear.

- /communityEmail?city=<city> 
	To get the list of emails of all citizens for the given city