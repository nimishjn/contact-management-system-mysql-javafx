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

## Data Flow Diagram
![image](https://user-images.githubusercontent.com/63140632/144741431-2dca6982-75b7-4302-8a3d-1170eee4bdd2.png)

## Screenshots
### Homepage
![1](https://user-images.githubusercontent.com/63140632/181920605-8017c89a-5eaf-423a-a641-281cb955ac67.png)
### Login
![2](https://user-images.githubusercontent.com/63140632/181920622-d8d2d143-48a8-44fe-b36e-3f0783297fa4.png)
![3](https://user-images.githubusercontent.com/63140632/181920631-386e782e-a503-4a01-9d8e-d4d39771db85.png)
### Signup
![4](https://user-images.githubusercontent.com/63140632/181920639-ab607ba5-6531-4f2d-b94e-eccc072139e2.png)
![5](https://user-images.githubusercontent.com/63140632/181920642-dc8f53f6-1269-426f-8395-8431e8c1d847.png)
![6](https://user-images.githubusercontent.com/63140632/181920649-5f5d1cbb-cc3f-4b35-bee8-79020c517b39.png)
### Delete User
![7](https://user-images.githubusercontent.com/63140632/181920660-5c09189f-1535-41a0-a366-2e8ad13cbc9b.png)
![8](https://user-images.githubusercontent.com/63140632/181920669-e0f9f5a4-6be9-4a32-9d4e-07576db113f0.png)
### Contact Page
![9](https://user-images.githubusercontent.com/63140632/181920673-126dd805-c9da-4645-b1f7-2422dd0b02a2.png)
![10](https://user-images.githubusercontent.com/63140632/181920679-3c2085ba-a9ec-4628-b3c8-4e7afacfeeff.png)
![11](https://user-images.githubusercontent.com/63140632/181920700-e83efffc-2a09-4c2a-9230-561aed7739e7.png)
### Clear All
![12](https://user-images.githubusercontent.com/63140632/181920708-066581cd-1349-42ec-a708-6059180991ac.png)
### Add New Contact
![13](https://user-images.githubusercontent.com/63140632/181920716-123cc489-7763-49da-a8d3-ef0416eccd3a.png)
### Edit Existing Contact
![14](https://user-images.githubusercontent.com/63140632/181920721-091e5b31-a039-455d-b8a3-f08ff85dba1c.png)
### Edit Password Page
![15](https://user-images.githubusercontent.com/63140632/181920726-8d01635e-3655-4ccd-a56c-77ef049bafb4.png)
![16](https://user-images.githubusercontent.com/63140632/181920730-30ccc7b0-1acc-4fc3-b611-208de70f945b.png)
![17](https://user-images.githubusercontent.com/63140632/181920735-c2a46ae8-026b-4ab1-b5cd-6fb77757eb2f.png)

