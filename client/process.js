// Javascript
let home;
let loadedData = [
    {
        id: 0,
        name: "Nom de la salle",
        number: "A-456",
        comment: "Au fond du couloir"
    },
    {
        id: 1,
        name: "test",
        number: "2",
        comment: "test"
    }
];

// Exécute un appel AJAX GET
// Prend en paramètres l'URL cible et la fonction callback appelée en cas de succès
function ajaxGet(url, callback) {
    let req = new XMLHttpRequest();
    req.open("GET", url);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            // Appelle la fonction callback en lui passant la réponse de la requête
            callback(req.responseText);
        } else {
            console.error(req.status + " " + req.statusText + " " + url);
        }
    });
    req.addEventListener("error", function () {
        console.error("Erreur réseau avec l'URL " + url);
    });
    req.send(null);
}

// Creates the vue with the data from "loadedData" and it pagination
function fillRoomList(responseText) {
    loadedData = JSON.parse(responseText);
    home = new Vue({
        el: '#room-list',
        data: {
            roomList: []
        }
    });

    $('#pagination-container').pagination({
        dataSource: loadedData,
        pageSize: 5,
        showPrevious: true,
        showNext: true,
        className: 'paginationjs-theme-blue',
        callback: function (data, pagination) {
            home.roomList = data;
        }
    })
}

$().ready(function () {

    /* Vue component defining a room :
     * takes a room object as prop
     * @todo: manage case where info is missing (especially with the dots between words)
     * @todo: link icons to a value from the room, color grading and/or differents icons
     * @todo: "reserver" button not implemented
     * This template is used for every room in the room list
     */
    Vue.component('room-item', {
        props: ['room'],
        template: '<div class="room">' +
            '<div class="room-infos">' +
            '<span class="room-name">{{room.name}}</span>' +
            '<span class="room-location">{{room.location}} • Bâtiment {{room.building }} • Étage {{room.floor}} • {{room.comment}}</span>' +
            '<span class="room-type">{{room.capacity === null ? 34 : 12 }} Personnes • {{room.type === null ? "Laboratoire informatique" : "Amphitéatre"}} </span>' +
            '</div>' +
            '<div class="room-icons">' +
            '<i class="room-wifi fa fa-wifi fa-lg" aria-hidden="false"></i>' +
            '<i class="room-video fa fa-object-group fa-lg" aria-hidden="false"></i>' +
            '<button class="room-book"> Réserver</button>' +
            '   </div>' +
            '</div>'
    });


    // Function getting the data from the server, hiding buttons, putting on top title and showing room list
    $("#btn-now").click(function () {
        ajaxGet("http://vps316698.ovh.net/api/rooms", fillRoomList);
        $("#home").css("margin-top", "0%");
        $("#home-btn").hide();
        $("#room-list").show();
    });


    // Work in progress
    $("#btn-later").click(function () {
        alert("J'ai pas fini de faire ça non mais oh !")
    })

});
