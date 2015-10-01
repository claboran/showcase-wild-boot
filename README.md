wildfly 8 BootsFaces + PrimeFaces Showcase: Extension of the Pete Muirs Wildfly Quickstarter
========================
Author: Christian Laboranowitsch original from Pete Muir
Level: Intermediate
Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS, BV, BootsFaces, Bootstrap, PrimeFaces, lombok
Summary: An example that incorporates multiple technologies
Source: <https://github.com/wildfly/quickstart/>

What is it?
-----------

It is a sample, deployable Maven 3 project to help you get your foot in the door developing with Java EE 7, BootsFaces, PrimeFaces on JBoss WildFly.

This project is setup to allow you to create a compliant Java EE 7 application using JSF 2.2, CDI 1.1, EJB 3.3, JPA 2.1 and Bean Validation 1.1, BootsFaces 0.6.6 and PrimeFaces 5.2. It includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.


System requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8), Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.

Install projectlombok within your IDE 

 
Build and Deploy
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line._

1. Run mvn install to build the WAR
2. Deploy the artefact
 

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/showcase-wild-boot/>.


Run it in the IDE
--------------------

1. Install Wildfly in your IDE
2. Deploy the application

Run the Arquillian Tests 
-------------------------

This quickstart provides Arquillian tests. By default, these tests are configured to be skipped as Arquillian tests require the use of a container. 

_NOTE: The following commands assume you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Run the Arquillian Tests](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/RUN_ARQUILLIAN_TESTS.md) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:

        mvn clean test -Pwildfy81-embedded

You can run the test also within the IDE
