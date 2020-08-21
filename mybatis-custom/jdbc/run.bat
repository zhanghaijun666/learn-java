@echo off
setlocal EnableDelayedExpansion


javac JDBCDemo.java
java -cp mysql-connector-java-5.1.38.jar; JDBCDemo

pause