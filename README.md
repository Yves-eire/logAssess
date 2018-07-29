# logAssess
flag the longest event (finished - started) log entry

<CREDIT SUISSE Coding Assignment>

# Fulfil on Requirements:

Java 8 lambada
Third party library: apach-common, jackson, junit, hsqldb
build tool: gradle
IDE: IntelliJ 14 Community 

# Features:

 - Take the input file path as input argument:
 
Only one argv accept, which is a string describe the log file path 

 - Flag any long events that take longer than 4ms with a column in the database called "alert":
the required Columne of DB:ENTRIES is (BIT) alert, After loading log entries into DB, a select sql reads the column value and one logger.info to return records onto stdrr

 - Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder:
HSQLDB implement with in-process model

 - The application should a new table if necessary and enter the following values: Event id, Event duration, Type and Host if applicable
"alert" true is applicable:
The table doesn't contains any primary key and defined as Id,State,Type,Host, TimeStamp, Alert

# Additional points will be granted for:
 - Proper use of info and debug logging:
Logger 

 - Proper use of Object Oriented programming:
POJO, Encapsulation, Object Composition, Inheritance, Override etc

 - Unit test coverage:
Coverage is low, mainly on functional

 - Multi-threaded solution:
 Not implemented
 
 - Program that can handle very large files (gigabytes):
Run successfully after loading a customerise log file which expending the example snippet 
