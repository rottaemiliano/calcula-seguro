CREATE TABLE INSURANCE_VALUE
(
    id         INT AUTO_INCREMENT,
    percentage DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

    CREATE TABLE INSURANCE_RULE
(
    id             INT         NOT NULL AUTO_INCREMENT,
    insurance_value_id INT         NOT NULL,
    reference_value VARCHAR(16) NOT NULL,
    operation       VARCHAR(2)  NOT NULL,
    field_name      VARCHAR(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT insurance_value_id_FK FOREIGN KEY (insurance_value_id) REFERENCES insurance_value (id)
);