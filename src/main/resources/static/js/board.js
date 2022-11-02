let index ={
    init:function(){
        $("#btn-save").on("click",()=>{
        this.save();
        });
        $("#btn-delete").on("click",()=>{
                this.deleteById();
                });
        $("#btn-update").on("click",()=>{
                        this.update();
                        });
                        $("#btn-reply-save").on("click",()=>{
                                                this.replySave();
                                                });
//        $("#btn-login").on("click",()=>{
//        this.login();
//        });
    },
    save:function(){
        //alert('user의 save함수 호출됨');
        let data={
            title:$("#title").val(),
            content:$("#content").val()
        };

        //console.log(data);

    //ajax호출시 default가 비동기 호출
    //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
    $.ajax({
        //회원가입 수행 요청
        type:"POST",
        url:"/api/board",
        data:JSON.stringify(data), //http body데이터
        contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
        dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
    }).done(function(resp){
        alert("글쓰기가 완료되었습니다.");
        //console.log(resp);
        location.href="/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });

    },

    deleteById:function(){

        //ajax호출시 default가 비동기 호출
        //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
        let id = $("#id").text();

        $.ajax({
            //회원가입 수행 요청
            type:"DELETE",
            url:"/api/board/"+id,
            dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            //console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

        },

        update:function(){
            let id = $("#id").val();

                let data={
                    title:$("#title").val(),
                    content:$("#content").val()
                };

                //console.log(data);

            //ajax호출시 default가 비동기 호출
            //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
            $.ajax({
                //회원가입 수행 요청
                type:"PUT",
                url:"/api/board/"+id,
                data:JSON.stringify(data), //http body데이터
                contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
                dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
            }).done(function(resp){
                alert("글수정이 완료되었습니다.");
                //console.log(resp);
                location.href="/";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });

            },

            replySave:function(){
                    //alert('user의 save함수 호출됨');
                    let data={
                        userId:$("#userId").val(),
                        boardId:$("#boardId").val(),
                        content:$("#reply-content").val()

                    };
                //ajax호출시 default가 비동기 호출
                //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
                $.ajax({
                    //회원가입 수행 요청
                    type:"POST",
                    url:`/api/board/${data.boardId}/reply`,
                    data:JSON.stringify(data), //http body데이터
                    contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
                    dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
                }).done(function(resp){
                    alert("댓글 작성이 완료되었습니다.");
                    //console.log(resp);
                    location.href=`/board/${data.boardId}`;
                }).fail(function(error){
                    alert(JSON.stringify(error));
                });

                },

                replyDelete:function(boardId,replyId){

                                //ajax호출시 default가 비동기 호출
                                //ajax통신 이용해서 3개 데이터를 json으로 변경 insert요청
                                $.ajax({
                                    //회원가입 수행 요청
                                    type:"DELETE",
                                    url:`/api/board/${boardId}/reply/${replyId}`,
                                    contentType:"application/json; charset=UTF-8", //body데이터가 어떤 타입인지
                                    dataType:"json" //요청을 서버로해서 응답이 왔을때 json이라면 javascript오브젝트로 변경
                                }).done(function(resp){
                                    alert("댓글 삭제 성공.");
                                    //console.log(resp);
                                    location.href=`/board/${boardId}`;
                                }).fail(function(error){
                                    alert(JSON.stringify(error));
                                });

                                }


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