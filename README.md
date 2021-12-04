# Contact Management System

This project is basically me learning JavaFx and practicing by adding features to an existing project by [Zaid Careem](https://github.com/zaidcareem).

## Functionalities:

- Sign up
- Login
- Delete user
- Change Password
- Add contacts
- Display contacts
- Edit contacts
- Delete contacts
- Delete all contacts

## My Configuration

- IntelliJ IDEA Community - v2021.3
- SceneBuilder - v17.0.0
- Java OpenJDK - v17.0.1
- JavaFX SDK - v17.0.1
- MySQL - v3+
- MySQL Connector Java - v8.0.27
- VM options:
  ```
  --module-path <Path to JavaFX Libraries>
  
  --add-modules=javafx.graphics, javafx.controls, javafx.media, javafx.base, javafx.web, javafx.swing, javafx.fxml, java.sql
  
  -Dprism.verbose=true
  
  ```
- Main class:
  ```
  app.Main
  ```
- MySQL queries to setup database: ([createDatabase.sql](./sql/createDatabase.sql))
  ```
  CREATE DATABASE cms;

  CREATE TABLE cms.users (username varchar(20), password varchar(20));

  CREATE TABLE cms.contacts (name varchar(20), number varchar(15), username varchar(20));
  ```

## Folder structure

```
root
├── sql
|   └── createDatabase.sql
|   
├── src
|   ├── app
|   |   ├── Alerts.java
|   |   ├── ChangeView.java
|   |   ├── Database.java
|   |   ├── Main.java
|   |   └── UserSession.java
|   |
|   ├── controllers
|   |   ├── AddContactController.java
|   |   ├── ContactsController.java
|   |   ├── DeleteUserController.java
|   |   ├── EditContactController.java
|   |   ├── HomePageController.java
|   |   ├── LoginController.java
|   |   ├── PasswordChangeController.java
|   |   └── SignUpController.java
|   |
|   ├── images
|   |   ├── landing.png
|   |   └── profile.png
|   |
|   └── scenes
|       ├── AddContact.java
|       ├── Contacts.java
|       ├── DeleteUser.java
|       ├── EditContact.java
|       ├── HomePage.java
|       ├── LogIn.java
|       ├── PasswordChange.java
|       └── SignUp.java
|    
└── (end)
```
