
@Controller
public class MemberController {

  // 이렇게 하면 컨트롤러들이 죄다 서비스 새로 만들어서 쓰겠지?
  // 그럴 필요는 없다
  // private final MemberService memberService = new MemberService();
  private final MemberService memberService;

  // 맴버 컨트롤러를 만들 때 스프링 컨테이너 안에 있는 맴버 서비스를 가져온다다
  // 억지로 의존성을 강제로 인젝션 Dependency injection
  @Autowired // 스프링 컨테이너에서 가져온다
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm.html";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());
    member.setAge(form.getAge());
    System.out.println(member.getAge());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members); // 이렇게 해두면 html 파일에서 members라는 모델이 생겨나고 읽을 수 있게 된다.
    return "members/memberList";
  }
}
