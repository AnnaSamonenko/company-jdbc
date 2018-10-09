SELECT e.*, p.title, COUNT(wh.employee_id) as projectWorkedOn
FROM employees AS e
JOIN positions AS p
ON e.position_id = p.position_id AND p.title = "Test Engineer"
JOIN working_history AS wh
ON wh.employee_id = e.employee_id
GROUP BY e.employee_id;