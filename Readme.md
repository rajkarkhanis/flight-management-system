# Flight Management System
<hr>

### Abstract:
The Flight Management System is a Java-based booking solution for flight tickets. It
consolidates data provided by different airline carriers and hence provides the user details
and rates in real-time. Travellers may want to make changes in their bookings. The
application allows them to book, cancel, view and update their bookings with ease. Other
than this, it eases the management of bookings too. All the bookings, flights, schedules and
routes can be viewed, added and modified on a single application by the administrator.
### Scopes:

#### In scope:  
Following is the functionality provided by the system:

There are two categories of people who would access the system: customer and
administrator. Each of these would have some exclusive privileges.
1. The customer can:  
   1. Create his user account.  
   2. Login into the application.  
   3. Check for available flights.  
   4. Make a booking.  
   5. View the bookings made.  
   6. Cancel or modify a booking.  
2. The administrator can:  
   1. Login into the application.  
   2. Add flight, schedule and route details.  
   3. View the flight, schedule and route details.  
   4. Cancel or modify the flight, schedule and route details.  
#### Out scope:  
   The following functionalities have not been covered under the application:
1. The application does not cover boarding pass generation and seating plans.
2. Third party applications like email & sms integrations.
3. Payments are not yet accepted by the application.

<hr>

## Deployment
Follow [Cloud Deployment](./CloudDeployment.md)

## Developer notes:   
1. For Lombok's @RequiredArgsConstructor we need object to be: ```private final <DataType> variable```  
   </br>
2. Flow of Data is:  
   1. Controller receives DTO (Data Transfer Object)
   2. DTO is validated (using java validations)
   3. DTO mapped to entity (present in beans) in service after passing checks
   4. Entity is saved to DAO (Data Access Object)
   5. Exception package consists of a Global Exception Handler and several other custom exception classes.  
      </br>
3. Flow of Security is:
   1. Only requests to ```customer/adduser/``` and ```api/login``` are permitted to all.
   2. Authentication is performed through authentication manager of springframework security.
   3. Upon Successful authentication, ```accessToken``` and ```refreshToken``` are generated using JWT.
   4. These are returned to the user.
   5. User must include the ```accessToken``` in Authorization header of every request henceforth to access authorized APIs.  
      </br>
4. API requests collection on Postman: https://www.getpostman.com/collections/a4dc25ef1d9e96632d08  
   </br>
5. Setting of ```accessTokens``` in postman can be automated using variables and test to set the variable.  