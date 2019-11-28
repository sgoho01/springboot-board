
function columnValidate(form) {

    var result = true;

    $("form[name=" + form + "]").find("input, select, textarea").each(function(i){
        if($(this).prop('required')){
            if(!$(this).val().trim()){
                $(this).focus();
                alert($(this).prop("title") + "은(는) 필수항목입니다.");
                result = false;
                return false;
            }
        }
    });

    return result;
}
