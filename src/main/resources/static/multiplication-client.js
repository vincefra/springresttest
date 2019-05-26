function displayProducts() {
    $.ajax(
        {
            url: "http://localhost:8080/customer",
        }).then(function (data) {

        data.forEach(function (row) 
        {
            $('#stats-body').append(
                "<tr>" + 
                "<td>" + row.id + "</td>" +
                "<td>" + row.name + "</td>" +
                "<td>" + row.brand + "</td>" +
                "<td>" + row.description + "</td>" +
                "<td>" + row.price + "</td>" +
                "<td><button class='btn btn-success' onclick='addToCart(" + row.id + ")'>add To Cart</button></td></tr>");
        });       
    });
}

/*
 * Gjorde om button till button onclick, den lästes annars som att den anropade metoden istället, det var aldrig en onclick
 * Gjorde om reg-button hide, det klassades som id istället (eller tvärtom, minns ej)
 * Kodade om addToCart att den hanterar row.id istället för objekt, fick problem annars
 * Kodade om serverdelen att den tar emot product id, söker upp i db och skapar upp nytt product objekt
 */

function preLogin(){
    $("#stats").hide();
    $("#reg-form").hide();
    $("#result-message").hide();
}

function registerClick(){
    event.preventDefault();
    $("#login-form").hide();
    $("#reg-button").hide();
    $("#reg-form").show();
}



function addToCart(data){
    
    var data = data;
    $.ajax({
            url: '/addToCart',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                $("#result-message").show();
                if (result.id >0) {
                    $('#result-message').empty().append("You added a product: "+ result.productType);
                    $("#login-form").hide();
                    $(".reg-button").hide();
                    $(".stats").show();                    
                } else {
                    $('.result-message').empty().append("Ooops that's not correct! But keep trying!");
                }
            }
        });
    
    
}


$(document).ready(function () {

    displayProducts();
    preLogin();
    
    $(".reg-button").click(function (event) 
    {
        registerClick();
    });
    
    
    $("#login-form").submit(function (event) {

    // Don't submit the form normally
        event.preventDefault();
        
        // Get some values from elements on the page
        var $form = $(this),
                username = $form.find("input[name='user-name']").val(),
                password = $form.find("input[name='user-pass']").val();
                
        // Compose the data in the format that the API is expecting
        var data = {username: username, password: password};
        // Send the data using post
        $.ajax({
            url: '/login',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                if (result.username !== null) {
                    $('#result-message').empty().append("You have logged in: " + result.username);
                    $("#login-form").hide();
                    $("#reg-button").hide();
                    $("#stats").show();                    
                } else {
                    $('#result-message').empty().append("Ooops that's not correct! But keep trying!");
                }
            }
        });
    });


    $("#reg-form").submit(function (event) {

    // Don't submit the form normally
        event.preventDefault();
        // Get some values from elements on the page
        var $form = $(this),
                name = $form.find("input[name='name']").val(),
                username = $form.find("input[name='user-name']").val(),
                password = $form.find("input[name='user-pass']").val();
        // Compose the data in the format that the API is expecting
        var data = {name : name, username: username, password: password};
        // Send the data using post
        $.ajax({
            url: '/registration',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                if (result.username !== null ) {
                    $('#result-message').empty().append("You have been registered");
                    $("#reg-form").hide();
                    $("#login-form").show();
                    $("#reg-button").show();
                } else {
                    $('#result-message').empty().append("Ooops there is already a user!");
                }
            }
        });
    });
});
