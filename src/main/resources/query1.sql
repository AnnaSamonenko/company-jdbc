SELECT e.name, e.contact_information FROM employees AS e
JOIN positions as p
ON e.position_id = p.position_id AND p.title = "Project Manager"
WHERE e.project_id IN( SELECT project_id FROM (
	SELECT project_id, MAX(amount_of_jd)
    FROM (
		SELECT e.*, p.title as position_title, pr.title as project_title, COUNT(pr.project_id) as amount_of_jd
		FROM employees as e
		JOIN positions as p
		ON p.position_id = e.position_id AND (p.title = "Java Developer" OR p.title = "Project Manager")
		JOIN projects as pr
		ON pr.project_id = e.project_id
		GROUP BY pr.project_id, p.title DESC
	) AS resWithMax
	) AS res
);

# 1. Formatting