# Spring Boot
# Spring Boot MVC and RESTful APIs

- [✅ What is a Bean?](#-what-is-a-bean)
- [🔄 Lifecycle of a Bean in Spring](#-lifecycle-of-a-bean-in-spring)
- [✅ What is Dependency Injection?](#-what-is-dependency-injection)
- [🧩 @ConditionalOnProperty in Spring Boot](#-conditionalonproperty-in-spring-boot)
- [📦 @SpringBootApplication in Spring Boot](#-springbootapplication-in-spring-boot)
- [🌐 How Does a Web Server Work in Spring Boot?](#how-does-a-web-server-work-in-spring-boot)
- [🎨 Presentation Layer & REST Annotations](#-presentation-layer--rest-annotations)
  - [📌 Core REST Annotations](#-core-rest-annotations)
  - [🔀 Request Mappings](#request-mappings)
  - [🌍 Dynamic URLs Paths](#dynamic-urls-paths)
  - [📩 RequestBody](#requestbody)
- [🗄️ Persistence Layer & JPA](#-persistence-layer--jpa)
- [⚙️ Service Layer & Business Logic](#--service-layer--business-logic)
  - [📁 Repository Integration](#-repository-layer)
  - [🔄 DTO Conversion with ModelMapper](#-modelmapper--entity--dto)
  - [🧪 ReflectionUtils ](#-reflectionutils--reflectiontestutils)
- [✅ Input Validation in Spring Boot](#-input-validation-in-spring-boot)
  - [📦 1 Dependency: spring-boot-starter-validation](#-1-dependency-spring-boot-starter-validation)
  - [🧩 2 Common Validation Annotations](#-2-common-validation-annotations)
  - [📥 3. Validating Request Body](#-3-validating-request-body)
  - [🧪 4. Custom Validation](#-4-custom-validation)
- [🌐 Exception Handling in Spring Boot](#-exception-handling-in-spring-boot)
  - [Local Exception Handling (@ExceptionHandler inside controller)](#-1-local-exception-handling-exceptionhandler-inside-controller)
  - [Global Exception Handling (@RestControllerAdvice)](#-2-global-exception-handling-restcontrolleradvice)
- [📒 ResponseBodyAdvice in Spring Boot](#-responsebodyadvice-in-spring-boot)
  - [Benefits](#-benefits)
- [🧩 Full Example: Unified API Response (Success + Error)](#-full-example-unified-api-responses-with-responsebodyadvice)
  - [1️⃣ DTO Class](#1-dto-class)
  - [2️⃣ ApiResponse Wrapper](#2-apiresponse-wrapper)
  - [3️⃣ ErrorDetail Class](#3-errordetail-class)
  - [4️⃣ ErrorCode Enum](#4-errorcode-enum)
  - [5️⃣ GlobalExceptionHandler](#5-globalexceptionhandler)
  - [6️⃣ ResponseBodyAdvice Wrapper](#6-responsebodyadvice-wrapper)
  - [7️⃣ Controller Example](#7-controller-example)
  - [8️⃣ Example Outputs](#8-example-outputs)
# Hibernate and JPA
- 


### ✅ What is a Bean?

A Bean in Spring is a Java object that is managed by the Spring IoC (Inversion of Control) container. It is created, configured, and wired by Spring.

### 🧪 How to Define a Bean?
1. Using @Component (Most Common)

```java
@Component
public class MyService {
    public void serve() {
        System.out.println("Service is running...");
    }
}
```
📌 Make sure to enable component scanning using @ComponentScan (optional in Spring Boot).

2. Using @Bean in a @Configuration class
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```
3. Using XML (Old Style)
```
<bean id="myService" class="com.example.MyService"/>
 ```
🔍 Common Stereotype Annotations

| Annotation                     |            Usage            |       
|:-------------------------------|:---------------------------:|
| @Component                     |   **Generic Spring Bean**   | 
| @Service                       |    `Service layer logic`    |  
| @Repository                    |    _DAO/Database layer_     |  
| @Controller                    |  _Web layer (Spring MVC)_   | 
| RestController                 |    _Web API controller_     | 

### 📌 Summary

Spring manages beans for dependency injection and configuration.

You can create beans using @Component, @Bean, or XML.

Spring Boot auto-scans and registers components in the same or child packages.

## 🔄 Lifecycle of a Bean in Spring

✅ What is Bean Lifecycle?

The Bean Lifecycle is the complete process from creation to destruction of a bean, managed by the Spring IoC container.

### 🔁 Lifecycle Phases

| Phase                              |                                            Description                                             |       
|:-----------------------------------|:--------------------------------------------------------------------------------------------------:|
| 1️⃣ Instantiation                  |                      **Bean object is created (via constructor or factory).**                      | 
| 2️⃣ Dependency Injection           |                        `Spring injects dependencies using @Autowired, etc.`                        |  
| 3️⃣ Aware Interfaces               | _If the bean implements BeanNameAware, ApplicationContextAware, etc., Spring calls those methods._ |  
| 4️⃣ Pre-initialization             |                       _Any logic inside BeanPostProcessor before init runs._                       | 
| 5️⃣ Initialization                 |            _Calls methods like @PostConstruct, afterPropertiesSet() (if implemented)._             | 
| 6️⃣ Post-initialization            |                          _BeanPostProcessor logic after init completes._                           | 
| 7️⃣ Ready to Use                   |                              _Bean is fully initialized and in use._                               | 
| 8️⃣ Destruction                    |                  _When container shuts down, calls @PreDestroy, destroy(), etc._                   | 

🧪 Example

```java
@Component
public class MyBean implements InitializingBean, DisposableBean {

    @PostConstruct
    public void initMethod() {
        System.out.println("Bean is initialized - @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Bean is initialized - afterPropertiesSet");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean is being destroyed - @PreDestroy");
    }

    @Override
    public void destroy() {
        System.out.println("Bean is being destroyed - destroy()");
    }
}

```
💡 Note

In Spring Boot, destruction methods like @PreDestroy only work for singleton-scoped beans by default.

Lifecycle methods are helpful for initializing resources (e.g., DB connections) or cleanup (e.g., closing connections).

### ✅ What is Dependency Injection?

Dependency Injection (DI) is a design pattern where one object supplies the dependencies (objects it needs) of another object.

In Spring, DI allows Spring to manage and inject the required objects (beans) automatically, so you don’t need to manually create them using new.

🧪 Example Without DI
```java
public class Car {
    Engine engine = new Engine(); // Tightly coupled
}
```
🧪 Example With DI (Using Spring)
```java
@Component
public class Engine { }

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```
Spring will automatically inject the Engine bean into Car.

### 💡 Types of Dependency Injection in Spring

| Type                  | How it Works                                                        |
|-----------------------|---------------------------------------------------------------------|
| Constructor Injection | Dependencies are passed through the constructor (**Recommended**)   |
| Setter Injection      | Dependencies are set using setter methods                           |
| Field Injection       | Dependencies are injected directly into fields (**Less preferred**) |

✅ Constructor Injection (Best Practice)

```java
@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```
⚠️ When You Should Use @Autowired
- If the class has multiple constructors, and you want to tell Spring which one to use.
- 
- For setter or field injection, @Autowired is still required.

📌 Final Tip
For clean code, it's best to:
- Use constructor injection without @Autowired when you have only one constructor.
- Keep your beans as final, which works well with constructor injection.

✅ Setter Injection
```java
@Component
public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```
✅ Field Injection (Not Recommended)
```java
@Component
public class Car {

    @Autowired
    private Engine engine;
}
```
🧠 Why Use DI?
- Loose coupling between classes
- asier to test (can mock dependencies)
- Managed by Spring, not manually created
- Cleaner, maintainable code

## 🧩 @ConditionalOnProperty in Spring Boot

### ✅ What is @ConditionalOnProperty?
@ConditionalOnProperty is a Spring Boot conditional annotation used to enable or disable a bean based on a value in the application.properties or application.yml file.

### 📌 Use Case:

It helps you control whether a specific configuration or bean should be loaded, depending on a specific property value

### 🧪 Example
✅ Step 1: Add property in application.properties
```
my.feature.enabled=true
```
✅ Step 2: Use @ConditionalOnProperty in your config
```java
@Configuration
public class FeatureConfig {

    @Bean
    @ConditionalOnProperty(
        name = "my.feature.enabled",
        havingValue = "true",
        matchIfMissing = false // optional
    )
    public MyFeatureBean myFeatureBean() {
        return new MyFeatureBean();
    }
}
```
🟢 This bean will only be created if my.feature.enabled=true is present in properties.

### ⚙️ Key Parameters

| Parameter        | Description                                                                         |
|------------------|-------------------------------------------------------------------------------------|
| `name`           | The property name to check (e.g. `"my.feature.enabled"`)                            |
| `havingValue`    | The expected value to match (e.g. `"true"`)                                         |
| `matchIfMissing` | If `true`, bean will be created even if the property is missing. Default is `false` |

💡 Real-World Example
Useful for:
- Feature toggles
- Dev vs Prod environments
- Optional modules (like enabling a specific datasource or scheduler)

![internal_working_of_spring_boot ](src/main/resources/static/InternalWorkingOfSpringBoot.png)


## 📦 @SpringBootApplication in Spring Boot

@SpringBootApplication is a meta-annotation in Spring Boot.

It bundles three important annotations:

### 1️⃣ @Configuration
- Marks the class as a source of bean definitions for the Spring IoC container.
- You can define @Bean methods here.

### 2️⃣ @EnableAutoConfiguration
- Tells Spring Boot to enable auto-configuration.
- It looks at the classpath, existing beans, and properties to configure the application automatically.
- Driven by spring.factories and META-INF.

### 3️⃣ @ComponentScan
- Scans the package and its sub-packages for Spring-managed components (@Component, @Service, @Repository, @Controller, etc.).
- Without this, Spring wouldn’t find your beans automatically.

### 🧠 Why bundle them?
Instead of writing:
```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyApp { }

```
You just write:
```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```
![internal_working_of_spring_boot ](src/main/resources/static/server.png)

## How Does a Web Server Work in Spring Boot?
1. Client Request
   - The client sends an HTTP request (e.g., GET/POST) to the server.
   - The server responds with JSON data.

2. Tomcat (Embedded Web Server)
   - Tomcat receives the HTTP request.
   - It maps the request to the appropriate Servlet (in Spring Boot, it's usually DispatcherServlet).

3. DispatcherServlet (Router & Dispatcher)

    - Creates HttpServletRequest and HttpServletResponse objects.
    - Delegates the request to HandlerMapping (e.g., @GetMapping("/api/hello")).
    - Invokes the corresponding Controller method.
4. Controller
   - Handles the request and processes the business logic:
     - Validation
     - Authentication 
     - Business Logic 
     - Database Queries 
     - Returns a Java object or response data.
5. HttpMessageConverter
    - Converts the Java object to JSON (serialization) for the HTTP response.
6. Response to Client
   - The JSON response is sent back to the client via Tomcat.

# 🎨 Presentation Layer & REST Annotations

## 🖥 What is the Presentation Layer ?

The Presentation Layer is the part of your app that handles user interaction — either through web pages or APIs.

It takes requests ➡ sends them to the right business logic ➡ sends responses back.

💡 Think: Hotel receptionist — your first point of contact


Spring MVC provides an annotation-based programming model where
@Controller and @RestController components use annotations to express
request mappings, request input, exception handling, and more.


## 📌 Core REST Annotations

| Annotation        | Purpose / Usage                                                                          | Example                                                     |
|-------------------|------------------------------------------------------------------------------------------|-------------------------------------------------------------|
| `@RestController` | Combines `@Controller` and `@ResponseBody` — returns data (JSON/XML) directly, no views. | `@RestController public class MyApi {}`                     |
| `@ResponseBody`   | Places the return value directly into the HTTP response body. Used in REST APIs.         | `@GetMapping("/hi") public String hi() { return "Hello"; }` |
| `@RequestMapping` | Maps HTTP requests (any method) to handler methods; used at class or method level.       | `@RequestMapping("/users")`                                 |
| `@GetMapping`     | Handles HTTP GET requests (read data).                                                   | `@GetMapping("/users")`                                     |
| `@PostMapping`    | Handles HTTP POST requests (create data).                                                | `@PostMapping("/users")`                                    |
| `@PutMapping`     | Handles HTTP PUT requests (full update of a resource).                                   | `@PutMapping("/users/{id}")`                                |
| `@DeleteMapping`  | Handles HTTP DELETE requests (remove resource).                                          | `@DeleteMapping("/users/{id}")`                             |
| `@PatchMapping`   | Handles HTTP PATCH requests (partial update of a resource).                              | `@PatchMapping("/users/{id}")`                              |
| DTO               | **Data Transfer Object** — carries data between layers without exposing entities.        | `UserDTO { name, email }`                                   |
| `@PathVariable`   | Extracts values from URL path segments.                                                  | `/users/101` → `@PathVariable int id`                       |
| `@RequestParam`   | Extracts query parameters from URL.                                                      | `/users?sort=asc` → `@RequestParam String sort`             |
| `@RequestBody`    | Maps JSON/XML request body to a Java object.                                             | `@PostMapping UserDTO user`                                 |

## Request Mappings

You can use the @RequestMapping annotation to map requests to
controllers methods. It has various attributes to match by URL, HTTP
method, request parameters, headers, and media types.

There are also HTTP method specific shortcut variants of
@RequestMapping:

- @GetMapping
- @PostMapping
- @PutMapping
- @DeleteMapping
- @PatchMapping

## Dynamic URLs Paths

| @PathVariable                                                                                          | @RequestParam                                                                                                              |
|--------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| `/employees/123`                                                                                       | /employees?id=123                                                                                                          |
| Use path variables when the parameter is an essential part of the URL path that identifies a resource. | Use query parameters when the parameter is optional and usedfor filtering, sorting, or other modifications to the request. |

## RequestBody

@RequestBody is used to bind the HTTP request body to a Java object.
When a client sends data in the body of a request (e.g., JSON or XML),
@RequestBody maps this data to a Java object.

Use Case:

- Typically used in POST, PUT, and PATCH methods where the client
sends data that needs to be processed by the server.
-  Converts JSON or XML data from the request body into a Java object
using a message converter (e.g., Jackson for JSON).

### 🛠 Example

````java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "User ID: " + id;
    }

    @GetMapping
    public String getUsers(@RequestParam(defaultValue = "asc") String sort) {
        return "Sorting: " + sort;
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO user) {
        return "Created user: " + user.getName();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        return "Updated user " + id + " to name: " + user.getName();
    }

    @PatchMapping("/{id}")
    public String partiallyUpdateUser(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        return "Partial update for " + id + ": " + updates;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return "Deleted user " + id;
    }
}
````

✅ Best Practices
- Keep controllers slim — push logic to Service layer.
- Use DTOs instead of exposing entity models.
- Validate inputs with @Valid and error handling.
- Stick to RESTful naming: /users, /users/{id}, /users?filter=value.


## 🗄️ Persistence Layer & JPA

### 📦 What is the Persistence Layer?

The Persistence Layer is responsible for storing, retrieving, and managing data in your application.

It interacts with the database using tools like JPA (Java Persistence API) and ORM frameworks like Hibernate.

##### 💡 Think of it as the warehouse of your app — where all the data lives and gets organized.

### 🔧 Key Component

## 🔧 Key Components – Persistence Layer

| Component        | Role                                                                        |
|------------------|-----------------------------------------------------------------------------|
| **JPA**          | Specification for ORM in Java. Defines how Java objects map to DB tables.   |
| **Entity**       | A Java class annotated with `@Entity` that represents a DB table.           |
| **Repository**   | Interface that provides CRUD operations using Spring Data JPA.              |
| **JPA Provider** | Implementation of JPA (e.g., Hibernate) that handles actual DB interaction. |

🧱 Entity Example

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Getters and setters
}
```
- @Entity → Marks this class as a DB table.
- @Id → Primary key.
- @GeneratedValue → Auto-generates ID.
- @Table(name = "users") → Optional: maps to specific table name

### 📁 Repository Example
```java
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods
    List<User> findByName(String name);
}
```
- JpaRepository<Entity, ID> → Gives you CRUD methods out of the box.
- You can define custom queries like findByEmail, findByNameContaining, etc

🔄 How It All Connects
```text
Controller → Service → Repository → Database
```
- Controller handles user/API requests.
- Service contains business logic.
- Repository interacts with the database using JPA.

✅ Best Practices
- Keep entities clean — no business logic.
- Use DTOs to transfer data between layers.
- Prefer constructor-based injection in services.
- Use Spring Data JPA for rapid development — it handles most boilerplate.


## ⚙️ ⚙️ Service Layer & Business Logic

### 🧩 What is the Service Layer

The Service Layer contains your business logic — it’s the brain of your application.
It sits between the Controller (which handles requests) and the Repository (which talks to the database).

💡 Think of it as the operations team: the controller says “do this,” and the service figures out how

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return modelMapper.map(user, UserDTO.class);
    }
}
```
- Annotated with @Service
- Calls repository methods
- Returns DTOs or domain objects
### 📁 Repository Layer

The Repository Layer interacts directly with the database using Spring Data JPA.
```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```
- Extends JpaRepository<Entity, ID>
- Auto-generates CRUD methods
- Supports custom queries like findByEmail, findByAgeGreaterThan

### 📦 ResponseEntity

Used in the Controller Layer to return structured HTTP responses.
```java
@GetMapping("/{id}")
public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    UserDTO user = userService.getUserById(id);
    return ResponseEntity.ok(user);
}
```
- Controls status code, headers, and body
- Common patterns: ok(), status(), noContent(), created()

### 🔄 ModelMapper – Entity ↔ DTO
ModelMapper is a library that automatically maps between objects (e.g., Entity ↔ DTO).

```java
@Autowired
private ModelMapper modelMapper;

UserDTO dto = modelMapper.map(user, UserDTO.class);
User user = modelMapper.map(dto, User.class);
```
- Reduces boilerplate mapping code
- Can be customized for nested or mismatched fields
- Add to your config

```java
@Bean
public ModelMapper modelMapper() {
    return new ModelMapper();
}
```

### 🧪 ReflectionUtils / ReflectionTestUtils
Used in unit testing to access or modify private fields/methods.
```text
User user = new User();
ReflectionTestUtils.setField(user, "email", "test@example.com");

String result = ReflectionTestUtils.invokeMethod(user, "privateMethodName");
```
- Useful for testing classes with private fields/methods
- Can inject mocks into private fields
- Part of spring-test module

✅ Best Practices
- Keep Service Layer focused on logic, not HTTP.
- Use DTOs to isolate domain models from API contracts.
- Let Controller handle ResponseEntity, not Service.
- Use ModelMapper for clean object conversion.
- Use ReflectionTestUtils only in tests — not in production code.

## ✅ Input Validation in Spring Boot

### 📦 1. Dependency: spring-boot-starter-validation
Add this to your pom.xml to enable Bean Validation using Hibernate Validator:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
- Automatically integrates with Spring Boot
- Enables annotations like @NotNull, @Size, @Email, etc.
- Works with @Valid and @Validated in controllers an

### 🧩 2. Common Validation Annotations

| 🏷️ Annotation  | 📌 Purpose                                  | 🧪 Example Usage                      |
|-----------------|---------------------------------------------|---------------------------------------|
| `@NotNull`      | Field must not be `null`                    | `@NotNull private String name;`       |
| `@NotBlank`     | Must not be empty or whitespace             | `@NotBlank private String username;`  |
| `@NotEmpty`     | Must not be empty (for collections/strings) | `@NotEmpty List<String> tags;`        |
| `@Size`         | Length or size constraints                  | `@Size(min=2, max=30)`                |
| `@Email`        | Valid email format                          | `@Email private String email;`        |
| `@Min` / `@Max` | Numeric range                               | `@Min(18) @Max(99)`                   |
| `@Pattern`      | Regex match                                 | `@Pattern(regexp="\\d{10}")`          |
| `@Positive`     | Must be > 0                                 | `@Positive private int quantity;`     |
| `@Negative`     | Must be < 0                                 | `@Negative private int offset;`       |
| `@AssertTrue`   | Must be `true`                              | `@AssertTrue private boolean agreed;` |
| `@AssertFalse`  | Must be `false`                             | `@AssertFalse private boolean error;` |
| `@Future`       | Date must be in the future                  | `@Future private LocalDate expiry;`   |
| `@Past`         | Date must be in the past                    | `@Past private LocalDate dob;`        |

Use these in DTOs or request models to enforce field-level constraints.

### 📥 3. Validating Request Body
```java
@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO user) {
    // If validation fails, Spring auto-returns 400 Bad Request
    return ResponseEntity.ok("User registered");
}
```
- @Valid triggers validation on incoming request body
- Errors are handled automatically unless overridden

### 🧪 4. Custom Validation
✅ Step 1: Create Custom Annotation
```java
@Documented
@Constraint(validatedBy = IpAddressValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIp {
    String message() default "Invalid IP address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```
✅ Step 2: Create Validator Class
```java
public class IpAddressValidator implements ConstraintValidator<ValidIp, String> {
    @Override
    public boolean isValid(String ip, ConstraintValidatorContext context) {
        return ip != null && ip.matches("^(\\d{1,3}\\.){3}\\d{1,3}$");
    }
}
```
✅ Step 3: Apply in DTO
```java
public class DeviceDTO {
    @ValidIp
    private String ipAddress;
}
```
### 🧠 5. `@Validated` vs `@Valid`

| 🏷️ Annotation | 🔍 Scope / Target                     | ⚙️ Use Case / Behavior                                                     |
|----------------|---------------------------------------|----------------------------------------------------------------------------|
| `@Valid`       | Method parameters, fields             | Triggers validation on request bodies, nested objects, and fields in DTOs  |
| `@Validated`   | Class-level (e.g., on service)        | Enables method-level validation and supports validation groups             |
|                |                                       | Required for validating method parameters in service or controller methods |
| ✅ Key Point    | `@Valid` is simpler and field-focused | `@Validated` is more flexible and supports advanced scenarios like groups  |


✅ Best Practices
-  	Use DTOs for validation — don’t annotate entities directly.
- 	Keep custom validators reusable and well-documented.
- 	Use  for centralized error handling.
- 	Prefer  for request bodies,  for method-level checks.


## 🌐 Exception Handling in Spring Boot
-  Why Exception Handling?
- Prevents raw stack traces from leaking to clients.
- Returns clean, structured, user-friendly error responses.
- Keeps code centralized & maintainable.

#### Example:
If you request /employees/99 and employee doesn’t exist:

❌ Bad:
```text
java.util.NoSuchElementException: No value present
   at java.util.Optional.get(Optional.java:133)
   ...
```
✅ Good:
```JSON
{
  "success": false,
  "code": "NOT_FOUND",
  "message": "Employee not found with id 99",
  "path": "/api/employees/99",
  "timestamp": "2025-09-01T10:15:00Z"
}
```
### 🔹 1. Local Exception Handling (@ExceptionHandler inside controller)
```java
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        if (id == 99) {
            throw new NoSuchElementException("Employee not found with id " + id);
        }
        return new EmployeeDTO(id, "John Doe", "Developer");
    }

    // Handles exceptions ONLY in this controller
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```
👉 Here, if a **NoSuchElementException** is thrown in this controller, the method **handleNotFound()** will run.

- Works only inside this controller.
- If you want global handling, this becomes repetitive.
###  🔹 2. Global Exception Handling (@RestControllerAdvice)
@RestControllerAdvice = @ControllerAdvice + @ResponseBody

- Applies to all controllers in your app.
- Lets you define centralized error handling logic.

Example:
```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(NoSuchElementException exception, HttpServletRequest req)
    {
        ApiResponse<Void> body = ApiResponse.error(ErrorCode.NOT_FOUND.name(),exception.getMessage(),null);
        body.setPath(req.getRequestURI());
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleallexceptions(MethodArgumentNotValidException e, HttpServletRequest req)
    {
        List<ErrorDetail> details = e.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ErrorDetail(err.getField(),err.getDefaultMessage(),err.getCode()))
                .toList();
        ApiResponse<Void> body = ApiResponse.error(ErrorCode.VALIDATION_ERROR.name(), "Invalid input provided",details);
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

}

```
👉 Now all controllers benefit from the same error handling.
No need to repeat @ExceptionHandler everywhere.

### 🔹 How Spring decides which handler to use

- If an exception happens in a controller:
  - First checks for a local @ExceptionHandler.
  - If none → looks in global @RestControllerAdvice.
  - If none → returns Spring’s default error JSON

🔹 Example Flow

1. Request: GET /api/employees/99
2. Controller throws NoSuchElementException.
3. Spring looks for @ExceptionHandler(NoSuchElementException.class).
   - If found in controller → executes it.
   - Else → executes global handler (@RestControllerAdvice).
4. Response returned in JSON format

## 📒 ResponseBodyAdvice in Spring Boot

### 🔹 What is ResponseBodyAdvice?
- An interceptor provided by Spring MVC.
- It allows you to customize or wrap the response body before it’s sent to the client.
- Commonly used for:
  - Wrapping all API responses in a standard format (e.g., ApiResponse).
  - Logging responses.
  - Encrypting/masking data before sending.
### 🔹 Example Implementation

```java
@RestControllerAdvice
public class ApiResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, 
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // Apply to all responses except when it's already ApiResponse
        return !returnType.getParameterType().equals(ApiResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // Skip if already wrapped by error handler
        if (body instanceof ApiResponse) return body;

        // Wrap normal success responses
        ApiResponse<Object> res = ApiResponse.success(body, "Request successful");
        res.setPath(((ServletServerHttpRequest) request).getServletRequest().getRequestURI());
        res.setTimestamp(OffsetDateTime.now());
        return res;
    }
}
```
### 🔹 Example Controller
```java
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @PostMapping
    public EmployeeDTO postEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        // Normally you'd save to DB; here we just return the DTO
        return employeeDTO;
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        if (id == 99) throw new NoSuchElementException("Employee not found with id " + id);
        return new EmployeeDTO(id, "John Doe", "Developer");
    }
}
```
### 🔹 Example Outputs
✅ Success Response (POST /api/employees)

Controller returned raw EmployeeDTO, but ResponseBodyAdvice wrapped it:
```json
{
    "success": true,
    "code": "OK",
    "message": "Request successful",
    "data": {
        "name": "Mr. Ora Strosin",
        "role": "USER",
        "email": "Meta.Dickens47@hotmail.com",
        "active": false,
        "phoneNumber": 9910561642
    },
    "path": "/v1/employees",
    "timestamp": "2025-09-02T17:11:05.7626731+05:30"
}
```
❌ Error Response (GET /api/employees/99)

Exception is caught by @RestControllerAdvice (GlobalExceptionHandler), so no wrapping happens:

```json
{
    "success": false,
    "code": "VALIDATION_ERROR",
    "message": "Invalid input provided",
    "errors": [
        {
            "field": "role",
            "message": "Role of Employee can either be USER OR ADMIN",
            "code": "EmployeeRoleValidation"
        },
        {
            "field": "phoneNumber",
            "message": "must be greater than or equal to 1000000000",
            "code": "Min"
        }
    ],
    "timestamp": "2025-09-02T17:11:39.9791358+05:30"
}
```
### 🔹 Benefits
- Ensures every response (success/error) has the same format.
- Controllers remain clean → they only return DTOs.
- Frontend always receives a predictable JSON structure.

# 🧩 Full Example: Unified API Responses with ResponseBodyAdvice
- A EmployeeDTO class.
- A ApiResponse wrapper.
- ErrorDetail + ErrorCode.
- A GlobalExceptionHandler (@RestControllerAdvice).
- A ResponseBodyAdvice (ApiResponseWrapper).
- A EmployeeController
##  1️⃣ DTO Class
```java
// package com.example.demo.employee.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EmployeeDTO {
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    public EmployeeDTO() {}

    public EmployeeDTO(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    // getters & setters
}
```
### 2️⃣ ApiResponse Wrapper
```java
// package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;
    private List<ErrorDetail> errors;
    private String path;
    private OffsetDateTime timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = true;
        res.code = "OK";
        res.message = (message != null ? message : "Request successful");
        res.data = data;
        res.timestamp = OffsetDateTime.now();
        return res;
    }

    public static <T> ApiResponse<T> error(String code, String message, List<ErrorDetail> errors) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = false;
        res.code = code;
        res.message = message;
        res.errors = errors;
        res.timestamp = OffsetDateTime.now();
        return res;
    }
    // getters & setters
    public void setPath(String path) { this.path = path; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
}

```
### 3️⃣ ErrorDetail Class
```java
// package com.example.demo.api.model;

public class ErrorDetail {
    private String field;
    private String message;
    private String code;

    public ErrorDetail(String field, String message, String code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }

    // getters & setters
}
```
### 4️⃣ ErrorCode Enum
```java
// package com.example.demo.api.model;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    OK(HttpStatus.OK, "Request successful"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation failed"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");

    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ErrorCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
    public String getDefaultMessage() { return defaultMessage; }
}
```
### 5️⃣ GlobalExceptionHandler
```java
// package com.example.demo.api.error;

import com.example.demo.api.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoSuchElementException ex, HttpServletRequest req) {
        ApiResponse<Void> body = ApiResponse.error(
                ErrorCode.NOT_FOUND.name(),
                ex.getMessage(),
                null
        );
        body.setPath(req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ErrorDetail(err.getField(), err.getDefaultMessage(), err.getCode()))
                .collect(Collectors.toList());

        ApiResponse<Void> body = ApiResponse.error(
                ErrorCode.VALIDATION_ERROR.name(),
                "Invalid input provided",
                details
        );
        body.setPath(req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex, HttpServletRequest req) {
        ApiResponse<Void> body = ApiResponse.error(
                ErrorCode.INTERNAL_ERROR.name(),
                ex.getMessage(),
                null
        );
        body.setPath(req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

```
### 6️⃣ ResponseBodyAdvice Wrapper
```java
// package com.example.demo.api.advice;

import com.example.demo.api.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(ApiResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof ApiResponse) return body;

        ApiResponse<Object> res = ApiResponse.success(body, "Request successful");
        res.setPath(((ServletServerHttpRequest) request).getServletRequest().getRequestURI());
        res.setTimestamp(OffsetDateTime.now());
        return res;
    }
}

```
### 7️⃣ Controller Example
```java
// package com.example.demo.employee.controller;

import com.example.demo.employee.dto.EmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        if (id == 99) throw new NoSuchElementException("Employee not found with id " + id);
        return new EmployeeDTO(id, "John Doe", "Developer");
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody EmployeeDTO employeeDTO) {
        // here normally you'd save employee
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }
}

```
### 8️⃣ Example Outputs
✅ Success – GET /api/employees/1
```json
{
  "success": true,
  "code": "OK",
  "message": "Request successful",
  "data": {
    "id": 1,
    "name": "John Doe",
    "role": "Developer",
    "age": 0
  },
  "path": "/api/employees/1",
  "timestamp": "2025-08-31T12:00:00Z"
}
```
❌ Error – GET /api/employees/99
```json
{
  "success": false,
  "code": "NOT_FOUND",
  "message": "Employee not found with id 99",
  "path": "/api/employees/99",
  "timestamp": "2025-08-31T12:01:00Z"
}

```
❌ Error – Validation (POST without name)
```json
{
  "success": false,
  "code": "VALIDATION_ERROR",
  "message": "Invalid input provided",
  "errors": [
    { "field": "name", "message": "Name is mandatory", "code": "NotBlank" }
  ],
  "path": "/api/employees",
  "timestamp": "2025-08-31T12:02:00Z"
}
```

## Hibernate and JPA
#### Hibernate ORM Mapping

![rishabh](src/main/resources/static/JPA1.png)
![rishabh](src/main/resources/static/JPA2.png)

## Hibernate

Hibernate is a powerful, high-performance Object-Relational Mapping
(ORM) framework that is widely used with Java. It provides a framework
for mapping an object-oriented domain model to a relational database.

Hibernate is one of the implementations of the Java Persistence API
(JPA), which is a standard specification for ORM in Java.

## JPA (Java Persistence API)

JPA is a specification for object-relational mapping (ORM) in Java.

It defines a set of interfaces and annotations for mapping Java
objects to database tables and vice versa.

JPA itself is just a set of guidelines and does not provide any
implementation. The implementation of JPA is provided by ORM
frameworks such as Hibernate, EclipseLink, and OpenJPA.

### So Basically…
JPA Provides a standard for ORM in Java applications, ensuring that
developers can switch between different JPA providers without changing
their code.  

And

Hibernate is one such JPA Provider.

However,

Hibernate is a specific implementation of JPA and a powerful ORM
framework on its own. It offers additional features and optimizations
beyond the JPA specification, making it a popular choice for ORM in
Java applications.

## Common Hibernate Configurations
Hibernate needs some settings to work with the database.
Usually written in application.properties (Spring Boot) or hibernate.cfg.xml (core Hibernate).

Example (Spring Boot way):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update/create/validate/createdrop/none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL
   5Dialect (Optional)
```
- hibernate.dialect → tells Hibernate which SQL flavor to use (MySQL, Oracle, Postgres, etc).

- ddl-auto → controls table creation:

  - create → drops old and creates new tables every time.
  - update → updates schema (recommended for dev).
  - validate → only checks schema, doesn’t change.
  - none → no action.

## Entity Annotation
`Marks a Java class as a JPA entity (i.e., it maps to a DB table).`

`Without @Entity, Hibernate ignores the class.`
- @Entity
- @Table
- @Id
- @GeneratedValue(strategy = GenerationType.IDENTITY)
- @Column(name = "name", nullable = false, length = 50)
- @CreationTimestamp and @UpdateTimestamp

#### Table Annotation
```java
@Table(
name = "employees",
catalog = "employee_catalog",
schema = "hr",
uniqueConstraints = {
@UniqueConstraint(columnNames = {"email"})
},
indexes = {
@Index(name = "idx_name", columnList = "name"),
@Index(name = "idx_department", columnList = "department")
}
)
```

### Key features of JPA
1. Entity Management: Defines how entities (Java objects) are
   persisted to the database.
2. Query Language: Provides JPQL (Java Persistence Query Language) for
   querying entities.
3. Transactions: Manages transactions, making it easier to handle
   database operations within a transactional context.
4. Entity Relationships: Supports defining relationships between
   entities (e.g., One-to-One, One-to-Many, Many-to-One, Many-toMany).