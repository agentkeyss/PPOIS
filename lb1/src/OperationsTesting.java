import org.junit.Assert;
import org.junit.Test;

public class OperationsTesting {
    // Тесты на сложение
    @Test
    public void test1() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("160642");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test2() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger exceptedAnswer = new LongInteger("1321");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(1321));
    }

    @Test
    public void test3() {
        LongInteger longInteger1 = new LongInteger("4325434364345345436345435432423545425652");
        LongInteger longInteger2 = new LongInteger("1324323432432432543254254242454365786987");
        LongInteger exceptedAnswer = new LongInteger("5649757796777777979599689674877911212639");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test4() {
        LongInteger longInteger1 = new LongInteger(17000000);
        LongInteger longInteger2 = new LongInteger("100000000000001");
        LongInteger exceptedAnswer = new LongInteger("100000017000001");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test5() {
        LongInteger longInteger1 = new LongInteger("-1593211432534634234");
        LongInteger longInteger2 = new LongInteger("985374301248521");
        LongInteger exceptedAnswer = new LongInteger("-1592226058233385713");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test6() {
        LongInteger longInteger1 = new LongInteger("-12390534829182475243");
        LongInteger longInteger2 = new LongInteger("-4320129485201248345063468924986234");
        LongInteger exceptedAnswer = new LongInteger("-4320129485201260735598298107461477");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test7() {
        LongInteger longInteger1 = new LongInteger(0);
        LongInteger longInteger2 = new LongInteger(0);
        LongInteger exceptedAnswer = new LongInteger(0);
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test8() {
        LongInteger longInteger1 = new LongInteger(-31243634);
        LongInteger longInteger2 = new LongInteger(-1234213);
        LongInteger exceptedAnswer = new LongInteger("-32477847");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test9() {
        LongInteger longInteger1 = new LongInteger(-123412523);
        LongInteger longInteger2 = new LongInteger(3214354);
        LongInteger exceptedAnswer = new LongInteger("-120198169");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    @Test
    public void test10() {
        LongInteger longInteger1 = new LongInteger(2000000000);
        LongInteger longInteger2 = new LongInteger(1900000000);
        LongInteger exceptedAnswer = new LongInteger("3900000000");
        Assert.assertEquals(exceptedAnswer, longInteger1.add(longInteger2));
    }

    // Тесты на вычитание
    @Test
    public void test11() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("158000");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test12() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger exceptedAnswer = new LongInteger("-1321");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(1321));
    }

    @Test
    public void test13() {
        LongInteger longInteger1 = new LongInteger("1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("4325434364345345436345435432423545425652");
        LongInteger exceptedAnswer = new LongInteger("-3001110931912912893091181189969179638665");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test14() {
        LongInteger longInteger1 = new LongInteger(17000000);
        LongInteger longInteger2 = new LongInteger("100000000000001");
        LongInteger exceptedAnswer = new LongInteger("-99999983000001");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test15() {
        LongInteger longInteger1 = new LongInteger("1593211432534634234");
        LongInteger longInteger2 = new LongInteger("985374301248521");
        LongInteger exceptedAnswer = new LongInteger("1592226058233385713");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test16() {
        LongInteger longInteger1 = new LongInteger("-12390534829182475243");
        LongInteger longInteger2 = new LongInteger("-4320129485201248345063468924986234");
        LongInteger exceptedAnswer = new LongInteger("4320129485201235954528639742510991");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test17() {
        LongInteger longInteger1 = new LongInteger(0);
        LongInteger longInteger2 = new LongInteger(0);
        LongInteger exceptedAnswer = new LongInteger(0);
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test18() {
        LongInteger longInteger1 = new LongInteger(-31243634);
        LongInteger longInteger2 = new LongInteger(-1234213);
        LongInteger exceptedAnswer = new LongInteger(-30009421);
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test19() {
        LongInteger longInteger1 = new LongInteger(-2000000000);
        LongInteger longInteger2 = new LongInteger(2000000000);
        LongInteger exceptedAnswer = new LongInteger("-4000000000");
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    @Test
    public void test20() {
        LongInteger longInteger1 = new LongInteger(120021451);
        LongInteger longInteger2 = new LongInteger(901245);
        LongInteger exceptedAnswer = new LongInteger(119120206);
        Assert.assertEquals(exceptedAnswer, longInteger1.subtract(longInteger2));
    }

    // Тесты на произведение
    @Test
    public void test21() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("210463041");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test22() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("0");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test23() {
        LongInteger longInteger1 = new LongInteger("1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("4325434364345345436345435432423545425652");
        LongInteger exceptedAnswer = new LongInteger("5728274084151024884339730884246562045355130787420965241626035489819114877590524");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test24() {
        LongInteger longInteger1 = new LongInteger(17000000);
        LongInteger longInteger2 = new LongInteger("100000000000001");
        LongInteger exceptedAnswer = new LongInteger("1700000000000017000000");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test25() {
        LongInteger longInteger1 = new LongInteger("1593211432534634234");
        LongInteger longInteger2 = new LongInteger("985374301248521");
        LongInteger exceptedAnswer = new LongInteger("1569909602074970365043360268467914");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test26() {
        LongInteger longInteger1 = new LongInteger("-12390534829182475243");
        LongInteger longInteger2 = new LongInteger("-4320129485201248345063468924986234");
        LongInteger exceptedAnswer = new LongInteger("53528714852964224371391084626269925065053797820804862");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test27() {
        LongInteger longInteger1 = new LongInteger(0);
        LongInteger longInteger2 = new LongInteger(0);
        LongInteger exceptedAnswer = new LongInteger(0);
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test28() {
        LongInteger longInteger1 = new LongInteger(-31243634);
        LongInteger exceptedAnswer = new LongInteger("38561299250042");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(-1234213));
    }

    @Test
    public void test29() {
        LongInteger longInteger1 = new LongInteger(-2000000000);
        LongInteger longInteger2 = new LongInteger(2000000000);
        LongInteger exceptedAnswer = new LongInteger("-4000000000000000000");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    @Test
    public void test30() {
        LongInteger longInteger1 = new LongInteger(120021451);
        LongInteger longInteger2 = new LongInteger(901245);
        LongInteger exceptedAnswer = new LongInteger("108168732606495");
        Assert.assertEquals(exceptedAnswer, longInteger1.multiply(longInteger2));
    }

    // Тесты на деление
    @Test
    public void test31() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("120");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test32() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger longInteger2 = new LongInteger("1321");
        LongInteger exceptedAnswer = new LongInteger("0");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test33() {
        LongInteger longInteger1 = new LongInteger("1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("4325434364345345436345435432423545425652");
        LongInteger exceptedAnswer = new LongInteger("0");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test34() {
        LongInteger longInteger1 = new LongInteger("100000000000001");
        LongInteger longInteger2 = new LongInteger(17000000);
        LongInteger exceptedAnswer = new LongInteger("5882352");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test35() {
            LongInteger longInteger1 = new LongInteger("15932114325346342343214436565685");
        LongInteger longInteger2 = new LongInteger("985374301248521");
        LongInteger exceptedAnswer = new LongInteger("16168591270504534"); // 16168591270504534
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test36() {
        LongInteger longInteger1 = new LongInteger("-4320129485201248345063468924986234");
        LongInteger longInteger2 = new LongInteger("-12390534829182475243");
        LongInteger exceptedAnswer = new LongInteger("348663681169466");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test(expected = RuntimeException.class)
    public void test37() {
        LongInteger longInteger1 = new LongInteger(123445);
        LongInteger longInteger2 = new LongInteger(0);
        longInteger1.division(longInteger2);
    }

    @Test
    public void test38() {
        LongInteger longInteger1 = new LongInteger(-31243634);
        LongInteger exceptedAnswer = new LongInteger("25");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(-1234213));
    }

    @Test
    public void test39() {
        LongInteger longInteger1 = new LongInteger(-2000000000);
        LongInteger longInteger2 = new LongInteger(2000000000);
        LongInteger exceptedAnswer = new LongInteger(-1);
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    @Test
    public void test40() {
        LongInteger longInteger1 = new LongInteger(120021451);
        LongInteger longInteger2 = new LongInteger(901245);
        LongInteger exceptedAnswer = new LongInteger("133");
        Assert.assertEquals(exceptedAnswer, longInteger1.division(longInteger2));
    }

    // Тесты на инкремент/декримент
    @Test
    public void test41() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger exceptedAnswer = new LongInteger("159321");
        Assert.assertEquals(exceptedAnswer, longInteger1.postIncrement());
    }

    @Test
    public void test42() {
        LongInteger longInteger1 = new LongInteger(-12312341);
        LongInteger exceptedAnswer = new LongInteger(-12312341);
        Assert.assertEquals(exceptedAnswer, longInteger1.postIncrement());
    }

    @Test
    public void test43() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger exceptedAnswer = new LongInteger("159322");
        Assert.assertEquals(exceptedAnswer, longInteger1.preIncrement());
    }

    @Test
    public void test44() {
        LongInteger longInteger1 = new LongInteger(-12312341);
        LongInteger exceptedAnswer = new LongInteger(-12312340);
        Assert.assertEquals(exceptedAnswer, longInteger1.preIncrement());
    }

    @Test
    public void test45() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger exceptedAnswer = new LongInteger("159321");
        Assert.assertEquals(exceptedAnswer, longInteger1.postDecrement());
    }

    @Test
    public void test46() {
        LongInteger longInteger1 = new LongInteger(-12312341);
        LongInteger exceptedAnswer = new LongInteger(-12312341);
        Assert.assertEquals(exceptedAnswer, longInteger1.postDecrement());
    }

    @Test
    public void test47() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger exceptedAnswer = new LongInteger("159320");
        Assert.assertEquals(exceptedAnswer, longInteger1.preDecrement());
    }

    @Test
    public void test48() {
        LongInteger longInteger1 = new LongInteger(-12312342);
        LongInteger exceptedAnswer = new LongInteger(-12312343);
        Assert.assertEquals(exceptedAnswer, longInteger1.preDecrement());
    }

    // Тесты на равенства
    @Test
    public void test49() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        Assert.assertTrue(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test50() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger longInteger2 = new LongInteger("0");
        Assert.assertFalse(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test51() {
        LongInteger longInteger1 = new LongInteger("-1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("4325434364345345436345435432423545425652");
        Assert.assertFalse(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test52() {
        LongInteger longInteger1 = new LongInteger("100000000000001");
        LongInteger longInteger2 = new LongInteger(17000000);
        Assert.assertTrue(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test53() {
        LongInteger longInteger1 = new LongInteger("-15932114325346342343214436565685");
        LongInteger longInteger2 = new LongInteger("-985374301248521");
        Assert.assertFalse(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test54() {
        LongInteger longInteger1 = new LongInteger("4320129485201248345063468924986234");
        LongInteger longInteger2 = new LongInteger("-12390534829182475243");
        Assert.assertTrue(longInteger1.isMore(longInteger2));
    }

    @Test
    public void test55() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("1321");
        Assert.assertFalse(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test56() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger longInteger2 = new LongInteger("1321");
        Assert.assertTrue(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test57() {
        LongInteger longInteger1 = new LongInteger("-1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("4325434364345345436345435432423545425652");
        Assert.assertTrue(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test58() {
        LongInteger longInteger1 = new LongInteger("100000000000001");
        LongInteger longInteger2 = new LongInteger(17000000);
        Assert.assertFalse(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test59() {
        LongInteger longInteger1 = new LongInteger("-15932114325346342343214436565685");
        LongInteger longInteger2 = new LongInteger("-985374301248521");
        Assert.assertTrue(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test60() {
        LongInteger longInteger1 = new LongInteger("4320129485201248345063468924986234");
        LongInteger longInteger2 = new LongInteger("-12390534829182475243");
        Assert.assertFalse(longInteger1.isLess(longInteger2));
    }

    @Test
    public void test61() {
        LongInteger longInteger1 = new LongInteger("159321");
        LongInteger longInteger2 = new LongInteger("159321");
        Assert.assertEquals(longInteger1, longInteger2);
    }

    @Test
    public void test62() {
        LongInteger longInteger1 = new LongInteger("0");
        LongInteger longInteger2 = new LongInteger("1321");
        Assert.assertNotEquals(longInteger1, longInteger2);
    }

    @Test
    public void test63() {
        LongInteger longInteger1 = new LongInteger("-1324323432432432543254254242454365786987");
        LongInteger longInteger2 = new LongInteger("-1324323432432432543254254242454365786987");
        Assert.assertEquals(longInteger1, longInteger2);
    }

    @Test
    public void test64() {
        LongInteger longInteger1 = new LongInteger("100000000000001");
        LongInteger longInteger2 = new LongInteger(17000000);
        Assert.assertNotEquals(longInteger1, longInteger2);
    }

    @Test
    public void test65() {
        LongInteger longInteger1 = new LongInteger("3124325342");
        LongInteger longInteger2 = new LongInteger("-985374301248521");
        Assert.assertNotEquals(longInteger1, longInteger2);
    }

    @Test
    public void test66() {
        LongInteger longInteger1 = new LongInteger("4320129485201248345063468924986234");
        LongInteger longInteger2 = new LongInteger("-12390534829182475243");
        Assert.assertNotEquals(longInteger1, longInteger2);
    }

    @Test
    public void test67() {
        LongInteger longInteger = new LongInteger("4320129485201248345063468924986234");
        LongInteger exceptedAnswer = new LongInteger("-4320129485201248345063468924986234");
        longInteger.makeOposite();
        Assert.assertEquals(exceptedAnswer, longInteger);
    }

    @Test
    public void test68() {
        LongInteger longInteger = new LongInteger("4320129485201248345063468924986234");
        LongInteger exceptedAnswer = new LongInteger("-4320129485201248345063468924986234");
        Assert.assertEquals(exceptedAnswer, longInteger.getOpposite());
    }

    @Test
    public void test69() {
        LongInteger longInteger = new LongInteger("4320129485201248345063468924986234");
        Assert.assertTrue(longInteger.getIsPositive());
    }

    @Test
    public void test70() {
        LongInteger longInteger = new LongInteger("-4320129485201248345063468924986234");
        Assert.assertFalse(longInteger.getIsPositive());
    }
}
