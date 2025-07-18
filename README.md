# Java Quiz Application

## Project Overview
This project is a Quiz application built to adhere the Model-View-Controller 
(MVC) architectural pattern. It features a graphical user interface (GUI) 
created with the Swing framework, allowing users to interact with quizzes loaded 
from an XML file.

Users can select quizzes, answer multiple-choice questions, and receive 
immediate feedback on their answers. The application tracks the score during the 
quiz and presents the total at the end of the quiz.

---

## Prerequisites

- **Java Development Kit (JDK)**: Version **21**
- **Apache Maven**: Version **3.9.x** or later

---

## Build and Execution Guide
To execute the Quiz application, follow these steps:
1. Ensure that Maven is installed on your system.
2. Clone the repository containing the Quiz application source code.
3. Navigate to the root directory of the project.
4. Build the project using Maven by running the command: `mvn clean install`.
5. Once the build process is complete, locate the generated JAR file in the
   target directory.
6. Execute the JAR file by running the command: `java -jar <jar_filename>.jar`.
7. The Quiz application GUI should now be displayed, allowing you to interact.

---

## User Instructions 
1. Launch the Quiz application by running the executable JAR file.
2. Upon launching, the available quizzes are displayed.
3. Click on a quiz to view its preview
4. Start the quiz by clicking the "Start Quiz" button.
5. Answer each question by selecting one of the answer buttons.
6. After completing all questions, your total score is displayed.
7. You can exit the quiz anytime by clicking the "Exit" button.
8. For help, access the menu bar, click "Help" → "Instructions".
