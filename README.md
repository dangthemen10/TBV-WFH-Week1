# TBV-WFH-Week1

****


## I. Hibernate
* Hibernate là framework được sử dụng nhiều nhất hiện nay để giúp lập trình viên Java có thể map các class (Pojo) với một cơ sở dữ liệu bất kỳ.


* Trước khi Hibernate ra đời, chúng ta thường thao tác với cơ sở dữ liệu thông qua JDBC. Theo thời gian, JDBC bộc lộ nhiều điểm yếu như:

    * Có nhiều code thừa mà chỉ phục vụ mục đích là lấy dữ liệu.
    * Mất nhiều thời gian map dữ liệu vào object Java.
    * Sẽ tốn nhiều công sức khi hệ thống thay đổi CSDL (yêu cầu jdbc mới, code mới)
    * Giao tiếp giữa các bảng thường khó, thiếu tính OOP trong đó.

### 1. Định nghĩa
* Hibernate là một thư viện ORM (Object Relational Mapping) mã nguồn mở giúp lập trình viên viết ứng dụng Java có thể map các objects (pojo) với hệ quản trị cơ sở dữ liệu quan hệ, và hỗ trợ thực hiện các khái niệm lập trình hướng đối tượng với cớ dữ liệu quan hệ.


* Hiểu ngắn gọn thì Hibernate sẽ là một layer đứng trung gian giữa ứng dụng và database, và chúng ta sẽ giao tiếp với Hibernate thay vì giao tiếp với database.


![](https://loda.me/assets/static/2.1c5a837.fda0571.png)

 
* Để giao tiếp với Hibernate, chúng ta sẽ tạo ra một Class đại diện cho một Table. Và mọi dữ liệu từ Table trong database sẽ được Hibernate bind vào Class đó cho chúng ta.

### 2. POJO
* Pojo (plain old Java object) là class đại diện cho một Table, thuật ngữ này để định nghĩa chính xác thì không dám chắc, nhưng về ý nghĩa thì nó là một class java thuần túy, rất thuần túy:

    * All properties must public setter and getter methods (mọi biến đều phải có get/set)
    * All instance variables should be private (mọi biến là thuộc tính thì nên là private)

```
public class MyFirstPojo
{
    private String name;

    public static void main(String [] args)
    {
       for (String arg : args)
       {
          MyFirstPojo pojo = new MyFirstPojo(arg);  // Here's how you create a POJO
          System.out.println(pojo); 
       }
    }

    public MyFirstPojo(String name)
    {    
        this.name = name;
    }

    public String getName() { return this.name; } 

    public String toString() { return this.name; } 
}
```

### 3. Mapping dữ liệu
* Khi đã có Class đại diện cho Table rồi, chúng ta sẽ định nghĩa các trường trong class đó tương ứng với column nào trong database bằng tập hợp các Annotaion mà Hibernate cung cấp.

```
@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "USER") //Entity này đại diện cho table USER trong db
public class UserModel {
    @Id // Đánh dấu biến ở dưới là primary key của table này
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng giá trị khi insert
    private Long id;

    @Column(name = "email", unique = true) // trường email ở dưới đại diện cho cột email trong database
    private String email; 

    @Column(name = "name")
    private String name;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

* Bây giờ việc bạn lấy dữ liệu từ database sẽ đại loại như này:
```
public List<User> findAll() {
    return session.createQuery("SELECT a FROM User a", User.class).getResultList();      
}
```

### 4. Hibernate Query Language (HQL)

* Hibernate sử dụng ngôn ngữ Hibernate Query Language (HQL) để query dữ liệu. Nó chỉ khác SQL bình thường ở chỗ, đối tượng tác động lúc này là Entity chứ không còn là Table nữa:

**Example:**
```
-- SQL
-- from table name
Select u.id, u.email from USER u;
 
-- HQL
-- from class name
Select u.id, u.email from User u;
 
-- query toàn bộ object
Select u from User u;
```
## II. Spring Boot JPA
* Spring Boot JPA là một phần trong hệ sinh thái Spring Data, nó tạo ra một layer ở giữa tầng service và database, giúp chúng ta thao tác với database một cách dễ dàng hơn, tự động config và giảm thiểu code thừa thãi.


* Spring Boot JPA đã wrapper Hibernate và tạo ra một interface mạnh mẽ. Nếu như bạn gặp khó khăn khi làm việc với Hibernate thì đừng lo, bạn hãy để Spring JPA làm hộ.

### 1. Query Creation
* Trong Spring JPA, có một cơ chế giúp chúng ta tạo ra các câu Query mà không cần viết thêm code. Cơ chế này xây dựng Query từ tên của method.

**User.java**
```
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapping thông tin biến với tên cột trong Database
    @Column(name = "hp")
    private int hp;
    @Column(name = "stamina")
    private int stamina;

    // Nếu không đánh dấu @Column thì sẽ mapping tự động theo tên biến
    private int atk;
    private int def;
    private int agi;
}
```

* Khi chúng ta đặt tên method là: findByAtk(int atk)

* Thì Spring JPA sẽ tự định nghĩa câu Query cho method này, bằng cách xử lý tên method. Vậy là chúng ta đã có thể truy vấn dữ liệu mà chỉ mất thêm 1 dòng code.

* Cơ chế xây dựng Query từ tên method này giúp chúng ta tiết kiệm thời gian với những query có logic đơn giản, và cũng đặc biệt hữu ích là nó giống ngôn ngữ con người thường nói hơn là SQL. (human-readable)

### 2. Quy tắc đặt tên method trong Spring JPA

* Trong Spring JPA, cơ chế xây dựng truy vấn thông qua tên của method được quy định bởi các tiền tố sau: find…By, read…By, query…By, count…By, và get…By.


* Phần còn lại sẽ được parse theo tên của thuộc tính (viết hoa chữ cái đầu). Nếu chúng ta có nhiều điều kiện, thì các thuộc tính có thể kết hợp với nhau bằng chữ And hoặc Or.


**Example:**
```
interface PersonRepository extends JpaRepository<User, Long> {
    // Dễ
    // version rút gọn
    Person findByLastname(String lastname);
    // verson đầy đủ
    Person findPersonByLastname(String lastname);

    Person findAllByLastname(String lastname);

    // Trung bình
    List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);

    // findDistinct là tìm kiếm và loại bỏ đi các đối tượng trùng nhau
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

    // IgnoreCase là tìm kiếm không phân biệt hoa thường, ví dụ ở đây tìm kiếm lastname
    // lastname sẽ không phân biệt hoa thường
    List<Person> findByLastnameIgnoreCase(String lastname);

    // AllIgnoreCase là không phân biệt hoa thường cho tất cả thuộc tính
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    // OrderBy là cách sắp xếp thứ tự trả về
    // Sắp xếp theo Firstname ASC
    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
    // Sắp xếp theo Firstname Desc
    List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
```

* Các thuộc tính trong tên method phải thuộc về Class đó, nếu không sẽ gây ra lỗi. Tuy nhiên, trong một số trường hợp bạn có thể query bằng thuộc tính con.

**Example 2:** Đối tượng Person có thuộc tính là Address và trong Address lại có ZipCode
```
// person.address.zipCode
List<Person> findByAddressZipCode(ZipCode zipCode);
```

### 3. @Query
* Spring JPA còn hỗ trợ chúng ta một cách nguyên thủy khác. Với cách sử dụng @Query, bạn sẽ có thể sử dụng câu truy vấn JPQL (Hibernate) hoặc raw SQL.

**Example:**
```
public interface UserRepository extends JpaRepository<User, Long> {

    // Khi được gắn @Query, thì tên của method không còn tác dụng nữa
    // Đây là JPQL
    @Query("select u from User u where u.emailAddress = ?1")
    User myCustomQuery(String emailAddress);

    // Đây là Native SQL
    @Query(value = "select * from User u where u.email_address = ?1", nativeQuery = true)
    User myCustomQuery2(String emailAddress);
}
```

* Cách truyền tham số là gọi theo thứ tự các tham số của method bên dưới ?1, ?2.  Nếu không thích sử dụng ?{number} thì có thể đặt tên cho tham số.

```
public interface UserRepository extends JpaRepository<User, Long> {
    // JPQL
    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByNamedParams(@Param("status") Integer status, @Param("name") String name);

    // Native SQL
    @Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", nativeQuery = true)
    User findUserByNamedParamsNative(@Param("status") Integer status, @Param("name") String name);
}
```


## III. Specification
### 1. JPQL vs Criteria API
* JPQL có thể làm đầy đủ chức năng chúng ta cần chỉ với 1 câu lệnh, tuy nhiên, chính vì điều đó, chúng ta thường khó tùy biến hay sử dụng lại nó, thậm chí khó kiểm soát lỗi của nó hơn. Với một câu lệnh phức tạp, chúng ta không biết được nó có lỗi hay không cho tới khi chạy chương trình hay debug.


* Criteria API thì ngược lại, nó cho phép chúng ta xây dựng câu lệnh một cách Dynamic, rất linh động, và không bị hardcode trong một String và có thể tái sử dụng lại được. Đặc biệt, vì là Java Object, nên chúng ta sẽ biết một câu lệnh bị lỗi, không đúng quy tắc ngay khi biên dịch chương trình rồi.
### 2. How to use.
* **Example:**
```
CriteriaBuilder builder = em.getCriteriaBuilder();

CriteriaQuery<Office> query =  builder.createQuery(Office.class);
Root<Office> root = query.from(Office.class);
query.select(root);
```
* **CriteriaBuilder:** Để xây dựng một câu query, các bạn sẽ cần tới interface CriteriaBuilder, mục đích của nó là giúp tạo ra đối tượng chứa câu lệnh truy vấn CriteriaQuery và cung cấp cơ số các phép biến đổi, phép logic, điều kiện cho câu lệnh (and, or, not, avg, greater than,v.v...).
  

* **CriteriaQuery:** Đối tượng chính của chúng ta đây, nó được tạo ra bởi builder.createQuery(Office.class). Mục đích là khai báo đối tượng bạn muốn lấy ra sau khi thực hiện query. Nó tương đương với đoạn ngoặc đơn ở dưới đây:
    ```
    SELECT 'o' FROM Office o
    ```

* **Root:** root là khai báo đối tượng bạn sẽ sử dụng trong query, tương đương với đối tượng sau mệnh đề FROM.
    ```
    SELECT o FROM 'Office o'
    ````
* Cuối cùng, để hoàn thiện câu lệnh SELECT chỉ đơn giản là lấy đối tượng CriteriaQuery đã khai báo là sử dụng function select. Đối tượng truyền vào chính là cái root (hay cái đối tượng của FROM) kia.

=> Để sử dụng câu lệnh đã tạo, các bạn làm giống với JPQL đó là sử dụng đối tượng EntityManager:
```
TypedQuery<Office> query = em.createQuery(query);
List<Office> results = query.getResultList();
```
### 3. JPA Meta Model
* **Xét ví dụ**:
```
SELECT o FROM Office o WHERE o.city = 'hanoi'
```
* Lúc này query của chúng ta sẽ như thế này:
```
query.select(root).where(builder.equal(root.get("city"), "hanoi"));
```
* Khi muốn lấy column city để kiểm tra, chúng ta đang hardcode bằng String.

* Có một số bất lợi khi làm vậy: 
  * Thứ nhất là bạn phải tự nhớ tên các column mỗi khi gọi.
  * Thứ hai là bạn sẽ phải tìm kiếm tất cả các chỗ sử dụng mỗi sửa đổi tên cột.

* Cách giải quyết hay nhất là tham chiếu tên các column của Table vào một Object để chúng ta có thể gọi tới mỗi khi sử dụng. Khi có sự thay đổi, chỉ cần thay đổi trong đối tượng này là xong. Đối tượng biểu diễn này được gọi là Meta Model. Và Hibernate hỗ trợ chúng ta tự động generate ra các Meta Model từ các class @Entity


* **Example:**
    * Class Entity User:
    
    ```
    @Entity
    @Data
    @Builder
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private UserType type;
        private String name;
    
        public enum UserType {
            NORMAL, VIP;
        }
    }
    ```
    
    * Class Meta Model của User sẽ tên là User_ và có cấu trúc như sau:
    
    ```
    @Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
    @StaticMetamodel(User.class)
    public abstract class User_ {

        public static volatile SingularAttribute<User, String> name;
        public static volatile SingularAttribute<User, Long> id;
        public static volatile SingularAttribute<User, UserType> type;
    
        public static final String NAME = "name";
        public static final String ID = "id";
        public static final String TYPE = "type";

    }
    ```
    => Có thể sử dụng @FieldNameConstans của lombok để thay thế.

* **Annotation @FieldNameConstans**
    * @FieldNameConstants tạo ra một kiểu bên trong chứa 1 hằng số cho mỗi trường trong lớp của bạn; hoặc là hằng số chuỗi (các trường được đánh dấu là công khai tĩnh cuối cùng, kiểu java. ... Kiểu bên trong được tạo theo mặc định được gọi là Trường và là trường công khai.
    
    ```
    @Entity
    @Data
    @Builder
    @FieldNameConstants
    public class FieldNameConstantsExample {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private UserType type;
        private String name;
    
        public enum UserType {
            NORMAL, VIP;
        }
    }
    ```
### 4. Predicate
* Để có thể xây dựng câu truy vấn một cách trọn vẹn, cần biết Predicate. Tạm hiểu một cách đơn giản thì Predicate là một mệnh đề điều kiện trong câu lệnh truy vấn.


* **Example:**
```
@Repository
public class CustomUserRepository {

    @PersistenceContext
    private EntityManager em;

    public User getUserById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate condition = builder.equal(root.get(User_.ID), id);

        query.select(root).where(condition);

        return em.createQuery(query).getSingleResult();
    }
}
```

* Predicate có thể liên kết với nhau bằng các phép quan hệ and, or, not, v.v..

```
public Collection<User> getUserByComplexConditions(String name, UserType type) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> root = query.from(User.class);

    Predicate hasNameLike = builder.like(root.get(User_.NAME), name);
    Predicate hasType = builder.equal(root.get(User_.TYPE), type);

    Predicate condition = builder.and(hasNameLike, hasType);

    query.select(root).where(condition);
    return em.createQuery(query).getResultList();
}
```
### 5. Specification

* Specification là một cách để định nghĩa các Predicate có thể tái sử dụng được.

* Bản chất Specification là một funtional interface với 1 hàm duy nhất như sau:
```
public interface Specification<T> {
  Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
}
```

* Tham số đầu vào là 3 khái niệm tôi đã giới thiệu ở bài Criteria API, bao gồm:

```
 1. Root
 2. CriteriaQuery
 3. CriteriaBuilder
```

**Example:** một số implementation của Specification và đề cập cách sử dụng nó ở phía dưới:
* User.java
```
@Entity
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserType type;
    private String name;

    public enum UserType {
        NORMAL, VIP;
    }
}
```
* UserSpecification.java
```
public final class UserSpecification {
    /**
     * Lấy ra user có UserType chỉ định
     * @param type
     * @return
     */
    public static Specification<User> hasType(UserType type) {
        return (root, query, cb) -> cb.equal(root.get(User_.TYPE), type);
    }

    /**
     * Lấy ra user có id chỉ định
     * @param userId
     * @return
     */
    public static Specification<User> hasId(long userId) {
        return (root, query, cb) -> cb.equal(root.get(User_.ID), userId);
    }

    /**
     * Lấy ra user nằm trong tập ID chỉ định
     * @param userIds
     * @return
     */
    public static Specification<User> hasIdIn(Collection<Long> userIds) {
        return (root, query, cb) -> root.get(User_.ID).in(userIds);
    }
}
```

### 6. JpaSpecificationExecutor
* Để có thể sử dụng được Specification, bạn cần kế thừa JpaSpecificationExecutor từ Spring JPA

```
public interface UserRepository extends JpaRepository<User, Long>,
                                        JpaSpecificationExecutor<User> {
}
```

* Lúc này, ngoài các method truyền thống như findAll(), findOne(), findBy() thì bạn sẽ thấy xuất hiện các method mới có tham số đầu vào là Specification<T>:

```
Optional<T> findOne(@Nullable Specification<T> var1);

List<T> findAll(@Nullable Specification<T> var1);

Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);

List<T> findAll(@Nullable Specification<T> var1, Sort var2);

long count(@Nullable Specification<T> var1);
```

### 7. Usage
* Lúc này, để sử dụng, bạn gọi Specification.where() để xây dựng cho mình tập các điều kiện để query
```
// Lấy ra user nằm trong tập ID đã cho và có type là NORMAL
// hoặc lấy ra user có ID = 10
Specification conditions = Specification.where(UserSpecification.hasIdIn(Arrays.asList(1L, 2L, 3L, 4L, 5L)))
                                        .and(UserSpecification.hasType(UserType.NORMAL))
                                        .or(UserSpecification.hasId(10L));
// Truyền Specification vào hàm findAll()
userRepository.findAll(conditions).forEach(System.out::println);
```

* **Output:** 
```
User(id=1, type=NORMAL, name=name-0)
User(id=2, type=NORMAL, name=name-1)
User(id=5, type=NORMAL, name=name-4)
User(id=10, type=VIP, name=name-9)
```
