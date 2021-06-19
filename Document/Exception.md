# Exception 
****

## I. Exception Handling in Java

* Exception Handling trong java hay xử lý ngoại lệ trong java là một cơ chế mạnh mẽ để xử lý các lỗi runtime để có thể duy trì luồng bình thường của ứng dụng.

### 1. Exception là gì?
* Exception là một sự kiện xảy ra trong quá trình thực thi một chương trình Java, nó làm phá vỡ cái flow (luồng xử lý) bình thường của một chương trình, thậm chí chết chương trình.
  

* Một ngoại lệ có thể xảy ra với nhiều lý do khác nhau, nó nằm ngoài dự tính của chương trình. Một vài ngoại lệ xảy ra bởi lỗi của người dùng, một số khác bởi lỗi của lập trình viên và số khác nữa đến từ lỗi của nguồn dữ liệu vật lý. Chẳng hạn như:
    ```
    - Người dùng nhập dữ liệu không hợp lệ.
    - Truy cập ngoài chỉ số mảng.
    - Một file cần được mở nhưng không thể tìm thấy.
    - Kết nối mạng bị ngắt trong quá trình thực hiện giao tiếp hoặc JVM hết bộ nhớ.
    ```
### 2. Exception Handling
#### 2.1 Exception Handling là gì?
* Xử lý ngoại lệ (Exception Handling) trong java là một cơ chế xử lý các lỗi runtime để có thể duy trì luồng bình thường của ứng dụng.


* Quá trình xử lý exception được gọi là catch exception, nếu Runtime System không xử lý được ngoại lệ thì chương trình sẽ kết thúc.

    ![](https://gpcoder.com/wp-content/uploads/2017/11/img_5a0d9d7e54d5f.png)

#### 2.2 JVM xử lý Exceptions thế nào
* Khi một lỗi xảy ra trên một method, method đó sẽ tạo ra một object và đưa nó vào Runtime System. Object đó được gọi là Exception Object, nó chứa tất cả các thông tin về lỗi và trạng thái của chương trình khi xảy ra lỗi.


* Sau đó, Runtime System sẽ xử lý sẽ tìm cách xử lý ngoại lệ phù hợp được sử dụng tại method ấy. Nếu không có thì JVM tiếp tục tìm xử lý ngoại lệ phù hợp ở các method trên (là method gọi lớp hiện tại). Nếu không có method nào có xử lý ngoại lệ phù hợp thì Thread mà đang thực hiện chuỗi method xảy ra ngoại lệ bị ngắt. Nếu thread ấy là thread main thì chết chương trình.
![](https://gpcoder.com/wp-content/uploads/2017/11/exception-flow.png)
  

### 3. Lợi thế của Exception Handling trong java
* Lợi thế cốt lõi của việc xử lý ngoại lệ là duy trì luồng bình thường của ứng dụng. Ngoại lệ thường làm gián đoạn luồng bình thường của ứng dụng đó là lý do tại sao chúng ta sử dụng xử lý ngoại lệ.


* **Example:** Xét kịch bản sau:
    ```
    statement 1;  
    statement 2;  
    statement 3;  
    statement 4;  
    statement 5; //ngoại lệ xảy ra
    statement 6;  
    statement 7;  
    statement 8;  
    statement 9;  
    statement 10; 
    ```
  
* Giả sử có 10 câu lệnh trong chương trình của bạn và xảy ra trường hợp ngoại lệ ở câu lệnh 5, phần còn lại của chương trình sẽ không được thực thi, nghĩa là câu lệnh 6 đến 10 sẽ không chạy. Nếu chúng ta thực hiện xử lý ngoại lệ, phần còn lại của câu lệnh sẽ được thực hiện. Đó là lý do tại sao chúng ta sử dụng xử lý ngoại lệ trong java.

### 4. Hệ thống cấp bậc của các lớp ngoại lệ trong Java

![](https://viettuts.vn/images/java/exception-handling/exception-handling-trong-java.png)

### 5. Các kiểu của ngoại lệ
* Có hai loại ngoại lệ chính là: checked và unchecked. Còn Sun Microsystem nói rằng có ba loại ngoại lệ:
    * Checked Exception 
    * Unchecked Exception 
    * Error
    
### 6. Sự khác nhau giữa các ngoại lệ checked và unchecked
#### 6.1 Checked Exception
* Các lớp extends từ lớp Throwable ngoại trừ RuntimeException và Error được gọi là checked exception, ví dụ như Exception, SQLException vv. Các checked exception được kiểm tra tại compile-time.
#### 6.2 Unchecked Exception
* Các lớp extends từ RuntimeException được gọi là unchecked exception, ví dụ: ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException,... Các ngoại lệ unchecked không được kiểm tra tại compile-time mà chúng được kiểm tra tại runtime.
#### 6.3 Error
* Error là lỗi không thể cứu chữa được, ví dụ: OutOfMemoryError, VirtualMachineError, AssertionError, vv.
### 7. try-catch trong java
* Khối lệnh try trong java được sử dụng để chứa một đoạn code có thế xảy ra một ngoại lệ. Nó phải được khai báo trong phương thức. Sau một khối lệnh try bạn phải khai báo khối lệnh catch hoặc finally hoặc cả hai.


* Khối catch trong java được sử dụng để xử lý các Exception. Nó phải được sử dụng sau khối try. Có thể sử dụng nhiều khối catch với một khối try duy nhất.

* **Example:**
    ```
    public class TestTryCatch2 {
        public static void main(String args[]) {
            try {
                int data = 50 / 0;
            } catch (ArithmeticException e) {
                System.out.println(e);
            }
            System.out.println("rest of the code...");
        }
    }
    ```
  
#### 7.1 Đa khối lệnh catch trong java
* Nếu bạn phải thực hiện các tác vụ khác nhau mà ở đó có thể xảy ra các ngoại lệ khác nhau, hãy sử dụng đa khối lệnh catch trong java.

* **Example:**
    ```
    public class TestMultipleCatchBlock {
        public static void main(String args[]) {
            try {
                int a[] = new int[5];
                a[5] = 30 / 0;
            } catch (ArithmeticException e) {
                System.out.println("task1 is completed");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("task 2 completed");
            } catch (Exception e) {
                System.out.println("common task completed");
            }
     
            System.out.println("rest of the code...");
        }
    }
    ```
  
#### 7.2 Khối lệnh try lồng nhau trong java
* Đôi khi một tình huống có thể phát sinh khi một phần của một khối lệnh có thể xảy ra một lỗi và toàn bộ khối lệnh chính nó có thể xảy ra một lỗi khác. Trong những trường hợp như vậy, trình xử lý ngoại lệ phải được lồng nhau.

* **Example:**
    ```
    try {  
        statement 1;  
        statement 2;  
        try {  
            statement 1;  
            statement 2;  
        }  
        catch(Exception e) {
             
        }  
    }  
    catch(Exception e) {
         
    }
    ```
  
#### 7.3 Khối lệnh finally trong java
* Khối lệnh finally trong java được sử dụng để thực thi các lệnh quan trọng như đóng kết nối, đóng cá stream,...


* Khối lệnh finally trong java luôn được thực thi cho dù có ngoại lệ xảy ra hay không hoặc gặp lệnh return trong khối try.


* Khối lệnh finally trong java được khai báo sau khối lệnh try hoặc sau khối lệnh catch.

    ![](https://viettuts.vn/images/java/exception-handling/flow-cua-khoi-lenh-finally-trong-java.png)

* Khối finally có thể được sử dụng để chèn lệnh "cleanup" vào chương trình như việc đóng file, đóng các kết nối,...

* **Example:**
    ```
    public class TestFinallyBlock {
        public static void main(String args[]) {
            try {
                int data = 25 / 5;
                System.out.println(data);
            } catch (NullPointerException e) {
                System.out.println(e);
            } finally {
                System.out.println("finally block is always executed");
            }
            System.out.println("rest of the code...");
        }
    }
    ```
  
### 8. Từ khóa throw trong java
* Từ khoá throw trong java được sử dụng để ném ra một ngoại lệ cụ thể.


* Có thể ném một trong hai ngoại lệ checked hoặc unchecked trong java bằng từ khóa throw. Từ khóa throw chủ yếu được sử dụng để ném ngoại lệ tùy chỉnh


* Cú pháp: ```throw exception;```


* **Example:**
    ```
    public class TestThrow1 {
        static void validate(int age) {
            if (age < 18)
                throw new ArithmeticException("not valid");
            else
                System.out.println("welcome");
        }
     
        public static void main(String args[]) {
            validate(13);
            System.out.println("rest of the code...");
        }
    }
    ```
  
### 9. Từ khóa throws trong java
* Từ khóa throws trong java được sử dụng để khai báo một ngoại lệ. Nó thể hiện thông tin cho lập trình viên rằng có thể xảy ra một ngoại lệ, vì vậy nó là tốt hơn cho các lập trình viên để cung cấp các mã xử lý ngoại lệ để duy trì luồng bình thường của chương trình.


* Exception Handling chủ yếu được sử dụng để xử lý ngoại lệ checked. Nếu xảy ra bất kỳ ngoại lệ unchecked như NullPointerException, đó là lỗi của lập trình viên.


* Cú pháp: 
    ```
    return_type method_name() throws exception_class_name {  
     //method code
    }
    ```
  
* Chỉ ngoại lệ checked nên được khai báo, bởi vì:
    * Ngoại lệ unchecked: nằm trong sự kiểm soát của bạn.
    * error: nằm ngoài sự kiểm soát của bạn, ví dụ bạn sẽ không thể làm được bất kì điều gì khi các lỗi VirtualMachineError hoặc StackOverflowError xảy ra.


### 10. Exception handling với overriding phương thức trong java
* Nếu phương thức của lớp cha không khai báo ném ra exception, phương thức được ghi đè của lớp cha không thể khai báo ném ra ngoại lệ checked, nhưng ngoại lệ unchecked thì có thể.


* Nếu phương thức của lớp cha khai báo ném ra exception, phương thức được ghi đè của lớp cha có thể khai báo ném ra ngoại lệ tương tự, ngoại lệ con, nhưng không thể khai báo ném ra ngoại lệ cha.

### 11. Exception tùy chỉnh trong java
* Ngoại lệ tùy chỉnh (exception tùy chỉnh) hoặc ngoại lệ do người dùng định nghĩa. Các ngoại lệ tùy chỉnh trong Java được sử dụng để tùy chỉnh ngoại lệ theo nhu cầu của người dùng.


* Sử dụng ngoại lệ tùy chỉnh, bạn có thể có ngoại lệ và message của riêng bạn.

* **Example:**
  

* _File: InvalidAgeException.java_
    ```
    class InvalidAgeException extends Exception {
        InvalidAgeException(String s) {
            super(s);
        }
    }
    ```
* _File: TestCustomException1.java_
    ```
    class TestCustomException1 {
     
        static void validate(int age) throws InvalidAgeException {
            if (age < 18)
                throw new InvalidAgeException("not valid");
            else
                System.out.println("welcome to vote");
        }
     
        public static void main(String args[]) {
            try {
                validate(13);
            } catch (Exception m) {
                System.out.println("Exception occured: " + m);
            }
     
            System.out.println("rest of the code...");
        }
    }
    ```
  
### 12. Truyền Exception cho caller
* Một ngoại lệ đầu tiên được ném ra từ phía trên của call stack (stack chứa các phương thức gọi đến nhau) và nếu nó không được catch, nó sẽ giảm xuống ngăn xếp đến phương thức trước, nếu không được catch ở đó, ngoại lệ lại giảm xuống phương thức trước, và cứ như vậy cho đến khi chúng được catch hoặc cho đến khi chúng chạm đến đáy của stack.Điều này được gọi là truyền ngoại lệ (truyền exception trong java).

* **Example:**
    ```
    class TestExceptionPropagation1 {
        void m() {
            int data = 50 / 0;
        }
     
        void n() {
            m();
        }
     
        void p() {
            try {
                n();
            } catch (Exception e) {
                System.out.println("exception handled");
            }
        }
     
        public static void main(String args[]) {
            TestExceptionPropagation1 obj = new TestExceptionPropagation1();
            obj.p();
            System.out.println("normal flow...");
        }
    }
    ```
  
* _Output:_ 
    ```
    Output:exception handled
           normal flow...
    ```

## II. @RestControllerAdvice & @ControllerAdvice + @ExceptionHandler

* @RestControllerAdvice là một Annotation gắn trên Class. Có tác dụng xen vào quá trình xử lý của các @RestController. Tương tự với @ControllerAdvice
  ```
  @RestController = @Controller + @ResponseBody
  @RestControllerAdvice = @ControllerAdvice + @ResponseBody.
  ```

* @RestControllerAdvice thường được kết hợp với @ExceptionHandler để cắt ngang quá trình xử lý của Controller, và xử lý các ngoại lệ xảy ra.


* **Example:**
  ```
  @RestControllerAdvice
  public class ApiExceptionHandler {
  
      @ExceptionHandler(IndexOutOfBoundsException.class)
      @ResponseStatus(value = HttpStatus.BAD_REQUEST)
      public ErrorMessage TodoException(Exception ex,  WebRequest request) {
          return new ErrorMessage(10100, "Đối tượng không tồn tại");
      }
  }
  ```

* Hiểu đơn giản là Controller đang hoạt động bình thường, chẳng may có một Exception được ném ra, thì thay vì báo lỗi hệ thống, thì nó sẽ được thằng @RestControllerAdvice và @ExceptionHandler đón lấy và xử lý. Sau đó trả về cho người dùng thông tin hữu ích hơn.

## III. @ResponseStatus
* @ResponseStatus là một cách định nghĩa Http Status trả về cho người dùng.


* Nếu bạn không muốn sử dụng ResponseEntity thì có thể dùng @ResponseStatus đánh dấu trên Object trả về.


* **Example:** Sử dụng @ResponseStatus
  ```
  @ExceptionHandler(ResourceNotFoundException.class)
      @ResponseStatus(value = HttpStatus.NOT_FOUND)
      public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
          return new ErrorMessage(
                  HttpStatus.NOT_FOUND.value(),
                  new Date(),
                  ex.getMessage(),
                  request.getDescription(false));
      }
  ```

* **Example:** Sử dụng ResponseEntity
  ```
  @ExceptionHandler(ResourceNotFoundException.class)
      public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
          ErrorMessage message = new ErrorMessage(
                  HttpStatus.NOT_FOUND.value(),
                  new Date(),
                  ex.getMessage(),
                  request.getDescription(false));
  
          return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
      }
  ```
  
## IV. @ControllerAdvice page not found
* Class DataNotFoundException.java
  ```
  @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such data")
  public class DataNotFoundException extends RuntimeException {
      public DataNotFoundException() {
          super();
      }
  }
  ```
  
* Class MainControllerAdvice.java
  ```
  @ControllerAdvice
  public class MainControllerAdvice {
  
      @ExceptionHandler(value = DataNotFoundException.class)
      public ModelAndView handleNotFound() {
          ModelAndView modelAndView = new ModelAndView();
          modelAndView.setViewName("error/400");
          return modelAndView;
      }
  
  }
  ```
  
* Class Controller.java
  ```
  @GetMapping(value = "/details/{id}")
  public String details(@PathVariable Integer id, Model model) throws DataNotFoundException {
      Pupil pupil = pupilService.get(id);
      if (pupil == null) {
          throw new DataNotFoundException();
      }
      model.addAttribute("pupil", pupil);
      return "details";
  }
  ```



















