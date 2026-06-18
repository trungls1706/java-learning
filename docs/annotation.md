# Java Annotations - Kiến Thức Cốt Lõi Để Đi Phỏng Vấn

## 1. Annotation là gì?
- **Annotation (chú thích)** là một dạng siêu dữ liệu (metadata) cung cấp thông tin về chương trình cho trình biên dịch (compiler) hoặc môi trường thực thi (runtime environment).
- **Đặc điểm quan trọng nhất:** Bản thân Annotation **KHÔNG** trực tiếp thay đổi logic hay luồng chạy của code. Nó chỉ đánh dấu, và cần có một công cụ khác (compiler hoặc framework qua Reflection) đọc và xử lý các đánh dấu đó.

## 2. Phân loại Annotation

### 2.1. Built-in Annotations (Có sẵn trong Java)
- `@Override`: Đảm bảo phương thức đang ghi đè chính xác phương thức của lớp cha. Tránh lỗi gõ sai tên.
- `@Deprecated`: Đánh dấu class/method/field đã lỗi thời và không nên sử dụng nữa, cảnh báo sẽ hiện ra khi compile.
- `@SuppressWarnings`: Yêu cầu compiler bỏ qua một số cảnh báo cụ thể.
- `@FunctionalInterface` (từ Java 8): Đảm bảo interface chỉ có duy nhất 1 abstract method (phục vụ Lambda Expression).

### 2.2. Meta-Annotations (Dùng để định nghĩa các Annotation khác)
Đây là phần **RẤT HAY BỊ HỎI** khi đi phỏng vấn.
- **`@Retention`**: Chỉ định "tuổi thọ" của Annotation (tồn tại đến lúc nào). Có 3 mức (`RetentionPolicy`):
  1. `SOURCE`: Chỉ tồn tại trong source code, bị trình biên dịch loại bỏ khi biên dịch ra file `.class` (ví dụ: `@Override`, `@SuppressWarnings`).
  2. `CLASS`: Tồn tại trong file `.class` nhưng bị máy ảo Java (JVM) bỏ qua khi chạy. Đây là mức mặc định.
  3. `RUNTIME`: Tồn tại trong source code, `.class` và cả lúc chương trình đang chạy. Có thể đọc được bằng Reflection. (Các Annotation của Spring, Hibernate thường ở mức này).
- **`@Target`**: Chỉ định Annotation có thể được đặt ở đâu (`ElementType`).
  - `TYPE`: Class, interface, enum.
  - `FIELD`: Thuộc tính (biến của class).
  - `METHOD`: Phương thức.
  - `PARAMETER`: Tham số của phương thức.
  - `CONSTRUCTOR`: Hàm tạo.
- **`@Documented`**: Annotation sẽ được đưa vào JavaDoc.
- **`@Inherited`**: Class con sẽ kế thừa annotation từ class cha.

## 3. Cách tự tạo một Custom Annotation (Interview Question)

**Bước 1: Định nghĩa Annotation**
Dùng từ khóa `@interface`.

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Chỉ áp dụng cho Method
@Retention(RetentionPolicy.RUNTIME) // Tồn tại lúc runtime để dùng Reflection đọc
public @interface MyCustomAnnotation {
    String value() default "Mặc định"; // Thuộc tính
    int count() default 1;
}
```

**Bước 2: Sử dụng Annotation**
```java
public class MyClass {
    @MyCustomAnnotation(value = "Test", count = 5)
    public void myMethod() {
        // ...
    }
}
```

**Bước 3: Xử lý Annotation bằng Reflection** (Đây là bản chất của các Framework như Spring)
```java
import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void main(String[] args) throws Exception {
        Method method = MyClass.class.getMethod("myMethod");
        
        // Kiểm tra xem method có được đánh dấu bởi MyCustomAnnotation không
        if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
            MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);
            System.out.println("Value: " + annotation.value());
            System.out.println("Count: " + annotation.count());
        }
    }
}
```

## 4. Câu Hỏi Phỏng Vấn Thường Gặp

**Q1: Annotation là gì? Có làm thay đổi code logic không?**
> Không. Nó chỉ cung cấp metadata. Logic chỉ thay đổi khi có một đoạn code khác (thường dùng Reflection) đọc annotation đó và đưa ra quyết định thực thi (như cách Spring xử lý `@Autowired`).

**Q2: So sánh cấu hình bằng XML và Annotation (VD: Trong Spring)?**
> - **Annotation:** Nhanh, gọn, dễ đọc, cấu hình ngay tại chỗ (decentralized). Nhược điểm là thay đổi cấu hình phải re-compile code.
> - **XML:** Quản lý tập trung, tách biệt cấu hình khỏi code, thay đổi file XML không cần re-compile code. Nhược điểm là dài dòng, khó kiểm soát khi dự án lớn.

**Q3: Phân biệt `@Retention(RetentionPolicy.RUNTIME)` và `@Retention(RetentionPolicy.SOURCE)`?**
> - `RUNTIME`: Dùng khi ta cần đọc annotation đó trong lúc chương trình đang chạy bằng Reflection (ví dụ: tạo custom annotation để check quyền, validate).
> - `SOURCE`: Dùng cho compiler, để nó báo lỗi hoặc tạo thêm code (ví dụ: `@Override` để compiler check method cha, Lombok `@Data` gen code lúc compile).

**Q4: `@Target` dùng để làm gì? Nêu một số ElementType.**
> Định nghĩa phạm vi sử dụng của Annotation. Nếu gán `@Target(ElementType.METHOD)` mà mang đi đặt trên Field thì compiler sẽ báo lỗi. Một số ElementType phổ biến: TYPE, FIELD, METHOD, PARAMETER.

**Q5: Dưới hood (bản chất), Annotation trong Java là gì?**
> Annotation thực chất là một **Interface** đặc biệt được kế thừa ngầm định từ interface `java.lang.annotation.Annotation`. Compiler sẽ tự động tạo class implement interface đó cho chúng ta.
