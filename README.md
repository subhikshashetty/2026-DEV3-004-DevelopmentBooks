# Development Books Kata - TDD with Spring Boot

This project is a **Test-Driven Development (TDD) Kata** built with **Java 21** and **Spring Boot**. It demonstrates the use of TDD principles to solve a pricing algorithm challenge using a RESTful API, along with integrated **Swagger UI** for testing.

---

## About this Kata

This short and simple Kata should be performed using **Test Driven Development (TDD)**.

There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books. The available books are :

1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. lean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively With Legacy Code (Michael C. Feathers, 2004)

### Rules

- One copy of the five books costs **50 EUR**.
- Discounts apply when **different titles** are bought together:
    - 2 different books = **5% discount**
    - 3 different books = **10% discount**
    - 4 different books = **20% discount**
    - 5 different books = **25% discount**

If books are repeated, they may be grouped in such a way that maximizes discounts.

#### 💡 Example

Basket:
- 2 × Clean Code
- 2 × The Clean Coder
- 2 × Clean Architecture
- 1 × TDD by Example
- 1 × Working Effectively with Legacy Code

Optimal grouping:
- Group 1: 4 books = 20% discount
- Group 2: 4 books = 20% discount

Calculation:
(4 × €50) - 20% = €160
(4 × €50) - 20% = €160
Total = €320


---

## Prerequisites

- Java 21
- Maven 3.x
- Spring Boot 4.0.2
- 
---


## How to Run
- First Clone the repository https://github.com/subhikshashetty/2026-DEV3-004-DevelopmentBooks.git

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

```
---

## API Testing with Swagger UI

- Once the application is running, open your browser and navigate to:
  - http://localhost:8080/swagger-ui.html

--- 

##  Testing the API with Postman

-   Once the application runming, we can test it using postman or the tool of your choice as per the below guidelines.

  - getTotalPrice() Service -
     This is POST Service where we can send our book request details and the response will be received with the total price and discounted price details for our purchase request

     HttpMethod : POST
     MediaType : JSON
     EndPoint : http://localhost:8080//api/bookprice/total

  - The sample request for this getTotalPrice are available under
  `src/main/resources`, at the root of the project.
   

--