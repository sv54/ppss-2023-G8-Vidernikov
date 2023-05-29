package ejercicio1;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* IMPORTANTE:
    Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
    vamos a usar "throws Esception" en los métodos, para que el código quede más
    legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit 
    con un assertDoesNotThrow()
    Es decir, que vamos a primar la legibilidad de los tests.
    Si la SUT puede lanza una excepción, SIEMPRE usaremos assertDoesNotThrow para
    invocar a la sut cuando no esperemos que se lance dicha excepción (independientemente de que hayamos propagado las excepciones provocadas por dbunit).
*/
public class ClienteDAO_IT {
  
  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "cadena de conexion";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false", "root", "ppss");
    connection = databaseTester.getConnection();

    clienteDAO = new ClienteDAO();
  }

  @Test
  public void testInsert() throws Exception {
    Cliente cliente = new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
    
     //invocamos a la sut
    Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente"); 

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void testDelete() throws Exception {
    Cliente cliente =  new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");
    
    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void test_insert2() throws Exception{

    //Inicializamos la base de datos
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-Insert2.xml");
    databaseTester.setDataSet(dataSet);

    databaseTester.onSetup();

    //Insertamos cliente
    Cliente cliente = new Cliente(3, "Robert", "Smith");
    cliente.setCiudad("Anycity");
    cliente.setDireccion("1 Main Street");

    Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));


    //Comparamos resultados
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperadoInsert2.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void testUpdate() throws Exception{
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-Insert2.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    Cliente cliente = new Cliente(2, "NombreCambiado", "Smith");
    cliente.setCiudad("Anycity");
    cliente.setDireccion("1 Main Street");
    Assertions.assertDoesNotThrow(()->clienteDAO.update(cliente));

    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado-Update.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);


  }

  @Test
  public void testRetrive() throws Exception{
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-Insert2.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    Cliente clienteEsperado = new Cliente(1, "John", "Smith");
    clienteEsperado.setDireccion("1 Main Street");
    clienteEsperado.setCiudad("Anycity");

    Cliente actualCliente = clienteDAO.retrieve(1);

    Cliente c = Assertions.assertDoesNotThrow(()->clienteDAO.retrieve(1));


    Assertions.assertAll(() -> {
      assertEquals(clienteEsperado.getId(), c.getId());
      assertEquals(clienteEsperado.getNombre(), c.getNombre());
      assertEquals(clienteEsperado.getDireccion(), c.getDireccion());
      assertEquals(clienteEsperado.getApellido(), c.getApellido());
      assertEquals(clienteEsperado.getCiudad(), c.getCiudad());
    });

  }

}
