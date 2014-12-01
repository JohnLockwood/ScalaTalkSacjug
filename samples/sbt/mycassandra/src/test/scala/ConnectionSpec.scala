import com.datastax.driver.core.{Row, Cluster}
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.typesafe.config.ConfigFactory
import org.scalatest._

class ConnectionSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

    // Use a typesafe configuration to load.  See test/resources/reference.conf for settings
    val conf = ConfigFactory.load()
    private def testKeyspaceName = conf.getString("cassandra.test.testKeyspace")
    private def testTable = conf.getString("cassandra.test.testTable")
    private def clusterHost = conf.getString("cassandra.host")
    private def port = conf.getInt("cassandra.port")

    // We'll be needing a cluster
    val cluster = {
        Cluster.builder()
          .addContactPoint(conf.getString("cassandra.host"))
          .withPort(port)
          .build()
    }

    // Get a session on the test keyspace.  Caller should call .close on the returned object
    // when finished with it.
    def testSession = {
        cluster.connect(testKeyspaceName)
    }

    // Set up
    override def beforeAll(): Unit = {
        val session = cluster.connect()
        session.execute("CREATE KEYSPACE IF NOT EXISTS "+ testKeyspaceName + 
           " WITH replication = {'class':'SimpleStrategy', 'replication_factor':3}")
        session.close()

        val sessionKeyspace = testSession
        sessionKeyspace.execute("CREATE TABLE IF NOT EXISTS " + testTable + " (user_name varchar PRIMARY KEY, email varchar)")
        sessionKeyspace.close()
    }

    // Clean up
    override def afterAll(): Unit = {
        val session = cluster.connect()
        session.execute("DROP KEYSPACE " + testKeyspaceName )
        session.close()
        cluster.close()
    }

    // Test the configuration first
    "The test configuration" should "have a port value of 9042" in {
        port shouldEqual 9042
    }

    it should "have a host value of 127.0.0.1" in {
        clusterHost shouldEqual "127.0.0.1"
    }

    it should "have a testKeyspace name of giter8CassandraTestKeyspace" in {
        testKeyspaceName shouldEqual "giter8CassandraTestKeyspace"
    }

    it should "have a testTable name of giter8CassandraUser" in {
        testTable shouldEqual "giter8CassandraUser"
    }

    // A really basic test using the database connection
    "A Cassandra connection" should "allow me to insert, select and delete a user" in {
        val session = testSession

        // Insert a user
        session.execute("INSERT INTO " + testTable + " (user_name, email) values('FavoriteExampleAuthor', 'john@particlewave.com')")

        // Verify we have exactly one row with our user in it
        val statement = QueryBuilder
            .select()
            .all()
            .from(testKeyspaceName, testTable)
            //.where(eq("user_name", "FavoriteExampleAuthor"))
        val results = session.execute(statement).all()
        val iterator = results.iterator()
        var rowCount = 0
        while(iterator.hasNext()) {
            iterator.next().getString("email") should equal("john@particlewave.com")
            rowCount = rowCount + 1
        }
        rowCount shouldBe 1

        // Clean up
        session.execute(s"DELETE FROM " + testTable + " where user_name = 'FavoriteExampleAuthor'")
        session.close()
    }

}
