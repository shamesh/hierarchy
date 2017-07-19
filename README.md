# Company Hierarchy Manager
This is a Maven SpringBoot Webapp. To run this project, you need to have Maven installed. 
After Maven is installed. Run following command:

    mvn spring-boot:run
   
This application run in 
     http://localhost:8080
     
## Endpoints
### /organisation 
This url is use to show the overall Organisational chart. The chart starts with the top most employee without any manager.
http://localhost:8080/organisationa/
### /addemployees
This url is use to add new employee in the system.

http://localhost:8080/addemployees?name=harry&id=1110&managerid=1
- This will show error message as managerid =1 is not available.

http://localhost:8080/addemployees?name=harry&id=1110&managerid=100
- This will add the new employee harry under managerid = 100. The UI will change to show changed organisational chart.

http://localhost:8080/addemployees?name=Sam&id=1110&managerid=100
- This will update the employee name harry to Sam under managerid = 100. The UI will change to show changed organisational chart.

### /employees 
This url is use to get specific employees and its subordinates / all employees
http://localhost:8080/employees
- Shows Organisational chart. Simiar to /organisation

http://localhost:8080/employees?name=Alan
- Shows Alan and his reportees

### /h2-console/
This url is use to chek H2 Embedded database. 
Use JDBC URL:= jdbc:h2:file:./default to connect to the database

## Input file
employee.csv is used as input for initial database setup.

## Log
The Log of the application will be produced under log folder if any error occurs. If any employee's manager id is mismatched or not available, it can be checked via log file.

## Assumption
- During data loading from csv, as the employee with unknown manager can still have many individuals reporting to her/him, the system only sets the managerid as null and logs the error for future reference.
- The REST service returns JSON data. To make it readble in browser, it is passed as string (without removing formatting spaces)
- Currently only addition of employees, search of employees, Update of Employee name and showing Organisational chart is handled in the system. Deletion of specific employee can be handled via additional endpoint in REST Service.
