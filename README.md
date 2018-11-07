# Zoo Manager

Technology stack used for the project

  - Backend
  -- Java
  -- Hibernate
  -- h2
  -- Spring
  -- Maven

  - Frontend
  -- Javascript
  -- CSS
  -- HTML
  -- React.js

### Overview




> The front end applicaton can be manged by npm and yarn. 
However, for this project Yarn is the recommended option


### Setup

##### Server
1. Add project to the IDE of your choice ( Eclipse / Intellij IDEA / Netbeans )
2. Create a database in Mysql
3. Edit the application.properties file and pass your db credentials there. For Example:
```sh
spring.datasource.url=jdbc:mysql://localhost:3306/zoo
spring.datasource.username=zoo
spring.datasource.password=zoo
```
You may also want to set the to change the: 
```
spring.jpa.hibernate.ddl-auto=create
```
to:
```
spring.jpa.hibernate.ddl-auto=update
```
To avoid the data loss in between switching on/off the server.
4. Run the main class which can be found in "ZooApplication".

**Important Note:**
Do not change the port on which the backend application is running. Keep it on 8080.

##### Client setup using Yarn
1. Navigate to the `client-spring` folder.
2. Run the following commands:
```
yarn install
yarn start
```
The first one will install all the dependencies and the second one will start the client instance.

##### Client setup using npm
1. Navigate to the `client-spring` folder.
2. Run the following commands:
```
npm install
npm start
```

##### The react application is now running.
By default it runs on port 3000, but that can be changed.



## Thats it!
You can now manage your animals.

Screenshoots from the application can be found [here](https://drive.google.com/open?id=19ICseETtXDNotd9mT-R0kM6wNUM6VSaZ) 
