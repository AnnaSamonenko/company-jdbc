SELECT  e.employee_id AS ID,
        e.name AS NAME_SURNAME,
        e.contact_information AS CONTACT_INFORMATION,
        pr.title AS PROJECT_TITLE,
        p.title AS POSITION_TITLE
FROM employees AS e
JOIN projects AS pr
ON pr.project_id = e.project_id
JOIN positions AS p
ON p.position_id = e.position_id;