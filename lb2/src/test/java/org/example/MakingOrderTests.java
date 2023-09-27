package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class MakingOrderTests {
    @Test
    public void makingOrderTest() throws SQLException {
        User user = new User();
        user.signIn("+375129845687", "stevejobs1955");
        Assert.assertTrue(user.makeOrder(1));
    }
}
