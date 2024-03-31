-- SELECT * FROM (
-- 	SELECT EFIRST, ENAME, SAL, DEPTNO,
-- 		RANK() over (partition by DEPTNO order by hiredate desc) as rank
-- 		FROM EMP
-- )
-- WHERE rank <= 2

SELECT empno, deptno, sal, hiredate,
       MAX (hiredate) OVER (ORDER BY sal
ROWS BETWEEN 2 PRECEDING AND 2 FOLLOWING) AS max
FROM emp