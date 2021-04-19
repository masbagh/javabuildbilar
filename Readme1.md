This project is a micro service using docker to manipulate  a database . it provides crud on a database called testdatabase. 

Video on Teams 20210309 -110457

should do: 

OBS: Kör från commandprompt som Administratör
** setx -m JAVA_HOME "C:\Progra~1\Java\jdk-15.0.2"

Spring Add Starter> Web server, data-jpa, mysql driver
    DemoController: This class uses attribute @RestController  
    to use get and set for json file these commands should be in lowercase 
    for a microservice http is used as a gui not console
    http://127.0.0.1:8080/booksotre is the url is used to get and connect to database we can see data in json form
    for https the standard port is 443 not 8080, we usually dont write this portnumber
    we can use http://jsonviewer.stack.hu/ or postman to see our data  in a structured form 

To connect to database:
run docker desktop
start container: mysql02

we can use an image many times to create a container, the images are templates on different machines/environments.
and then to see and to run them, we need to use som commands on ps(powershell)

Some commands in docker: 
    docker ps
    docker start 9b462f1126fd
    docker exec -it mysql02 bash
    mysql -u root -p     pass: hejsan123
    use testdatabasen;
    show tables;
    select * from book_store;
    mysql> delete from book_store where id= 3;      
    ********************************************************************

power point 020 on Teams:  Microservices och REST.pptx (1).pdf
// GET /bookstore                            Returns a list of all books
// GET /plabookstore/2                       Returns a single book with ID 99. Or 404 if not found
// POST /bookstore  [Title=”Bus 57”]        Creates a new book, sets title as Bus 57. Return 201 with URL to the new book dvs /books/99 tex
// PUT /bookstore/100 [Title=”Bus 57”]      Replace a book with ID 100 all properties must to be sent 
// DELETE /bookstore/100                     Deletes book with id 100. Or 404 if not found
// PATCH /bookstore/100                      [price=424] Partial update...SVÅRT API
*************************************************************
???Errors
When trying to run docker pull mysql :
1.	no matching manifest for windows/amd64 10.0.19042 in the manifest list entries
Solution:
    1.	Right click Docker icon in the Windows System Tray
    2.	Go to Settings
    3.	Daemon
    4.	Advanced
    5.	Set the "experimental": true
    6.	Restart Docker

Trying to play mysql01 on  docker desktop:
    2.	(HTTP code 500) server error - failed to create endpoint mysql01 on network nat: failed during hnsCallRawResponse: 
    hnsCall failed in Win32: The process cannot access the file because it is being used by another process. (0x20)

    3.	 Only one usage of each socket address (protocol/network address/port) is normally permitted
    Solution: I solved the problem by ending the "mysqld.exe" process in the task manager. Because this process has occupied port 3306.
 

   On powershell when attempted to run docker run -d -p 80:80 docker/getting-started
4.	docker: no matching manifest for windows/amd64 10.0.19042 in the manifest list entries.See 'docker run --help'.
	Solution: 
    1.Click Docker Icon in System Tray In Context Menu
    2.	Click "Switch to Window/Linux Container"
    3.	Option Click Switch Button in Switch Dialog
    4.	It may take little time
    5.	Make Sure Docker is Running State Now


*********************************************************************************************************
****************  Deploying to  AWS Amazon web service/azur instead of 3306/localhost  ******************* 
                                Inlämning 2
 G:run your application (API) locally (on your own computer) against a database in AWS or Azure.
 That is,create a database and configure your application so that it runs against the cloud database
 VG = Deploy your application so BOTH the application and the database are in AWS or Azure

//@CrossOrigin(origins = "http://localhost:8080")   this allows to answer to request from localhost we use @CrossOrigin() that will work for any request
Then we can reach to our CRUD via file:///E:/STI/Java-Code/TableSample5/TableSample5.html

To be usable our application by others on cloud we need to buy or connect to a host/webhotel on a server like  Amazon or Microsoft(Azure), Google 
 These servers are enough scalability for example having oracl or sqlserver 
 cloud?
● Software as a Service (SaaS) – exempelvis: Dropbox, Visma eEkonomi, Fortnox, Microsoft 365
● Instrastructure as aService (IaaS) – exempelvis: Amazon Webservices, Microsoft Azure
    virtual machines
    möjligt att bygga applikationer utan att tänka på infrastrukturen. Där IAAS pratar virtuella maskiner kan vi
prata om Applikationer
Ex AWS Beanstalk
Heorku
App Engine
OpenShift
Azure App Service
● Platform as a Service (PaaS) – exempelvis: Salesforce Heroku, IBM SmartCloud, Google App Engine

So instead of using spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/testdatabasen in resources\application.properties 
we gonna to use AWS
So we will run both our app and our database on AWS:

Deploying database on RDS:

1- log in AWS(Amazon management console):    https://us-east-2.console.aws.amazon.com/console/home?region=us-east-2#
in video Möte i _General_-20210316_135757-Mötesinspelning:
2- Then RDS: All SQL services 
Databases> create database> mysql> password: hejsan123> Burstable classes (Include previous generation classes),db,t3.micro,
>  create new VPC,  create new DB> public>
user:admin  pass:hejsan123(for login to database)

and then in apllication.properties we do connect  the applicationen to the database.
We also need to copy the endpoint of our database server in html file.  

Downloading mysql server and mysql workbench from dev.mysql.com/downloads
then in workbench we will create a sql file with this command
we need to create a AWS database take the endpoint and connect to database(testdatabasen) on workbench. 
create database testdatabasen;

***********Deploying  the application on AWSPS
Before changing path to AWS in local: spring.datasource.url=jdbc:mysql://localhost:3306/proddatabasen

we need to run: 
PS E:\STI\Java-Code\javaProductApp\demo>  ./mvnw.cmd clean package -Pproduction -DskipTests

at AWS elasitc Beanstalk  we deploy our jar file from target in the application :demo-0.0.1-SNAPSHOT.jar this file creates after  ./mvnw.cmd clean package -Pproduction -DskipTests

to ensure if the application on aws will work correctly we do copy the link on configurataion in Beanstalk and use in application.properties as endpoint:
spring.datasource.url=jdbc:mysql://https://us-east-2.console.aws.amazon.com/rds/home?region=us-east-2#dbinstances:id=aa1dqtabuhnpjeu:3306/proddatabasen
 if it changes to linkform it shows that will work

 Then we use this code instead of writing username password 
 we create a file in resources application.properties:
 spring.datasource.url=jdbc:mysql://${RDS_HOSTNAME}:${RDS_PORT}/${RDS_DB_NAME} 
spring.datasource.username=${RDS_USERNAME}
spring.datasource.password=${RDS_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
server.port=5000


in tablesample.html we change all localhost to the new database address that we take from elastic Beanstalk and replace 
http://masbookstore2.us-east-2.elasticbeanstalk.com/

502 Bad Gateway:
change  in elastic Beanstalk   configuration ->environment properties : 
SPRING_PROFILES_AKTIVE    TO    production


