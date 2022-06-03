TRUNCATE transaction;

INSERT INTO transaction (id, credit, date, description, amount) VALUES (
    1, false, '2022-06-02', 'McDonalds', -15.42
),(
    2, true, '2022-05-31', 'Paycheck', 1000
),(
    3, false, '2022-05-15', 'Bar', -50
),(
    4, true, '2022-05-15', 'Paycheck', 1000
);