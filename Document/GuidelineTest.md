# Guideline Test

****

## 1. JUnit 5

* JUnit là một java testing framework được sử dụng rộng rãi trong các dự án java. 


* JUnit 5 là phiên bản mới của JUnit với mục đích hỗ trợ các tính năng mới ra mắt từ Java 8 trở về sau. 


* JUnit 5 gồm có 3 module khác nhau từ 3 sub-project khác nhau:
    
    * JUnit platform
    * JUnit Jupiter
    * JUnit Vintage
    
### 1.1 JUnit Platform

* JUnit platform chịu trách nhiệm khởi chạy JUnit framework trên JVM. Nó định nghĩa các interface mạnh mẽ và ổn định để tương thích tốt với các môi trường khác nhau như chạy trên các IDE khác nhau.

* Ngoài ra, JUnit Platform cũng định nghĩa TestEngine API để phát triển các testing framework chạy nên JUnit platform.

### 1.2. JUnit Jupiter

* JUnit Jupiter bao gồm các mô hình lập trình và mở rộng mới phục vụ để viết unit test trong JUnit 5. 
  
* Nó bao gồm các annotation từ JUnit 4 và các annotation mới được thêm trong JUnit 5:

    ```
    @Test – Đặt ở đầu method để thông báo rằng method được dùng để kiểm thử(test method).
    @ParameterizedTest – Test method chạy nhiều lần với các tham số khác nhau.
    @RepeatedTest  -Test method chạy kiểm thử lặp lại n lần.
    @TestFactory – Chỉ định method là một test factory cho các dynamic test.
    @TestTemplate – Chỉ định method là một mẫu cho các test case.
    @TestMethodOrder – Cấu hình thứ tự thực thi cho các @Test.
    @TestInstance – Quy định vòng đời của cho các annotation test class (@Nested, biến static etc)
    @DisplayName – Đặt tên cho test class hoặc test method.
    @DisplayNameGeneration – Đặt tên cho các test class được generate.
    @BeforeEach – chỉ định 1 method luôn được thực thi trước mỗi test method thực thi.
    @AfterEach – chỉ định 1 method luôn thực thi sau khi 1 test method thực thi xong.
    @BeforeAll – Chỉ định method sẽ được thực thi đầu tiên trong test class.
    @AfterAll – Chỉ định method sẽ được thực thi khi tất cả các test method trong class thực thi xong.
    @Disable – Vô hiệu hoá một test method hay một test class thực thi (Tương ứng với @Ignore ở version cũ).
    @Nested
    @Tag
    @ExtendWith
    ```
  
#### 1.2.1 @Test
* Annotation này biểu thị rằng method này là method test. Không giống với annotation @Test trong JUnit 4, anotaion này không chứa bất kì một attribute nào. Từ khi test mở rộng trong JUnit Jupiter hoạt động dựa trên các annotation của riêng nó. Các phương thức như vậy được kế thừa trừ khi nó bị ghi đè.

* **Example:**
    ```
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import org.junit.jupiter.api.Test;
    class FirstJUnit5Tests {
        @Test
        void myFirstTest() {
            assertEquals(2, 1 + 1);
        }
    }
    ```
  
#### 1.2.2 @RepeatedTest
* Annotation này cung cấp một khả năng lặp lại test một số lần xác định một cách đơn giản bằng cách chú thích một method bằng annotation @RepeatedTest và xác định tổng số lần lặp lại mong muốn. Mỗi lời gọi của phép lặp test hoạt động giống như việc thực hiện một method test @Test thông thường với sự hỗ trợ đầy đủ cho các callbacks và các phần mở rộng vòng đời giống nhau.


* **Example:**
    ```
    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }
    ```

#### 1.2.3 @DisplayName
* Test class và test method có thể định nghĩa tên hiển thị một cách tùy biến với khoảng trắng, kí tự đặc biệt, và emojis. Tên này sẽ được hiển thị trong test runners và test report.


* **Example:**
    ```
    @DisplayName("Example Test Method with No Business Logic")
    void test() {
        assertTrue(3 > 0);
    }
    ```

#### 1.2.4 @BeforeEach
* Annotation này biểu thị rằng phương thức được chú thích phải được thực hiện trước mỗi phương thức @Test, @RepeatedTest, @ParameterizedTest hoặc @TestFactory trong lớp hiện tại. Tương tự với @Before của JUnit 4. Các phương thức như vậy được kế thừa trừ khi chúng bị ghi đè.


* **Example:**
    ```
    class StandardTests {
        @BeforeEach
        void init() {
        }
        @Test
        void succeedingTest() {
        }
        @AfterEach
        void tearDown() {
        }
    }
    ```
  
#### 1.2.5 @AfterEach
* Annotation này biểu thị rằng phương thức được chú thích nên được thực hiện sau mỗi phương thức @Test, @RepeatedTest, @ParameterizedTest hoặc @TestFactory trong lớp hiện tại. Tương tự như @After của JUnit 4. Các phương thức như vậy được kế thừa trừ khi chúng bị ghi đè.

* **Example:**
    ```
    class StandardTests {
        @BeforeEach
        void init() {
        }
        @Test
        void succeedingTest() {
        }
        @AfterEach
        void tearDown() {
        }
    }
    ```
  
#### 1.2.6 @BeforAll
* Annotation này biểu thị rằng phương thức được chú thích phải được thực hiện trước tất cả các phương thức @Test, @RepeatedTest, @ParameterizedTest hoặc @TestFactory trong lớp hiện tại. Tương tự như @BeforeClass của JUnit 4.


* **Example:**
    ```
    class StandardTests {
        @BeforeAll
        static void initAll() {
        }
        @Test
        void succeedingTest() {
        }
        @AfterAll
        static void tearDownAll() {
        }
    }
    ```

#### 1.2.7 @AfterAll
* Annotation này biểu thị rằng phương thức được chú thích phải được thực hiện sau tất cả các phương thức @Test, @RepeatedTest, @ParameterizedTest hoặc @TestFactory trong lớp hiện tại. Tương tự như @AfterClass của JUnit 4.

* **Example:**
    ```
    class StandardTests {
        @AfterAll
        static void freeAll() {
        }
        @Test
        void succeedingTest() {
        }
        @AfterAll
        static void tearDownAll() {
        }
    }
    ```
#### 1.2.8 @Nested
* Annotation này có nghĩa là THỬ NGHIỆM, nó biểu thị rằng phương thức được chú thích phải được thực hiện trước tất cả @Test, @RepeatedTest, @Parameterize. Annotaion này biểu thị rằng lớp được chú thích là lớp thử nghiệm không lồng nhau, không tĩnh (non-static). Các phương thức @BeforeAll và @AfterAll không thể được sử dụng trực tiếp trong lớp thử nghiệm @Nested trừ khi vòng đời cá thể kiểm tra "per-class" được sử dụng. Chú thích như vậy không được kế thừa.
  

* Các thử nghiệm lồng nhau cho phép người viết thử nghiệm có thêm khả năng thể hiện mối quan hệ giữa một số nhóm thử nghiệm. Đây là một ví dụ phức tạp.
  

* Bộ thử nghiệm lồng nhau để kiểm tra ngăn xếp: phương thức @Test và @TestFactory trong lớp hiện tại. Tương tự với @BeforeClass của JUnit 4.
  

* **Example:**
    ```
    @DisplayName("A stack")
    class TestingAStackDemo {
    Stack<Object> stack;
    
        @Test
        @DisplayName("is instantiated with new Stack()")
        void isInstantiatedWithNew() {
            new Stack<>();
        }
        
        @Nested
        @DisplayName("when new")
        class WhenNew {
        
            @BeforeEach
            void createNewStack() {
                stack = new Stack<>();
            }
            
            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(stack.isEmpty());
            }
            
            @Test
            @DisplayName("throws EmptyStackException when popped")
            void throwsExceptionWhenPopped() {
                assertThrows(EmptyStackException.class, () -> stack.pop());
            }
            
            @Test
            @DisplayName("throws EmptyStackException when peeked")
            void throwsExceptionWhenPeeked() {
                assertThrows(EmptyStackException.class, () -> stack.peek());
            }
            
            @Nested
            @DisplayName("after pushing an element")
            class AfterPushing {
            
                String anElement = "an element";
                
                @BeforeEach
                void pushAnElement() {
                    stack.push(anElement);
                }
                
                @Test
                @DisplayName("it is no longer empty")
                void isNotEmpty() {
                    assertFalse(stack.isEmpty());
                }
                
                @Test
                @DisplayName("returns the element when popped and is empty")
                void returnElementWhenPopped() {
                    assertEquals(anElement, stack.pop());
                    assertTrue(stack.isEmpty());
                }
                
                @Test
                @DisplayName("returns the element when peeked but remains not empty")
                void returnElementWhenPeeked() {
                    assertEquals(anElement, stack.peek());
                    assertFalse(stack.isEmpty());
                }
            }
        }
    }
    ```
  
#### 1.2.9 @Tag
* Annotation này được sử dụng để khai báo các thẻ cho việc kiểm tra lọc, ở cấp class hoặc method. Tương tự như test nhóm thử nghiệm trong TestNG hoặc Categories trong JUnit 4. Chú thích như vậy được kế thừa ở cấp class chứ không phải ở cấp method.

* **Example:**
    ```
    @Tag("fast")
    @Tag("model")
    class TaggingDemo {
    
        @Test
        @Tag("taxes")
        void testingTaxCalculation() {
        }
    }
    ```

#### 1.2.10 @Disabled
* Annotation này dùng để vô hiệu hóa một class hay method test, nó tương tự như @Ignore của JUnit 4. Annotation này không được phép kế thừa.
  

* **Example:**
    ```
    @Disabled
    class DisabledClassDemo {
        @Test
        void testWillBeSkipped() {
        }
    }
    ```

#### 1.2.11 @ExtendWith
* Annotation này được dùng để đăng ký các mở rộng tùy biến. Ví dụ để đăng ký một tùy biến RandomParametersExtension cho một method cụ thể, chúng ta chú thích method test như sau.


* **Example:**
    ```
    @ExtendWith(RandomParametersExtension.class)
    @Test
        void test(@Random int i) {
        // ...
    }
    ```

#### 1.2.12 @TestFactory
* Test động được tạo ra khi chạy - runtime bởi method Factory được chú thích bằng @TestFactory. Lớp DynamicTestsDemo sau đây trình bày một số ví dụ về các test Factory và các test động.


* **Example:**
    ```
    @TestFactory
    Iterable<DynamicTest> dynamicTestsFromIterable() {
        return Arrays.asList(
            dynamicTest("3rd dynamic test", () -> assertTrue(true)),
            dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2))
        );
    }
    ```

#### 1.2.13 @TestInstance
* Annotation này dùng để cấu hình vòng đời cho một đối tượng test cụ thể. Ví dụ, nếu bạn muốn JUnit Jupiter thực hiện tất cả các phương thức test trên cùng một đối tượng test cụ thể, chỉ cần chú thích class test của bạn với @TestInstance

#### 1.2.14 @TestTemplate
* Một phương thức @TestTemplate không phải là trường hợp test thông thường mà là mẫu cho các trường hợp test. Như vậy, nó được thiết kế để được gọi nhiều lần tùy thuộc vào số lượng ngữ cảnh yêu cầu được trả về bởi các provider đã đăng ký. Do đó, nó phải được sử dụng kết hợp với phần mở rộng TestTemplateInvocationContextProvider đã đăng ký.

* **Example:**

    ```
    @TestTemplate
    @ExtendWith(MyTestTemplateInvocationContextProvider.class)
    void testTemplate(String parameter) {
        assertEquals(3, parameter.length());
    }
    
    public class MyTestTemplateInvocationContextProvider implements TestTemplateInvocationContextProvider {
        @Override
        public boolean supportsTestTemplate(ExtensionContext context) {
            return true;
        }
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return Stream.of(invocationContext("foo"), invocationContext("bar"));
        }
        private TestTemplateInvocationContext invocationContext(String parameter) {
            return new TestTemplateInvocationContext() {
                @Override
                public String getDisplayName(int invocationIndex) {
                    return parameter;
                }
                @Override
                public List<Extension> getAdditionalExtensions() {
                    return Collections.singletonList(new ParameterResolver() {
                        @Override
                        public boolean supportsParameter(ParameterContext parameterContext,
                                ExtensionContext extensionContext) {
                            return parameterContext.getParameter().getType().equals(String.class);
                        }
                        @Override
                        public Object resolveParameter(ParameterContext parameterContext,
                                ExtensionContext extensionContext) {
                            return parameter;
                        }
                    });
                }
            };
        }
    }
    ```
### 1.3. JUnit Vintage

* Hỗ trợ chạy các verrsion cũ của JUnit(3, 4) được viết trước đó khi hệ thống cập nhật lên JUnit 5.

### 1.4. Assertion và Assumption JUnit

* Assertion dùng để kiểm tra kết quả của quá trình thực thi. Project phải luông cố gắng đạt đến trạng trái không có mệnh đề Assertion nào bị false vì bất cứ lý do gì. 

    #### 1.4.1 assertTrue
    * assertTrue sử dụng để kiểm tra xem kết quả có phải là true hay không?

        ```
        @Test
        void assertTrueEx() {
        assertTrue(Stream.of(1, 2, 3)
            .mapToInt(i -> i)
            .sum() > 5, () -> "Sum should be greater than 5");
        }
        ```

    #### 1.4.2 assertEquals
    * assertEquals(expected, actual) được sử dụng để kiểm tra kết quả tính toán có giống với kết quả mong đợi hay không?
    
        ```
        @Test
        void assertEqualsEx() {
            assertEquals(7, 5 + 2);
        }
        ```
  
    #### 1.4.3 assertAll
    * Ngoài ra, chúng ta còn có thể kết hợp nhiều assertion với nhau thông qua assertAll.
    
        ```
        @Test
        void assertAllEx() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
            () -> assertEquals(numbers[0], 1),
            () -> assertTrue(numbers[3] == 3, "should be 3"),
            () -> assertEquals(numbers[4], 1)
            );
        }
        ```
  
    #### 1.4.4 Assumption 
    * Assumption được sử dụng để đảm bảo rằng các dữ liệu được chuẩn bị cho test method phải đúng trước khi chúng thực thi. Nếu các điều kiện bên trong Assumption false, test method sẽ bị ignore.
    
        ##### 1.4.4.1 assumeTrue
        * Đảm bảo điều kiện phải đúng trước khi thực thi test method.
        
            ```
            @Test
            public void assumpTrue() {
                int x = 5, y = 4;
                assumeTrue(x < y);
                assertTrue(x - y < 0);
            }
            
            // Output
            org.opentest4j.TestAbortedException: Assumption failed: assumption is not true
            ```
        
        ##### 1.4.4.2 assumeFalse
        * Đảm bảo điều kiện phải trả về false trước khi thực thi test method.
        
            ```
            @Test
            public void assumpFalse() {
                int x = 5, y = 4;
                assumeFalse(x < y);
                assertTrue(x - y < 0);
            }
            ```
        
        ##### 1.4.4.3 assumeThat
        * Sử dụng assumeThat để kết hợp assume và assert cách nhanh chóng.
        
            ```
            @Test
            void assumptionThat() {
                String someString = "Just a string";
                assumingThat(
                    someString.equals("Just a string"),
                    () -> assertEquals(2 + 2, 4)
                );
            }
            ```
            
    #### 1.4.5 Exception Testing
    * Đôi lúc chúng ta mong đợi kết quả trả về là một exception vì dữ liệu của chúng ta không đúng hoặc dữ liệu xung đột etc. Sử dụng assertThrows() để mong đợi kết quả trả về là một exception.
    
        ```
        @Test
        void shouldThrowException() {
            Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
                throw new UnsupportedOperationException("Not supported");
            });
            assertEquals(exception.getMessage(), "Not supported");
        }
      
        @Test
        void assertThrowsException() {
            String str = null;
            assertThrows(IllegalArgumentException.class, () -> {
                Integer.valueOf(str);
            })5
        }
        ```
    
    #### 1.4.6 Test Suite
    * JUnit Test suite cho phép chúng ta chạy một lúc nhiều test class, tính năng này sẽ rất hữu ích so với việc chúng ta phải chọn từng test class để chạy. 
    
    * Thực thi trong IntellIJ cũng cho phép chọn một package để chay tất cả các test class trong đó. 
    
    * Nếu các bạn dùng IDE khác thì có thể áp dụng nhé. JUnit cung cấp @SelectPackages and @SelectClasses để khởi tạo một test suite.
    
    #### 1.4.7 Dynamic test
    * JUnit 5 cho phép chúng ta tạo các test case tạo thời điểm runtime bằng cách định nghĩa chúng bên trong method được bao bởi @TestFactory. 
    
    * @TestFactory method phải trả về các kiểu dữ liệu dạng danh sách như Stream, Collection, Iterable, etc. Ngoài ra @TestFactory method không được là static method hoặc access private.
    
## 2. Mockito

### 2.1 Giới thiệu:

* **Example:** Các kịch bản sau thường diễn ra:

    * Chức năng A gọi tới chức năng B. tuy nhiên, B chưa viết xong 
    * Chức năng A gọi tới chức năng B. tuy nhiên, B khởi tạo rất phức tạp (truy xuất Database, yêu cầu nhiều params, v.v.)
    * Muốn test chức năng A, tuy nhiên A yêu cầu nỗ lực lớn của cả hệ thống (Yêu cầu có HTTP-server, authorize, v.v.)

    ```
    // Giả sử muốn test hàm này
    public int getAllData(){
    
        // Giả sử như thằng driver không được khởi tạo hoặc bị lỗi
        // thì function này coi như hỏng
    
        MySQLdriver.connect() // Kết nối xuống Database 
        var data = MySQLdriver.get()// Lấy dữ liệu
    
        // Chúng ta muốn test logic xử lý dữ liệu ở dưới đây, 
        // mà k cần kết nối databse, phải làm sao?
    
        // Xử lý dữ liệu 
        ...
        // trả ra dữ liệu
        return data
    }
    ```
    
* Rất nhiều kịch bản phức tạp xảy ra, mà trên thực tế, chúng ta chỉ mong muốn confirm rằng A ổn, chứ thằng B, C, D gì đó thì hãy cứ tạm coi là nó "đã ổn" đi.

* Để đơn giản hoá các kịch bản test như trên, khái niệm Mock ra đời.

* Tư tưởng của Mock đơn giản là khi muốn test (A gọi B) thì thay vì tạo ra một đối tượng B thật sự, bạn tạo ra một thằng B' giả mạo, có đầy đủ chức năng như B thật (nhưng không phải thật)

* Bạn sẽ giả lập cho B' biết là khi thằng A gọi tới nó, nó cần làm gì, trả lại cái gì (hardcode). Miễn làm sao cho nó trả ra đúng cái thằng A cần để chúng ta có thể test A thuận lợi nhất.
    
    ```
    // Đại loại như này
    // Khi driver.get() được gọi, hãy trả ra một List(1,2,3)
    Mockito.when(driver.get())
           .thenReturn(Arrays.asList(1, 2, 3));
    ```

    => Ở trong Java, Mockito chính là thư viện nổi tiếng nhất để các bạn làm việc này.
  
### 2.2 Cách Mock

* Khái niệm cơ bản đầu tiên, đó là làm sao để tạo ra một đối tượng bị Mock.

#### 2.2.1 Cách 1: Mockito.mock()

* Sử dụng Mockito.mock() để tạo ra một object của Class bạn yêu cầu, nó thậm chí còn không quan tâm hàm khởi tạo của Class ý như nào và ra sao, vì nó không có thật. 

    ```
    @Test
    public void testUserMockFunction() {
    List mockList = Mockito.mock(List.class);
    
        Mockito.when(mockList.size()).thenReturn(2);
    
        Assert.assertEquals(2, mockList.size());
    }
    ```
  
#### 2.2.2 Cách 2: Sử dụng @Mock

* Bạn muốn mock cái gì, thì đánh dấu @Mock lên nó.

    ```
    @Mock
    List<String> mockedList;
    ```
  
* Tuy nhiên, gắn @Mock là chưa đủ, cần kích hoạt Mockito để nó mock các object đó.


* Sau khi kích hoạt, thì tất cả các object được gắn @Mock sẽ trở thành 1 object giả mạo, và đã được khởi tạo (tức là != null). Các cách kích hoạt như sau:

    * **Cách 1:** Gắn @RunWith(MockitoJUnitRunner.class) lên class test của bạn:
    
        ```
        // Cách 1
        @RunWith(MockitoJUnitRunner.class)
        public class MockitoAnnotationTest {
        @Mock
        List<String> mockedList;
        }
        ```
    * **Cách 2**: tạo ra một đối tượng MockitoRule bên trong class test của bạn.
    
    ```
    public class MockitoAnnotationTest {

        @Rule
        public MockitoRule initRule = MockitoJUnit.rule();
        
        @Mock
        List<String> mockedList;
    }
    ```
  
    * **Cách 3:** Sử dụng Mockito.init()
    
    ```
    public class MockitoAnnotationTest {

        @Mock
        List<String> mockedList;
    
        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
        }
    }
    ```
  
### 2.3 Cách sử dụng

* Mockito cung cấp rất nhiều methods khác nhau để giúp bạn giả lập đầy đủ các thứ bạn cần. Hay sử dụng nhất là hàm **when()**.

    ```
    // Trả là size 100 khi gọi hàm size()
    Mockito.when(mockedList.size()).thenReturn(100);
    // Throw exception khi gọi hàm size()
    Mockito.when(mockedList.size()).thenThrow(NullPointerException.class);
    // Bạn có thể đổi ngược cách viết, còn logic vẫn vậy
    // Ném ra exception khi gọi hàm .get() với tham số truyền vào bất kì 
    Mockito.doThrow(NullPointerException.class).when(mockedList.get(Mockito.any()));
    ```

### 2.4 Spy

* Spy là việc sửa một đối tượng Thật -> Giả

    ```
    @RunWith(MockitoJUnitRunner.class)
    public class SpyTest {
        @Spy
        List<String> list = new ArrayList<>();
    
        @Test
        public void testSpy() {
            list.add("one");
            list.add("two");
            // show the list items
            System.out.println(list);
    
            Mockito.verify(list).add("one");
            Mockito.verify(list).add("two");
    
            // @Spy thực sự gọi hàm .add của List nên nó có size là 2 mà không cần giả lập
            Assert.assertEquals(2, list.size());
    
            // Vẫn có thể làm giả thông tin gọi hàm với @Spy
            Mockito.when(list.size()).thenReturn(100);
    
            Assert.assertEquals(100, list.size());
        }
    }
    ```
  
### 2.5 Captor

* Captor có nhiệm vụ ghi lại các tham số của đối tượng.

    ```
    @RunWith(MockitoJUnitRunner.class)
    public class CaptorTest {
        @Mock
        List<Object> list;
    
        @Captor
        ArgumentCaptor<Object> captor;
    
        @Test
        public void testCaptor1() {
            list.add(1);
            // Capture lần gọi add vừa rồi có giá trị là gì
            Mockito.verify(list).add(captor.capture());
    
            System.out.println(captor.getAllValues());
    
            Assert.assertEquals(1, captor.getValue());
        }
    }
    ```
  
### 2.6 Inject Mock

* **Example:** Lớp Service chứa lớp DatabaseDriver như sau:

    ```
    public interface DatabaseDriver {
        List<Object> get() throws SQLException;
    }
    
    @Data
    @AllArgsConstructor
    public static class SuperService {
        DatabaseDriver driver;
    
        public List<Object> getObjects() throws SQLException {
            System.out.println("LOG: Getting objects");
            List<Object> list = driver.get();
    
            System.out.println("LOG: Sorting");
            Collections.sort(list, Comparator.comparingInt(value -> Integer.valueOf(value.toString())));
    
            System.out.println("LOG: Done");
            return list;
        }
    }
    ```
  
* Rõ ràng thì driver chính là mắt xích trong trong việc SuperService có hoạt động được hay không. Vì thế, muốn test được SuperService, chúng ta phải mock đối tượng driver.

* Kịch bản bây giờ sẽ là mock một đối tượng của DatabaseDriver rồi truyền nó vào đối tượng SuperService.

* Để truyền một đối tượng mock vào một đối tượng khác, chúng ta dùng @InjectMocks.

    ```
    @RunWith(MockitoJUnitRunner.class)
    public class InjectMockTest {
        @Mock
        DatabaseDriver driver;
    
        /**
         * Inject driver vào superService.
         * Mọi người có thể liên tưởng nó giống với Spring Injection
         */
        @InjectMocks
        SuperService superService;
    
        @Test(expected = SQLException.class)
        public void testInjectMock() throws SQLException {
            // Giả lập cho driver luôn trả về list (3,2,1) khi được gọi tới
            Mockito.doReturn(Arrays.asList(3, 2, 1)).when(driver).get();
    
            Assert.assertEquals(driver, superService.getDriver());
    
            // Test xem superService trả ra ngoài có đúng không?
            Assert.assertEquals(Arrays.asList(1, 2, 3), superService.getObjects());
    
            // Giả lập cho driver bắn exception
            Mockito.when(driver.get()).thenThrow(SQLException.class);
            superService.getObjects();
        }
    }
    ```
  
## 3 Test Spring Boot

### 3.1 Vấn đề Test + Spring

* Chúng ta đều biết rằng Spring Boot sẽ phải tạo Context và tìm kiếm các Bean và nhét vào đó. Sau tất cả các bước config và khởi tạo thì chúng ta sử dụng @Autowired để lấy đối tượng ra sử dụng.


* Vấn đề đầu tiên bạn nghĩ tới khi viết Test sẽ là làm sao @Autowired bean vào class Test được và làm sao cho JUnit hiểu @Autowired là gì.


* Hướng giải quyết là tích hợp Spring vào với JUnit.


### 3.2 @RunWith(SpringRunner.class)

* Spring Boot đã thiết kế ra lớp SpringRunner, sẽ giúp chúng ta tích hợp Spring + JUnit.


* Để test trong Spring, trong mọi class Test chúng ta sẽ thêm @RunWith(SpringRunner.class) lên đầu Class Test đó.

    ```
    @RunWith(SpringRunner.class)
    public class TodoServiceTest {
    ...
    }
    ```

* Khi chạy TodoServiceTest nó sẽ tạo ra một Context riêng để chứa bean trong đó, vậy là chúng ta có thể @Autowired thoải mái trong nội hàm Class này.


* Vấn đề tiếp theo là làm sao đưa Bean vào trong Context.

    * Có 2 cách:
    
        * @SpringBootTest
    
        * @TestConfiguration
    
#### 3.2.1 @SpringBootTest

* @SpringBootTest sẽ đi tìm kiếm class có gắn @SpringBootApplication và từ đó đi tìm toàn bộ Bean và nạp vào Context.


* **Note**: Nên sử dụng trong trường hợp muốn Integration Tests, vì nó sẽ tạo toàn bộ Bean, không khác gì bạn chạy cả cái SpringApplication.run(App.class, args);, rất tốn thời gian mà rất nhiều Bean thừa thãi, không cần dùng đến cũng khởi tạo.

    ```
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class TodoServiceTest {
    
        /**
         * Cách này sử dụng @SpringBootTest
         * Nó sẽ load toàn bộ Bean trong app của bạn lên,
         * Giống với việc chạy App.java vậy
         */
    
        @Autowired
        private TodoService todoService;
    }
    ```
  
#### 3.2.2 @TestConfiguration

* @TestConfiguration giống với @Configuration, chúng ta tự định nghĩa ra Bean.


* Các Bean được tạo bởi @TestConfiguration chỉ tồn tại trong môi trường Test. Rất phụ hợp với việc viết UnitTest.


* Class Test nào, cần Bean gì, thì tự tạo ra trong @TestConfiguration

    ```
    @RunWith(SpringRunner.class)
    public class TodoServiceTest2 {
    
        /**
         * Cách này sử dụng @TestConfiguration
         * Nó chỉ tạo ra Bean trong Context test này mà thôi
         * Tiết kiệm thời gian hơn khi sử dụng @SpringBootTest (vì phải load hết Bean lên mà không dùng đến)
         */
        @TestConfiguration
        public static class TodoServiceTest2Configuration{
    
            /*
            Tạo ra trong Context một Bean TodoService
             */
            @Bean
            TodoService todoService(){
                return new TodoService();
            }
        }
    
        @Autowired
        private TodoService todoService;
    ```

### 3.3 @MockBean

* Spring hỗ trợ mock với annotation @MockBean, chúng ta có thể mock lấy ra một Bean "giả" mà không thèm để ý tới thằng Bean "thật". (Kể cả thằng "thật" có ở trong Context đi nữa, cũng không quan tâm).

    ```
    @RunWith(SpringRunner.class)
    public class TodoServiceTest2 {
    
        /**
         * Đối tượng TodoRepository sẽ được mock, chứ không phải bean trong context
         */
        @MockBean
        TodoRepository todoRepository;
    ```
  
### 3.4 @WebMvcTest

* Spring Boot hỗ trợ test Controller mà không cần khởi động Tomcat Server bằng annotation @WebMvcTest và sử dụng Mock.


* Bây giờ, khi muốn test một Controller nào đó, chúng ta làm như sau:

    ```
    @RunWith(SpringRunner.class)
    // Bạn cần cung cấp lớp Controller cho @WebMvcTest
    @WebMvcTest(TodoRestController.class)
    public class TodoRestControllerTest {
        /**
         * Đối tượng MockMvc do Spring cung cấp
         * Có tác dụng giả lập request, thay thế việc khởi động Server
         */
        @Autowired
        private MockMvc mvc;
    }
    ```
  
* **Example:**
  
    * Tạo 1 Controller:
        ```
        @RestController
        @RequestMapping("/api/v1")
        public class TodoRestController {
      
            @Autowired
            TodoService todoService;
            
            @GetMapping("/todo")
            public List<Todo> findAll(){
                return todoService.getAll();
            }
        }
        ```
      
    * TestController:
        ```
        @RunWith(SpringRunner.class)
        // Bạn cần cung cấp lớp Controller cho @WebMvcTest
        @WebMvcTest(TodoRestController.class)
        public class TodoRestControllerTest {
            /**
             * Đối tượng MockMvc do Spring cung cấp
             * Có tác dụng giả lập request, thay thế việc khởi động Server
             */
            @Autowired
            private MockMvc mvc;
        
            @MockBean
            private TodoService todoService;
        
            @Test
            public void testFindAll() throws Exception {
                // Tạo ra một List<Todo> 10 phần tử
                List<Todo> allTodos = IntStream.range(0, 10)
                                               .mapToObj(i -> new Todo(i, "title-" + i, "detail-" + i))
                                               .collect(Collectors.toList());
                
                // giả lập todoService trả về List mong muốn
                given(todoService.getAll()).willReturn(allTodos);
        
                mvc.perform(get("/api/v1/todo").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                   .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                   .andExpect(jsonPath("$", hasSize(10))) // Hi vọng server trả về List độ dài 10
                   .andExpect(jsonPath("$[0].id", is(0))) // Hi vọng phần tử trả về đầu tiên có id = 0
                   .andExpect(jsonPath("$[0].title", is("title-0"))) // Hi vọng phần tử trả về đầu tiên có title = "title-0"
                   .andExpect(jsonPath("$[0].detail", is("detail-0")));
            }
        }
        ```
      
### 3.5 @DataJpaTest

* Về cơ bản, chúng ta không thể mock hay làm giả dữ liệu của Database mãi được, nó sẽ là một lỗ hổng lớn trong hệ thống. Ngoài ra, bạn cũng sẽ không thể test được quá trình thao tác với Database của dự án.


* Để tập trung vào việc test các thao tác với Database, Spring Boot đã hỗ trợ chúng ta bằng @DataJpaTest


* Kết hợp giữa @DataJpaTest và h2-database (Memory database).

    ```
    @RunWith(SpringRunner.class)
    /**
     * Khi đánh dấu một class là @DataJpaTest.
     * Spring Boot sẽ load lên tất cả các Bean có liên quan tới tầng Repository
     * Thay vì load hết tất cả Bean như là @SpringBootTest
     */
    @DataJpaTest
    public class DataJpaAnnotationTest {
    
        @Autowired
        private DataSource dataSource;
        @Autowired
        private JdbcTemplate jdbcTemplate;
        @Autowired
        private EntityManager entityManager;
        @Autowired
        private TodoRepository todoRepository;
    
        @Test
        public void allComponentAreNotNull() {
            Assertions.assertThat(dataSource).isNotNull();
            Assertions.assertThat(jdbcTemplate).isNotNull();
            Assertions.assertThat(entityManager).isNotNull();
            Assertions.assertThat(todoRepository).isNotNull();
        }
    
    }
    ```
  
* @DataJpaTest có tác dụng khởi tạo các Bean cần thiết cho tầng Repository. Ngoài ra, nó không khởi tạo thêm các Bean thừa thãi nào khác. (Chuyên biệt hơn @SpringBootTest)


* **Example:** Cách Fake dữ liệu đơn giản nhất là tự insert bằng repository.

    ```
    import org.assertj.core.api.Assertions;
    import org.junit.After;
    
        @Test
        public void testTodoRepositoryByCode() {
            todoRepository.save(new Todo(0, "Todo-1", "Detail-1"));
            todoRepository.save(new Todo(0, "Todo-2", "Detail-2"));
    
            Assertions.assertThat(todoRepository.findAll()).hasSize(2);
            Assertions.assertThat(todoRepository.findById(1).getTitle()).isEqualTo("Todo-1");
        }
    
        @After
        public void clean() {
            todoRepository.deleteAll();
        }
    ```
  
### 3.6 @Sql

* Một cách làm hay hơn để chuẩn bị dữ liệu cho Test đó là sử dụng annotation @Sql


* @Sql có tác dụng load một hoặc nhiều file scripts sql lên và thực thi.


* **Example:** Ta có file createToDo.sql

    ```
    INSERT INTO todo (title, detail) VALUES ('Todo-1', 'Do homework');
    INSERT INTO todo (title, detail) VALUES ('Todo-2', 'Walking');
    ```
    
* Để chạy scripts này trong class Test, ta làm như sau:
    ```
    @RunWith(SpringRunner.class)
    @DataJpaTest
    public class SqlAnnotationTest {
        @Autowired
        private TodoRepository todoRepository;
    
        @Test
        @Sql("/createTodo.sql")
        public void testTodoRepositoryBySqlSchema() {
            Assertions.assertThat(todoRepository.findAll()).hasSize(2);
            Assertions.assertThat(todoRepository.findById(1).getTitle()).isEqualTo("Todo-1");
        }
    
    }
    ```
  








