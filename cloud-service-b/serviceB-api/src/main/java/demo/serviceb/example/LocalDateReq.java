package demo.serviceb.example;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class LocalDateReq {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    String number;
}
