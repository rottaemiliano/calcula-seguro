# Insurance API

### Coverage

Run: ```./gradlew test koverHtmlReport```

Report: [build/reports/tests/test/index.html](
build/reports/tests/test/index.html)

### H2 Database

Url: http://localhost:8080/h2-console

Username: sa 

Password: pass

### SQL
```
INSERT INTO INSURANCE_VALUE (id, percentage)
VALUES (5, 10.0);

INSERT INTO INSURANCE_RULE (id, insurance_value_id, reference_value, operation, field_name)
VALUES (7, 5, '150000', '>=', 'vehicle_value'),
       (8, 5, 'SP', '==', 'location');
```

### CURL
```
curl --location 'localhost:8080/insurance/customer/vehicle/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "customer": {
        "name": "João",
        "document": "123.456.789-10",
        "birthday": "1990-07-10",
        "location": "BH",
        "vehicle_value": 70000
    }
}'
```