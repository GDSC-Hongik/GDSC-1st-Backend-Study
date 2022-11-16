package hello.spring_core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() { // 생성자
        System.out.println("생성자 호출, Url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출

    public void disconnect() {
        System.out.println("close " + url);
    }
}
