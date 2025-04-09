@echo off
echo Creating lib folder...
mkdir lib 2>nul

echo Downloading JUnit and JaCoCo...
curl -L -o lib\junit-4.13.2.jar https://search.maven.org/remotecontent?filepath=junit/junit/4.13.2/junit-4.13.2.jar
curl -L -o lib\hamcrest-core-1.3.jar https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
curl -L -o lib\jacococli.jar https://search.maven.org/remotecontent?filepath=org/jacoco/org.jacoco.cli/0.8.11/org.jacoco.cli-0.8.11-nodeps.jar
curl -L -o lib\jacocoagent.jar https://search.maven.org/remotecontent?filepath=org/jacoco/org.jacoco.agent/0.8.11/org.jacoco.agent-0.8.11-runtime.jar

echo Compiling Java files...
javac -cp "lib\*;." TaskManagementApp.java test\TaskManagerTest.java

echo Running JUnit tests with JaCoCo agent...
java -javaagent:lib\jacocoagent.jar=destfile=jacoco.exec -cp "lib\*;.;test" org.junit.runner.JUnitCore TaskManagerTest

echo ✅ JaCoCo exec file generated at jacoco.exec
