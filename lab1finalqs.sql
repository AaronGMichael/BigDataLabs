-- SELECT empno from (SELECT empno, COUNT(projno) as project_count from 
-- 	EMP INNER JOIN (SELECT * from Project INNER JOIN project_emp ON project = projno) on empno = employee
-- 	GROUP BY empno) WHERE 
-- 	project_count = (SELECT DISTINCT COUNT(*) FROM project)

-- CREATE VIEW sales_staff AS
-- SELECT empno, ename, deptno
--     FROM emp
--     WHERE deptno = 10 WITH CHECK OPTION

-- INSERT INTO sales_staff VALUES (7584, 'OSTER', 10);
-- INSERT INTO sales_staff VALUES (7591, 'WILLIAMS', 30);

SELECT empno, COUNT(*) as Project_Count 
	FROM emp e inner join project_emp pe on pe.employee = e.empno 
	inner join project pr on pr.projno = pe.project 
	GROUP BY e.empno
	Having COUNT(*) > 1;

-- SELECT empno, ename, efirst, job FROM emp where empno in 
-- 	(SELECT employee FROM project_emp where project = 1
-- 		INTERSECT
-- 	SELECT employee FROM project_emp where project = 2)

-- SELECT empno, ename, efirst, job FROM emp where empno in 
-- 	(SELECT employee FROM project_emp where project = 3
-- 		EXCEPT
-- 	SELECT employee FROM project_emp where project = 4)

-- SELECT * FROM (
-- SELECT  project, empno, sal,
-- 	RANK() over(partition by project order by SAL desc) as rank
-- 	FROM emp e inner join project_emp pe on pe.employee = e.empno 
-- 	inner join project pr on pr.projno = pe.project
-- 	) WHERE rank < 4;

Select *, CASE 
	WHEN Project_Percentage = 0 Then 'Empty'
	WHEN Project_Percentage between 10 and 49 then 'Small'
	WHEN Project_Percentage between 50 and 79 then 'Medium'
	When Project_Percentage between 80 and 99 then 'Large'
	When Project_percentage = 100 Then 'Total'
	END AS scope_size
FROM (
SELECT empno, 
	ceil(cast(COUNT(projno) as decimal)*100/(SELECT count(*) from project)) as Project_Percentage
	FROM emp e left join project_emp pe on pe.employee = e.empno 
	left join project pr on pr.projno = pe.project 
	GROUP BY e.empno
);
	
-- 		CASE 
-- 		WHEN ceil(cast(COUNT(projno) as decimal)*100/(SELECT count(*) from project)) = 0 Then 'Empty'
-- 	END AS scope_size