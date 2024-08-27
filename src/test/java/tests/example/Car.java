package tests.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    private String mark;
    private String speed;
}