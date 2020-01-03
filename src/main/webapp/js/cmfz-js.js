/**
 *      表单重置
 *
 * @param formId
 */
function resetFrom(formId) {

    $(formId).get(0).reset();
}


function setTheme(option) {

    $("#theme-body").attr("class", "main-body-"+option);
}
