package by.spk.DAO;

public class SQLQueries {

    static final String SELECT_POSITIONS = "SELECT * from positions";
    static final String SELECT_DEPARTMENTS = "SELECT * from departments";
    static final String SELECT_ACCOUNTS = "SELECT * from accounts";
    static final String SELECT_HOLIDAYS = "SELECT * from holidays";
    static final String SELECT_SALARIES = "SELECT * from salaries";
    static final String SELECT_EMPLOYEES = "SELECT * from employees";

    static final String SELECT_ACCOUNT_WHERE_ID = "SELECT * FROM accounts WHERE id_account = ?";
    static final String SELECT_DEPARTMENT_WHERE_ID = "SELECT * FROM departments WHERE id_department = ?";
    static final String SELECT_EMPLOYEE_WHERE_ID = "SELECT * FROM employees WHERE id_employee = ?";
    static final String SELECT_HOLIDAY_WHERE_ID = "SELECT * FROM holidays WHERE id_holiday = ?";
    static final String SELECT_POSITION_WHERE_ID = "SELECT * FROM positions WHERE id_position = ?";
    static final String SELECT_SALARY_WHERE_ID = "SELECT * FROM salaries WHERE id_salary = ?";

    static final String SELECT_ACCOUNT_WHERE_LOGIN = "SELECT * FROM accounts WHERE login = ?";
    static final String SELECT_EMPLOYEE_WHERE_DEPARTMENT_ID = "SELECT * FROM employees WHERE department_id = ?";
    static final String SELECT_HOLIDAYS_WHERE_EMPLOYEE_ID = "SELECT * FROM holidays WHERE employee_id = ?";

    static final String SELECT_DEPARTMENT_ID_WHERE_ID_EMPLOYEE = "SELECT department_id FROM employees WHERE id_employee = ?";


    public static final String INSERT_LOGIN = "INSERT INTO logins (login) values(?)";
    public static final String INSERT_TEST = "INSERT INTO tests (test) values(?)";
    public static final String INSERT_INTO_RESULTS = "INSERT INTO results (loginid, testid, dat, mark) VALUES(?,?,?,?)";
    public static final String SELECT_IDLOGIN = "SELECT idlogin FROM logins WHERE login = ?";
    public static final String SELECT_IDTEST = "SELECT idtest FROM tests WHERE test = ?";

    public static final String SELECT_AVERAGE = "SELECT login, avg(mark) FROM results " +
            "INNER JOIN logins ON results.loginid = logins.idlogin " +
            "GROUP BY login " +
            "ORDER BY 2 DESC ";

    public static final String SELECT_RESULTS_BY_MONTH_YEAR = "SELECT login,test,dat,mark FROM ((results " +
            "INNER JOIN tests ON tests.idtest = results.testid) " +
            "INNER JOIN logins ON logins.idlogin = results.loginid) " +
            "WHERE MONTH(dat) = MONTH(now()) AND YEAR(dat) = YEAR(now()) " +
            "ORDER BY dat";


}
