package ward.complain.portal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Africa/Dar_es_Salaam")
    private Date timeStamp;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private String reason;
    private String message;
    protected Object data;


    public HttpResponse(HttpStatus httpStatus, int httpStatusCode, String reason, String message) {
        this.timeStamp = new Date();
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
        this.reason = reason;
        this.message = message;
    }
}
