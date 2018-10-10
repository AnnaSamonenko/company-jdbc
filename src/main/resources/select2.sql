SELECT e.name, pr.title, wh.start_date, wh.end_date, p.title
FROM working_history AS wh
JOIN employees AS e
ON e.employee_id =  wh.employee_id
JOIN projects AS pr
ON pr.project_id = wh.project_id
JOIN positions AS p
ON p.position_id = e.position_id AND p.title = 'Test Engineer';