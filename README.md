# Money Transfer

RESTful API for money transfers between accounts.


# Features
- Multi-threaded solution
- Account creation
- Money tranfer
- Stand-alone jar
- Integration test (JUnit)


# Requirements
- Java 8
- Maven


# How to build the application
- mvn clean package

# How to run tests
- mvn clean verify

# How to run the application
- java -jar moneytransfer.jar

# How to use the application
. To create an account
- POST http://localhost:8080/accounts/
- {
	"name": "Eli",
	"balance": "90000000.00"
}
 
 . Response:
 HTTP 201 Created
 - {
    "id": 2,
    "name": "Eli",
    "balance": 9.0E7
}

