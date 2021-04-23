# Selenium Project using Serenity and Cucumber
##Short Introduction
Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.


### The project directory structure
The project is built using Maven and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                        Test runners and supporting code
    + resources
      + features                  Feature files
     + mvc_reminder                Page Elements, Navigation, Actions, etc. 
```

## The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, Sergey (who likes to search for stuff) is performing a search on the internet:

```Gherkin
@creating-reminder
Feature: As a busy tester, I should be able to create reminders, so that I can plan my day and not forget key tasks

  @single-reminder
  Scenario: Creating single reminder task
     Given Pravin is on create reminder landing page
     When he creates a reminder "Review TestCases"
     Then he should see "Review TestCases" added to the reminder list
     And he should see a total of 1 reminders added to the list

```

## Executing the tests
To run the  project, you can either;
 * run the `CucumberTestSuite` test runner class
 * run `mvn verify` from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Ddriver=firefox
```
The test results will be recorded in the `target/site/serenity` directory.

## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the `pom.xml` file causes the reports to be generated automatically once all the tests have completed when you run `mvn verify`?

```
             <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## WebDriver Manager
The project has integration with WebdriverManager to download webdriver binaries.

The project uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.

## Additional Configurations

This project uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.  
### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as mentioned below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```


### Environment-specific configurations
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, __dev__, _staging_ and _prod_, with different starting URLs for each:
```json
environments {
  default {
    webdriver.base.url = "https://todomvc.com/examples/vue/#/all"
  }
  dev {
    webdriver.base.url = "https://todomvc.com/examples/vue/#/all"
  }
  prod {
    webdriver.base.url = "https://todomvc.com/examples/vue/#/all"
  }
}
```

You use the `environment` system property to determine which environment to run against. For example to run the tests in the dev environment, you could run:
```json
$ mvn clean verify -Denvironment=dev
```