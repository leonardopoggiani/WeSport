var identificatore = "";

function setRating(rating,userid) {
    console.log("SETTANDO "+rating);
}

function handleClick(name,surname,username,id,i)
{
    document.getElementById("tableText").textContent="NAME: "+name.toString();
    document.getElementById("tableText2").textContent="SURNAME: "+surname.toString();
    document.getElementById("tableText3").textContent="USERNAME: "+username.toString();
    document.getElementById("tableText4").textContent="PLAYER RATING:";
    var inptext = document.createElement( "input" );
    inptext.id="inp";
    identificatore=id;
    console.log("ident->"+identificatore);
    inptext.name="inputScore";
    var buttonElement=document.createElement( "button" );
    buttonElement.id="enterButton";
    buttonElement.innerHTML="INVIA";
    buttonElement.addEventListener("click", function() {
        console.log(document.getElementById("inp").value);
        setRating(document.getElementById("inp").value,id);
    });

    if(document.getElementById("input").value== undefined)
    {
        document.getElementById("input").value=1;
        document.getElementById("input").append(inptext);
        document.getElementById("input").append(buttonElement);
    }
}

function passaUrl(){
    var stringa="http://<%= actual_ip %>:8080/WeSport_webapp/bookedEvent?event=<%=bookedID%>&userId=";
    var url=stringa+identificatore;

    console.log("url-->"+url);
    location.assign(url);
    setRating(document.getElementById("inp").value,id);

    return url;
}
