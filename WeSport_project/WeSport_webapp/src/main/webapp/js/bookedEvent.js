function setRating() {
    console.log("SETTANDO");
}

function handleClick(name,surname,username)
{
    console.log("Dentro handle "+name);
    document.getElementById("tableText").textContent="NAME: "+name.toString();
    document.getElementById("tableText2").textContent="SURNAME: "+surname.toString();
    document.getElementById("tableText3").textContent="USERNAME: "+username.toString();
    document.getElementById("input").textContent="PLAYER RATING:";
    var inptext = document.createElement( "input" );

    var buttonElement = document.createElement( "button" );

    inptext.id="inp";
    document.getElementById("input").append(inptext);
    document.getElementById("input").append(buttonElement);


    console.log("Dentro handle2");

}
