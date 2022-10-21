# CACI Technical Test

Thank you for the test. I really enjoyed working on it and done my best.

### Installation
The application was initialised using the Spring Initializr tool and uses Maven for package management and MySQL for data persistence.
To start the database, run `$ docker-compose up` assuming that docker and docker compose are present on your machine.
Once the database is up and running, start the application and access it on port 5555.

### Testing
To test the API, load the **Included Postman collection** into postman. \
In case it is not automatically loaded, the following environment variables will be necessary in postman: \
base_url: http://localhost:5555 \
order_reference
> Please note that the order_reference environment variable will be populated automatically upon creating an order as per the simple javascript logic in the postman test panel. 

I hope you find my solution clean and simple.

[rolandtreiber@gmail.com](mailto:rolandtreiber@gmail.com) \
Roland Treiber
