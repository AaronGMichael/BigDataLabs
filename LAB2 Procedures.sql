-- CREATE OR REPLACE PROCEDURE EMP_NAME_FROM_ID(in_EMP_ID NUMERIC)
-- LANGUAGE plpgsql
-- AS $procedure$
-- DECLARE
-- 	R_EMP EMP%ROWTYPE;
-- BEGIN
-- 	SELECT * INTO R_EMP FROM EMP WHERE EMPNO = in_EMP_ID;
-- 	-- To print the name of the employee
-- 	RAISE NOTICE 'Employee name: %, Employee Salary: %',
-- 	R_EMP.EFIRST, R_EMP.SAL;
-- END;
-- $procedure$

-- CREATE OR REPLACE PROCEDURE EMP_SALARY_COMPARISION(in_EMP_ID NUMERIC)
-- LANGUAGE plpgsql
-- AS $procedure$
-- DECLARE
-- 	R_EMP EMP%ROWTYPE;
-- 	AVG_SAL NUMERIC;
-- BEGIN
-- 	SELECT * INTO R_EMP FROM EMP WHERE EMPNO = in_EMP_ID;
-- 	SELECT AVG(sal) INTO AVG_SAL FROM EMP WHERE job = R_EMP.job;  
-- 	-- SELECT AVG(SAL) FRom EMP where JOB IN (SELECT JOB FROM EMP WHERE EMPNO = 7499)
-- 	RAISE NOTICE 'Employee name: %, Employee Salary: %',
-- 	R_EMP.EFIRST, R_EMP.SAL;
-- 	RAISE NOTICE 'Average Salary of % Job: %', R_EMP.job, AVG_SAL;
-- EXCEPTION
-- 	WHEN OTHERS THEN
-- 	RAISE NOTICE 'SQL ERROR OF QUERY: %', SQLERRM;
-- END;
-- $procedure$


-- Create a procedure that updates the employee’s salary:
-- a. if his/her salary is greater or equal to the average one, wage increase of 10%
-- b. else his/her new salary becomes the average


-- CREATE OR REPLACE PROCEDURE EMP_UPDATE_SALARIES(in_EMP_ID NUMERIC)
-- LANGUAGE plpgsql
-- AS $procedure$
-- DECLARE
-- 	AVG_SAL

-- EXcercise 5

-- UPDATE EMP SET COMM = 0;

-- CREATE OR REPLACE PROCEDURE UPDATECOMMISSIONS()
-- LANGUAGE plpgsql
-- AS $procedure$
-- DECLARE
-- 	ONE_EMP EMP%ROWTYPE;
-- 	NEW_COMM NUMERIC;
-- 	E_SAL NUMERIC;
-- 	SAL_EMP CURSOR FOR SELECT * FROM EMP FOR UPDATE;
-- BEGIN
-- 	-- opening the cursor
-- 	OPEN SAL_EMP;
-- 	-- Looping the Cursor
-- 	LOOP
-- 		FETCH SAL_EMP into ONE_EMP;
-- 		EXIT WHEN NOT FOUND;
-- 		-- USAGE
-- 		E_SAL := ONE_EMP.SAL;
-- 		IF E_SAL <= 1000 THEN
-- 			NEW_COMM :=800;
-- 		ELSE IF E_SAL <=2000 THEN
-- 			NEW_COMM := 1200;
-- 		ELSE 
-- 			NEW_COMM := 1500;
-- 		END IF;
-- 		END IF;
-- 		UPDATE EMP SET COMM = NEW_COMM WHERE EMPNO = ONE_EMP.EMPNO;
-- 		RAISE NOTICE 'EMPLOYEE NO: %, SALARY: %, COMMISION: %', ONE_EMP.EMPNO, ONE_EMP.SAL, NEW_COMM;
-- 	END LOOP;
-- 	COMMIT;
-- 	-- Closing the cursor
-- 	CLOSE SAL_EMP;
-- END;
-- $procedure$


-- Excercise 7
CREATE OR REPLACE FUNCTION auto_insert() RETURNS TRIGGER AS $body$
BEGIN
	RAISE NOTICE 'USER MODIFYING: %, and Date: %', current_user, current_date;
	INSERT INTO monitor(user1, DATE_USER) VALUES (current_user, current_date);
	RETURN NULL;
END;
$body$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER MYTRIGGER
AFTER INSERT OR UPDATE OR DELETE ON EMP
EXECUTE FUNCTION auto_insert();




	