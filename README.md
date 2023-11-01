# Appium2-templates
Template scripts for Appium2/Selenium4 Tests in Perfecto

1. Clone the project and open in IntelliJ/Eclipse.
2. Open the file src/main/resources/tokens.ini and add your cloud tokens (Exclude this file from commiting and make sure you never commit it).
3. To get a token for cloud in your tests, call PerfectoTokenStorage.getTokenForCloud(host) where host is the name of the cloud i.e. "demo", "mobilecloud" etc. The token will be returned if it exists in the tokens.ini file.
4. All tests are located in src/test/java/ folder. Modify them per your requirements. 
   
Optionally:
1. Modify the src/main/resources/logback.xml to increase/decrease the logging level i.e. <root level="debug">
