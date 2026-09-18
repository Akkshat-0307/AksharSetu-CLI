# AksharSetu-CLI: Adaptive Learning & Assessment Tracker

## Overview
AksharSetu-CLI is a terminal-based educational diagnostic engine built entirely in Java. It is designed to evaluate student performance in real-time, dynamically identify specific learning gaps based on assessment responses, and generate targeted study recommendations without requiring a graphical interface.

## Features
* **Role-Based Access:** Distinct terminal workflows for Mentors and Students.
* **Diagnostic Analytics Engine:** Maps incorrect quiz responses to specific foundational topics using Java Collections.
* **Concurrent Load Simulation:** Utilizes Java multi-threading to simulate batch processing of multiple student assessments simultaneously.
* **Local Report Export:** Implements File I/O to automatically generate downloadable `.txt` diagnostic reports.
* **Resilient Terminal UI:** Robust Exception Handling ensures invalid user inputs do not crash the application loop.

## Technologies Used
* **Language:** Java (JDK 17+)
* **Core Concepts:** OOP, Collections Framework, Exception Handling, File I/O, Concurrency, and JDBC.
* **Database:** SQLite via JDBC

## Steps to Install & Run
*Assume a macOS/Linux terminal environment.*

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Akkshat-0307/AksharSetu-CLI.git](https://github.com/Akkshat-0307/AksharSetu-CLI.git)
   cd AksharSetu-CLI
2. **Compile the Java source files:**
   ```bash
   javac -cp "lib/*" -d bin src/**/*.java src/*.java
3. **Execute the application:**
```bash
   java -cp "bin:lib/*" Main

##Instructions for Testing
 
 Launch the application and s elect 1. Login as Student.
 Enter your name and take the diagnostic assessment by entering the numeric choice for each question.
 Upon completion, verify that a new .txt file containing the learning gap analysis has been created in the data/ directory.
 Restart the application and select 2. Mentor Login to trigger the multi-threaded batch processing simulation. Observe the concurrent terminal output.

##Screenshots
![alt text](image.png)
![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)
