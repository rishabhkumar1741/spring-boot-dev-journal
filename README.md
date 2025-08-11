# Spring Boot
# Table of Contents

- [✅ What is a Bean?](#-what-is-a-bean)
- [🔄 Lifecycle of a Bean in Spring](#-lifecycle-of-a-bean-in-spring)
- [✅ What is Dependency Injection?](#-what-is-dependency-injection)
- [🧩 @ConditionalOnProperty in Spring Boot](#-conditionalonproperty-in-spring-boot)
- [📦 @SpringBootApplication in Spring Boot](#-springbootapplication-in-spring-boot)




### ✅ What is a Bean?

A Bean in Spring is a Java object that is managed by the Spring IoC (Inversion of Control) container. It is created, configured, and wired by Spring.

### 🧪 How to Define a Bean?
1. Using @Component (Most Common)

```declarative
@Component
public class MyService {
    public void serve() {
        System.out.println("Service is running...");
    }
}
```
📌 Make sure to enable component scanning using @ComponentScan (optional in Spring Boot).

2. Using @Bean in a @Configuration class
```declarative
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

```declarative
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
```java
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

| Parameter       | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| `name`           | The property name to check (e.g. `"my.feature.enabled"`)                    |
| `havingValue`    | The expected value to match (e.g. `"true"`)                                 |
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



