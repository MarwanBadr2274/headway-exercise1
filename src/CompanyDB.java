import java.sql.*;


public class CompanyDB {
    // Database connection parameters
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Company";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Merwen@PostgreSQL#1";

    public static void main(String[] args){

        try{
            // DB connection establishment
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if(connection != null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }


            // Creating the "Students" table
            createStudentsTable(connection);

            // Creating the "Departments" table
            createDepartmentsTable(connection);

            // Creating the "students_departments" join table
            createStudentsDepartmentsTable(connection);


            // Inserting sample data
            insertSampleData(connection);

            // Performing the operations
            selectAllStudents(connection);
            selectStudentsInDepartment(connection, "Computer Science");
            updateStudentInformation(connection, 1, "John Doe", 25, "F", "Computer Science", 123456);
            deleteStudent(connection, 2);
            getStudentCountInDepartment(connection, "Computer Science");
            listStudentsWithNoDepartment(connection);

            // Closing the connection
            connection.close();
        }
        catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void createStudentsTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Students (" +
                "ID SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT NOT NULL," +
                "grade VARCHAR(10) NOT NULL," +
                "department VARCHAR(50)," +
                "faculty_serial_number INT UNIQUE" +
                ")";
        executeQuery(connection, query);
    }

    private static void createDepartmentsTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Departments (" +
                "name VARCHAR(50) NOT NULL," +
                "ID SERIAL PRIMARY KEY," +
                "boss VARCHAR(50) NOT NULL" +
                ")";
        executeQuery(connection, query);
    }

    private static void createStudentsDepartmentsTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS students_departments (" +
                "student_id INT REFERENCES Students(ID) ON DELETE CASCADE," +
                "department_id INT REFERENCES Departments(ID) ON DELETE CASCADE" +
                ")";
        executeQuery(connection, query);
    }

    private static void insertSampleData(Connection connection) throws SQLException {
        String insertStudentsQuery = "INSERT INTO Students (name, age, grade, department, faculty_serial_number) VALUES " +
                "('John Doe', 23, 'A', 'Computer Science', 123456)," +
                "('Jane Smith', 22, 'B', 'Mathematics', 234567)," +
                "('Marwan Badr', 23, 'B', NULL, 334553)," +
                "('Bob Johnson', 24, 'A', 'Computer Science', 345678)";
        executeQuery(connection, insertStudentsQuery);

        String insertDepartmentsQuery = "INSERT INTO Departments (name, boss) VALUES " +
                "('Computer Science', 'John Smith')," +
                "('Physics', 'Marwan Badr')," +
                "('Mathematics', 'Jane Johnson')";
        executeQuery(connection, insertDepartmentsQuery);

        String insertStudentsDepartmentsQuery = "INSERT INTO students_departments (student_id, department_id) VALUES " +
                "(1, 1)," +
                "(2, 3)," +
                "(3, 2)," +
                "(4, 1)";
        executeQuery(connection, insertStudentsDepartmentsQuery);

    }

    private static void selectAllStudents(Connection connection) throws SQLException {
        String query = "SELECT * FROM Students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("All students:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                String department = resultSet.getString("department");
                int facultySerialNumber = resultSet.getInt("faculty_serial_number");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                        ", Grade: " + grade + ", Department: " + department +
                        ", Faculty Serial Number: " + facultySerialNumber);
            }
            System.out.println();
        }
    }

    private static void selectStudentsInDepartment(Connection connection, String departmentName) throws SQLException {
        String query = "SELECT Students.* FROM Students " +
                "INNER JOIN students_departments ON Students.ID = students_departments.student_id " +
                "INNER JOIN Departments ON students_departments.department_id = Departments.ID " +
                "WHERE Departments.name = ?";        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Students in department: " + departmentName);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                String department = resultSet.getString("department");
                int facultySerialNumber = resultSet.getInt("faculty_serial_number");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                        ", Grade: " + grade + ", Department: " + department +
                        ", Faculty Serial Number: " + facultySerialNumber);
            }
            System.out.println();
        }
    }

    private static void updateStudentInformation(Connection connection, int id, String name, int age, String grade,
                                                 String department, int facultySerialNumber) throws SQLException {
        String query = "UPDATE Students SET name = ?, age = ?, grade = ?, department = ?, faculty_serial_number = ? " +
                "WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, grade);
            statement.setString(4, department);
            statement.setInt(5, facultySerialNumber);
            statement.setInt(6, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student information updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String query = "DELETE FROM Students WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student information deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    private static void getStudentCountInDepartment(Connection connection, String departmentName) throws SQLException {
        String query = "SELECT COUNT(*) FROM Students " +
                "INNER JOIN students_departments ON Students.ID = students_departments.student_id " +
                "INNER JOIN Departments ON students_departments.department_id = Departments.ID " +
                "WHERE Departments.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Number of students in department " + departmentName + ": " + count);
            }
            System.out.println();
        }
    }

    private static void listStudentsWithNoDepartment(Connection connection) throws SQLException {
        String query = "SELECT * FROM Students " +
                "WHERE Students.department IS NULL";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Students with no department:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                String department = resultSet.getString("department");
                int facultySerialNumber = resultSet.getInt("faculty_serial_number");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                        ", Grade: " + grade + ", Department: " + department +
                        ", Faculty Serial Number: " + facultySerialNumber);
            }
            System.out.println();
        }
    }

    private static void executeQuery(Connection connection, String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

}
