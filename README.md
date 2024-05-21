# JWT Quiz - Secure Quiz Creation and Assessment

**Introduction:**
JWT Quiz is a Spring Boot application designed for secure quiz creation, participation, and result delivery. It leverages JSON Web Tokens (JWT) for robust authentication and authorization, ensuring only authorized users can access specific functionalities.

**User Roles and Access Control:**
The application defines three distinct user roles with varying access levels:
- **ADMIN:** Has full access to all functionalities, including user management, quiz creation, quiz deletion, and viewing results.
- **TEACHER:** Can create quizzes, edit existing quizzes, and view submitted quiz results for their students.
- **STUDENT:** Can log in, participate by submitting answers, and view their own quiz results.
  
**Authentication and Authorization**
1. **Login:** Users authenticate by providing their username and password. The server validates credentials and, upon successful authentication, generates a JWT containing user information and claims.
2. **Token Exchange:** The generated JWT is returned to the client application.
3. **Authorization:** For subsequent requests, the client includes the JWT in the Authorization header. The server verifies the token's validity and user claims to determine access rights.

**Usage:**
1. Clone the repository: `git clone https://github.com/MustafaGamal000/JWT-Quiz.git`
2. Navigate to the project directory: `cd your-repo`
3. Build the project: `mvn clean install`
4. Run the Spring Boot application: `mvn spring-boot:run`
