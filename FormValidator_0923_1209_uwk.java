// 代码生成时间: 2025-09-23 12:09:50
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class FormValidator<T> {

    // 实例化ValidatorFactory和Validator
    private final Validator validator;

    public FormValidator(Class<T> cls) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    // 验证对象
    public boolean validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        return violations.isEmpty();
    }

    // 获取验证错误消息
    public Set<ConstraintViolation<T>> getViolations(T object) {
        return validator.validate(object);
    }
}

// 使用示例
// public class User {
//     @NotNull(message = "Username cannot be null")
//     private String username;
//     @Size(min = 6, message = "Password must be at least 6 characters")
//     private String password;
//     // getters and setters
// // }

// FormValidator<User> userValidator = new FormValidator<>(User.class);
// User user = new User();
// user.setUsername(null);
// user.setPassword("pass");
// boolean isValid = userValidator.validate(user);
// if (!isValid) {
//     Set<ConstraintViolation<User>> violations = userValidator.getViolations(user);
//     for (ConstraintViolation<User> violation : violations) {
//         System.out.println(violation.getMessage());
//     }
// }