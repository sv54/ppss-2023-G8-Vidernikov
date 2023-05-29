package ppss.matriculacion.dao;

import oracle.security.crypto.core.Padding;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

public class AlumnoDAOIT {

    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    String exception1= "Error al conectar con BD";
    @BeforeEach
    public void setup() throws Exception {
        String cadenaConexion = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", cadenaConexion, "root","ppss");
        databaseConnection = databaseTester.getConnection();

    }

    @Test
    public void testA1() throws Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNif("33333333C");
        a.setNombre("Elena Aguirre Juarez");
        a.setFechaNacimiento(LocalDate.of(1985, 02, 22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        Assertions.assertDoesNotThrow(()->new FactoriaDAO().getAlumnoDAO().addAlumno(a));

        IDataSet databaseDataSet = databaseConnection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertions.assertEquals(expectedTable,actualTable);

    }


    @Test
    public void testA2() throws Exception{

        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));
        alumno.setNombre("Alfonso Ramirez Ruiz");

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class ,() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));
        Assertions.assertEquals(exception1,exception.getMessage());

    }

    @Test
    public void testA3() throws Exception{

        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));
        alumno.setNombre(null);

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class ,() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));
        Assertions.assertEquals(exception1,exception.getMessage());

    }

    @Test
    public void testA4() throws Exception{

        AlumnoTO alumno = new AlumnoTO();


        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class ,() -> new FactoriaDAO().getAlumnoDAO().addAlumno(null));
        Assertions.assertEquals("Alumno nulo",exception.getMessage());

    }

    @Test
    public void testA5() throws Exception{

        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));
        alumno.setNombre("Pedro Garcia Lopez");

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class ,() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));
        Assertions.assertEquals(exception1,exception.getMessage());

    }

    @Test
    public void testB1() throws Exception{

        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().delAlumno(alumno.getNif()));

        IDataSet actualDataset = databaseConnection.createDataSet();
        ITable actualTable = actualDataset.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = actualDataset.getTable("alumnos");

        Assertions.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception{

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exception = Assertions.assertThrows(DAOException.class ,() -> new FactoriaDAO().getAlumnoDAO().delAlumno("33333333C"));
        Assertions.assertEquals("No se ha borrado ningun alumno",exception.getMessage());
    }


}
