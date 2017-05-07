jQuery(document).ready(function($) {
    // Reveal Login form
    setTimeout(function() {
        $(".fade-in-effect").addClass('in');
    }, 1);
    
    // Validation and Ajax action
    $("form#login").validate({
        rules: {
            username: {
                required: true
            },

            passwd: {
                required: true
            }
        },

        messages: {
            username: {
                required: '请输入账号'
            },

            passwd: {
                required: '请输入密码'
            }
        },

        // Form Processing via AJAX
        submitHandler: function(form) {
            show_loading_bar(70); // Fill progress bar to 70% (just a given value)

            $('#loginBtn').attr("disabled", true);

            $.ajax({
                url: "login",
                method: 'POST',
                dataType: 'json',
                data: {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                success: function(resp) {
                    if (resp.head.state=="success") {
                        window.location.href = 'home';
                    }else{
                        $(".login-footer span").html("用户名或密码不正确");
                    }
                }
            });
        }
    });

    //Set Form focus
    $("form#login .form-group:has(.form-control):first .form-control").focus();
});