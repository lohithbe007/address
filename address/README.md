# PROBLEM STATEMENT

## Addressline

An address provider returns addresses only with concatenated street names and numbers. Our own system on the other hand has separate fields for street name and street number.

Input: string of address

Output: string of street and string of street-number as JSON object

Write a simple program that does the task for the most simple cases, e.g.

1. "Winterallee 3" -> {"street": "Winterallee", "housenumber": "3"}
2. "Musterstrasse 45" -> {"street": "Musterstrasse", "housenumber": "45"}
3. "Blaufeldweg 123B" -> {"street": "Blaufeldweg", "housenumber": "123B"}

Consider more complicated cases

1. "Am Bächle 23" -> {"street": "Am Bächle", "housenumber": "23"}
2. "Auf der Vogelwiese 23 b" -> {"street": "Auf der Vogelwiese", "housenumber": "23 b"}

Consider other countries (complex cases)

1. "4, rue de la revolution" -> {"street": "rue de la revolution", "housenumber": "4"}
2. "200 Broadway Av" -> {"street": "Broadway Av", "housenumber": "200"}
3. "Calle Aduana, 29" -> {"street": "Calle Aduana", "housenumber": "29"}
4. "Calle 39 No 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}


# SOLUTION

The address parsing is well known complex problem because of the uncertainty of the address string.
Here I have tried to cover as much as cases which can be possible using REGULAR EXPRESSION to perform string pattern match and split functionality.

The String match has been divided into following three rules

1. ^[^0-9]+\d+[^\d]*$ : this rule looks for group of characters followed by digits. The string can end with a digit or can be followed by group of characters.
2. ^[0-9]+[,]*+[^\d]*$ : this rule looks for string starting with number followed by a "," (zero or more occurrence) followed by group of characters.
3. ^[^0-9]+\d+[^\d]+\d+[^\d]*$ : this rule looks for the combination of group of characters followed by a digit.

The project is built on springboot framework, junit 5 for writing test cases and gradle as a build tool.
The code can be tested in the following ways:

### 1. Testing through rest client: 
The project is built to support rest api which will return the required json format.Once the project is successfully built and started the below given url can be used to check the output\
http://localhost:8080/?address=<Address to be tested can be given here>

#### Example
url : http://localhost:8080/?address=Sewanstrassee%20215%20b
Result : {"streetName":"Sewanstrassee","housenumber":"215 b"}

#### Note : Please check the port number.

#### 2. Running JUnit test case:
The JUnits are covering the all the cases mentioned in the problem statement and can be found in the src/test/java/com.addressparser.address package.

To run JUnits run the following command:

./gradlew test



