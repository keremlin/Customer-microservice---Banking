# Customer microservice - Tosan Banking=Loan project
There are Four microServices in this project.
## Documents
### Code
This project is based on Spring boot 2.6.4 and JDK 11. 
There are Five packages in this project :

  ├───java
  │   └───com
  │       └───tosan
  │           └───customer
  │               ├───controller
  │               ├───Exceptions
  │               ├───model
  │               ├───repositories
  │               └───services
  └───resources

All rest controllers are placed in the controller package. The connection between services is based on REST. The REST service is implemented by RESTfull standards(link).

Exceptions package is included by user-defiend exceptions and also related handlers. Models and related validations are in the model package.

Models are protected by bean validation annotations, so data constrains are implemented by them. The exception handling of the bean validation is take place in the RestControllerAdvice in Exception package in handleValidationExceptions.java file.

Jpa repositories also is in its package. 

And provided services like customerService are in the service package.

### Logging
With Lombok implementation of SLF4J, the logging process is done with @SF4j.

###
