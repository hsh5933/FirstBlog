let index ={
    init:function(){
        $("#btn-save").on("click",()=>{
        this.save();
        });
//        $("#btn-login").on("click",()=>{
//        this.login();
//        });
    },
    save:function(){
        //alert('user의 save함수 호출됨');
        let data={
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        //console.log(data);

    //ajax호출시 default가 비동기 호출
    //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
    $.ajax({
        //회원가입 수행 요청
        type:"POST",
        url:"/auth/joinProc",
        data:JSON.stringify(data), //http body데이터
        contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
        dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
    }).done(function(resp){
        alert("회원가입이 완료되었습니다.");
        //console.log(resp);
        location.href="/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });

    },


//    login:function(){
//        //alert('user의 save함수 호출됨');
//        let data={
//            username:$("#username").val(),
//            password:$("#password").val()
//        };
//
//
//    $.ajax({
//        type:"POST",
//        url:"/api/user/login",
//        data:JSON.stringify(data), //http body데이터
//        contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
//        dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
//    }).done(function(resp){
//        alert("로그인이 완료되었습니다.");
//        //console.log(resp);
//        location.href="/";
//    }).fail(function(error){
//        alert(JSON.stringify(error));
//    });
//
//    }
}

index.init();