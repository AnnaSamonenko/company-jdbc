SELECT
    e.name AS NAME_SURNAME,
    pr.title AS PROJECT_TITLE,
     p.title AS POSITION_TITLE,
    wh.start_date AS START_DATE,
    wh.end_date AS END_DATE
FROM working_history AS wh
JOIN employees AS e
ON e.employee_id =  wh.employee_id
JOIN projects AS pr
ON pr.project_id = wh.project_id
JOIN positions AS p
ON p.position_id = e.position_id;