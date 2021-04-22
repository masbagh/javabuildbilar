inlämning 3

Inlämningsuppgift:

	Gör två (minst) olika projekt 

	= Microservices. Av den typen vi redan gjort. CRUD + databas

	Projekten ska vara separata men del av ett större sammanhang. 



	Tex man kan ha

	ProductService (produkter och priser)

	ShoppingCartService (produktid, namn, pris, antal)



	Dessa två hör ju till samma APPLIKATION kan man förstå

	Men du ska bygga dom HELT SEPARAT

		- tänk att på ditt företag jobbar en grupp med ProductService i ex Python

		- en annan grupp sitter med Java (i Indien ) och jobbar med ShoppingCartService

		ERAN KOMMUNIKATION ÄR GENOM Rest-interfacet! Annars är ni helt separata

		Så TVÅ OLIKA DATABASER OCKSÅ!



	Källkod i github - två olika projekt

	Enhetstester ska finnas (behöver inte vara heltäckande eller så men ni fattar)



	Bygg två pipelines. 			

	Dessa ska automatiskt

		- KÖRA ENHETSTESTER

		- deploya till Elastic Beanstalk


              INLÄMNING = klistra in Github-länk



VG:

	Jobba agilt. Embrace the changes. När G delen är klar ska den lämnas in. 

	Sen ska ni (ex skapa branches eller nya projekt) göra samma sak fast 

		bygga Docker image och deploya till Elastic Beanstalk (i bakgrunden ECS)

       Skapa ett enkelt web UI - ungefär som Stefans super UI
       --------------------------------------

Exempel:
Bokförlagssystem - 

	ha en Company (namn), adress, kontakltuppgifter etc etc
	ha en Bok (titel, isbn, utgiven år, company_id)

Lagersystem - 

	produkt - id, namn, pris ??



	lagerhantering: product_id, antal

			- inleverans()

			- utleverans()

Projekt 1 - Bilar id, namn, modell, årsmodell, regnr   - egen databas. EGEN BEANSTALK ENV med databas och site

                    egna tester. API CRUD. Egen pipeline

Projekt 2 - Employee id, namn, avdelning, anställningsnummer, anställningsdatum, regnr  - egen databas. EGEN BEANSTALK ENV med databas och site

                    egna tester. API CRUD. Egen pipeline

*************************************************************************************************************************
                                                    Solution
- testar (min ändring)
- regressionstest (sidoeffekter)
- commit + push källkod
- ./mvn -DskipTests clean package
- logga in på AWS
- klicka in på beanstalk env
- laddar upp vår jar
----------------------------------------------------------------------
Vi skapar projekt bilar med static välues (volvo,... )
maven , then run that 
creating artifact:exe/war/jar:  
PS E:\STI\Java-Code\20210330\bilar\bilar>   ./mvnw.cmd clean package -DskipTests
gör publish på git hub.
                         ------------
our PIPeline to automatic deploy : Code commit '=> github-> Sourcecode + run codesqec.yml => Codebuild -> jarfile=> AWS S3 storage => CodeDeplopy => Elastic Beanstalk =>

                         skicka på molnet (Azur(Devops)/AWS(Codebuild))
	Creating beanstalk application:
Sing in to aws
Services>elastic beanstalk> Applications>create Application
Application name: bilar
Create, name:Bilar, 
Then create environment > web server> select managed platform, Platform java, ,Sample application
create>
then run/test the application:
PS E:\STI\Java-Code\20210330\bilar>f5
check data on http://localhost:8080/car on browser>  you will get all data 
to create jar file:
PS E:\STI\Java-Code\20210330\bilar\bilar> ./mvnw.cmd claan package DskipTests
Error: Got errors cant build
This command means do clean and package the project 
Solotion :If it doesn’t work run via maven on the side bar package>
Then in AWS go to Bilar(application)> bilar env> upload jar file
502 Bad Gateway:
change  in elastic Beanstalk   configuration ->environment properties : 
we add this to end of environment properties:
برای تست باید برنامه در حال اجرا باشد
PORT    8080  
تست می کنیم >|
Click on go to environment. An then copy endpoint and the list name
http://bilar-env.eba-pm3yfzqd.us-east-2.elasticbeanstalk.com/car

Video: Möte i _General_-20210330_100930-Mötesinspelning
Powerpoint: 200 AWS CodeBuild.pptx
Länk till video:  https://stise.sharepoint.com/sites/JAD21-JavaAutomationDeveloper/Delade%20dokument/General/Recordings/M%C3%B6te%20i%20_General_-20210330_100930-M%C3%B6tesinspelning.mp4
Code commit(AWS gitrepository)
AWS s3 is a Storage:very large harddisk för storing data.  
AWS Code build: skapar jar filen then sends to AWS s3 and then sends Code deploy which wakes up when code build starts. And the sends to elastic beanstalk maybe we don’t use code deploy and we manage it on another approach. 
BuildSpec.yml: specificerar jar filen.  yamel
Elastic Benstalk

codebuild:
In aws we use codebuild or codepipline
>build:git prata med aws>create build project>project name:BilarBuild, Source:github, check  repository in my github account, Githubrepository:javabuild and then put the link from git(aspcodenet/javabuildbilar.git, rebuild every time a code change is pushed to repository check så det blir tillgänglig till våran repository , check signal, 
Environment:
Operatin system:amazon linux 2 tillexample, runtime
 standard, image standard 3.0, new service role, use a buildspec file,
30:10 >Artifacts> s3, bucket name(is like our harddisk like d: så its good to have different folders for different builds so we assigen a name to folder for this build): choose, name: bilar is our compressed file in the bucket 
And then nothing just click creats build project>start build
The first step (aws build is finished)
Error: yaml file error   we need to configure and specify our code before sending to aws , when we creates aws codebuild we hade a choose to select if we want to co changes in  yaml  or aws so we choosed yml

In bilarcontroller.java our restcontroller is //infrastructure as code
We need to create buildspec.yml below the .gitignore
Yml is a file format like xml , sql 
And has a structure

We can see our try on bilarbuild
Min 48’: Creating pipeline
 Now We need to create a pipleline through connecting awscodebuild to code deploy
In AWS: click on pipeline>name:bilarpipeline, new service role> next
Advanced settings: nothing change
Source>we need to setup connection to github again we select it from a list
Repository nam from our github        aspcodenet/javabuildbilar
Branch name:main
Next
Source>we need to setup connection to github again we select it from a list
Repository nam from our github        aspcodenet/javabuildbilar
Branch name:main
Next
Add deploy stage:
Deploy provider: aws elastic beanstalk
Application name: Bilar
Environment name: Bilar-env
Create pipeline
Now click on url in bilar-env  then In browser: 
url/car
 

Then we test it with a change in code and we run application 
Commit push and check on the aws url
******************************************************************’


Video: Möte i _General_-20210330_125937-Mötesinspelning  min 29’
Kör quiz på lab1 .systementor.se/quiz
20210330_125937   min 10
We shall do 2 microservices completely separate , 
Example på projekten:
•	Product , med crud database , 
•	Ecomerce site produkter priser description kategorisering
•	Köpping kart product id , pris , antal, användare 
•	Hoky service och glasögon service
•	Sallary service och emloyees  två tjänster som går in samma applikation 
•	Finnas komminakation mellan dem
Två repository på github
Enheter tester
De ska inte prata med varndra
Men webapplikation ska använda båda servicer. 
Video Möte i _General_-20210413_100417-Mötesinspelning
Länk: https://stise.sharepoint.com/sites/JAD21-JavaAutomationDeveloper/Delade%20dokument/General/Recordings/M%C3%B6te%20i%20_General_-20210413_100417-M%C3%B6tesinspelning.mp4
Skapa applikation med CI och CD inlämning 3




skapar 
to run the project 