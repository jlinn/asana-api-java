asana-api-java
==============

A Java client library for Asana's API

Usage
-----
Creating a task:
```java
Asana asana = new Asana("your api key");
asana.tasks().createTask(new TaskRequestBuilder(4440299545542L, "New task!").addFollower(4858211767376L)
                               .addFollower(4440682739786L).notes("This is super important.").assignee(4440682739795L));
```