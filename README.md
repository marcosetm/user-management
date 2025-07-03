# User Management 
This is a microservice attempt.

### Using `.env`
Start by renaming the file (you could use `mv` too)
```bash
cp dev.env .env
```
Update the values in the `.env` file and run
```bash
source .env && ./mvnw spring-boot:run
```
>NOTE: You must source the `.env` file before running the application.
