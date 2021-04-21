var main = { //굳이 index.js 안에 main 이라는 객체를 만들어 두는 이유는 중복된 함수 이름이 자주 발생할수 있으므로
             //객체 선언 해서 이 객체 안에서만 사용이 가능하겠끔 만듬
    init : function(){
    var _this = this;
    $('#btn-save').on('click',function(){
        _this.save();
    });
    $('#btn-update').on('click',function(){ //#btn-update란 id를 가진 html 엘리먼트에 click이벤트가 밸생시 update function 실행
        _this.update();
    });
    $('#btn-delete').on('click',function(){
        _this.del();
    });
 },
 save : function(){
 var data = {
     title: $('#title').val(),
     author : $('#author').val(),
     content : $('#content').val()
    };
    //ajax 선언
    $.ajax({
        //전송 방식
       type:'POST' , //요청 메소드
       url:'/api/v1/posts', // url
       dataType:'json',
       contentType:'application/json; charset=utf-8',
       data:JSON.stringify(data)
    }).done(function() {
        alert('글이 등록되었습니다.');
        window.location.href = '/'; //
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
 },
 update : function(){
    var data = {
        title: $('#title').val(),
        content: $('#content').val()
    };

    var id = $('#id').val();

    $.ajax({
       type:'PUT' , //요청 메소드 put 방식 이미 postsapicontroller 에 put 방식으로 선언하였기 때문에 put 사용
                    //보통 생성(create)-post , 읽기(read)-get, 수정(update)-put, 삭제(delete)-delete
                    //REST에서 crud는 다음과 같이 HTTP Method에 매핑 됨
       url:'/api/v1/posts/'+id, // url
       dataType:'json',
       contentType:'application/json; charset=utf-8',
       data:JSON.stringify(data)
    }).done(function(){
        alert('글이 수정되었습니다.');
        window.location.href='/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
 },
 del : function(){
     var id = $('#id').val();

     $.ajax({
            type:'DELETE' , //요청 메소드 put
            url:'/api/v1/posts/'+id, // url
            dataType:'json',
            contentType:'application/json; charset=utf-8',
         }).done(function(){
             alert('글이 삭제되었습다.');
             window.location.href='/';
         }).fail(function (error){
             alert(JSON.stringify(error));
         });
  }
};

main.init();