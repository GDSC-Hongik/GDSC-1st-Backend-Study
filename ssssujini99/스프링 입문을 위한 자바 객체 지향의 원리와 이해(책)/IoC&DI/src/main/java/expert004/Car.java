package expert004;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
    @Autowired
    Tire americaTire;

    public String getTireBrand() {
        return "장착된 타이어: " + americaTire.getBrand();
    }
}
