function updateNextYearIfNeeded(month, year){
    if(month === 11){
        return parseInt(year) +1;
    }
    else return year;
}

function updateNextMonth(month){
    return (month+1)%12;
}

function updatePreviousMonth(month){
    return [12+(month-1)]%12;
}

function updatePreviousYearIfNeeded(month, year){
    if (month === 0) return parseInt(year) -1;
    else return year;
}

function getMonthNameFromNumber(month){
    monthsArray = ["JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"];
    return monthsArray[month];
}

function getMonthNumberFromName(name) {
    switch (name) {
        case "JANUARY":
            return 0;
        case "FEBRUARY":
            return 1;
        case "MARCH":
            return 2;
        case "APRIL":
            return 3;
        case "MAY":
            return 4;
        case "JUNE":
            return 5;
        case "JULY":
            return 6;
        case "AUGUST":
            return 7;
        case "SEPTEMBER":
            return 8;
        case "OCTOBER":
            return 9;
        case "NOVEMBER":
            return 10;
        default:
            return 11;
    }
}
function saveLocalSession(sports=document.getElementById("default").value, month=(new Date()).getMonth(), year=(new Date()).getFullYear()){
    sessionStorage.setItem("sports", sports);
    sessionStorage.setItem("month", month);
    sessionStorage.setItem("year", year);
}


function restoreLocalSession(){
    var sport = document.getElementById("default").value;
    var now = new Date();
    var monthRestored = now.getMonth();
    var yearRestored = now.getFullYear();
    if (sessionStorage.length !== 0){
        sport = sessionStorage.getItem("sports");
        monthRestored = sessionStorage.getItem("month");
        yearRestored = sessionStorage.getItem("year");
    }
    var select = document.getElementById("sports");
    var month = document.getElementById("month");
    var year = document.getElementById("year");
    select.value = sport;
    month.innerText = getMonthNameFromNumber(monthRestored);
    year.innerText = yearRestored;

}

function onload(e){
    restoreLocalSession();
}

function onchange(e) {
    var field = document.getElementById("sports");
    var month = document.getElementById("month");
    var year = document.getElementById("year");
    //saveLocalSession(sports=field.value, month=month.value, year=year.value);
    saveLocalSession(sports=field.value, 2, 2022);
    window.location.reload();
}

function onclickNext(e){
    var field = document.getElementById("sports");
    var monthName = document.getElementById("month");
    var month = getMonthNumberFromName(monthName.innerText);
    var year = document.getElementById("year");
    var newMonth = updateNextMonth(month);
    var newYear = updateNextYearIfNeeded(month, year.innerText);
    saveLocalSession(sports=field.value, month=newMonth, year=newYear);
    window.location.reload();
}

function onclickPrevious(e){
    var field = document.getElementById("sports");
    var monthName = document.getElementById("month");
    var month = getMonthNumberFromName(monthName.innerText);
    var year = document.getElementById("year");
    var newMonth = updatePreviousMonth(month);
    var newYear = updatePreviousYearIfNeeded(month, year.innerText);
    saveLocalSession(sports=field.value, month=newMonth, year=newYear);
    window.location.reload();
}

document.getElementById('sports').addEventListener('change', onchange);
document.getElementById("previous").addEventListener("click", onclick);
document.getElementById("next").addEventListener("click", onclick);
window.addEventListener('load', onload);