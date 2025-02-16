# adl-katalon-api-testing

## Overview
This project is an API automation testing framework built using **Katalon Studio** for testing weather and pollution data from OpenWeather API. It is designed to validate API responses, verify data consistency, and generate reports for analysis.

## Prerequisites
Before running the tests, ensure you have the following installed:

- **Katalon Studio** (version 10.1.0 or later) – [Download Here](https://www.katalon.com/download/)
- **Git** (for version control) – [Download Here](https://git-scm.com/downloads)
- **Internet connection** (for API calls)
- **A valid OpenWeather API key** (if required)

## Project Structure
The project follows Katalon's standard structure:

```
📂 adl-katalon-api-testing
├── 📂 Data Files         # Stores external test data (if used)
├── 📂 Include           # Contains reusable scripts and configurations
├── 📂 Object Repository # Stores API request objects
├── 📂 Profiles          # Environment configurations (e.g., API keys, endpoints)
├── 📂 Scripts           # Custom test scripts (if needed)
├── 📂 Test Cases        # Individual API test cases
│   ├── 📄 Test_AirPollution_API
│   ├── 📄 Test_Weather_API
├── 📂 Test Suites       # Groups of test cases for execution
│   ├── 📄 Weather_Pollution_Test_Suite
├── 📂 settings          # Katalon settings and preferences
├── 📄 My First API Project.prj  # Katalon project file
├── 📄 Web Service Request.txt   # API request details
├── 📄 .gitignore        # Files to exclude from Git tracking
├── 📄 build.gradle      # Dependency management
├── 📄 console.properties # Console logging settings
└── 📄 README.md         # Project documentation
```

## How to Run the Tests
### **1. Open the Project in Katalon Studio**
1. Launch **Katalon Studio**.
2. Click **File > Open Project**.
3. Navigate to the cloned repository and select **My First API Project.prj**.

### **2. Execute Individual Test Cases**
![image](https://github.com/user-attachments/assets/0367389d-d7fc-4122-8ac9-30f280ecd0a6)
1. Go to **Test Cases** folder.
2. Select either **Test_AirPollution_API** or **Test_Weather_API**.
3. Click **Run** (▶) and choose **API/Web Service** as the execution mode.

### **3. Execute Test Suite**
![image](https://github.com/user-attachments/assets/49b4338a-f1a6-4aff-ac51-721e3b158e8c)
1. Go to **Test Suites** folder.
2. Select **Weather_Pollution_Test_Suite**.
3. Click **Run** (▶) to execute all test cases within the suite.

## Generating and Viewing Reports
### **1. Automated Report Generation**
After test execution, reports are automatically generated under the **Reports** folder with a timestamped subfolder:

```
📂 Reports
   ├── 📂 20250216_205454  # Example timestamp
       ├── 📄 Test_Suite_Summary.csv
       ├── 📄 execution0.log
       ├── 📄 JUnit_Report.xml
       ├── 📄 20250216_205454.html
```

### **2. Viewing Reports**
- Open the **.html** file in any browser to see a structured test execution report.
- The **.csv** file contains detailed results in tabular format.
- The **JUnit_Report.xml** can be used for CI/CD integrations.
![image](https://github.com/user-attachments/assets/31070cb4-3379-4bab-9630-a67316d6fc05)
You can check the latest test execution report [here](./Reports/20250216_224726.html).

## Troubleshooting
### **1. API Key Issues**
If requests fail due to authentication, check your API key in **Profiles > default**.

### **2. Network Issues**
Ensure your internet connection is stable and the OpenWeather API is reachable.

### **3. Git Authentication Issues**
If facing push/pull issues:
```sh
git credential reject https://github.com
```
Then re-authenticate using:
```sh
git push origin main
```

## Contribution Guidelines
- Fork the repository and create a feature branch.
- Commit changes with clear messages.
- Open a pull request for review.

---
📌 **Maintainer:** [@gemgempy](https://github.com/gemgempy)  
📌 **License:** MIT

