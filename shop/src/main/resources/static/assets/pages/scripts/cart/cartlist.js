$(document).ready(function(){
	var totalprice = 0;
		$("strong#total").each(function() {
			var text = $(this).text().trim()
			var numericValue = parseFloat(text)
			if(!isNaN(numericValue)){
				totalprice += numericValue
			}
		    
		});
	$('.price').text(totalprice +"원");
	/*수량 조정*/
    $(".quantity_modify_btn").on("click",function(){
        var cartId = $(this).data("id")
        var count = $(this).prev().children('input').val()
        
        $("#cartNum").val(cartId)
        $("#cartCnt").val(count)
        $("#cartUpdate").submit()
    })
    
    /*상품 삭제 */
    $(".del-goods").on("click",function(e){
        e.preventDefault()
        var cartId = $(this).data("id")
        $('#delCartNum').val(cartId)
        $('#cartDelete').submit()	
    })  
    /*주문 페이지 이동 */
    $("#checkoutBtn").on("click",function(){
        let contents ='';
            let orderNum = 0;
            $(".cart_info").each(function(index,element){
            let productNum = $(element).find("#productNum").val();
                let cartCnt =$(element).find("#cartCnt").val();
                let productNum_input = "<input name='orders[" + orderNum + "].productNum' type='hidden' value='"+productNum+"'>"
                let cartCnt_input = "<input name='orders[" + orderNum + "].proCnt' type='hidden' value='"+cartCnt+"'>"
                
                contents += productNum_input;
                contents += cartCnt_input;
                
                orderNum += 1;
            })
            if(!contents){
                alert("상품이 존재 하지 않습니다.")
            }
            $(".order_form").html(contents)
            $(".order_form").submit()
            
    })
})
