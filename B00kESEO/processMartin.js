// Javascript
let homeVue, homeVueDisplay, homeVueEdit, homeVueLater, accountMenu;

// Stocking elements
let home,
    loginBtn,
    signoutBtn,
    loginModal,
    signupModal,
    menuEdit,
    loginTxt,
    errorMsg;

let homebtn,
    roomList,
    roomListLater,
    roomListEdit,
    roomListDisplay;

let roomType = {
    0: "Salle de réunion",
    1: "Classe",
    2: "Labo d'informatique",
    3: "Labo d'électronique",
    4: "Labo de physique",
    5: "Amphitéâtre"
};

let loadedData = [];
let baseUrl = "http://vps316698.ovh.net/api";

// Exécute un appel AJAX
// Prend en paramètres l'URL cible et la fonction callback appelée en cas de succès
function ajax(method, url, callback = null, body = null) {
    let req = new XMLHttpRequest();
    req.open(method, baseUrl + url);

    if (url !== "/auth") {
        req.setRequestHeader("x-token", getCookie("token"));
    }

    if (method === "PUT" || method === "POST") {
        req.setRequestHeader("Content-Type", "application/json");
        body = JSON.stringify(body);
    }

    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            if (callback === null) {
                // Alerte l'utilisateur du bon fonctionnement
                alert("Demande effectuée");
            } else {
                callback(req.responseText);
            }
        } else {
            console.error(req.status + " " + req.statusText + " " + baseUrl + url);
        }
    });

    req.addEventListener("error", function () {
        console.error("Erreur réseau avec l'URL " + baseUrl + url);
    });

    req.send(body);
}


//Set a new cookie
function setCookie(cookieName, cookieValue) {
    let d = new Date();
    d.setTime(64 * 365 * 24 * 60 * 60 * 1000);
    let expires = "expires=" + d.toUTCString();
    document.cookie = cookieName + "=" + cookieValue + ";" + expires;
}

//Erase a cookie (to end connection for example)
function eraseCookie(cookieName) {
    document.cookie = cookieName + "=;expires=Thu, 01-Jan-70 00:00:01 GMT;";
}

//Create a cookie named token
function createCookieToken(token) {
    setCookie("token", token);
}

//Create a cookie named login
function createCookieName(name) {
    setCookie("name", name);
}

//Get cookie by its name
function getCookie(cookieName) {
    let name = cookieName + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

//To know if the user is connected
function isConnected() {
    return getCookie("token") !== "";
}

//To end connection
function endConnection() {
    eraseCookie("token");
    eraseCookie("name");
    accountMenu.login = "";
    loginTxt.hide();
    signoutBtn.hide();
    menuEdit.hide();
    loginBtn.show();
    defaultPresentation();
}

// Update the state of the account menu
function changeStateMenu() {
    let token = getCookie("token");
    let name = getCookie("name");
    if (token !== "" && name !== "") {
        accountMenu.login = "Bonjour " + name;
        loginTxt.show();
        signoutBtn.show();
        menuEdit.show();
        loginBtn.hide();
    } else {
        accountMenu.login = name;
        loginTxt.hide();
        signoutBtn.hide();
        menuEdit.hide();
        loginBtn.show();
    }
}

//Object Person, with hashed password
function UserLogin(username, password) {
    this.username = username;
    this.password = CryptoJS.SHA256(username + ':' + CryptoJS.SHA256(password).toString()).toString();
}

//Object Person, with hashed password
function UserSignUp(firstname, lastname, username, password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.password = CryptoJS.SHA256(password).toString();
}

//Validate the connection of an user or inform the user of a problem
function isConnectionOk(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        createCookieToken(JSON.parse(apiResponse)['token']);
        createCookieName(JSON.parse(apiResponse)['displayName']);
        errorMsg.hide();
        loginModal.hide();
        changeStateMenu();
        menuEdit.show()
    } else {
        errorMsg.hide();
        $('.incorrect').show();
    }
}

// Create a User and then call the api with login and password to login the user
function connect(username, password) {
    let person = new UserLogin(username, password);
    ajax("POST", "/auth", isConnectionOk, person);
}

//Validate the creation of an user
function isCreateUserOk(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        createCookieToken(JSON.parse(apiResponse)['token']);
        createCookieName(JSON.parse(apiResponse)['displayName']);
        errorMsg.hide();
        signupModal.hide();
        window.location.reload();
    } else {
        errorMsg.hide();
        $('.used').show();
    }
}

//Function to create a user in the database with an ajax request to the api
function createUser(firstname, lastname, username, password) {
    let person = new UserSignUp(firstname, lastname, username, password);
    ajax("PUT", "/auth", isCreateUserOk, person);
}

//To verify the fields sent by user
function verifyWord(word) {
    return (word.length > 1 && word.length < 26)
}

function verifyDate(startD, endD){
    let start = new Date(startD);
    let end = new Date(endD);
    let startDate = new Date(start.getFullYear() + "-" + Number(start.getMonth()) + 1 + "-" + start.getDate());
    let endDate = new Date(end.getFullYear() + "-" + Number(end.getMonth()) + 1 + "-" + end.getDate());
    let now = new Date();
    if(end<start || end<now || end.getHours()>19 || end.getHours()<7 || start.getHours()>19 || start.getHours()<7 || startDate<endDate || startDate>endDate){
        return false;
    }
    return true;
}

////Get all the user's booking
//function getMyBookings(){
//    ajax("GET", "/bookings/my", callbackGetMyBookings);
//}
//
////Callback of the function getMyBookings with the api response of the ajax call
//function callbackGetMyBookings(apiResponse){
//    if (JSON.parse(apiResponse)['done'] === true) {
//        
//    } else {
//        
//    }
//}

//Delete a booking
function deleteBooking(idBook) {
    ajax("DELETE", "/bookings?id=" + idBook, callbackDeleteBooking);
}

//Callback of the function deleteBooking with the api response of the ajax call
function callbackDeleteBooking(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        alert("Suppression effectuée");
        reloadMyBookings();
    } else {
        alert('Réservation non supprimée: ' + JSON.parse(apiResponse)['info']);
    }
}

//Edit a booking
function editBooking(idBook, startDate, endDate) {
    let booking = new Booking(new Date(startDate), new Date(endDate));
    ajax("POST", "/bookings?id=" + idBook, callbackEditBooking, booking);
}

//Callback of the function editBooking with the api response of the ajax call
function callbackEditBooking(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        alert("Réservation modifiée");
        reloadMyBookings();
    } else {
        alert('Réservation non modifiée: ' + JSON.parse(apiResponse)['info']);
    }
}

// Add hours to a time
Date.prototype.addHours = function (h) {
    this.setHours(this.getHours() + h);
    return this;
};

// Add minutes to a time
Date.prototype.addMinutes = function (m) {
    this.setMinutes(this.getMinutes() + m);
    return this;
};

//Make a booking beginning now
function makeBookingNow(idRoom, duration) {
    duration = Number(duration);
    let endDate;
    if (duration < 1) {
        endDate = new Date().addMinutes(duration * 60);
    } else {
        endDate = new Date().addHours(duration);
    }
    let booking = new Booking(new Date(), endDate);
    ajax("PUT", "/bookings?room=" + idRoom, callbackMakeBookingNow, booking);
}

//Callback of the function makeBookingNow with the api response of the ajax call
function callbackMakeBookingNow(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        alert('Réservation acceptée');
        reloadRoomListAvailableNow();
    } else {
        alert('Réservation refusée : ' + JSON.parse(apiResponse)['info']);
    }
}

//Make a booking with a start date 
function makeBookingLater(idRoom, startDate, endDate) {
    let booking = new Booking(new Date(startDate), new Date(endDate));
    ajax("PUT", "/bookings?room=" + idRoom, callbackMakeBookingLater, booking);
}

//Callback of the function makeBookingLater with the api response of the ajax call
function callbackMakeBookingLater(apiResponse) {
    if (JSON.parse(apiResponse)['done'] === true) {
        alert('Réservation acceptée');
        reloadRoomListAvailableLater();
    } else {
        alert('Réservation refusée : ' + JSON.parse(apiResponse)['info']);
    }
}

//Booking object with a start date and a end date for the booking
function Booking(start, end) {
    //format 2019-01-01 18:34:56
    this.start = start;
    this.end = end;
}

// Add pagination whith the "maintenant" list
function paginationRoomList() {
    $('#pagination-container').pagination({
        dataSource: loadedData,
        pageSize: 5,
        showPrevious: true,
        showNext: true,
        className: 'paginationjs-theme-blue',
        callback: function (data, pagination) {
            homeVue.roomList = data;
            window.scroll(0, 0);
        }
    });
}

//Add pagination whith the "plus tard" list
function paginationRoomListLater() {
    $('#pagination-container-later').pagination({
        dataSource: loadedData,
        pageSize: 5,
        showPrevious: true,
        showNext: true,
        className: 'paginationjs-theme-blue',
        callback: function (data, pagination) {
            homeVueLater.roomList = data;
            window.scroll(0, 0);
        }
    });
}

//Add pagination whith the "Mes réservations" list
function paginationRoomListEdit() {
    $('#pagination-container-edit').pagination({
        dataSource: loadedData,
        pageSize: 5,
        showPrevious: true,
        showNext: true,
        className: 'paginationjs-theme-blue',
        callback: function (data, pagination) {
            homeVueEdit.roomList = data;
            window.scroll(0, 0);
        }
    });
}

//Add pagination whith the "Liste des salles" list
function paginationRoomListDisplay() {
    $('#pagination-container-display').pagination({
        dataSource: loadedData,
        pageSize: 5,
        showPrevious: true,
        showNext: true,
        className: 'paginationjs-theme-blue',
        callback: function (data, pagination) {
            homeVueDisplay.roomList = data;
            window.scroll(0, 0);
        }
    });
}

function reloadRoomListAvailableNow() {
    ajax("GET", "/rooms/now", fillRoomList);
}

function reloadRoomListAvailableLater() {
    ajax("GET", "/rooms", fillRoomListLater);
}

function reloadMyBookings() {
    ajax("GET", "/bookings/my", fillMyBookingList);
}

// Reload room list
function fillRoomList(responseText) {
    loadedData = JSON.parse(responseText);
    loadedData.forEach(function (room) {
      
        room.type = roomType[room.type];
    });
    paginationRoomList();
}

// Reload room list
function fillRoomListLater(responseText) {
    loadedData = JSON.parse(responseText);
    loadedData.forEach(function (room) {
      
        room.type = roomType[room.type];
    });
    
    paginationRoomListLater();
}

// Reload booking list
function fillMyBookingList(responseText) {
    loadedData = JSON.parse(responseText);

    loadedData.forEach(function (booking) {
        let dateStart = new Date(booking.start);
        booking.startDate = dateStart.getFullYear() + "-" + Number(dateStart.getMonth()) + 1 + "-" + dateStart.getDate();
        booking.startHour = ("0" + dateStart.getHours()).slice(-2) + ":" + ("0" + dateStart.getMinutes()).slice(-2);

        let dateEnd = new Date(booking.end);
        booking.endDate = dateEnd.getFullYear() + "-" + Number(dateEnd.getMonth()) + 1 + "-" + dateEnd.getDate();
        booking.endHour = ("0" + dateEnd.getHours()).slice(-2) + ":" + ("0" + dateEnd.getMinutes()).slice(-2);

        booking.room.type = roomType[booking.room.type];

    });
    paginationRoomListEdit();
}


// Creates the vue with the data from "loadedData" and its pagination
function fillRoomListDisplay(responseText) {
    loadedData = JSON.parse(responseText);
    
    loadedData.forEach(function (room) {
      
        room.type = roomType[room.type];
    });

    paginationRoomListDisplay();
}

// Set up the page to the home state
function defaultPresentation() {
    home.css("margin-top", "20%");
    home.find("span").css("cursor", "default");
    homebtn.show();
    roomList.hide();
    roomListLater.hide();
    roomListEdit.hide();
    roomListDisplay.hide();
}


// Set up the page to the "maintenant" state,and make a call for data
function nowPresentation() {
    ajax("GET", "/rooms/now", fillRoomList);
    home.css("margin-top", "5%");
    home.find("span").css("cursor", "pointer");
    homebtn.hide();
    roomList.show();
    roomListLater.hide();
    roomListEdit.hide();
    roomListDisplay.hide();
}

// Set up the page to the "plus tard" state, and make a call for data
function laterPresentation() {
    ajax("GET", "/rooms", fillRoomListLater);
    home.css("margin-top", "5%");
    home.find("span").css("cursor", "pointer");
    homebtn.hide();
    roomList.hide();
    roomListLater.show();
    roomListEdit.hide();
    roomListDisplay.hide();
}

// Set up the page to the "mes réservations" state, and make a call for data
function editPresentation() {
    ajax("GET", "/bookings/my", fillMyBookingList);
    home.css("margin-top", "5%");
    home.find("span").css("cursor", "pointer");
    homebtn.hide();
    roomList.hide();
    roomListLater.hide();
    roomListEdit.show();
    roomListDisplay.hide();
}

// Set up the page to the "liste des salles" state, and make a call for data
function displayPresentation() {
    ajax("GET", "/rooms", fillRoomListDisplay);
    home.css("margin-top", "5%");
    home.find("span").css("cursor", "pointer");
    homebtn.hide();
    roomList.hide();
    roomListLater.hide();
    roomListEdit.hide();
    roomListDisplay.show();
}

// Add a select to the room and display confirm option
function changeStateRoom(room) {
    let roomSelector = $(room.$el);
    let roomName = roomSelector.find(".room-name");

    roomName.text("Réserver la salle " + roomName.text() + " ?");
    roomName.after('<select name="duration">' +
        '    <option value="0.5">30 min</option>' +
        '    <option value="1">1H</option>' +
        '    <option value="2">2H</option>' +
        '    <option value="3">3H</option>' +
        '    <option value="4">4H</option>' +
        '  </select>');
    roomSelector.find(".room-book").hide();
    roomSelector.find(".room-confirm").show();
}

// Get room id and selected duration
function confirmBooking(room) {
    let roomSelector = $(room.$el);
    let duration = roomSelector.find("select").val();
    let roomId = roomSelector.attr("key");
    makeBookingNow(roomId, duration);

    //@TODO
    if (!isConnected()) {
        loginModal.show();
    }
}



$(document).ready(function () {

    // Gettings elements
    home = $('#home');
    loginBtn = $('#login-btn');
    signoutBtn = $('#signout');
    loginModal = $('#login-modal');
    signupModal = $('#signup-modal');
    menuEdit = $('#menu-edit');
    loginTxt = $('#login-txt');
    errorMsg = $('.error');

    homebtn = $('#home-btn');
    roomList = $('#room-list');
    roomListLater = $('#room-list-later');
    roomListEdit = $('#room-list-edit');
    roomListDisplay = $('#room-list-display');

    // Setting the account menu
    accountMenu = new Vue({
        el: "#accountMenu",
        data: {
            login: ""
        }
    });

    // Look at cookies and set up login menu
    changeStateMenu();

    // Disconnect user
    signoutBtn.click(function () {
        endConnection();
    });

    // Display modal
    loginBtn.click(function (event) {
        event.preventDefault();
        loginModal.show();
    });


    //When the login form is submitted, it calls the connect function with the password and the username set by the user
    $('#login-form').submit(function (event) {
        event.preventDefault();
        if (!verifyWord($("#login-input").val())) {
            errorMsg.hide();
            $('.emptyLoginInput').show();
        } else if (!verifyWord($("#password-input").val())) {
            errorMsg.hide();
            $('.emptyPasswordInput').show();
        } else {
            connect($("#login-input").val(), $("#password-input").val());
        }
    });

    //Display modal
    $('.signup-btn').click(function (event) {
        event.preventDefault();
        errorMsg.hide();
        loginModal.hide();
        signupModal.show();
    });

    //When the sign up form is submitted, it validates data first then creates the user in the database
    signupModal.submit(function (event) {
        event.preventDefault();
        if (!verifyWord($("#firstname-signup").val())) {
            errorMsg.hide();
            $('.emptyFirstnameSignup').show();
        } else if (!verifyWord($("#lastname-signup").val())) {
            errorMsg.hide();
            $('.emptyLastnameSignup').show();
        } else if (!verifyWord($("#login-signup").val())) {
            errorMsg.hide();
            $('.emptyLoginSignup').show();
        } else if (!verifyWord($("#password-signup").val())) {
            errorMsg.hide();
            $('.emptyPasswordSignup').show();
        } else {
            if ($("#password-signup").val() === $("#password2-signup").val()) {
                createUser($("#firstname-signup").val(), $("#lastname-signup").val(), $("#login-signup").val(), $("#password-signup").val());
            } else {
                errorMsg.hide();
                $('.different').show();
            }
        }
    });

    // Close modal
    $('.close').click(function (event) {
        event.preventDefault();
        errorMsg.hide();
        loginModal.hide();
        signupModal.hide();
    });

    // When the user clicks anywhere outside of the modal, close it
    $(document).click(function (event) {
        if (!$(event.target).closest(".modal-content,#login-btn").length) {
            errorMsg.hide();
            loginModal.hide();
            signupModal.hide();
        }
    });

    /* Vue component defining a room for the now options :
     * takes a room object as prop
     * @todo: manage case where info is missing (especially with the dots between words)
     * @todo: link icons to a value from the room, color grading and/or differents icons
     * This template is used for every room in the room list
     */
    Vue.component('room-item', {
        props: ['room'],
        template: '<div class="room">' +
        '<div class="room-infos">' +
        '<span class="room-name">{{room.name}}</span>' +
        '<span class="room-location">{{room.location}} • Bâtiment {{room.building }} • Étage {{room.floor}} {{room.comment ? "•":""}} {{room.comment}}</span>' +
        '<span class="room-type">{{room.capacity}} Personnes • {{room.type}} </span>' +
        '</div>' +
        '<div class="room-icons">' +
        '<i class="room-wifi fa fa-wifi fa-lg" aria-hidden="false"></i>' +
        '<i class="room-video fa fa-video-camera fa-lg" aria-hidden="false"></i>' +
        '<button @click="book" class="room-book"> Réserver</button>' +
        '<button @click="confirm" class="room-confirm" style="display: none">Confirmer</button>' +
        '</div>' +
        '</div>',
        methods: {
            // When click on "réserver"
            book: function () {
                changeStateRoom(this);
            },
            // When click on "Confirmer"
            confirm: function () {
                confirmBooking(this);
            }
        }
    });

    /* Vue component defining a room for the later option:
     * takes a room object as prop
     * This template is used for every room in the room list
     */
    Vue.component('room-item-later', {
        props: ['room'],
        template: '<div class="room">' +
        '<div class="room-infos">' +
        '<span class="room-name">{{room.name}}</span>' +
        '<span class="room-location">{{room.location}} • Bâtiment {{room.building }} • Étage {{room.floor}} {{room.comment ? "•":""}} {{room.comment}}</span>' +
        '<span class="room-type">{{room.capacity}} Personnes • {{room.type}}</span>' +
        '</div>' +
        '<div class="room-datetime">' +
        '<div class="start-div">' +
        '<input id="start-date{{room.id}}"  type="date" value="{{room.startDate}}">Date Début</input>' +
        '<input id="start-time{{room.id}}"  type="time" value="{{room.startHour}}">Heure Début</input>' +
        '</div>' +
        '<div class="end-div">' +
        '<input id="end-date{{room.id}}"  type="date" value="{{room.endDate}}">Date Fin</input>' +
        '<input id="end-time{{room.id}}"  type="time" value="{{room.endHour}}">Heure Fin</input>' +
        '</div>' +
        '</div>' +
        '<div class="room-icons">' +
        '<i class="room-wifi fa fa-wifi fa-lg" aria-hidden="false"></i>' +
        '<i class="room-video fa fa-video-camera fa-lg" aria-hidden="false"></i>' +
        '<button @click="newBooking" class="book-edit">Réserver</button>' +
        '</div>' +
        '</div>',
        methods: {
            // When click on "Valider"
            newBooking: function () {
                // function new
                let roomSelector = $(this.$el);
                let roomId = roomSelector.attr("key");
                let startDate = $('#start-date' + roomId).val() + " " + $('#start-time' + roomId).val();
                let endDate = $('#end-date' + roomId).val() + " " + $('#end-time' + roomId).val();
                if(verifyDate(startDate, endDate)){
                    makeBookingLater(roomId, startDate, endDate);
                } else {
                    alert ("Attention la date de début et de fin doit être similaire et les heures de réservations sont de 7h à 19h59");
                }  
            }
        }
    });

    /* Vue component defining a room for the edit option:
     * takes a room object as prop
     * This template is used for every room in the room list
     */
    Vue.component('room-item-edit', {
        props: ['booking'],
        template: '<div class="room">' +
        '<div class="room-infos">' +
        '<span class="room-name">{{booking.room.name}}</span>' +
        '<span class="room-location">{{booking.room.location}} • Bâtiment {{booking.room.building }} • Étage {{booking.room.floor}} {{booking.room.comment ? "•":""}} {{booking.room.comment}}</span>' +
        '<span class="room-type">{{booking.room.capacity}} Personnes • {{booking.room.type}}</span>' +
        '</div>' +
        '<table class="room-datetime">' +
        '<tr>'+
        	'<td>' +
        		'<label>Date début</label>' +
        	'</td>' +
        	'<td>' +    
        		'<input id="start-date{{booking.id}}" type="date" value="{{booking.startDate}}"/>' +
        	'</td>' +
        '</tr>'+
        '<tr>'+
        	'<td>' +
        		'<label>Heure début</label>' +
        	'</td>' +
        	'<td>' +    
        		'<input id="start-time{{booking.id}}" type="time" value="{{booking.startHour}}"/>' +
        	'</td>' +
        '</tr>'+
        '<tr>'+
        	'<td>' +
        		'<label>Date fin</label>' +
        	'</td>' +
        	'<td>' +    
        		'<input id="end-date{{booking.id}}" type="date" value="{{booking.endDate}}"/>' +
        	'</td>' +
        '</tr>'+
        '<tr>'+
        	'<td>' +
        		'<label>Heure fin</label>' +
        	'</td>' +
        	'<td>' +  
        		'<input id="end-time{{booking.id}}" type="time" value="{{booking.endHour}}"/>' +
        	'</td>' +
        '</tr>'+
        '<tr>'+
        	'<td>' +
        		'<button @click="edit" class="book-edit">Valider</button>' +
        	'</td>' +
        	'<td>' +  
        		'<button @click="delete" class="book-delete">Supprimer</button>' +
        	'</td>' +
        '</tr>'+
        '</table>' +
        '</div>',
        methods: {
            // When click on "Valider"
            edit: function () {
                // function edit
                let bookingSelector = $(this.$el);
                let bookingId = bookingSelector.attr("key");
                let startDate = $('#start-date' + bookingId).val() + " " + $('#start-time' + bookingId).val();
                let endDate = $('#end-date' + bookingId).val() + " " + $('#end-time' + bookingId).val();
                if(verifyDate(startDate, endDate)){
                    editBooking(bookingId, startDate, endDate);
                } else {
                    alert("Attention la date de début et de fin doit être similaire et les heures de réservations sont de 7h à 19h59");
                }
                
            },
            // When click on "Supprimer"
            delete: function () {
                // function delete
                let bookingSelector = $(this.$el);
                let bookingId = bookingSelector.attr("key");
                deleteBooking(bookingId);
            }
        }
    });

    /* Vue component defining a room for the display option:
     * takes a room object as prop
     * This template is used for every room in the room list
     */
    Vue.component('room-item-display', {
        props: ['room'],
        template: '<div class="room">' +
        '<div class="room-infos">' +
        '<span class="room-name">{{room.name}}</span>' +
        '<span class="room-location">{{room.location}} • Bâtiment {{room.building }} • Étage {{room.floor}} {{room.comment ? "•":""}} {{room.comment}}</span>' +
        '<span class="room-type">{{room.capacity}} Personnes • {{room.type}} </span>' +
        '</div>' +
        '<div class="room-icons">' +
        '<i class="room-wifi fa fa-wifi fa-lg" aria-hidden="false"></i>' +
        '<i class="room-video fa fa-video-camera fa-lg" aria-hidden="false"></i>' +
        '</div>' +
        '</div>'
    });


    // Go back to default
    home.find("span").click(function () {
        defaultPresentation()
    });

    // Menu and boutons actions
    $("#btn-now").click(function () {
        nowPresentation()
    });

    $("#btn-later").click(function () {
        laterPresentation();
    });

    $("#menu-home").click(function () {
        defaultPresentation();
    });

    $("#menu-now").click(function () {
        nowPresentation();
    });

    $("#menu-later").click(function () {
        laterPresentation();
    });

    menuEdit.click(function () {
        editPresentation();
    });

    $("#menu-display").click(function () {
        displayPresentation();
    });


    //Initializing vue
    homeVue = new Vue({
        el: '#room-list',
        data: {
            roomList: []
        },
    });

    homeVueLater = new Vue({
        el: '#room-list-later',
        data: {
            roomList: []
        },
    });

    homeVueEdit = new Vue({
        el: '#room-list-edit',
        data: {
            roomList: []
        },
    });

    homeVueDisplay = new Vue({
        el: '#room-list-display',
        data: {
            roomList: []
        },
    });

});
