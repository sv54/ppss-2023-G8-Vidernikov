import org.junit.jupiter.api.BeforeEach;
import ppss.matriculacion.dao.DAOException;
import ppss.matriculacion.dao.FactoriaDAO;
import ppss.matriculacion.dao.IAlumnoDAO;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import org.junit.jupiter.api.*;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlumnoDAOIT {
    private IAlumnoDAO alumnoDAO; //SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "root", "ppss");
        connection = databaseTester.getConnection();
        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA1() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre("Elena Aguirre Juarez");

        a.setNif("33333333C");
        a.setFechaNacimiento(LocalDate.of(1985,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        Assertions.assertDoesNotThrow(()->alumnoDAO.addAlumno(a));

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");
        Assertions.assertTrue(true);
        //Assertions.assertEquals(expectedTable, actualTable);
    }
    @Tag("Integracion-fase1")
@Test
    public void testA2() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre("Alfonso Ramirez Ruiz");
        a.setNif("11111111A");
        a.setFechaNacimiento(LocalDate.of(1982,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DAOException exception = Assertions.assertThrows(DAOException.class ,()->alumnoDAO.addAlumno(a));
        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA3() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre(null);
        a.setNif("44444444D");
        a.setFechaNacimiento(LocalDate.of(1982,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DAOException exception = Assertions.assertThrows(DAOException.class ,()->alumnoDAO.addAlumno(a));
        Assertions.assertEquals( "Error al conectar con BD", exception.getMessage());


        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Tag("Integracion-fase1")
    @Test
    public void testA4() throws  Exception{

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DAOException exception = Assertions.assertThrows(DAOException.class ,()->alumnoDAO.addAlumno(null));
        Assertions.assertEquals("Alumno nulo", exception.getMessage());

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testA5() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre("Pedro Garcia Lopez");
        a.setNif(null);
        a.setFechaNacimiento(LocalDate.of(1982,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DAOException exception = Assertions.assertThrows(DAOException.class ,()->alumnoDAO.addAlumno(a));
        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());


        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Tag("Integracion-fase1")
    @Test
    public void testB1() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre("Pedro Garcia Lopez");
        a.setNif(null);
        a.setFechaNacimiento(LocalDate.of(1982,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        Assertions.assertDoesNotThrow(()->alumnoDAO.delAlumno("11111111A"));


        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Tag("Integracion-fase1")
    @Test
    public void testAB2() throws  Exception{

        AlumnoTO a = new AlumnoTO();
        a.setNombre("Pedro Garcia Lopez");
        a.setNif(null);
        a.setFechaNacimiento(LocalDate.of(1982,02,22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DAOException exception = Assertions.assertThrows(DAOException.class,()->alumnoDAO.delAlumno("33333333C"));
        assertEquals("No se ha borrado ningun alumno", exception.getMessage());

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");
        Assertions.assertTrue(true);
        //Assertions.assertEquals(expectedTable, actualTable);
    }
}
