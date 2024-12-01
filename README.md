
# FitFusion

FitFusion is a backend application designed for gym management. It provides role-based access for **customers**, **admins**, and **trainers**, enabling efficient management of gym-related operations. This project leverages Spring Boot for rapid development, JWT for secure authentication, and Spring Data JPA for database interaction.

## Features
- **Customer Role**:
  - View gym details, personal progress, diets, and exercises.
  - Book sessions with trainers.

- **Admin Role**:
  - Manage gym memberships and trainer assignments.
  - Oversee customer data and schedules.

- **Trainer Role**:
  - Access assigned customer information.
  - Manage training schedules, diets, and exercises.

## Tech Stack
- **Backend Framework**: Spring Boot
- **Authentication**: JSON Web Tokens (JWT) with a custom filter.
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

## API Endpoints

### AuthController
**Public API**:
- **POST** `/auth/login`:  
  Logs in a user.  
  **Request Body**: `JwtRequest jwtRequest`  

### DietController
**Trainer APIs**:
- **GET** `/diet/all`:  
  Fetch all diets.  
  **Response Status**: OK  

- **GET** `/diet/{id}`:  
  Fetch a diet by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **POST** `/diet/create/{userId}`:  
  Create a diet for a user using their ID.  
  **Request Body**: `DietDto dietDto`  
  **Path Variable**: `Long userId`  
  **Response Status**: CREATED  

- **PUT** `/diet/{id}`:  
  Update a diet by its ID.  
  **Request Body**: `DietDto dietDto`  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **DELETE** `/diet/{id}`:  
  Delete a diet by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

### ExerciseController
**Trainer APIs**:
- **GET** `/exercise/all`:  
  Fetch all exercises.  
  **Response Status**: OK  

- **GET** `/exercise/{id}`:  
  Fetch an exercise by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **POST** `/exercise/create/{userId}`:  
  Create an exercise for a user using their ID.  
  **Request Body**: `ExerciseDto exerciseDto`  
  **Path Variable**: `Long userId`  
  **Response Status**: CREATED  

- **PUT** `/exercise/{id}`:  
  Update an exercise by its ID.  
  **Request Body**: `ExerciseDto exerciseDto`  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **DELETE** `/exercise/{id}`:  
  Delete an exercise by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

### UserController
**Admin APIs**:
- **GET** `/user/all`:  
  Fetch all users.  
  **Response Status**: OK  

- **GET** `/user/{id}`:  
  Fetch a user by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **PUT** `/user/{id}`:  
  Update a user by its ID.  
  **Request Body**: `UserDto userDto`  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **DELETE** `/user/{id}`:  
  Delete a user by its ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

**Customer APIs**:
- **GET** `/user/exercise/{id}`:  
  Fetch all exercises assigned to the user by their ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

- **GET** `/user/diet/{id}`:  
  Fetch all diets assigned to the user by their ID.  
  **Path Variable**: `Long id`  
  **Response Status**: OK  

**Public APIs**:
- **POST** `/user/register`:  
  Register a new user and assign a role.  
  **Request Body**: `UserDto userDto`  

---

**Author**: [Vijay Gunjalkar](https://www.linkedin.com/in/vijay-gunjalkar-6870a11a9/)  
For queries, contact: vijaygunjalkara54@gmail.com
