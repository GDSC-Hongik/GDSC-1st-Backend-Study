package expert001_03;

import static org.junit.Assert.*;
import org.junit.Test;

public class CarTest {
    @Test
    public void 자동차_코리아타이어_장착_타이어브랜드_테스트() {
        Tire tire1 = new KoreaTire();
        Car car1 = new Car();
        car1.setTire(tire1);

        assertEquals("장착된 타이어: 코리아 타이어", car1.getTireBrand());
    }

    @Test
    public void 자동차_미국타이어_장착_타이어브랜드_테스트() {
        Tire tire2 = new AmericaTire();
        Car car2 = new Car();
        car2.setTire(tire2);

        assertEquals("장착된 타이어: 미국 타이어", car2.getTireBrand());
    }
}
