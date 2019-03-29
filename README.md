# About
This project is a proof of concept for a RESTful web service with Java (Jersey / JAX-RS), Maven and Thorntail.
Application stores screenshots in a dedicated directory which is located in the root directory.

# 1. Setup the environment on Mac OS X

1. Install Homebrew (https://brew.sh/).
2. Install Java environment (version 8 or higher).
  ```
  $ brew tap caskroom/versions
  $ brew cask install java8
  ```
3. Install Maven (version 3.6 or higher).
  ```
  $ brew install maven
  ```

# 2. Run

2.1. In terminal navigate to the project directory.
2.2. Execute the application using Maven:
  ```
  $ mvn thorntail:run
  ```
2.3. To access the application open http://localhost:8080/screenshots/
    You will see an xml representation of several screenshots.
2.4. To see how many screenshots are stored, call the http://localhost:8080/screenshots/count URL.
2.5. To see a screenshot with id 1, call the http://localhost:8080/screenshots/1 URL.
2.6. To see a screenshot with id 2, call the http://localhost:8080/screenshots/2 URL.
