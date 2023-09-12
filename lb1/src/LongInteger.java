import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Данный класс предназачен для работы с <b>длинными целыми числами</b>.
 * @author Dmitry Oskirko
 */
public class LongInteger implements Cloneable {

    private static final int BASE = 1_000_000_000;
    private static final int SIZE = 10;

    private List<Integer> longIntegerList = new ArrayList<>(SIZE);
    private boolean isPositive = true;

    public LongInteger(String longIntegerString) {
        Pattern longIntegerPattern = Pattern.compile("^-?\\d+$");
        Matcher longIntegerMatcher = longIntegerPattern.matcher(longIntegerString);
        if (!longIntegerMatcher.find()) throw new RuntimeException("LongInteger constructor must contain only digits!");

        if (longIntegerString.charAt(0) == '-') {
            longIntegerString = longIntegerString.substring(1);
            this.isPositive = false;
        }

        for (int i = longIntegerString.length(); i > 0; i -= 9) {
            if (i < 9) this.longIntegerList.add(Integer.parseInt(longIntegerString.substring(0, i)));
            else this.longIntegerList.add(Integer.parseInt(longIntegerString.substring(i-9, i)));
        }

        this.removeLeadingZeros();
    }

    public LongInteger(int number) {
        this(Long.toString(number));
    }

    public List<Integer> getLongIntegerList() {
        return new ArrayList<>(longIntegerList);
    }

    public boolean getIsPositive() {
        return isPositive;
    }

    /**
     * Функция, предназначенная для вывода длинного целого числа на экран.
     * @param longInteger целое длинное число, которое будет выведено на экран.
     */
    public static void write(LongInteger longInteger) {
        if (!longInteger.isPositive) System.out.print('-');
        System.out.print(longInteger.longIntegerList.get(longInteger.longIntegerList.size() - 1));

        for (int i = longInteger.longIntegerList.size() - 2; i >= 0; i--)
            System.out.printf("%09d", longInteger.longIntegerList.get(i));
        System.out.println();
    }

    /**
     * Функция, предназченная для удаления ведущих нулей.
     */
    private void removeLeadingZeros() {
        while(this.longIntegerList.size() > 1 && this.longIntegerList.get(this.longIntegerList.size() - 1) == 0) {
            this.longIntegerList.remove(this.longIntegerList.size() - 1);
        }

        if (this.longIntegerList.size() == 1 && this.longIntegerList.get(0) == 0) this.isPositive = true;
    }

    /**
     * Функция, предназначенная для побитового сдвига.
     */
    private void shiftRight() {
        if (this.equals(new LongInteger(0))) {
            return;
        }

        this.longIntegerList.add(this.longIntegerList.get(this.longIntegerList.size() - 1));

        for (int i = this.longIntegerList.size() - 2; i > 0; i--) this.longIntegerList.set(i, this.longIntegerList.get(i-1));
        this.longIntegerList.set(0, 0);
    }

    /**
     * Функция, предназначенная для изменения знака на противоположный.
     */
    public void makeOposite() {
        this.isPositive = !this.isPositive;
    }

    /**
     * Функция, предназначенная для получения числа с противоположным знаком.
     * @return число с противоположным знаком.
     */
    public LongInteger getOpposite() {
        LongInteger longIntegerListCopy = this.clone();
        longIntegerListCopy.isPositive = !longIntegerListCopy.isPositive;
        return longIntegerListCopy;
    }

    /**
     * Функция, предназначенная для проверки того, больше ли текущее число другого.
     * @param secondOperand длинное целое число, с которым сравниваем текущее.
     * @return истинность условия.
     */
    public boolean isMore(LongInteger secondOperand) {
        if (!this.isPositive) {
            if (!secondOperand.isPositive) return secondOperand.getOpposite().isMore(this.getOpposite());
            else return false;
        }

        else if (!secondOperand.isPositive) return true;
        else {
            if (this.longIntegerList.size() != secondOperand.longIntegerList.size())
                return this.longIntegerList.size() > secondOperand.longIntegerList.size();
            else {
                for (int i = this.longIntegerList.size() - 1; i >= 0; i--) {
                    if (!this.longIntegerList.get(i).equals(secondOperand.longIntegerList.get(i)))
                        return this.longIntegerList.get(i) > secondOperand.longIntegerList.get(i);
                }

                return false;
            }
        }
    }

    /**
     * Функция, предназначенная для проверки того, меньше ли текущее число другого.
     * @param secondOperand длинное целое число, с которым сравниваем текущее.
     * @return истинность условия.
     */
    public boolean isLess(LongInteger secondOperand) {
        if (!this.isPositive) {
            if (!secondOperand.isPositive) return secondOperand.getOpposite().isLess(this.getOpposite());
            else return true;
        }

        else if (!secondOperand.isPositive) return false;
        else {
            if (this.longIntegerList.size() != secondOperand.longIntegerList.size())
                return this.longIntegerList.size() < secondOperand.longIntegerList.size();
            else {
                for (int i = this.longIntegerList.size() - 1; i >= 0; i--) {
                    if (!this.longIntegerList.get(i).equals(secondOperand.longIntegerList.get(i)))
                        return this.longIntegerList.get(i) < secondOperand.longIntegerList.get(i);
                }

                return false;
            }
        }
    }

    /**
     * Функция, предназначенная для выполнения сложения двух длинных целых чисел.
     * @param secondOperand длинное целое число, с которым мы складываем текущее.
     * @return сумма чисел.
     */
    public LongInteger add(LongInteger secondOperand) {
        if (!this.isPositive) {
            if (!secondOperand.isPositive) return (this.getOpposite().add(secondOperand.getOpposite())).getOpposite();
            else return secondOperand.subtract(this.getOpposite());
        } else if (!secondOperand.isPositive) return this.subtract(secondOperand.getOpposite());

        int memoryDigit  = 0;
        List<Integer> answerList = new ArrayList<>(this.longIntegerList);

        for (int i = 0; i < Math.max(this.longIntegerList.size(), secondOperand.longIntegerList.size()) || memoryDigit != 0; i++) {
            if (i == this.longIntegerList.size()) answerList.add(0);
            answerList.set(i, answerList.get(i) + memoryDigit
                    + (i < secondOperand.longIntegerList.size() ? secondOperand.longIntegerList.get(i) : 0));
            memoryDigit = answerList.get(i) >= BASE ? 1 : 0;
            if (memoryDigit != 0) answerList.set(i, answerList.get(i) - BASE);
        }

        StringBuilder answerString = new StringBuilder();
        answerString.append(answerList.get(answerList.size() - 1));

        for (int i = answerList.size() - 2; i >= 0; i--)
            answerString.append(String.format("%09d", answerList.get(i)));

        return new LongInteger(new String(answerString));
    }

    /**
     * Функция, предназначенная для выполнения сложения длинного целого числа с целым числом.
     * @param secondOperand целое число, с которым мы складываем текущее.
     * @return сумма чисел.
     */
    public LongInteger add(int secondOperand) {
        return this.add(new LongInteger(secondOperand));
    }

    /**
     * Функция, предназначенная для выполнения разности двух длинных целых чисел.
     * @param secondOperand длинное целое число, которое мы отнимаем от текущего.
     * @return разность чисел.
     */
    public LongInteger subtract(LongInteger secondOperand) {
        if (!secondOperand.isPositive) return this.add(secondOperand.getOpposite());
        else if (!this.isPositive) return (this.getOpposite().add(secondOperand)).getOpposite();
        else if (this.isLess(secondOperand)) return (secondOperand.subtract(this)).getOpposite();

        int memoryDigit = 0;
        List<Integer> answerList = new ArrayList<>(this.longIntegerList);

        for (int i = 0; i < secondOperand.longIntegerList.size() || memoryDigit != 0; i++) {
            answerList.set(i, answerList.get(i) - (memoryDigit + (i < secondOperand.longIntegerList.size() ?
                    secondOperand.longIntegerList.get(i) : 0)));
            memoryDigit = answerList.get(i) < 0 ? 1 : 0;
            if (memoryDigit != 0) answerList.set(i, answerList.get(i) + BASE);
        }


        StringBuilder answerString = new StringBuilder();
        answerString.append(answerList.get(answerList.size() - 1));

        for (int i = answerList.size() - 2; i >= 0; i--)
            answerString.append(String.format("%09d", answerList.get(i)));

        return new LongInteger(new String(answerString));
    }

    /**
     * Функция, предназначенная для выполнения разности длинного целого числа и целого числа.
     * @param secondOperand целое число, которое мы отнимаем от текущего.
     * @return разность чисел.
     */
    public LongInteger subtract(int secondOperand) {
        return this.subtract(new LongInteger(secondOperand));
    }

    /**
     * Функция, предназначенная для выполнения произведения двух длинных целых чисел.
     * @param secondOperand длинное целое число, которое мы умножаем на текущее.
     * @return произведение чисел.
     */
    public LongInteger multiply(LongInteger secondOperand) {
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < this.longIntegerList.size() + secondOperand.longIntegerList.size(); i++) answerList.add(0);
        for (int i = 0; i < this.longIntegerList.size(); i++) {
            int memoryDigit = 0;
            for (int j = 0; j < secondOperand.longIntegerList.size() || memoryDigit != 0; j++) {
                long currentElement = answerList.get(i+j) + (long) this.longIntegerList.get(i) *
                        (j < secondOperand.longIntegerList.size() ? secondOperand.longIntegerList.get(j) : 0) + memoryDigit;
                answerList.set(i+j, (int)(currentElement % BASE));
                memoryDigit = (int)(currentElement / BASE);
            }
        }

        StringBuilder answerString = new StringBuilder();
        answerString.append(answerList.get(answerList.size() - 1));

        for (int i = answerList.size() - 2; i >= 0; i--)
            answerString.append(String.format("%09d", answerList.get(i)));

        LongInteger answerLongInteger = new LongInteger(new String(answerString));
        answerLongInteger.isPositive = this.isPositive == secondOperand.isPositive;

        return answerLongInteger;
    }

    /**
     * Функция, предназначенная для выполнения произведения длинного целого числа и целого числа.
     * @param secondOperand целое число, которое мы умножаем на текущее.
     * @return произведение чисел.
     */
    public LongInteger multiply(int secondOperand) {
        return this.multiply(new LongInteger(secondOperand));
    }

    /**
     * Функция, предназначенная для выполнения деления двух длинных целых чисел.
     * @param secondOperand длинное целое число, на которое мы делим текущее.
     * @return частное чисел.
     */
    public LongInteger division(LongInteger secondOperand) {
        if (secondOperand.equals(new LongInteger("0"))) throw new RuntimeException("Division by zero!");
        LongInteger secondOperandCopy = secondOperand.clone();
        secondOperandCopy.isPositive = true;
        List<Integer> answerList = new ArrayList<>();
        LongInteger current = new LongInteger("0");
        for (int i = 0; i < this.longIntegerList.size(); i++) answerList.add(0);

        for (int i = this.longIntegerList.size() - 1; i >= 0; i--) {
            current.shiftRight();
            current.longIntegerList.set(0, this.longIntegerList.get(i));
            current.removeLeadingZeros();
            int midCopy = 0, left = 0, right = BASE;

            while(left <= right) {
                int mid = (left + right) / 2;
                LongInteger temporary = secondOperandCopy.multiply(new LongInteger(mid));
                if (temporary.isLess(current) || temporary.equals(current)) {
                    midCopy = mid;
                    left = mid + 1;
                }
                else right = mid - 1;
            }

            answerList.set(i, midCopy);
            current = (secondOperandCopy.multiply(new LongInteger(midCopy))).getOpposite().add(current);
        }

        StringBuilder answerString = new StringBuilder();
        answerString.append(answerList.get(answerList.size() - 1));

        for (int i = answerList.size() - 2; i >= 0; i--)
            answerString.append(String.format("%09d", answerList.get(i)));

        LongInteger answerLongInteger = new LongInteger(new String(answerString));
        answerLongInteger.isPositive = this.isPositive == secondOperand.isPositive;

        return answerLongInteger;
    }

    /**
     * Функция, предназначенная для выполнения деления длинного целого числа на целое число.
     * @param secondOperand целое число, на которое мы делим текущее.
     * @return частное чисел.
     */
    public LongInteger division(int secondOperand) {
        return this.division(new LongInteger(secondOperand));
    }

    /**
     * Функция, предназначенная для выполнения постфиксного инкремента.
     * @return значение числа до увеличения.
     */
    public LongInteger postIncrement() {
        LongInteger thisCopy = this.clone();
        this.longIntegerList = this.add(1).longIntegerList;
        this.isPositive = this.add(1).isPositive;
        return thisCopy;
    }

    /**
     * Функция, предназначенная для выполнения префиксного инкремента.
     * @return значение числа после увеличения.
     */
    public LongInteger preIncrement() {
        this.longIntegerList = this.add(1).longIntegerList;
        this.isPositive = this.add(1).isPositive;
        return this;
    }

    /**
     * Функция, предназначенная для выполнения постфиксного декримента.
     * @return значение числа до уменьшения.
     */
    public LongInteger postDecrement() {
        LongInteger thisCopy = this.clone();
        this.longIntegerList = this.subtract(1).longIntegerList;
        this.isPositive = this.subtract(1).isPositive;
        return thisCopy;
    }

    /**
     * Функция, предназначенная для выполнения префиксного декримента.
     * @return значение числа после уменьшения.
     */
    public LongInteger preDecrement() {
        this.longIntegerList = this.subtract(1).longIntegerList;
        this.isPositive = this.subtract(1).isPositive;
        return this;
    }

    /**
     * Функция, предназначенная для сравнения двух длинных целых чисел.
     * @param secondOperand сравниваемый элемент.
     * @return истинность условия.
     */
    @Override
    public boolean equals(Object secondOperand) {
        if (this.getClass() != secondOperand.getClass()) return false;

        if (this.isPositive != ((LongInteger) secondOperand).isPositive) return false;
        if (this.longIntegerList.size() != ((LongInteger) secondOperand).longIntegerList.size()) return false;

        for (int i = 0; i < this.getLongIntegerList().size(); i++) {
            if (!this.longIntegerList.get(i).equals(((LongInteger)secondOperand).longIntegerList.get(i))) return false;
        }

        return true;
    }

    /**
     * Функция, предназначенная для получения хэш-кода текущего объекта.
     * @return хэш-код текущего объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(longIntegerList, isPositive);
    }

    /**
     * Функция, предназначенная для копирования текущего объекта.
     * @return копия текущего объекта.
     */
    @Override
    public LongInteger clone() {
        try {
            return (LongInteger) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

