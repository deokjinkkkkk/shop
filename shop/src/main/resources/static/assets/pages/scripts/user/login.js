$(document).ready(function() {
  const email_auth_cd = '';
  const $signInBtn = $('#toggleSignIn');
  const $signUpBtn = $('#toggleSignUp');

  const $signUpForm = $('#sign-up-container');
  const $signInForm = $('#sign-in-container');

  // Change form when clicking on button
  const changeForm = ($form1, $form2) => {
    $form1.toggleClass('hide');
    $form2.toggleClass('hide');
  }

  // Show the Sign In form
   // Show the Sign In form
   $signInBtn.click(() => {
    changeForm($signUpForm, $signInForm);
  });

  // Show the Sign Up form
  $signUpBtn.click(() => {
    changeForm($signUpForm, $signInForm);
  });
  $("#signUp").click(function() {
    // 유효성 검사 함수 호출
    if (!validateForm()) {
        // 유효성 검사를 통과하지 못하면 기본 제출을 막습니다.
        event.preventDefault();
        return false;
    }
    var emailSel = $("#email").val() + "@" + $("#domain").val();
    $("#signUpProc").submit()
    // 유효성 검사가 통과하면 폼 제출이 계속 진행됩니다.
  });

  $("#emailBtn").click(function() {
    emailSel = $("#email").val() + "@" + $("#domain").val();
    if(emailSel == '@'){
      alert("이메일을 입력해주세요")
      return false;
    }
    $.ajax({
        url : "/user/mailConfirm",
        type:'post',
        data : {
            "email" : emailSel
        },
        success : function(data) {
            console.log(data);
            if (data == "success") {
                console.log("성공하였습니다.")
                $(".email_chk").css("display","inline-block")
                $(".email_alr").css("display","none")
                email_auth_cd = data; //인증번호 담아두기
            } else {
                console.log("아이디가 중복상태입니다.")
                $(".email_alr").css("display","inline-block")
                $(".email_chk").css("display","none")
            }
        },
        error : function(xhr, status, error) {
            alert("오류가 발생하였습니다.")
        }
    });
  });

});

//이메일 인증 번호 인증후에 버튼 활성화
//비밀번호 8자 이상 숫자,문자 섞어 스게하기
//휴대전호 번호 10자 이상 되야 하기
//빈칸 있으면 경고문 드게 하기


// 기존 함수들을 활용하여 폼 유효성 검사 수행
function validateForm() {
  var name = $("#name").val();
  var emailSel = $("#email").val() + "@" + $("#domain").val();
  var userPwd = $("#userPwd").val();
  var passchk = $("#passchk").val();
  var tel = $("#tel").val();

  // 필수 필드에 대한 유효성 검사
  if (name === '' || emailSel === '' || userPwd === '' || passchk === '' || tel === '') {
      console.log("필수 항목을 모두 입력하세요.");
      return false;
  }

  // 다른 함수에 있는 유효성 검사 함수 호출
  if ( !pwdChk() || !pwdChk2() || !telChk()) {
      console.log("유효성 검사를 통과하지 못했습니다.");
      return false;
  }

  if(email_auth_cd )
  return true; // 모든 유효성 검사를 통과했을 때 true 반환
}


function checkEmail(){
  var email  = $("#email").val() + "@"+$("#domain").val();
  var emailSel = $("#domain").val();
  
  if(emailSel == ''){
    $(".email_com").css("display","inline-block")
    $(".email_alr").css("display","none")
    $(".email_chk").css("display","none")
  }else {
     $(".email_chk").css("display","none")
       $(".email_alr").css("display","none")
       $(".email_com").css("display","none")
  }
  
    $.ajax({
          url : "/user/emailChk",
          type:'post',
          data : {
              "email" : email
          },
          success : function(data) {
            console.log(data)
              if (data == "success") {
                  
                  $(".email_chk").css("display","inline-block")
                  $(".email_alr").css("display","none")
                  $(".email_com").css("display","none")
              } else {
                  
                  $(".email_alr").css("display","inline-block")
                  $(".email_chk").css("display","none")
                  $(".email_com").css("display","none")
              }
          },
          error : function(xhr, status, error) {
              console.log(error)
          }
      });
  
  
}

function pwdChk(){
  var userPwd = $("#userPwd").val()
  var num = userPwd.search(/[0-9]/g);
  var eng = userPwd.search(/[a-z]/ig);

  if(userPwd.length < 8 || num < 0 || eng < 0 ){
      $(".pwd_bug").css("display","inline-block")
      $(".pwd_chk").css("display","none")
      return false;
  } else {
      $(".pwd_chk").css("display","inline-block")
      $(".pwd_bug").css("display","none")
      return true;
  }
}

function pwdChk2(){
  var userPwd = $("#userPwd").val()
  var passchk = $("#passchk").val()
  if(userPwd == ''){
    $(".pwdC_chk").css("display","none")
    $(".pwdC_bug").css("display","none")
    return false;
  }
  if(userPwd != passchk ){
      $(".pwdC_bug").css("display","inline-block")
      $(".pwdC_chk").css("display","none")
      return false;
  } else {
      $(".pwdC_chk").css("display","inline-block")
      $(".pwdC_bug").css("display","none")
      return true;
  }
}

function telChk() {
  var tel = $("#tel").val();

  if (tel.length == 11 || tel.length == 10) {
      var telPattern = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;

      if (telPattern.test(tel)) {
          tel = tel.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1$2$3");
          $("#tel").val(tel);
          $(".tel_chk").css("display", "none");
          return true;
      } else {
          $(".tel_chk").css("display", "inline-block");
          return false;
      }
  } else {
      $(".tel_chk").css("display", "inline-block");
      return false;
  }
}


