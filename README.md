# 🚀 Priority Job Scheduler (Custom 3-ary Heap)

The **Priority Job Scheduler** is a console application in Java designed to manage and dispatch tasks based on their effective priority.  
Its core feature is a custom implementation of a **3-ary Max Heap**, demonstrating strong understanding of data structures, OOP principles, and exception handling.

---

## ✨ Key Features

- **Polymorphic Task Management:**  
  Includes `BugFix` and `FeatureRequest` classes inheriting from an abstract `Task` base class.

- **Dynamic Prioritization:**  
  `BugFix` tasks automatically get a priority boost (e.g., +20), ensuring that critical issues are handled before lower-impact tasks.

- **Queue Operations:**  
  - `PEEK` – preview the next task without removing it  
  - `POP` – complete and remove the highest-priority task

- **Custom Exceptions:**  
  Includes `InvalidTaskDataException` and `EmptyQueueException` for clean error control flow.

---

## 🛠️ Technology Stack

- **Language:** Java 17+
- **Build Tool:** Apache Maven
- **Testing:** JUnit 5 (Jupiter)
- **Data Structure:** Custom 3-ary Max Heap implementation

---

## 🚀 Getting Started (Local Setup)

This project uses Maven for building and dependency management.

---

### 1️⃣ Prerequisites

Make sure you have installed:

- **Java JDK 17 or later**
- **Apache Maven**  
  Check installation with:
  ```bash
  mvn -v
  ```
  
###2️⃣ Compile the Project
Open a terminal in the project root directory (where pom.xml is located) and run:
  ```bash
  mvn clean install
  ```

This will:
-Clean previous builds
-Compile the project
-Run all unit tests

###3️⃣ Run the Application
After building, run the console application using:
  ```bash
  java -cp target/priority-scheduler-1.0-SNAPSHOT.jar app.ConsoleApp
  ```

###🧪 Testing
All major functionality—including scheduler logic and the custom heap—is covered with JUnit 5 tests.
Run tests with:
  ```bash
  mvn test
  ```

###🧱 Project Structure
This project follows standard Maven directory layout:

PriorityJobScheduler/
├── pom.xml                   # Maven configuration
└── src/
    ├── main/
    │   └── java/
    │       ├── app/          # Main console application
    │       ├── logic/        # Scheduler logic (wrapper around the heap)
    │       ├── model/        # Task, BugFix, FeatureRequest
    │       ├── exceptions/   # Custom exceptions
    │       └── structures/   # MaxHeap interface & Array3Heap implementation
    └── test/
        └── java/
            └── tests/        # JUnit 5 tests (SchedulerTest.java)

###📌 Summary
The Priority Job Scheduler demonstrates:
Clean OOP design
A fully custom priority management system
Robust exception workflow
Comprehensive unit testing
Custom-built 3-ary max heap data structure
Perfect as a learning project or as a demonstration of data-structure-driven task management in Java.
