# CACI Technical Test

Thank you for the test. I really enjoyed working on it and done my best.

### Installation
The application was initialised using the Spring Initializr tool and uses Maven for package management as well as MySQL for data persistence.
To start the database, run `$ docker-compose up` assuming that docker and docker compose are present on your machine.
Once the database is up and running, start the application and access it on port 5555.

### Testing
To test the API in Postman, load the **[included Postman collection](https://github.com/rolandtreiber/caci-technical-test/blob/main/CACI_tech_test_postman_collection.json)** into the app. \
In case it is not automatically loaded, the following environment variables will be necessary: \
base_url: [http://localhost:5555](http://localhost:5555) \
order_reference: [YOUR_ORDER_REFERENCE]
> Please note that the order_reference environment variable will be populated automatically upon creating an order as per the simple javascript logic in the postman test panel. 

I hope you find my solution clean and simple.

[rolandtreiber@gmail.com](mailto:rolandtreiber@gmail.com) \
Roland Treiber
