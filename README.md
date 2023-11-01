# Appium2-templates
Template scripts for Appium2/Selenium4 Tests in Perfecto

1. Clone the project
2. Open the file src/main/resources/tokens.ini and add your cloud tokens
3. In your tests, call PerfectoTokenStorage.getTokenForCloud(host) where host is the name of the cloud i.e. "demo", "mobilecloud" etc. The token will be automatically loaded if it exists in the tokens.ini file.
