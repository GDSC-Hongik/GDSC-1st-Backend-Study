package expert003;

public class Car {
    Tire tire;

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) { // 속성을 통한 의존성 주입
        this.tire = tire;
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
