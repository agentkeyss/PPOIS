import org.junit.Assert;
import org.junit.Test;

public class CreationTesting {
    @Test
    public void test1() {
        LongInteger longInteger = new LongInteger(123124523);
        Assert.assertEquals(new LongInteger(123124523), longInteger);
    }

    @Test
    public void test2() {
        LongInteger longInteger = new LongInteger("1124329583496309234324143534");
        Assert.assertEquals(new LongInteger("1124329583496309234324143534"), longInteger);
    }

    @Test(expected = RuntimeException.class)
    public void test3() {
        LongInteger longInteger = new LongInteger("1124329583496309234324143534jfsde");
    }

    @Test(expected = RuntimeException.class)
    public void test4() {
        LongInteger longInteger = new LongInteger("11243295834963 09234324143534");
    }

    @Test(expected = RuntimeException.class)
    public void test5() {
        LongInteger longInteger = new LongInteger("");
    }
}
