//Обработчики графических элементов страницы
//Определение функции для запросов на сервер для работы с графикой
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
            console.log(textStatus);
        },
        error (XHR, textStatus, errorThrown) {
            сonsole.log("Сервер не ответил или у пользователя пустой список сайтов");
            console.log(textStatus);
        }
    });
    console.log(JSON.stringify(response, null, 4));
    return response;
}
//Данные для показа
var sitename = [];
var created = [];
var upcreated = [];
var uniqword = [];
var word = [];
var latinword = [];
var cyrillycword = [];
var wordlist = [];
var letterlist = [];
var lenAnswer = 0; //Количество сайтов в списке
//Обработчик данных респонса
async function handlerlist() {
    var object = {};
    object["action"] = "dataview";
    var answer = await mainpost(object); //Это массив объектов
    lenAnswer = answer.length;
    var site = {};
    for (var i=0; i<lenAnswer; i++) {
        site = answer[i];
        sitename.push(site["url"]);
        created.push(new Date(site["tmsCreated"]).toISOString());
        upcreated.push(new Date(site["tmsLastIndex"]).toISOString());
        uniqword.push(site["uniqwords"]);
        word.push(site["words"]);
        latinword.push(site["latinWords"]);
        cyrillycword.push(site["cyrillicWords"]);
        wordlist.push(site["freqWordList"]);
        letterlist.push(site["freqLetter"]);
    }
}
//Обработчик вывода списка сайтов
async function showsitelist() {
    var siteList;
    for (var i=0; i<lenAnswer; i++) {
        siteList = "<tr>\n" +
            "                <td id=\""+i+"1\" title=\""+sitename[i]+"\">\n" +
            "                    <input id=\"f"+i+"\" name=\"flag\" type=\"radio\">"+sitename[i]+"</td>\n" +
            "                <td id=\""+i+"2\">"+created[i]+"</td><td id=\""+i+"3\">"+upcreated[i]+"</td>\n" +
            "                <td id=\""+i+"4\">"+uniqword[i]+"</td><td id=\""+i+"5\">"+word[i]+"</td>\n" +
            "                <td id=\""+i+"6\">"+latinword[i]+"</td>\n" +
            "                <td id=\""+i+"7\">"+cyrillycword[i]+"</td>\n" +
            "            </tr>";
        document.getElementById("tb").insertAdjacentHTML("beforeEnd",siteList);
        //console.log(siteList);
    }
}
//Построение гистограмм
async function builddataobj(list, j) {
    var key = [];
    var value = [];
    if (list !== []) {
    var arr = list.slice(1, -1).split(","); //строка вида - [белка=15, заяц=81, лебедь=16]
    if (j === "hw") {
        arr.length = 10;
    } else {
        arr.length = 30;
    }
    console.log(list);
    var arr2 = [];
    for (var i = 0; i < arr.length; i++) {
        arr2[i] = arr[i].split("=");
        key[i] = arr2[i][0];
        value[i] = Number(arr2[i][1]);
        console.log(key[i] + "  " + value[i]);
    }
    }
    return {
        labels: key,
        datasets: [ { label: "Graph" , data: value } ]
    };
}
var k = 0; //Номер сайта, меняется в функции radioclick
async function buildhist(list , i) {
    var dataobj = await builddataobj(list, i);
    var densityCanvas;
    if (i === "hw") {
        densityCanvas = document.getElementById("histword").getContext("2d");
    } else {
        densityCanvas = document.getElementById("histletter").getContext("2d");
    }
    return new Chart(densityCanvas, {
        type: 'bar',
        data: dataobj,
        options: {
            legend: {
                display: false
            }
        }
    });
}
//Обработчик показа гистрограмм
async function showhist(id, i) {
    if (id === "hl") {
        document.getElementById("histletter").value = await buildhist(letterlist[i], "hl");
        document.getElementById("hl").style.zIndex = 1000;
        document.getElementById("hw").style.zIndex = 1;
        console.log("показываем частоту использования букв");
    } else {
        document.getElementById("histword").value = await buildhist(wordlist[i], "hw");
        document.getElementById("hl").style.zIndex = 1;
        document.getElementById("hw").style.zIndex = 1000;
        console.log("показываем частоту использования слов");
    }
}
document.getElementById("showhw").addEventListener("click", function () { showhist("hw", k) }, false);
document.getElementById("showhl").addEventListener("click", function () { showhist("hl", k) }, false);
//Обработчик графических элементов
async function handlergraph() {
    await handlerlist(); //Сделали пост запрос и обработали данные списка сайтов
    showsitelist(); //Показали таблицу
    showhist("hw", k); //Показали гистограмму
    console.log("Страница готова к работе");
}
handlergraph(); //Эта самая главная функция, после выполнения которой, страница готова к работе



//Обработчики пользовательских действий
//Обработчик сценария удаления сайта из списка
var radio = false;
var urlsite;
var action;
function radioclick() {
    var target = event.target; //ловит элемент, на котором произошло событие
    var ptarget = target.parentNode;
    if (target.name !== "flag") return; //Игнорируем все элементы, кроме значков
    if (target.name === "flag") {
        radio = true;
        urlsite = document.getElementById(ptarget.id).innerText;
        k = Number(ptarget.id[0]);
    }
    console.log("из списка выделен сайт "+urlsite);
}
document.getElementById("tb").addEventListener("click", radioclick, false);
async function deleteclick() {
    if (radio === true) {
        action = "deletesite";
        var object = {};
        object["action"] = action;
        object["url"] = urlsite;
        var answer = await mainpost(object);
        alert(answer["message"]);
        console.log("сайт "+urlsite+" нужно "+action);
    }
}
document.getElementById("del").addEventListener("click", deleteclick, false);
//Обработчик сценария индексации сайта
async function indexclick() {
    if (radio === true) {
        action = "addstatistics";
        var object = {};
        object["action"] = action;
        object["url"] = urlsite;
        var answer = await mainpost(object);
        alert(answer["message"]);
        console.log("сайт "+urlsite+" нужно "+action);
    }
}
document.getElementById("index").addEventListener("click", indexclick, false);
//Обработчик сценария добавления сайта
async function addclick() {
    urlsite = document.getElementById("addurl").value;
    if (urlsite.length !== 0) {
        action = "addsite";
        var object = {};
        object["action"] = action;
        object["url"] = urlsite;
        var answer = await mainpost(object);
        alert(answer["message"]);
        console.log("сайт " + urlsite + " нужно " + action);
        var questionindex = confirm("Индексировать сейчас или позже?");
        if (questionindex === true) {
            radio = true;
            indexclick();
        }
    }
}
document.getElementById("add").addEventListener("click", addclick, false);
//Обработчик кнопки выйти
async function goout() {
    //Запрос на заказ страницы входа
    window.location.replace("/IndexSite");
    console.log("выходим и деавторизуемся");
    //пост запрос на деавторизацию
    jQuery.ajax({
        url: "",
        type: "POST",
        async: true,
        contentType: "application/json",
        dataType: "json",
        processData: false,
        data : JSON.stringify({"action":"deauthorization"}),
        success: function(data, textStatus, XHR) {
            console.log("Операция деавторизации прошла со статусом "+textStatus);
        },
        error (XHR, textStatus, errorThrown) {
            console.log("Операция деавторизации прошла со статусом "+textStatus);
        }
    });
}
document.getElementById("go_out").addEventListener("click", goout, false);








