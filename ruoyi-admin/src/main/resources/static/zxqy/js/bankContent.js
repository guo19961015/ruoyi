$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "https://www.ordoszxqy.org.cn/ry/homeAjax",
        dataType: "json",
        success: function (data) {
            var content = ""
            for (var i = 0; i < data.length; i++) {
                content = content + "<li class=\"col-sm-4\">\n" +
                    "<div>\n" +
                    "<img class=\"img-responsive\" src=\"" + data[i].bankImageUrl + "\" alt=\"\">\n" +
                    "<div>\n" +
                    "<span><a href=\"http://ordoszxqy.org.cn/ry/details?bankId=" + data[i].id + "\">查看详情</a></span><a href=\"http://ordoszxqy.org.cn/ry/index?bankId=" + data[i].id + "\" class=\"time\">立即申请</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</li>";
            }
            $('#capitalContent').html(content);
        }
    }).done(function (data) {
        // 请求成功
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // net::ERR_CONNECTION_REFUSED 发生时，也能进入
        console.info("网络出错");
    });
});
