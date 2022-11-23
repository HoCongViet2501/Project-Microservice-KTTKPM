package dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private Integer userId;
    private Integer cartId;
    private String status;
}
