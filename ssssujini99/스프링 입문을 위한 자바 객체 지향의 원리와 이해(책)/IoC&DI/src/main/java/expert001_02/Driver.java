package expert001_02;

public class Driver {
    public static void main(String[] args) {
        Tire tire = new KoreaTire(); // 운전자가 타이어를 선택
        Car car = new Car(tire); // "만들어진" tire 객체를 car에 주입

        System.out.println(car.getTireBrand());
    }
}
