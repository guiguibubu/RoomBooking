// Javascript functions
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
        name: "Nom de la salle de test",
        number: "A-4786",
        comment: "gauche"
    },
    {
        id: 2,
        name: "Nom de la salle de testeuh",
        number: "B-756",
        comment: "couloir"
    },
    {
        id: 3,
        name: "heisenedber de la salle",
        number: "C-14",
        comment: "Au fond"
    }
];

// Exécute un appel AJAX GET
// Prend en paramètres l'URL cible et la fonction callback appelée en cas de succès
function ajaxGet(url, callback) {
    var req = new XMLHttpRequest();
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

function fillTab(responseText) {
    loadedData = JSON.parse(responseText);
    home = new Vue({
        el: '#room-list',
        data: {
            roomList: loadedData
        }
    });
}




$(document).ready(function () {

    Vue.component('room-item', {
        props: ['room'],
        template:
        '<div class="room">' +
            '<div class="room-infos">' +
                '<span class="room-name">{{room.name}}</span>' +
                '<span class="room-location">{{room.location}} • Bâtiment {{room.building }} • Étage {{room.floor}} • {{room.comment}}</span>' +
                '<span class="room-type">{{room.capacity === null ? 34 : 12 }} Personnes • {{room.type === null ? "Laboratoire informatique" : "Amphitéatre"}} </span>' +
            '</div>' +
            '<div class="room-icons">' +
                '<i class="room-wifi fa fa-wifi fa-lg" aria-hidden="false"></i>' +
                '<i class="room-video fa fa-object-group fa-lg" aria-hidden="false"></i>'+
                '<button class="room-book"> Réserver</button>'+
        '   </div>' +
        '</div>'
    });

    ajaxGet("http://vps316698.ovh.net/api/rooms", fillTab);

});
