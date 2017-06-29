@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_65
set ANT_HOME=D:\SoftwareDump\ANT\apache-ant-1.6.5
set M2_HOME=D:\SoftwareDump\Maven\apache-maven-3.2.1
rem M3_HOME=E:\Java\apache-maven-3.0.3
set PATH=%JAVA_HOME%\bin;%PATH%;
set ORACLE_HOME=E:\app\product\11.2.0\dbhome_1
set POSTGRES_HOME=C:\Program Files\PostgreSQL\9.2
set PATH=%POSTGRES_HOME%\bin;%ORACLE_HOME%\bin;%PATH%;%M2_HOME%\bin;C:\Program Files\TortoiseSVN;%PATH%
set MAVEN_OPTS=-Xms2048m -Xmx2048m -XX:PermSize=512m -XX:MaxPermSize=512m



