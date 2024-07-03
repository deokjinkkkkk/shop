$(document).ready(function(){
    setPrice()

    $("#zipSearch").on('click',function(){
        zipAddressSearch();
    })
    $("#button-confirm").on("click",function(){
        let contents = "";
        let orderDnum = 0;
        let user = $("#userNumber").val();
        $('.product-list').each(function(index,element){
            
            
        //상퓸번호 
        let productNum = $(element).find("#productNum").val();
        
        //상품수량
        let proCnt = $(element).find("#proCnt").val();
        
        let proPrice = $(element).find("#proPrice").val();
        //상품 번호 input태그 만들기
        let productNum_input = "<input name ='orders[" + orderDnum + "].productNum' type ='hidden' value='" + productNum + "'>";
        let orderCnt_input = "<input name ='orders[" + orderDnum + "].orderCnt'type ='hidden' value='" + proCnt + "'>";
        let orderPrice_input = "<input name ='orders[" + orderDnum + "].orderPrice'type ='hidden' value='" + proPrice + "'>";
        
        contents += productNum_input;
        contents += orderCnt_input;
        contents += orderPrice_input;
        
        orderDnum += 1;
        })
        if(!contents){
            alert("주문 정보가 존재하지 않습니다.")
        }
        contents += "<input name ='userNumber' type ='hidden' value='" + user + "'>"
        $(".checkOut_div").html(contents);
        $(".checkOut_form").submit();
    })
})



function setPrice() {
    let totalPrice = 0; //총 가격
    let deliveryPrice = 0;// 배송비 
    let finalPrice = 0;//최종 가격
        $('.product-list').each(function(index,element){
            console.log($(element).find('#oneTotalPrice').text());
            numericValue = parseInt($(element).find('#oneTotalPrice').text());  		
            totalPrice += numericValue;		
        });
    
    if(totalPrice => 30000){
        deliveryPrice = 0;
    }else if(totalPrice == 0){
        deilveryPrice = 0;
    }else{
        deliveryPrice = 3000;
    }
    finalPrice = deliveryPrice + totalPrice;
    $('.totalPrice').text(totalPrice +"원")
    $('.deliveryPrice').text(deliveryPrice +"원")
    $('.finalPrice').text(finalPrice +"원")	
}

   /* 우편번호 api */
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
