var sel = $('#selCateogry').val();
var act = $("#link").text($("#catName").val())
function gopage(page) {
    
        location.href = "/product/list?category=" + sel + "&page=" + page;
}
function propage(category) {
    location.href = "/product/list?category=" + category;
}