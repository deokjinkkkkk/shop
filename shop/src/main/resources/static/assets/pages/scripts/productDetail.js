$(document).ready(function(){

    reviewlist();
});


$("#orderBtn").click(function(){
	let proCount = $("#cnt").val();
    $("#orderForm").find("input[name='orders[0].orderCnt']").val(proCount);
    $("#orderForm").submit();
});

$("#updateBtn").click(function(){
	let productNum = $("input[name='productNum']").val();
    location.href = "/product/updateForm?productNum=" + productNum;
});

$("#deleteBtn").click(function(){
    $("#cartInsert").attr("action", "/product/productDelete").submit();
});

$("#cartBtn").click(function(e){
    var cnt = $("#cnt").val();Q
    console.log(cnt);
    var productNumber = $("#productNum").val();
    $.ajax({
        url: '/cart/add',
        type: 'POST',
        data: {
            cnt: cnt,
            productNum: productNumber
        },
        success: function(result){
            if(result == 0){
                alert("장바구니에 추가를 하지 못하였습니다.");
            } else if(result == 1){
                alert("장바구니에 추가 하였습니다.");
            } else if(result == 2){
                alert("장바구니에 이미 상품이 있습니다.");
            } else if(result == 3){
                alert("로그인이 필요 합니다.");
            } else if(result == 4){
                alert("상품의 재고가 부족합니다.");
            }
        }
    });
});

$("#review_button").click(function(){
	let productNum = $("input[name='productNum']").val();
    let review = $("#reviewText").val();
    let revStar = $("input[name='rating']:checked").val();
    if (review == "") {
        alert("내용을 작성해 주세요");
        return;
    }
    if(revStar == undefined){
        alert("별점을 입력해주세요");
        return;
    }
    $.ajax({
        url: "/review/add",
        type: "POST",
        data: {
            revContent: review,
            revStar: revStar,
            productNum: productNum
        },
        success: function(result){
            if (result == 1) {
                alert("등록완료");
                reviewlist();
            } else if(result == 2){
                alert("로그인후에 댓글등록이 가능합니다.");
            } else if (result == 3) {
                alert("리뷰를 작성한 상품 입니다.");
            }
        }
    });
});

function reviewlist(){
	let productNum = $("input[name='productNum']").val();
    $.ajax({
        url: '/review/list',
        type: 'get',
        data: {productNum: productNum},
        success: function(data){
            let reviewText = '';
            $(data).each(function(index, reviews){
                reviewText += '<div class="review-item-submitted">';
                reviewText += '<div class="rate2"><span style="width: '+reviews.revStar+'0%"></span></div>';
                reviewText += '<strong>'+reviews.email+'</strong>';
                reviewText += '<em>'+reviews.revDate+'</em>';
                reviewText += '<div class="rateit" data-rateit-value="" data-rateit-ispreset="true" data-rateit-readonly="true"></div>';
                reviewText += '</div>';
                reviewText += '<div class="review-item-content">';
                reviewText += '<p>'+reviews.revContent+'</p>';
                reviewText += '</div>';
            });
            $(".review-item.clearfix").html(reviewText);
            let paginationText = '';
            if (data.startPage > 1) {
                paginationText += '<li><a href="javascript:gopage(' + (data.startPage - 1) + ')">&laquo;</a></li>';
            }
            for (let num = data.startPage; num <= data.endPage; num++) {
                paginationText += '<li><a href="javascript:gopage(' + num + ')">' + num + '</a></li>';
            }
            if (data.endPage < data.lastPage) {
                paginationText += '<li><a href="javascript:gopage(' + (data.endPage + 1) + ')">&raquo;</a></li>';
            }
            $("#pagination").html(paginationText);
        }
    });
}
