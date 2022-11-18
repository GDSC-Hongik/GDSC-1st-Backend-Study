# 인증/인가

## 인증과 인가의 차이점

네이버 메일 서비스를 이용하는 상황을 가정해보자.

우선 로그인을 하고, 메일 쓰기 페이지로 옮겨가야할 것이다.

이때 로그인이 인증(Authentication)이다.

메일 쓰기 페이지는 아무나 접속해서는 안 된다.

누가 메일을 쓰는지 알아야 하고, 로그인 상태가 아니라면 메일을 쓸 수 없도록 해야하므로 사용자가 누구인지 확인해야 한다.

정리하자면, 사용자가 접근할 권한이 있는지를 확인하는 것이 인가(Authorization)이다.

## token을 이용한 인증/인가

토큰 방식은 클라이언트가 인증 정보를 가지고 있다.

인증 정보는 다양한 종류의 토큰이 되겠다.

토큰 작동 방식을 가볍게 정리하자면 아래와 같다.

1. 클라이언트의 로그인 성공
2. 서버는 토큰을 반환
3. 클라이언트는 토큰의 만료시까지 모든 요청의 header에 토큰을 포함시켜 활용

## session을 이용한 인증/인가

세션 방식은 서버가 인증 정보를 가지고 있는다.

클라이언트가 유효한 로그인 아이디와 비밀번호로 로그인을 시도하면,

서버는 이를 세션 저장소에 저장하고 저장 정보를 식별할 수 있는 primary key를 클라이언트에게 보낸다.

발급된 primary key는 session Id인데, 이는 로컬에 쿠키 형태로 저장된다.

## 쿠키가 등장한 배경과 존재 이유

HTTP는 request와 response를 주고 받으면 연결을 끊어버리는 특성이 있다.

이를 Connectionless라고 한다.

Stateless도 있다.

이는 request와 response를 주고 받은 후 그 정보를 기억하지 못하는 성질을 말한다.

근데 생각해보면 상태가 없다는 일은 꽤 큰일이다.

url이 바뀔 때마다 다시 로그인을 해야하는 일이 발생한다.

이를 방지하기 위해 존재하는 것이 쿠키이다.


### session 로그인을 위한 class-based-view
```python
class login(APIView):
    permission_classes = [AllowAny]
    def post(self,request):
        serializer = LoginSerializer(data=request.data)
        #serializer.is_valid(raise_exception=True)

        #pw 확인
        input_id = request.data['user_id']
        input_pw = request.data['password']
        data_from_DB = User.objects.get(user_id=input_id)
        if(check_password(input_pw,data_from_DB.password)):
            request.session['user'] = data_from_DB.user_id
            return Response(status=status.HTTP_200_OK)
        return Response(status=status.HTTP_400_BAD_REQUEST)
```

