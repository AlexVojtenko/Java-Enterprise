import database.Database;
import org.junit.Assert;
import org.junit.Test;

public class TestDataBaseConnection {

    @Test
    public void TestConnection() throws Exception {
        try {
            Database database = new Database();
            database.getConnection();
        } catch (Exception e) {
            Assert.fail("Exception is " + e.toString());
        }
    }
}
