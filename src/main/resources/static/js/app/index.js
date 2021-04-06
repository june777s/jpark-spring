var main = { //굳이 index.js 안에 main 이라는 객체를 만들어 두는 이유는 중복된 함수 이름이 자주 발생할수 있으므로
             //객체 선언 해서 이 객체 안에서만 사용이 가능하겠끔 만듬
    init : function(){
    var _this = this;
    $('#btn-save').on('click',function(){
        _this.save();
    });
 },
 save : function(){
 var data = {
     title: $('#title').val(),
     author : $('author').val(),
     content : $('#content').val()
    };
    $.ajax({
       type:'POST' ,
       url:'/api/v1/posts',
       dataType:'json',
       contentType:'application/json; charset=utf-8',
       data:JSON.stringify(data)
    }).done(function() {
        alert('글이 등록되었습니다.');
        window.location.href = '/'; //
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
 }
};

main.init();