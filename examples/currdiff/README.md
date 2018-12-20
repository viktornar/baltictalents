![image](https://travis-ci.org/viktornar/currdiff.svg?branch=master)

CurrDiff - simple web page application
===================
CurrDiff is a simple web page application that can be used for getting exchange rate changes by using remote Lithuanian National Bank service. Exchange rate changes by default are ordered in descending order.

![image](https://raw.githubusercontent.com/viktornar/currdiff/master/images/application.png)

The main technologies (frameworks, libraries) that was used in project
-------------
- Embedded Tomcat – For development process speedup embedded tomcat was used. Currently I use IDEA community edition that can’t run a web application using Tomcat Server. The simplest way to bypass this restriction is just write helper class with main method that runs embedded Tomcat server;
- Maven – For project management maven was used. Maven handles dependencies management for project and it can be used for task execution (war packaging, removing libraries in package stage, copying resources while project is building etc.). Gradle as alternative exist, but on my computer project building is much quicker with maven than Gradle. In project Maven wrapper was used. So every user who would like to quickly test application can run project even if maven and tomcat isn’t installed on computer;
- JSF – JavaServer Faces technology was used. JSF allows creating reusable components in the web application. JSF 2 (Mojarra implementation) with facelets templating was used for creating UI of application;
- Prime Faces – Prime Faces was used as JSF component library. Prime Faces has well designed collection of components that can be used in existing project on top of JSF. So if you need to quickly write application with nice looking UI its natural choice to use it. Prime Faces have a lot of themes and one of them (bootstrap) can be quite well integrated with Bootstrap css library;
- Bootstrap – For quick UI mockup Bootstrap was used. Bootsrap allows creating well looking responsive UI.  Bootstrap 3 was used as the latest stable version;
- Spring Framework – Spring framework was used for dependency injection and other service layer oriented tasks (e.g. Rest Template for communication with remote service);
- Junit for testing, BeanUtils for manipulation with Java beans (copying of properties), slf4j for logging, Lombok for syntactic sugar, Xstream and Jackson for marshaling objects from remote service.

> **Note:**

> - Project was written by using Java SDK 1.8. Source was compiled with Java 1.7 compatibility so project should run if Java SDK 1.7  is installed.
> - Java 1.6 and earlier versions is not supported. 
> - Tomcat 7, 8 only supported.

Application architecture
-------------
In application MVC architecture was used. JSF can be used as MVC web framework where main controller is Faces Servlet that acts similar to Spring Dispatcher Servlet. Application views are facelets pages (index.xhtml) with templates (header.xhtml, main.xhtml, content.xhtml). Backing beans (they are normal managed beans that handles page action and are responsible for view resolving) was used as sub controllers (IndexController.java) that binds model data to views through service layer. Services (CurrencyRateService.java, SettingsService.java) are responsible for fetching data from remote service, marshaling data to model (Item.java), calculating changes of exchange rate and getting appliaction settings. The principal schema used during development are shown below:

![image](https://raw.githubusercontent.com/viktornar/currdiff/master/images/pincipal_schema.png)

How to build and run project
-------------
Clone the project with the following command:

```bash
$ git clone https://github.com/viktornar/currdiff
```

Go to the project directory:

```bash
$ cd currdiff
```

On Windows OS run:

```bash
mvnw.cmd package
target\bin\currdiff.bat
```

On Linux OS run:

```bash
$ chmod u+x mvnw && ./mvnw package
$ chmod u+x target/bin/currdiff && ./target/bin/currdiff
```

If you want to run embedded server with another port you must export *PORT* environment variable.

On Windows OS run:

```bash
SET PORT=9090
target\bin\currdiff.bat
```

On Linux OS run:

```bash
$ export PORT=9090
$ ./target/bin/currdiff
```

Maven will download dependencies, build project and generate startup script to start web application in console. After launching startup script application will be accessible from the web browser through http://localhost:8080/ address.

If you want to deploy application to standalone tomcat (tomcat 7 and 8 are only supported) change from:

```xml
<packaging>jar</packaging>
<!--<packaging>war</packaging>-->
```

to:

```xml
<!--<packaging>jar</packaging>-->
<packaging>war</packaging>
```

and then on Windows run:

```bash
mvnw.cmd package
```

or on Linux run:

```bash
$ ./mvnw package
```

Put generated war ( [PROJECT_HOME]/target/currdiff.war ) to tomcat webapps directory and restart it if tomcat doesn't support hot redeploy. Application will be accessible through http://localhost:[PORT]/currdiff address.

> **Note:**

> Deploying with exploded war is not supported you must use war file or in exploded war directory manually delete tomcat-`*.jar libraries (e.g. [PROJECT_HOME]/target/currdiff/WEB-INF/lib/tomcat-`*.jar)

How to develop project
-------------
If you use IDEA or Eclipse just import project as maven project. In IDEA use mvn wrapper. After project successful import find Main.java in lt.viktornar.currdiff java package and run it as application. Web application will be accessible from the web browser through http://localhost:8080/ address. 