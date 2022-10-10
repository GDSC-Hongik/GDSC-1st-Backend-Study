
## [레포지토리 바로가기](https://github.com/binary-ho/mvc-blog2)

<!-- 
```java
// main/webapp/WEB-INF/views/board/list.jsp
@RequestMapping(value = "/board/list")
public String list(Model model) {
  model.addAttribute("boardList", boardService.list());
    return "/board/list";
}
```

```java
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  <beans:property name="prefix" value="/WEB-INF/views/" />
  <beans:property name="suffix" value=".jsp" />
</beans:bean>
```

prefix, suffix

폼 유효성 검증 3단계 -> 유효성에 관해
1. jsp 파일 수정
2. hibernate 임포트
3. controller의 VO에 `@Valid` 붙이기.
4. BoardVO에 검증용 내용 추가

## session arrtibutes가 뭐 하는 물건 -->
