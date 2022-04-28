# Food trunk finder implementation. 
    The primary focus of this project implementation is on REST end point service to find out nearby food trucks by the given co-ordinates (Latitude and Longitude). Client wrapper project has also been implemented for easy access of the End point through command line. 

# End point URL
http://localhost:8080/trucks?latitude=< inputLatitude >&longitude=< inputLongitude >

Ex:  http://localhost:8080/trucks?latitude=37.78520535858918&longitude=-122.41594524663745 // These coordinates I got from google maps (one of SFO locations)

Git Link
https://github.com/apatiban/truck-finder-assignment.git

# Folders/Files 	
1.	truck-finder-service // REST Endpoint implementation
2.	truck-finder-client // REST client project
3.	README.md
# Instructions to setup/run the Projects
1.	truck-finder-service 
		cd truck-finder-service
		mvnw.cmd clean -f "pom.xml"
  	mvnw.cmd" install -f "pom.xml"
  	java -jar target/truck-finder-service-0.0.1-SNAPSHOT.jar
2.	truck-finder-client
  	cd truck-finder-client
  	mvnw.cmd clean -f "pom.xml"
  	mvnw.cmd install -f "pom.xml"
  	java -jar target/truck-finder-client-0.0.1-SNAPSHOT.jar --latitude=< latitude > --longitude=< longitude > //  Ex: java -jar target/truck-finder-client-0.0.1-SNAPSHOT.jar --latitude=37.78520535858918 --longitude=-122.41594524663745

# Frameworks Used
	Spring boot framework.  I chose this for the ease of the development. Was planning to dockerise the application . Spring boot gives a quick way to do that.
	But couldn’t get enough time to do that.

# Assumptions made
1.	Retrieving top 5 trucks within radius of 20 mi.
2.	The logic for Distance calculation from source to nearest truck has been taken from the internet sources.
3.	Assuming only 5 results are needed, didn’t implement the pagination logic and also due to time constraint.  
# Design approach used
1.	Straightforward Spring boot application with REST api implementation. 
2.	Tried to generalize to the maximum extend to work for any nearest location search and not just the food truck.
3.	This while design can be extended for any type of search with large datasets.
4.	Java client project has been created for command line interface. The idea behind is, to create a sh script out of this command as a simple solution. This is just for  the ease of using the api.   

# Algorithm used
1.	https://www.geodatasource.com/developers/java this is the source I have used for calculating the distance from source to truck.
2.	Sourcing the Truck data dump from https://data.sfgov.org/api/views/rqzj-sfat/rows.csv
3	For the sorting the data based on nearest truck location for give co-ordinates, I had used Heap MinHeap. This performs with large scale of input data. As insertion     and removing from the tree is O(logn).
4	Using HeapSort fetching the first 5 closest trucks.

# Things can be improved if there a time budget
	Sky is the only limit for improvements 
1.	Code improvement
		1.	But primarily,  I would add more test jUnit and integrated test cases. And many more coding comments.
		2.	Exception handling with better Exception Handlers given the spring-boot.
2.	Client project can be either apache-cli some kind of batch.  Ideal way could be like kubectl (tfctl get all trucks –radius=5M) tf - (Truck Finder)
3.	Pagination support can be added for the endpoint.
4.	Given time, Would like add some react front-end as well with map UI integrated. Although I hadn’t worked on this type wanted to try that.
5.	Lastly, I could have deployed this project my personal website.
6.	Application bundles could have been for messages
7.	Mvn production-kind build mechanism with generic versioned dependencies.
