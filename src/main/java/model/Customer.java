package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String id;
    private String title;
    private String name;
    private LocalDate birthday;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

}
