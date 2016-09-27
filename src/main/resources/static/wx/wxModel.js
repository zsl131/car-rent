$(function() {
    setHeadName();
});
function setHeadName() {
    var name = $("title").html();
    if(name==null || $.trim(name)=='') {name = "汽车租赁";}
    $("#head-name").html(name);
}