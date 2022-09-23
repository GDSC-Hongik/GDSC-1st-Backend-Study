# GDSC-1st-Backend-Study
GDSC 1기 백엔드 스프링 스터디 레포입니다


## 📁 Project Structure

```
│
├─ GDSC-1st-Backend-Study
│     │
│     ├─ Lecture01 (디렉토리)
│     │     ├─ gradle/wrapper
│     │     ├─ src
│     │     ├─ .gitignore
│     │     ├─ README.md (해당 주차 리드미 파일)
│     │     ├─ build.gradle
│     │     ├─ gradlew
│     │     ├─ gradlew.bat
│     │     └─ settings.gradle
│     │ 
│     ├─ Lecture02
│     │     └─ '' 이하 동일
│     │ 
│     ├─ Lecture03
│     │     └─ '' 이하 동일
│     │ 
│     ├─ Lecture04
│     │     └─ '' 이하 동일
│     │ 
│     └─ README.md
│ 
│ 
│ 
```

## 📝 해당 주차 과제 가이드


### 🔧️ Github 레포지토리 fork 하기
* Github ```GDSC-Hongik/GDSC-1st-Backend-Study``` fork하기
    * https://github.com/GDSC-Hongik/GDSC-1st-Backend-Study
* fork 후 local 환경에 clone 한 후 작업하기


### 💾 중간 중간 commit 하기
* 파트 별로 작업을 끝냈거나 더 작은 단위로 작업을 끝낼 때 마다 commit을 해 주는 게 좋아요
* ❗commit convention ❗️
  * **Feat**: 새로운 기능 추가
  * **Fix**: 버그 수정
  * **Docs**: 문서 수정
  * **Style**: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
  * **Refactor**: 코드 리펙토링
  * **Test**: 테스트 코드, 리펙토링 테스트 코드 추가
  * **Chore**: 빌드 업무 수정, 패키지 매니저 수정

* 예시: ```git commit -m "[Feat(해당 파일): OO 기능 추가]"```


### ✍🏻 README.md 파일 작성하기


* 해당 주차 책을 공부하면서 알게 된 부분, 중요하다고 생각한 부분이 있다면 정리해 주세요!
  * 개념 정립도, 요약도 모두 좋습니다! 😊
* 해당 주차 강의를 들으면서 새롭게 알게 된 사실이나 추가적으로 더 공부한 부분이 있다면 정리해 주세요!
    * 요약도, 복습도 모두 좋습니다! 😊😊
* 1~4주차는 **책 내용 우선** -> **강의 내용** 순서로 작성해 주시면 됩니다!


* .md 파일은 mark down 언어로 작성된 파일을 뜻해요
  * [참고] https://gist.github.com/ihoneymon/652be052a0727ad59601#24-코드


### 🙌🏻 Github 레포지토리에 push하기

* 해당 주차의 강의를 모두 듣고 작업을 모두 끝냈다면, 프로젝트 변경 사항을 remote repository(github repository)에 push 합니다
  * ```git push origin [본인 github 아이디 브랜치 명]``` : git에 등록되어 있는 origin(github repository)에 있는 자신의 branch로 프로젝트의 변경 사항을 반영합니다
* push를 완료했다면 fork한 자신의 repository에서 pull request를 진행합니다
* PR(Pull Request)시 메세지 제목은 다음과 같이 ```[1기_OOO] 1주차 미션 제출합니다.``` 라고 적은 후 ```create pull request``` 버튼을 눌러주세요
* PR : GDSC-Hongik / ```[본인 github 아이디 브랜치 명]``` <- ```[본인 github 아이디]``` / ```[본인 github 아이디 브랜치 명]```


### ⏰ 과제 제출 마감 기한

* 백엔드 스터디가 있는 해당 요일 낮 12시까지 PR을 보내면 됩니다! 😊