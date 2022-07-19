$(document).ready(function(){
    getCoffeeList();

    $("#btnReg").click(function(){
        $("#message").text('작업중입니다... 잠시 기다려주세요..')
        let korName = document.getElementById("korName").value;
        let engName = document.getElementById("engName").value;
        let price = document.getElementById("price").value;
        let coffeeCode = document.getElementById("coffeeCode").value;

        if(!korName){
            alert("커피 이름(한글)을 입력해주세요.");
            return false;
        }

        if(!engName){
            alert("커피 이름(영문)을 입력해주세요.");
            return false;
        }

        if(!price){
            alert("가격을 입력해주세요.");
            return false;
        }

        if(!coffeeCode){
            alert("커피 코드를 입력해주세요.");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/v11/coffees",
            data: JSON.stringify({
                korName: korName,
                engName: engName,
                price: price,
                coffeeCode: coffeeCode
            }),
            success: function(result) {
                if($('#coffeeList > tbody:first').find("td").length == 1){
                    $('#coffeeList > tbody').empty()
                }
                $('#coffeeList > tbody:last').append('<tr><td>' + result.data.korName + '(' + result.data.engName + '</td><td>'
                    + result.data.price + '</td><td>' + result.data.coffeeStatus + '</td></tr>');

                $("#message").text('')
            },
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        });
    });

    $("#btnOther").click(function(){
        alert('기다리는 동안 다른 작업을 할 수 있어요. ^^')
    })

    function getCoffeeList(){
        $.get("http://localhost:8080/v11/coffees?page=1&size=10",{

        }, function(result){
            if(result.data.length > 0){
                let contents = "";
                for (let i in result.data) {
                    let coffee = result.data[i];
                    contents += '<tr><td>' + coffee.korName + '(' + coffee.engName + '</td><td>' + coffee.price
                        + '</td><td>' + coffee.coffeeStatus + '</td></tr>';
                }

                $("#coffeeContents").html(contents);
            }else{
                $('#coffeeList > tbody:last')
                .append('<tr><td colspan="3" align="center">등록된 커피가 없습니다.</td></tr>');
            }
        }, 'json');
    }
});