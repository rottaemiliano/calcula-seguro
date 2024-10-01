INSERT INTO INSURANCE_VALUE (id, percentage)
VALUES (1, 4.0),
       (2, 5.0),
       (3, 5.5),
       (4, 6.0);

INSERT INTO INSURANCE_RULE (id, insurance_value_id, reference_value, operation, field_name)
VALUES (1, 1, '70000', '<=', 'vehicle_value'),

       (2, 2, '70000', '<=', 'vehicle_value'),
       (3, 2, 'SP', '==', 'location'),

       (4, 3, '70000', '>', 'vehicle_value'),
       (5, 3, '100000', '<', 'vehicle_value'),

       (6, 4, '100000', '>=', 'vehicle_value');
