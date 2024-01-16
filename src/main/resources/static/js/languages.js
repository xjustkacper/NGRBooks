$(document).ready(function() {
    $("[data-bs-lang-value]").click(function () {
        var selectedOption = $(this).data('bs-lang-value');
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
        }
    });
});