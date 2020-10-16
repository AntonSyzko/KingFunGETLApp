# King FunG Extract Transform Load (ETL) Application

  King FunG ETL application parses input log files, validates content, filters and transforms to corresponding file formats.
## Installation

Use Maven as build package tool 

```bash
cd /{path to downloaded application parent directory}/KingFungLoggingProcessing
```
For running tests execute :
```bash
mvn clean test
```
For packaging application to executable JAR archive run:
```bash
mvn clean install
```

To execute application run providing input and  output directories locations:
```bash
java -jar target/KingFungLoggingProcessing-1.0-SNAPSHOT.jar \
{fullPathToInputLogFilesDirectory} \ 
{fullPathToOutputFilesDirectory} 
```
Example Windows:
```bash
java -jar KingFungLoggingProcessing-1.0-SNAPSHOT.jar \
 C:\Users\KingUser\data-processing-v3-java\data-processing-v3-java\input \
 C:\Users\KingUser\data-processing-v3-java\data-processing-v3-java\output22

```
Example Unix:
```bash
java -jar KingFungLoggingProcessing-1.0-SNAPSHOT.jar \
/home/KingUser/data-processing-v3-java/data-processing-v3-java/input \
/home/KingUser/data-processing-v3-java/data-processing-v3-java/output
```

[Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html) is horizontally scalable centralized configuration service for distributed systems. It uses a pluggable repository layer that currently supports local storage, Git, and Subversion. 

## Usage
Processed files will be located at provided output folder
Invalid log entries stored at invalid.log file without transformations.
Transformed CSV files will represent correspondent Tracking Event

##@Copyright

Visit [King Games](https://www.king.com/)

##Community 
[King games community](https://community.king.com/en/?utm_campaign=king.com_community&utm_source=king.com)

##Tech Blog
[King's Tech Blog at Medium](https://medium.com/@TechKing)

##LinkedIn
[King's Linked In Profile](https://www.linkedin.com/company/king/)
```
## License
