Installation and start of application in Windows
After successfully unpacked "telecom-product.zip" file, following could be find there:
	- "Telecom product" - source code directory 
	- "app" - directory with files to start application 
	- "db scripts" - direcotry with SQL script to create db
	- "readme" - file with installation instructions
	
Steps to start application:
1. Execute SQL script "telecom-product.sql" from "db scripts" directory
2. In the "app" directory, update "application.properties" file with valid db username and password
3. Start application by running command in directory "app":
	java -jar telecom-product.jar
4. In browser open URL to test if app is started:
	http://localhost:8080/product
