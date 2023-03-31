package demo.serviceb.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import demo.model.Date2Long;
import lombok.Data;

@Data
public class RequestParam {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date2Long startDate;

    String number;

}
