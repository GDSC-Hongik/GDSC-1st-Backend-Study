package expert001_01;

public class Car {
    Tire tire;

    public Car() {
        tire = new KoreaTire(); // 자동차가 타이어를 생산하는 부분 // 의존 관계가 일어나고 있는 부분
        // 여기서는 Car 객체가 Tire를 직접 생산하는,
        // 즉 Tire에 대한 의존성을 자체적으로 해결하는 방식이었다
    }

    public String getTireBrand() {
        return "장착한 타이어: " + tire.getBrand();
    }
}
