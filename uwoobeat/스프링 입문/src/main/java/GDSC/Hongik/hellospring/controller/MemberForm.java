package GDSC.Hongik.hellospring.controller;

public class MemberForm {
    private String name;
    // createMemberForm.html에서 input으로 전달받은 name이 여기로 전달된다.
    // 스프링은 setName을 호출하여 name 필드를 할당해준다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
