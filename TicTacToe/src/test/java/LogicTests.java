import com.example.tictactoe.Models.Field;
import com.example.tictactoe.Models.Sign;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LogicTests {
    Field field;

    @BeforeEach
    public void initField() {
        field = new Field();
    }

    @Test
    public void getEmptyFieldIndexTest() {
        field.getField().put(3, Sign.CROSS);
        field.getField().put(2, Sign.NOUGHT);
        field.getField().put(8, Sign.CROSS);
        int emptyIndex = field.getEmptyFieldIndex();

        Assertions.assertEquals(Sign.EMPTY, field.getField().get(emptyIndex));
    }

    @Test
    public void getFieldDataTest() {
        field.getField().put(3, Sign.CROSS);
        field.getField().put(2, Sign.NOUGHT);
        field.getField().put(8, Sign.CROSS);
        List<Sign> data = List.of(
                Sign.EMPTY, Sign.EMPTY, Sign.NOUGHT,
                Sign.CROSS, Sign.EMPTY, Sign.EMPTY,
                Sign.EMPTY, Sign.EMPTY, Sign.CROSS);
        
        Assertions.assertEquals(data, field.getFieldData());
    }

    @Test
    public void checkWinTest() {
        field.getField().put(0, Sign.NOUGHT);
        field.getField().put(1, Sign.NOUGHT);
        field.getField().put(2, Sign.NOUGHT);

        Assertions.assertEquals(Sign.NOUGHT, field.checkWin());
    }
}
