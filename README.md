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
    "id": 1,
    "name": "Eli",
    "balance": 90000000
}

# Update an account
. To update an account, use its id:
- PUT http://localhost:8080/accounts/1
- {
	"name": "Eli",
	"balance": "100000000"
}

. Response:
HTTP 201 Created
- {
    "id": 1,
    "name": "Eli",
    "balance": 100000000
}

# Delete an account
- DELETE http://localhost:8080/accounts/1

. Response:
HTTP 204 No Content

# Retrieve All Accounts
- GET http://localhost:8080/accounts

. Response:
HTTP 200 OK
- [
    {
        "id": 2,
        "name": "Alice",
        "balance": 9000.0
    },
    {
        "id": 3,
        "name": "Clive",
        "balance": 45000.0
    }
]

# Retrieve one account
- GET http://localhost:8080/accounts/3

. Response:
HTTP 200 OK
- {
    "id": 3,
    "name": "Clive",
    "balance": 45000.0
}

# Tranfer from one account to another /fromAcct/ToAcct/Amount
- POST http://localhost:8080/accounts/transfer/2/3/2000

. Response:
HTTP 204 No Content

# Retrieve all accounts after transfer
- GET http://localhost:8080/accounts

. Response:
HTTP 200 OK
- # Retrieve All Accounts
- GET http://localhost:8080/accounts

. Response:
HTTP 200 OK
- [
    {
        "id": 2,
        "name": "Alice",
        "balance": 9000.0
    },
    {
        "id": 3,
        "name": "Clive",
        "balance": 45000.0
    }
]




