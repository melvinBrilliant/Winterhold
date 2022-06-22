package melvin.mvc.winterhold.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {
    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username can not be more than 20 characters")
    private String id;
    @NotBlank(message = "Password is required")
    private String password;
}
