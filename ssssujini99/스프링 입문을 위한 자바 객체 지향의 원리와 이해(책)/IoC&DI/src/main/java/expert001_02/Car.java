package expert001_02;

public class Car {
    Tire tire;

    public Car(Tire tire) { // Car의 생성자에 인자가 생김
        this.tire = tire; // new가 사라지고 //  외부에서 생산된 tire 객체를 Car의 생산자의 인자로 주입하는 형태
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
