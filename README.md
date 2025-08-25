# Spring Boot
# Table of Contents

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