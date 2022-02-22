# Forum
The idea is to build a working forum, as one of those popular in the late 2000s and early 2010s.

### Running the appliation
1. type ```git clone https://github.com/ReallyLastOne/Forum```
2. go to folder containing repository
3. type ```mvn spring-boot:run```
4. go to http://localhost:8080/
 
### Used technologies
JDK 17, Spring Web, Spring Data JPA, Thymeleaf, Spring Security, Lombok, h2 database, passay

### Capabilities of application
1. Visitor can:<br />
  a) register,<br />
  b) login,<br />
  c) look over threads/ user profiles.<br />
2. Logged user can:<br />
  a) create new threads,<br />
  b) add posts to existing threads,<br />
  c) customize profile,<br />
  d) edit password,<br />
  e) send private messages to other users,<br />
  f) give reputation to posts.<br />
3. Moderator/ admin can:<br />
  a) delete posts,<br />
  b) give warn to posts.<br />

### Main page
![Main-page](https://github.com/ReallyLastOne/Forum/blob/master/src/main/resources/main-page.png?raw=true "Main page")

### Thread
![User](https://github.com/ReallyLastOne/Forum/blob/master/src/main/resources/thread.png?raw=true "Thread")

### User page
![User-page](https://github.com/ReallyLastOne/Forum/blob/master/src/main/resources/user.png?raw=true "User")

### User panel
![User-panel](https://github.com/ReallyLastOne/Forum/blob/master/src/main/resources/private-messages.png?raw=true "User panel")



