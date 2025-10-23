package app.web.seun_olo2.URLShortingAPI.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseMessage {
    private String type;
    private Object object;
    private String message;
    private HttpStatus httpStatus;
}
