Celebrity Finder is a Spring Boot Application that solves "Find the Celebrity" problem.

It supports an H2 In-Memory Database that stores initial values from /resources/data.sql file. It also supports data upload from CSV files by using CsvDataHandler class.

Exposes the following endpoints:

GET: http://localhost:9000/persons: retrieves data from existing persons in database.

POST: http://localhost:9000/persons: to store a new person in database.

GET: http://localhost:9000/persons/id/{id}: retrieves data from particular id.

GET: http://localhost:9000/persons/id/{id}/relations: retrieves a collection with existing relations from person with the given id.

POST: http://localhost:9000/persons/relations: stores a reference to known person into subject person. Json example: {"subject":{"id":13},"known":{"id":3}}.

GET: http://localhost:9000/persons/celebrities: returns a person object if found.

Instructions:

Initial data stores 12 objects (persons) in H2 database and then creates relations between persons. At this point there is no a celebrity.

If line 31 in data.sql file is included, then person with id = 3 (Lewis) is the celebrity.

Keeping line 31 in data.sql as a comment and creating a new relation between person with id 12 (Lance) and person with id 3 (Lewis) through the enpoint also identifies Lewis as a celebrity. Example: sending a post request to localhost:9000/persons/relations with body {"subject":{"id":12},"known":{"id":3}}, turns Lewis into a celebrity.

At this point, storing a new person, eliminates the celebrity. It should be necessary to create a relation between the last stored person and Lewis, in order to retrieve Lewis his celebrity status. Example: sending a post request to localhost:9000/persons with body {"name":"Sebastian"} will create a new person with id 13. It would be necessary to repeat last step to create a new relation between id 13 and id 3 to turn Lewis into a celebrity again.

In order to build the project, run from console command: your-path\celebrity>gradlew build

And then, for launch it: your-path\celebrity\build\libs>java -jar celebrity-0.0.1-SNAPSHOT.jar
