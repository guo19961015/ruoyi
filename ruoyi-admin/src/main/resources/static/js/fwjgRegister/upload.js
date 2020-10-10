$(document).ready(function () {
    $("input[name='qualificationDisplay']").click(function () {
        $("input[name='url']").click();
    });
    $("input[name='organizationPicture']").click(function () {
        $("input[name='url1']").click();
    })
});
function uploadFile(filePath) {
    var prefix = ctx + "system/loan";
    $.modal.loading("文件上传中，请勿做其他操作，耐心等待"); 	//若依的遮罩
    var formData = new FormData();
    formData.append('file', $("input[name='" + filePath + "']")[0].files[0]);
    $.ajax({
        url: prefix + "/uploadFile",
        type: 'post',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (result) {

            if (filePath == "url") {
                $("input[name='qualificationDisplay']").val(result.fileName);
            } else {
                $("input[name='organizationPicture']").val(result.fileName);
            }

            $.modal.closeLoading(); 	//遮罩结束
        }
    });
}