# 🚀 Priority Job Scheduler (Custom 3-ary Heap)

The **Priority Job Scheduler** is a console application in Java designed to manage and dispatch tasks based on their effective priority.  
Its core feature is a custom implementation of a **3-ary Max Heap**, demonstrating strong understanding of data structures, OOP principles, and exception handling.

---

## ✨ Key Features

- **Polymorphic Task Management:**  
  Includes `BugFix` and `FeatureRequest` classes inheriting from an abstract `Task` base class.

- **Dynamic Prioritization:**  
  `BugFix` tasks automatically get a priority boost, ensuring that critical issues are handled before lower-impact tasks.

- **Queue Operations:**  
  - `PEEK` – preview the next task without removing it  
  - `POP` – complete and remove the highest-priority task

- **Custom Exceptions:**  
  Includes `InvalidTaskDataException` and `EmptyQueueException` for clean error control flow.

---

## 🧠 Data Structure Logic: 3-ary Max Heap

Standard heaps are typically **binary** (2 children per node). This project implements a **3-ary Heap** (d-ary heap where d=3), which offers specific performance trade-offs and structural differences.

### How it works?
In a 3-ary heap, the tree is **flatter** than a binary heap because each node can hold up to 3 children. This reduces the height of the tree ($\log_3 n$ vs $\log_2 n$).

### Array Representation Logic
Since the heap is implemented using a flat array, we use specific formulas to navigate relations between nodes:

| Relation | Formula (for index `i`) |
| :--- | :--- |
| **Parent** | `(i - 1) / 3` |
| **Child 1** | `3 * i + 1` |
| **Child 2** | `3 * i + 2` |
| **Child 3** | `3 * i + 3` |

### Performance Trade-offs
- **✅ Faster Insertions (Push):** Due to the lower tree height, "bubbling up" a new element requires fewer comparisons.
- **⚠️ Slower Deletions (Pop):** "Bubbling down" is slightly more complex, as the algorithm must compare 3 children (instead of 2) to find the largest one to swap with.

---

## 🛠️ Technology Stack

- **Language:** Java 17+
- **Build Tool:** Apache Maven
- **Testing:** JUnit 5 (Jupiter)
- **Data Structure:** Custom 3-ary Max Heap implementation

---

## 🧱 Project Structure
This project follows standard Maven directory layout:

  ```bash
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
  ```

---

## 🚀 Getting Started (Local Setup)

This project uses Maven for building and dependency management.


### 1️⃣ Prerequisites

Make sure you have installed:

- **Java JDK 17 or later**
- **Apache Maven**  
  Check installation with:
  ```bash
  mvn -v
  ```
  
### 2️⃣ Compile the Project
Open a terminal in the project root directory (where pom.xml is located) and run:
  ```bash
  mvn clean install
  ```

This will:
-Clean previous builds
-Compile the project
-Run all unit tests

### 3️⃣ Run the Application
After building, run the console application using:
  ```bash
  java -cp target/priority-scheduler-1.0-SNAPSHOT.jar app.ConsoleApp
  ```

---

## 🧪 Testing
All major functionality—including scheduler logic and the custom heap—is covered with JUnit 5 tests.
Run tests with:
  ```bash
  mvn test
  ```
