# IMS Backend API Documentation

This document provides details of all API endpoints related to the IMS Backend Application.

## Base URL
All API endpoints are based on the following base URL:


## Endpoints

### Users API

1. **Save User**

   - **Endpoint**: `POST /user/save`
   - **Description**: Save a new user in the system.
   - **Request Body**:
     ```json
     {
       "email": "service@hdfcbank.com",
       "password": "Welcome@123",
       "entityName": "HDFC Bank PVT LTD",
       "userType": "BANK"
     }
     ```

2. **Get All Users**

   - **Endpoint**: `GET /user`
   - **Description**: Fetch all users in the system.

3. **Get Particular User**

   - **Endpoint**: `GET /user?entityName={entityName}`
   - **Description**: Fetch a user by the entity name.
   - **Query Parameters**:
     - `entityName`: The name of the entity (e.g., "HDFC Bank PVT LTD").

### Department API

1. **Save Department**

   - **Endpoint**: `POST /department/save`
   - **Description**: Save a new department in the system.
   - **Request Body**:
     ```json
     {
       "name": "BPO SERVICES",
       "description": "BPO Services"
     }
     ```

### Employee API

1. **Save Employee**

   - **Endpoint**: `POST /employee/save`
   - **Description**: Save a new employee in the system.
   - **Request Body**:
     ```json
     {
       "department": "BPO SERVICES",
       "firstName": "Praneeth",
       "lastName": "Jagirdhar",
       "email": "Praneeth.Jagirdhar@gmail.com",
       "password": "welcome@123"
     }
     ```

2. **Get All Employees**

   - **Endpoint**: `GET /employee`
   - **Description**: Fetch all employees in the system.

3. **Get Particular Employee**

   - **Endpoint**: `GET /employee?email={email}`
   - **Description**: Fetch an employee by email.
   - **Query Parameters**:
     - `email`: The email of the employee (e.g., "jagirdharnaveen69@gmail.com").

### Ticket API

1. **Save Ticket**

   - **Endpoint**: `POST /ticket/save`
   - **Description**: Save a new ticket and assign it to a particular employee in the department.
   - **Request Body**:
     ```json
     {
       "title": "Request Access for Application",
       "description": "Requesting access to tools like mobile/sim/landline",
       "department": "BPO SERVICES",
       "entityName": "HDFC Bank PVT LTD"
     }
     ```

2. **Get Tickets by User**

   - **Endpoint**: `GET /ticket/user/{entityName}`
   - **Description**: Fetch all tickets raised by a user.
   - **Path Parameter**:
     - `entityName`: The name of the entity (e.g., "HDFC Bank PVT LTD").

3. **Get Tickets by Employee**

   - **Endpoint**: `GET /ticket/employee/{email}`
   - **Description**: Fetch all tickets assigned to a particular employee.
   - **Path Parameter**:
     - `email`: The email of the employee (e.g., "Praneeth.Jagirdhar@gmail.com").

## Sample Responses

- **Success Response (200 OK)**:
  - On successful retrieval or creation of resources, the API will return the appropriate resource details with a status code of `200 OK`.

- **Error Response (4xx or 5xx)**:
  - In case of an error such as invalid input or internal server failure, the API will return an error message with an appropriate HTTP status code.

## Notes

- Make sure to replace `localhost:8080` with your actual backend URL if deployed in a production environment.
- The provided `entityName` should correspond to the correct entity associated with the user.
- The email used for saving employees and users must be unique to avoid conflicts.

