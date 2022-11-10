
@Controller
public class HelloController {

  @GetMapping("hello1") // 주소 뒤에 mapping 한거임
  public String hello(Model model) {
    model.addAttribute("data", "hello!!");
    return "hello2"; // 템플릿에서 hello라는 것을 찾아내라
    // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버 viewResolver가 화면을 찾아 처리
  }

  @GetMapping("hello-mvc") // 주소 뒤 /슬레쉬 뒤에 매핑
  public String helloMvc(@RequestParam(value = "name", defaultValue = "Jinhohohoho") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template"; // 리턴 값과 같은 이름의 파일을 템플릿 폴더에서 찾아라
  }

  @GetMapping("hello-string")
  @ResponseBody
  public String helloString(@RequestParam(value = "name", defaultValue = "JINHO!!") String name) {
    return "hello " + name;
  }

  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam(value = "name", defaultValue = "Hello there") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }

  static class Hello {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
