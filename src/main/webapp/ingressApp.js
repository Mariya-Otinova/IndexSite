//Определение функции для запросов на сервер
async function mainpost(obj) {
    console.log(obj);
    var response;
    await jQuery.ajax({
        url: "",
        type: "POST",
        async: true,
            contentType: "application/json",
            dataType: "json",
            processData: false,
            data : JSON.stringify(obj),
            success: function(data, textStatus, XHR) {
                response = data;
                console.log(response);
                console.log(textStatus);
            },
            error (XHR, textStatus, errorThrown) {
            console.log(textStatus);
            }
        });
    console.log(JSON.stringify(response, null, 4));
    return response;
}
//Обработчик сценария показа блока регистрации и смены пароля
function showpsw2(id) {
    if (id==="registration") {
        document.getElementById("npf").innerHTML = 'Повторите пароль:';
        console.log("повторите пароль");
    } else {
        document.getElementById("npf").innerHTML = 'Новый пароль:';
        console.log("новый пароль");
    }
    document.getElementById("psw2").style.display = 'block';
    console.log("блок открыт");
}
document.getElementById("registration").addEventListener("click", function () { showpsw2("registration") }, false);
document.getElementById("change_password").addEventListener("click", function () { showpsw2("change_password") }, false);
//Обработчик сценария отмены на странице авторизации
function hidepsw2() {
    document.getElementById("psw2").style.display = 'none';
    console.log("блок скрыт");
}
document.getElementById("cl").addEventListener("click", hidepsw2, false);
//Обработчик перехода на страницу статистики
async function authostatus() {
    var object = {};
    var answer = {};
    var status = false;
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;
    var newpassword = document.getElementById("new_password").value;
    var labelnpf = document.getElementById("npf").innerHTML;
    console.log("login= "+login+", password= "+password+", newpassword= "+newpassword);
    switch (true) {
        case newpassword==="" && password!=="" && login!=="":
            console.log("операция авторизации");
            object["action"] = "authorization";
            object["login"] = login;
            object["password"] = password;
            answer = await mainpost(object);
            if (answer["message"] === "Авторизация прошла успешно!") {
                status = true;
            }
            alert(answer["message"]);
            break;
        case newpassword!=="" && password!=="" && labelnpf==='Повторите пароль:' :
            console.log("операция регистрации");
            if (login.length > 5 && password.length > 6 && newpassword===password) {
                object["action"] = "registration";
                object["login"] = login;
                object["password"] = password;
                answer = await mainpost(object);
                if (answer["message"] === "Регистрация прошла успешно!") {
                    status = true;
                }
                alert(answer["message"]);
            } else {
                alert("Логин и пароль недостаточной длины или пароли не совпадают");
            }
            break;
        case newpassword!=="" && labelnpf==='Новый пароль:' :
            console.log("операция смены пароля");
            if ( newpassword.length > 6) {
                object["action"] = "changepassword";
                object["login"] = login;
                object["password"] = password;
                object["newpassword"] = newpassword;
                answer = await mainpost(object);
                if (answer["message"] === "Смена пароля прошла успешно!") {
                    status = true;
                }
                alert(answer["message"]);
            } else {
                alert("Новый пароль слишком короткий!");
            }
            break;
        default:
            console.log("операция авторизации не состоялась");
    }
    //Запрос авторизации на сервер, возвращает логическое значение, которое записываеся в status
    return status;
}
async function jumpstat() {
    var st = await authostatus();
    console.log("статус= "+st);
    if ( st === true ) {
        //Запрос на показ страницы статистики
        window.location.assign("Statistic");
        console.log("просмотр статистики разрешён");
    } else {
        console.log("просмотр статистики запрещён");
    }
}
document.getElementById("igr").addEventListener("click", jumpstat , false);
