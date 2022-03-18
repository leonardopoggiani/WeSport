function saveLocalSession(sports="tennis", month=new Date().getMonth(), year=new Date().getFullYear()){
    sessionStorage.setItem("sports", sports);
    sessionStorage.setItem("month", month);
    sessionStorage.setItem("year", year);
}

function restoreLocalSession(){
    var sport = "Tennis";
    var month = new Date().getMonth();
    var year = new Date().setFullYear();
    if (sessionStorage.length != 0){
        sport = sessionStorage.getItem("sports");
        month = sessionStorage.getItem("month");
        year = sessionStorage.getItem("year");
    }
    var select = document.getElementById("sports");
    var month_year = document.getElementById("month-year");
    select.value = sport;
    month_year.value = month + year;


}

function onload(e){
    restoreLocalSession();
}

function onchange(e) {
    var field = document.getElementById("sports");
    window.alert("Change:");
    saveLocalSession(sports=field.value);
    window.location.reload();
}

document.getElementById('sports').addEventListener('change', onchange);
window.addEventListener('load', onload);