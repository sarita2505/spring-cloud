 we have discussed how to use Spring Cloud Bus to broadcast the refresh event ( /actuator/bus-refresh) across all the connected services. In here the refresh event should be manually triggered on any service that is connected to the Spring Cloud Bus. (You can select any service as you wish. The only requirement is that it should connect to the Spring Cloud Bus).


docker run -d  --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

Once we executed the above command, we can go to the web browser and open http://localhost:15672, which will show the management console login form.

The default username is: ‘guest’; password: ‘guest’.

RabbitMQ will also listen on port 5672. (but the web port is 15672)

We maintain the application propertie shere:

https://github.com/sarita2505/config-server-property/blob/master/application.properties

Run this application (current folder) on 2 ports with below command
java -jar -Dserver.port=9090 traget/app.war
java -jar -Dserver.port=9091 traget/app.war

Both of the below end point will show same response as they are getting from git.

http://localhost:9090/hello

http://localhost:9091/hello

Now go to the git and update the application properties msg = new message
This time also the 9090 and 9091 will show old messages. we need to refresh one of them by using below end point with http POST call from postman.

http://localhost:9090/actuator/bus-refresh

Above end point will publish a event on clous bus which is listened by the 9091 and the properties in 9091 will be refreshed by talking to the config server.

At this time 9091 will interact with config server and update own properties. hence the below end point will show the same new response.

http://localhost:9090/hello

http://localhost:9091/hello


