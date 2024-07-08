# Task List

A simple task list tracker

## Description

This projects purpose is for a household to have each person have their own tab with their own given tasks via a list.

## Getting Started

### Dependencies

* Node.js version 20.11.1 or higher
* MongoDB
* Express
* React
* Npm
* Vite
* Mongoose
* Nodemon
* Axios
* React-dom
* React-router-dom
* BootStrap


### Executing program (VS Code)

* Create your own .env file within the server file
* create your own MongoDB server and copy its unique connection string
* .env file should resemble the following 
```
PORT=9999
MONGODB_URI=mongodb+srv://<your_username>:<your_password>@<host>/?retryWrites=true&w=majority&appName=<your_cluster>
```
* Open two seperate terminals
    * Terminal one
        * cd into the **server** file path 
            * ex. C:\Users\userName\Documents\taskList\server
        * Install dependencies with this terminal for the **server**
            ```
            npm i
            ```
        * Run **server** side to connect to your database
            ```
            npm run dev
            ```
        * should see "✔ Successfully connected to database: dataBase name"
    * Terminal two
        * cd into the **client** file path
            * ex. C:\Users\userName\Documents\taskList\client
            ```
            npm i
            ```
        * Run **client** side to connect to your local host
            ```
            npm run dev
            ```
        * should see "http://localhost:5173/" as a clickable link


## Authors

Seth Ozaki


## Version History

* 0.1
    * Initial Release

