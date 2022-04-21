var identificatore = "";

function setRating(rating, userid) {
    console.log("SETTANDO "+rating);
}

function handleClick(name,surname,username,id, ip)
{
    var name_text = document.getElementById("tableText");
    name_text.textContent="NAME: "+ name.toString();
    name_text.style.visibility = "visible";

    var surname_text = document.getElementById("tableText2");
    surname_text.textContent="SURNAME: " + surname.toString();
    surname_text.style.visibility = "visible";

    var username_text = document.getElementById("usernamerating");
    username_text.textContent="USERNAME: " + username.toString();
    username_text.style.visibility = "visible";

    var rating_text = document.getElementById("tableText4");
    rating_text.textContent="PLAYER RATING:(from 0 to 5)";
    rating_text.style.visibility = "visible";

    var input = document.getElementById("input");
    input.style.visibility = "visible";
    style="display:none;"

    var form = document.getElementById("form");
    form.action = "http://" + ip + ":8080/WeSport_webapp/bookedEvent?id=" + id;

    var inptext = document.getElementById("rating");
    inptext.style.visibility = "visible";

    inptext.id = id;
    identificatore = id;
    console.log("ident->" + identificatore);
    inptext.name = "inputScore";
    var buttonElement = document.getElementById( "button" );
    buttonElement.style.visibility = "visible";
    buttonElement.id = id;


    if(document.getElementById("input").value== undefined)
    {
        document.getElementById("input").value=1;
        document.getElementById("input").append(inptext);
        document.getElementById("input").append(buttonElement);
    }
}