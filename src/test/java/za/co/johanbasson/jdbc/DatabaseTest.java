package za.co.johanbasson.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @org.junit.Test
    public void testQuery() throws Exception {

        //given
        Database db = new Database("org.h2.Driver", "jdbc:h2:target/test", "sa", "");

        //when
        db.update("DROP TABLE IF EXISTS test ").execute();
        db.update("CREATE TABLE test(val1 int, val2 varchar(10), val3 date)").execute();
        db.update("INSERT INTO test VALUES (1, 'abc', CURRENT_DATE())").execute();
        db.update("INSERT INTO test VALUES (2, 'def', CURRENT_DATE())").execute();

        Query qry = db.query("SELECT * FROM test");

        List<Test> items = qry.queryForList(new RowMapper<Test>(){
            @Override
            public Test mapRow(ResultSet rs) throws SQLException {
                return new Test(rs.getInt("val1"), rs.getString("val2"), rs.getDate("val3"));
            }
        });

        //then
        assertEquals(2, items.size());
    }

    @org.junit.Test
    public void testUpdate() throws Exception {
        //given
        Database db = new Database("org.h2.Driver", "jdbc:h2:target/test", "sa", "");

        //when
        db.update("DROP TABLE IF EXISTS test ").execute();
        db.update("CREATE TABLE test (val1 int, val2 varchar(10), val3 date)").execute();
        db.update("INSERT INTO test VALUES (1, 'abc', CURRENT_DATE())").execute();

        db.update("UPDATE test SET val1 = 10 where val1 = 1").execute();

        String val = db.query("SELECT val2 FROM test WHERE val1 = 10").queryForString();

        //then
        assertEquals("abc", val);
    }

    public void testStartTransaction() throws Exception {

    }

    class Test {
        private int val1;
        private String val2;
        private Date val3;

        Test(int val1, String val2, Date val3) {
            this.val1 = val1;
            this.val2 = val2;
            this.val3 = val3;
        }

        public int getVal1() {
            return val1;
        }

        public String getVal2() {
            return val2;
        }

        public Date getVal3() {
            return val3;
        }
    }
}
