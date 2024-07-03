
$(document).ready(function(){
    $(".create").css('display', 'none');
    $("#addressList").css('display', 'none');

    $("#createAddress").on('click',function(){
        var tel1 = 	$("#tel1").val();
        var tel2 =	$("#tel2").val();
        var tel3 =	$("#tel3").val();
        var realtel =tel1  + tel2 + tel3;
        $("#addTel").val(realtel);
        createAddressForm.submit();
    })

    $("#zipSearch").on('click',function(){
        zipAddressSearch();
    })

    $("#addressDelete").on('click',function(){
        var addNum = $(this).data("id")
        $("#addNum").val(addNum);
        $("#deleteAddressForm").submit();
    })
    
    $("#addressUpdate").on('click',function(){
        var addNum = $(this).data("id")
        $("#addNum").val(addNum);
        $("#deleteAddressForm").submit();
    })
})

function showAddress(page){
    //주소지 등록 페이지 보이기
    if(page == 1){
        $(".create").css('display', 'none');
        $(".list").css('display', 'block');
        $("#addressList").css('display', 'none')
        $("#addressCreate").css('display', 'block');
    }else{
        $(".create").css('display', 'block');
        $(".list").css('display', 'none');
        $("#addressCreate").css('display', 'none');
        $("#addressList").css('display', 'block');
        
    } 
    //주소지 목록 페이지 보이기 	
}
//우편번호 api 
function zipAddressSearch(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
            addr += extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addZip').value = data.zonecode;
            document.getElementById("addAddr").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addAddr2").focus();
        }
    }).open();  
}