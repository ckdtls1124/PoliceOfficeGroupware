
프로그래밍 요구사항

    1. 자바 코드 컨벤션을 지키면서 프로그래밍한다.(컨벤션은 각자의 재량 Google Java Style Guide 참고)
    
    2. indent(인덴트, 들여쓰기) depth를 2가 넘지 않도록 구현한다. 1까지만 허용한다.
        예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
        힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
        
    3. else 예약어를 쓰지 않는다.
        힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
        else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
        
        예시 : 
         String generateRandomNumbers() {
        int intResult = (int) Math.floor(Math.random() * 1000);
        
        String result="";
        if (intResult > 99) {
            result = result + String.valueOf(Integer.valueOf(intResult));
        }
        return result;
        }

        
    4. 모든 로직에 단위 테스트를 구현한다. 
        
        예시 : 
        
        @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void splitTest1() {
        String[] actual = "1,2".split(",");
        assertThat(actual[0]).contains("1");
    }

    @Test
    void splitTest2() {
        String[] actual = "1,2".split(",");
        assertThat(actual[1]).contains("2");
    }

    @Test
    void splitTest3() {
        String[] actual = "1".split(",");
        assertThat(actual).containsExactly("1");
    }

    @Test
    void splitTest4() {
        String actual = "(1,2)".substring(1, 4);
        System.out.println(actual);
    }

    @Test
    void charAtTest() {
        char actual = "abc".charAt(2);
    }
    
    5. 3항 연산자를 쓰지 않는다.(메소드 작성 시, 매개변수를 2개이내로 사용한다.)
    
    6. 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
    
    7. 코드의 흐름은 위에서 아래로 내려가는 것이 좋다.
    
    8. 변수명, 주석은 자세하게 쓰도록 한다.
    
    전체 반영 예시:
    
    
//    특정한 계산식을 처리하는 Test code ================================


// IOTest class ======================================

    public abstract class IOTest {
    public IOTest() {
    }

    protected void systemIn(String input) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        }
    }
// IOTest class ======================================

// StringTest class ======================================

    public class StringTest extends IOTest {
    String[] inputStringConvertToList() {

        systemIn("2 + 3 * 4 / 2 - 5 - 5");
        return ListConverter();
    }

    String[] ListConverter() {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        String[] values = value.split(" ");
        return values;
    }

    @Test
    void calculate2() {
        String[] calList = inputStringConvertToList();
        int[] numberList = stringNumArrayToIntArray(calList);
        String[] calSymbolList = toCalculateSymbolsArray(calList);
        int result = numberList[0];

        for (int i = 0; i < calSymbolList.length; ++i) {
            if (calSymbolList[i].equals("+")) {
                result = add(result, numberList[i + 1]);
            } else if (calSymbolList[i].equals("-")) {
                result = subtract(result, numberList[i + 1]);
            } else if (calSymbolList[i].equals("*")) {
                result = multiple(result, numberList[i + 1]);
            } else if (calSymbolList[i].equals("/")) {
                result = divide(result, numberList[i + 1]);
            }
        }

        System.out.println("This is final result :" + result);
    }

    int[] stringNumArrayToIntArray(String[] originalList) {
        int[] newNumberList = new int[originalList.length / 2 + 1];

        for (int index = 0; index < originalList.length; ++index) {
            if (index % 2 == 0) {
                newNumberList[index / 2] = stringNumToIntConverter(originalList[index]);
            }
        }

        return newNumberList;
    }

    String[] toCalculateSymbolsArray(String[] originalList) {
        String[] newCalSymbolList = new String[originalList.length / 2];

        for (int index = 0; index < originalList.length; ++index) {
            if (index % 2 == 1) {
                newCalSymbolList[index / 2] = originalList[index];
            }
        }

        return newCalSymbolList;
    }

    int stringNumToIntConverter(String a) {
        return Integer.parseInt(a);
    }

//    사칙연산

    int add(int a, int b) {
        return a + b;
    }

    int subtract(int a, int b) {
        return a - b;
    }

    int multiple(int a, int b) {
        return a * b;
    }

    int divide(int a, int b) {
        return b != 0 ? a / b : 0;
    }
}

// StringTest class ======================================
//    특정한 계산식을 처리하는 Test code ================================
