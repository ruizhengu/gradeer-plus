# gradeer-plus

<img src="./gradeer-plus-frontend/public/favicon.png" alt="Description" width="200">  

# Instructions

## 1. Requirements

### 1.1 Install RabbitMQ

Mac

```
brew install rabbitmq
```

## 1.2 Frontend Dependencies

```
cd gradeer-plus-frontend
npm install
```

## 2. Execution

### 2.1 Execute Frontend

```
cd gradeer-plus-frontend
npm run dev
```

### 2.2 Execute Backend

Run **gradeer-plus-backend** in Intellij.

### 2.3 Execute Client

Run **gradeer-client** in IntelliJ.

## 3. Interfaces

### 3.1 Marking Process

1. Go to tab **Markers**.
2. Go to the **Submissions** of an assignment.
3. Click **Load Submissions** to enter the path of the local folder that contains the submissions, e.g., /Users/ruizhengu/GTA/COM1003/2022-2023/Gradeer_manualMarking_calibration_master. (This action should be replaced by a **Download** function.)
4. Use the sliders to specify the mark of each check.
5. Click **Submit** to finish the marking of the current submission.