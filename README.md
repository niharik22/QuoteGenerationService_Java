# Project: QGS (Quote Generation System)

### Overview

The project, "QGS (Quote Generation System)," aims to provide an efficient and user-friendly platform for users to generate insurance quotes. It addresses the problem of streamlining the insurance quote generation process, making it easier for both insured individuals and agents to obtain accurate quotes for various insurance policies.

### Technology Stack

The project utilizes the following technologies and tools:

- **Java**: The primary programming language for building the application logic.
- **SQL**: Structured Query Language for managing and querying the database.
- **JDBC (Java Database Connectivity)**: A Java-based API for connecting and interacting with the relational database.
- **Custom Exceptions**: Custom exception classes for handling specific error scenarios.
- **Properties File**: Usage of properties files for database configuration (jdbc.properties).
- **Database Management System**: A relational database system (e.g., MySQL, PostgreSQL) to store user and policy data.
- **User Interface**: Though not explicitly mentioned, a user interface technology or framework may be used for user interaction if applicable.

### Custom Exceptions (QGSException)

- **Custom Exception Class**: QGSException is a custom exception class that extends the standard Exception class. It's designed to handle project-specific exceptions and provide meaningful error messages to developers or users when exceptional situations occur.
- **Error Handling**: Custom exceptions are thrown in various parts of the project when specific error conditions are encountered. These exceptions encapsulate details about the error, making it easier to diagnose and fix issues during development or operation.
- **Exception Handling**: The project includes exception handling mechanisms, likely in the service and utility layers, to catch and manage these custom exceptions. When an exception is caught, it may log the error, provide user-friendly error messages, and take appropriate actions to handle or report the error.
- **Enhanced Debugging**: Custom exceptions contribute to enhanced debugging and error tracing. Developers can identify the nature of the exception quickly and pinpoint the code where the error occurred. This aids in maintaining the reliability and stability of the application.
- **User-Friendly Messages**: Custom exceptions help in presenting user-friendly error messages to end-users in scenarios where something goes wrong. These messages guide users on how to proceed or report issues effectively.
- **Separation of Concerns**: By using custom exceptions, the project maintains a clear separation of concerns, separating error handling and reporting from the main application logic. This improves code readability and maintainability.

### Model Classes

In the QGS project, model classes represent data entities within the system. These classes are responsible for defining the structure and behavior of various data objects used throughout the application. Here's an overview of the key model classes along with their attributes and methods:

#### Accounts Class:

- **Attributes**:
  - accountNumber (long): Unique identifier for each account.
  - insuredName (String): Name of the insured user associated with the account.
  - insuredStreet (String): Street address of the insured user.
  - insuredCity (String): City where the insured user resides.
  - insuredState (String): State where the insured user resides.
  - insuredZip (int): Zip code of the insured user's location.
  - businessSegment (String): The type of business segment associated with the account.
  - profileCreatorId (String): ID of the user who created the profile for the insured user.
- **Methods**:
  - Getters and setters for all attributes to access and update account information.

#### Policy Class:

- **Attributes**:
  - policyNumber (long): Unique identifier for each policy.
  - policyPremium (double): The premium amount associated with the policy.
  - accountNumber (long): The account number to which the policy is linked.
- **Methods**:
  - Constructors to initialize policy attributes.
  - Getters and setters for all attributes.
  - toString(): Generates a string representation of the policy object.

#### PolicyDetails Class:

- **Attributes**:
  - policyNumber (long): The policy number to which the details belong.
  - questionId (String): Unique identifier for each policy question.
  - answer (String): The user's answer to the policy question.
- **Methods**:
  - Constructors to initialize details attributes.
  - Getters and setters for all attributes.
  - toString(): Generates a string representation of the policy details object.

#### PolicyQuestions Class:

- **Attributes**:
  - polQuesId (String): Unique identifier for each policy question.
  - polQuesSeq (int): Sequence number of the policy question.
  - busSegId (String): The business segment ID to which the question is related.
  - polQuesDesc (String): The description of the policy question.
  - polQuesAns1 (String): First answer option for the policy question.
  - polQuesAns1Weightage (int): Weightage associated with the first answer option.
  - polQuesAns2 (String): Second answer option for the policy question.
  - polQuesAns2Weightage (int): Weightage associated with the second answer option.
  - polQuesAns3 (String): Third answer option for the policy question.
  - polQuesAns3Weightage (int): Weightage associated with the third answer option.
- **Methods**:
  - Constructors to initialize question attributes.
  - Getters and setters for all attributes.
  - toString(): Generates a string representation of the policy question object.

#### UserRole Class:

- **Attributes**:
  - userName (String): The username of the user.
  - password (String): The password associated with the user's account.
  - roleCode (String): The role code defining the user's role in the system (e.g., admin, insured, agent).
- **Methods**:
  - Constructors to initialize user role attributes.
  - Getters and setters for all attributes.
  - toString(): Generates a string representation of the user role object.

These model classes provide a structured representation of various data entities within the QGS system, allowing for efficient data manipulation and interaction between different layers of the application. They encapsulate data attributes and provide methods for accessing and modifying the data, promoting code organization and maintainability.

### DAO (Data Access Object) Layer

The DAO layer in the QGS project is responsible for interacting with the database, performing CRUD (Create, Read, Update, Delete) operations, and handling exceptions related to database access. It consists of several interfaces and their corresponding implementations:

#### IQGSDaoCommon Interface:

- **Purpose**: This interface defines common database operations that can be shared among various user roles.
- **Methods**:
  - getCredentials(String loginId): Retrieves the password associated with a given login ID.
  - getRoleCode(String loginId): Retrieves the role code of a user based on their login ID.
  - createAccount(Accounts account): Inserts a new account into the database.
  - getPolicyQuestions(String busSegId): Retrieves policy questions based on the business segment.
  - getBusinessSegmentDetails(): Retrieves a map of business segment names and IDs.
  - updateProfileCreatorId(String loginId, long accountNumber): Updates the profile creator ID in the account.

#### IQGSDaoAdmin Interface:

- **Purpose**: This interface defines database operations specific to the admin user role.
- **Methods**:
  - InsertUser(UserRole userRole): Inserts a new user (admin, agent,

 or insured) into the system.
  - GetUserName(): Retrieves a list of all usernames.
  - quickViewPolicyAdmin(): Retrieves a list of policies for admin user.
  - generateReport(long accNum, long policy_num): Generates a detailed policy report.

#### IQGSDaoAgent Interface:

- **Purpose**: This interface defines database operations specific to the agent user role.
- **Methods**:
  - quickViewPolicyAgent(String username): Retrieves a list of policies for an agent user.

#### IQGSDaoInsured Interface:

- **Purpose**: This interface defines database operations specific to the insured user role.
- **Methods**:
  - quickViewPolicy(String username): Retrieves a list of policies for an insured user.

**Implementations (e.g., QGSDaoAdminImpl, QGSDaoAgentImpl, QGSDaoCommonImpl, QGSDaoInsuredImpl)**:

- **Purpose**: These classes provide concrete implementations of the DAO interfaces, executing SQL queries and handling database operations.
- **Database Interaction**: They establish connections to the database using JDBC and execute SQL queries to perform CRUD operations, such as creating accounts, retrieving policy questions, inserting users, and fetching policy information.
- **Exception Handling**: The DAO implementations handle database-related exceptions, such as SQL exceptions, and translate them into custom exceptions (QGSException) to provide meaningful error messages and propagate exceptions to the service layer.

The DAO layer separates the database-related code from the business logic in the service layer, promoting modularity and maintainability. Each DAO interface focuses on a specific set of functionalities, making it easier to manage database interactions for different user roles while ensuring that exceptions are appropriately handled and reported to the service layer.

### 5. Service Layer

The service layer in the QGS project contains the `QGSServiceImpl` class, which serves as an intermediary between the client and the DAO layer. It encapsulates the business logic, performs validations, and interacts with the DAO interfaces for database operations. Below are the methods within the `QGSServiceImpl` class, along with their functionalities and interactions with DAO interfaces:

- `verifyCredentials(String loginId, String password)`:
  - **Functionality**: This method is used to verify the login credentials of users.
  - **Interactions**:
    - It interacts with the `IQGSDaoCommon` interface to retrieve the stored password associated with the provided login ID.
    - Compares the retrieved password with the entered password and returns true if they match.
    - Throws a custom `QGSException` if the login ID or password is blank or if the entered password is incorrect.

- `getRoleCode(String loginId)`:
  - **Functionality**: Retrieves the role code of a user based on their login ID.
  - **Interactions**:
    - Calls the `getRoleCode` method of the `IQGSDaoCommon` interface to fetch the user's role code from the database.
    - Returns the role code as a string.

- `createAccount(Accounts account)`:
  - **Functionality**: Inserts a new account into the database.
  - **Interactions**:
    - Utilizes the `IQGSDaoCommon` interface to create a new account by calling the `createAccount` method.
    - Validates the provided account details, such as insured name, street, city, state, and ZIP code, using various validation methods.
    - Ensures that the business segment name provided exists in the list of available business segments.
    - Throws a custom `QGSException` with specific error messages for validation failures or database insertion errors.

- `checkInsuredName(String insuredName)`:
  - **Functionality**: Validates the insured name entered during account creation.
  - **Interactions**:
    - Performs a regular expression validation to check if the insured name follows the pattern (First letter capital, maximum length of 30 characters).
    - Throws a custom `QGSException` with an error message for invalid insured name.

- `getPolicyQuestions(String busSegId)`:
  - **Functionality**: Retrieves policy questions based on the selected business segment ID.
  - **Interactions**:
    - Calls the `getPolicyQuestions` method of the `IQGSDaoCommon` interface to fetch policy questions from the database.
    - Returns a list of `PolicyQuestions` objects representing the questions.

- `checkInsuredStreet(String insuredStreet)`:
  - **Functionality**: Validates the street entered during account creation.
  - **Interactions**:
    - Performs a regular expression validation to check if the street name follows the pattern (Starts with a capital letter, maximum length of 40 characters).
    - Throws a custom `QGSException` with an error message for invalid street name.

- `checkCity(String insuredCity)`:
  - **Functionality**: Validates the city entered during account creation.
  - **Interactions**:
    - Performs a regular expression validation to check if the city name follows the pattern (Starts with a capital letter, maximum length of 15 characters).
    - Throws a custom `QGSException` with an error message for invalid city name.

- `checkState(String insuredState)`:
  - **Functionality**: Validates the state entered during account creation.
  - **Interactions**:
    - Performs a regular expression validation to check if the state name follows the pattern (Starts with a capital letter, maximum length of 15 characters).
    - Throws a custom `QGSException` with an error message for invalid state name.

- `checkInsuredZip(int insuredZip)`:
  - **Functionality**: Validates the ZIP code entered during account creation.
  - **Interactions**:
    - Performs a regular expression validation to check if the ZIP code consists of 5 digits.
    - Throws a custom `QGSException` with an error message for an invalid ZIP code.

- `getBusinessSegmentDetails()`:
  - **Functionality**: Retrieves a map containing business segment names and their corresponding IDs.
  - **Interactions**:
    - Calls the `getBusinessSegmentDetails` method of the `IQGSDaoCommon` interface to fetch business segment details from the database.
    - Returns a HashMap containing the business segment names as keys and their IDs as values.

- `validateBusinessSegmentName(String busSegName, Set<String> businessName)`:
  - **Functionality**: Validates the selected business segment name during account creation.
  - **Interactions**:
    - Checks if the provided business segment name exists in the set of available business segment names.
    - Throws a custom `QGSException` with an error message for an invalid business segment name.

- `InsertUser(UserRole userRole)`:
  - **Functionality**: Inserts a new user (admin, agent, or insured) into the system.
  - **Interactions**:
    - Utilizes the `IQGSDaoAdmin` interface to insert a new user by calling the `InsertUser` method.
    - Validates the provided user details, such as username, password, and role code, using various validation methods.
    - Throws a custom `QGSException` with specific error messages for validation failures or database insertion errors.

- `ValidateUserName(String userName)`:
  - **Functionality**: Validates the username during profile creation.

### 6. Database Configuration

The database connection setup in the QGS project is managed by the `JDBCUtility` class. This class handles the reading of properties from the `jdbc.properties` file and uses them to establish a connection to the database. Here's how the database connection is configured:

- `getConnection()`:
  - The `getConnection` method is responsible for establishing a database connection.
  - It reads the database configuration properties from the `jdbc.properties` file.
  - The following properties are read from the file:
    - `db.driver`: Specifies the JDBC driver class name.
    - `db.url`: Specifies the URL of the database.
    - `db.username`: Specifies the username for the database connection.
    - `db.password`: Specifies the password for the database connection.
  - The properties are read using a `FileInputStream` and loaded into a `Properties` object.
  - The JDBC driver class is loaded using `Class.forName(driver)`.
  - The `DriverManager.getConnection(url, username, password)` method is called to create a connection to the database.
  - The established database connection is returned.

This configuration ensures that the project can dynamically change its database connection details by modifying the `jdbc.properties` file without needing to recompile the code. It also centralizes the database configuration for easy maintenance and management. Any changes to the database properties can be made in the properties file, making it a flexible and scalable solution for connecting to different databases or modifying connection details as needed.

### 7. User Authentication

The user authentication process in the QGS project is managed by the `verifyCredentials` method in the `QGSServiceImpl` class. This method is responsible for validating login credentials and handling exceptions. Here's an explanation of the process:

**

Input Validation**:

- The method takes two parameters: `loginId` and `password`.
- It begins by checking if `loginId` or `password` is blank. If either of them is blank, it throws a custom `QGSException` with an appropriate error message.

**Checking Credentials**:

- The method calls the `getCredentials` method from the `commonDao` object to retrieve the stored password associated with the provided `loginId`.
- It compares the retrieved password with the password parameter provided by the user.
- If the provided password matches the stored password, the method sets `credentialFlag` to true, indicating successful authentication.

**Handling Exceptions**:

- If the provided password does not match the stored password, the method throws a custom `QGSException` with the message "Login Failed: entered wrong password."
- If any other exception occurs during the process, it is propagated as a `QGSException`.

This user authentication process ensures that the login credentials are validated, and the user is granted access to the system only if the credentials match those stored in the database. It also handles potential exceptions, providing informative error messages for various scenarios, such as blank inputs or incorrect passwords.


### 8. Account Creation

The account creation process in the QGS project is managed by methods in the `QGSServiceImpl` class, and it involves the use of the `Accounts` model class to represent account information. Here's an overview of the process:

**Input Validation:**

- The `createAccount` method in the `QGSServiceImpl` class takes an `Accounts` object as a parameter.
- Before creating an account, the method performs various input validations using helper methods:
  - `checkInsuredName`: Validates the insured name to ensure it starts with a capital letter and has a maximum length of 30 characters.
  - `checkInsuredStreet`: Validates the street name to ensure it starts with a capital letter and is less than 40 characters.
  - `checkCity`: Validates the city name to ensure it starts with a capital letter and is less than 15 characters.
  - `checkState`: Validates the state name to ensure it starts with a capital letter and is less than 15 characters.
  - `checkInsuredZip`: Validates the zip code to ensure it contains 5 digits.

**Business Segment Handling:**

- The `getBusinessSegmentDetails` method is used to fetch business segment details, which are stored in a map with business segment names as keys and IDs as values.
- The `validateBusinessSegmentName` method is responsible for validating the selected business segment name during account creation. It checks if the entered name matches any of the available business segments.

**Account Creation:**

- After successful input validation and business segment validation, the `createAccount` method calls the `createAccount` method in the DAO layer (commonDao) to insert the account information into the database.
- This account creation process ensures that the provided user data is valid and that the selected business segment exists before creating the account. It leverages model classes (e.g., `Accounts`) to represent and manage account information.

### 9. User Profile Creation

The QGS project allows the creation of user profiles, which includes validating usernames, passwords, and role codes. Here are the steps involved:

**Username Validation:**

- During the profile creation process, the system validates the uniqueness and format of the username.
- The `validateUserNameForAdminOrAgent` method in the `QGSServiceImpl` class handles this validation:
  - It checks if the provided username already exists in the system by calling the `validateUserNameForInsured` and `checkAccountHolder` methods.
  - If the username exists or if the user already has an account, it throws an exception with the message "This user already has an account."
  - If the username does not exist, it proceeds to validate the format of the username.

**Username Format Validation:**

- The system ensures that the username follows a specific format:
  - Length between 5 and 20 characters.
  - Contains at least one number along with letters.
- The `validateUserName` method in the `QGSServiceImpl` class handles this validation:
  - It checks the length and format of the provided username.
  - If the username format is not as per the specified rules, it throws an exception with the message "Username length must be between 5 and 20 and must contain at least one number along with letters."

**Password Validation:**

- The system enforces specific password requirements for user profiles.
- The `validatePassword` method in the `QGSServiceImpl` class ensures that the password meets the following criteria:
  - Length between 8 and 12 characters.
  - Contains at least one uppercase letter, one lowercase letter, one digit, and one special character among ($%_@).
- If the password format is not as per the specified rules, it throws an exception with the message "Password should contain at least one uppercase, one lowercase, one digit, and one special character among ($%_@)."

**Role Code Validation:**

- When creating a user profile, the system validates the role code to ensure it matches one of the predefined values (admin, insured, agent).
- The `validateRoleCode` method in the `QGSServiceImpl` class handles this validation:
  - It checks if the provided role code is either "admin," "insured," or "agent," and it is case-insensitive.
  - If the role code is not valid, it throws an exception with the message "Enter a valid role code."

By implementing these username, password, and role code validations, the QGS system ensures that user profiles are created with unique usernames, strong passwords, and valid role codes, enhancing security and data integrity.

### 10. Policy Creation

The QGS project allows the creation of policies, and this process involves the use of the `createPolicy` and `createPolicyDetails` methods. These methods use model classes (`Policy` and `PolicyDetails`) to represent and store policy-related information. Here's how policies are created:

**createPolicy Method:**

- The `createPolicy` method in the `QGSServiceImpl` class is responsible for creating a policy.
- It takes an instance of the `Policy` model class as a parameter, which contains information related to the policy, such as `businessSegment`, `policyPremium`, and `accountNumber`.
- This method interacts with the DAO layer (specifically, the `commonDao` object) to insert policy data into the database.
- The method returns an integer value that represents the status of the policy creation operation. It can be used to verify if the policy creation was successful.

**createPolicyDetails Method:**

- The `createPolicyDetails` method in the `QGSServiceImpl` class is responsible for creating policy details.
- It takes an instance of the `PolicyDetails` model class as a parameter, which contains detailed information about the policy, such as `policyEffectiveDate`, `policyEndDate`, and `policyTerm`.
- This method also interacts with the DAO layer (specifically, the `commonDao` object

) to insert policy details data into the database.
- The method returns a long value, which represents the policy number assigned to the newly created policy. This policy number can be used for reference.
- By utilizing the `Policy` and `PolicyDetails` model classes and coordinating with the DAO layer, the QGS system allows the creation of policies with associated details. This ensures that policy-related data is accurately stored in the database for future reference and management.

### 11. User Role Management

The QGS project manages user roles, including admin, agent, and insured, and allows for their insertion into the system. Here's how user roles are managed and inserted:

**User Role Management:**

- User roles are managed using the `UserRole` model class, which represents user information, including `userName`, `password`, and `roleCode`.
- The system provides functionality for inserting user roles into the system, which is typically performed by administrators or authorized personnel.
- User roles are differentiated based on their `roleCode`, which can be 'admin,' 'agent,' or 'insured.'

**Username Validation:**

- The system includes username validation to ensure that usernames meet specific criteria. This validation is performed in the `validateUserNameForAdminOrAgent` method within the `QGSServiceImpl` class.
- Valid usernames must be between 5 and 20 characters in length and contain at least one number along with letters.
- The validation ensures that usernames are unique and not already in use by other users.

**Checks for Existing Accounts:**

- Before inserting a user role into the system, the application checks whether the user already has an account. This verification is crucial to prevent duplicate accounts for the same user.
- The `checkAccountHolder` method in the `QGSServiceImpl` class is used to determine if a user has an existing account.
- If a user role is being created for an insured user, the system checks whether the user already has an account.
- If a user role is being created for an admin or agent, it ensures that the user does not already have an account.

### 12. Report Generation

The QGS project provides functionality for generating detailed policy reports for admin users. Here's how the report generation works:

**Generate Detailed Policy Reports:**

- Admin users have the ability to generate detailed policy reports, which provide comprehensive information about policies stored in the system.
- The `generateReport` method within the `QGSServiceImpl` class is responsible for generating these reports.
- The method takes parameters, including the account number and policy number, to specify the policies for which reports need to be generated.
- It interacts with the DAO layer to retrieve policy details and then generates a detailed report, which may include policy terms, premium amounts, and other relevant policy information.
- The generated report can be used for auditing, analysis, or other administrative purposes.

By implementing these features, the QGS project effectively manages user roles, ensures username uniqueness, and enables admin users to generate detailed policy reports for effective policy management and analysis.
