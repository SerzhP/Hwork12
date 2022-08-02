import java.sql.*;

public class DBDogs {
    private static final String USER_NAME = "N3xxZOM26b";
    private static final String DATABASE_NAME = "N3xxZOM26b";
    private static final String PASSWORD = "7r3P2T90JU";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    public static void main(String[] args) throws SQLException {
        Connection conect = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT,USER_NAME,PASSWORD);

        createTable(conect);
        insertDogs(conect,"Alex",4,"Akita");
        insertDogs(conect,"Baron",6,"Beagle");
        insertDogs(conect,"Chico",3,"Doberman");
        updateSecondDogAge(conect,8,"Baron");
        deleteThirdDog(conect, "Chico");
        printDogsNames(conect);

        conect.close();
    }
    private static void createTable(Connection conect)throws SQLException{
        String statementToExecute ="CREATE TABLE " + DATABASE_NAME + ".`dogs`(`name` VARCHAR(40) NOT NULL,`age` INT NOT NULL,`breed` VARCHAR(30) NOT NULL, PRIMARY KEY (`name`));";
        conect.createStatement().execute(statementToExecute);
    }
    private static void insertDogs(Connection conect, String name, int age, String breed ) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs ( `name`,`age`,`breed`) VALUES ('" + name + "', '" + age + "','" + breed + "');";
        conect.createStatement().execute(statementToExecute);
    }
    private static void updateSecondDogAge(Connection conect, int age, String name) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `age`='"+age+"' WHERE `name`='"+name+"';";
        conect.createStatement().executeUpdate(statementToExecute);
    }
    private static void deleteThirdDog(Connection conect, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `name`='"+name+"';";
        conect.createStatement().execute(statementToExecute);
    }
    private static void printDogsNames(Connection conect) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = conect.createStatement();
        ResultSet result = stmt.executeQuery(statementToExecute);
        while(result.next()){

            int age  = result.getInt("age");
            String name = result.getString("name");
            String breed = result.getString("breed");


            System.out.println("  Name: " + name);
            System.out.print(" Breed: " + breed);
            System.out.print(" Age: " + age);
        }
        result.close();
    }

}
