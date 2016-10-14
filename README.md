# Api-Rest-Java
Api rest that calculates the angle between the two pointers of the clock.

# Requeriments
Java 1.8 and Maven.

# How to Test?
0 - Open {cmd} and choose a directory

1 - git clone https://github.com/jeancatarina/Api-Rest-Java.git

2 - mvn spring-boot:run

3 - Open another cmd and write $ <b>Curl http://localhost:8080/rest/clock/6/0 </b>

4 - Response should be {"angle":180}
