asana-api-java
==============

A Java client library for Asana's API

Usage
-----
Maven dependency:
```xml
<dependency>
     <groupId>net.joelinn</groupId>
     <artifactId>asana</artifactId>
     <version>0.5.7</version>
</dependency>
```

Creating a task:
```java
Asana asana = new Asana("your api key");
asana.tasks().createTask(new TaskRequestBuilder(4440299545542L, "New task!").addFollower(4858211767376L)
                               .addFollower(4440682739786L).notes("This is super important.").assignee(4440682739795L));
```
