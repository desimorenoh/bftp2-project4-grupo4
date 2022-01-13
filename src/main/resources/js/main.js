$(document).ready(function () {

    $(".form-check-input :checkbox").click(function() {
        $("#flexCheckDefault").hide();
        $(".form-check-input :checkbox:checked").each(function () {
            $("." + $(this).val()).fadeIn();
        });

    })
})




