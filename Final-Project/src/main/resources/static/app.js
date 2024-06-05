/**
 * 
 */
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8081/auth/auction'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/auth/auction/bid', (max) => {
		let response=JSON.parse(max.body);
		if(response.parent == $('#num').val()){
			alert(response.price)
        	showmax(response.price);
        }
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendPrice() {
	let price = $('#price').val();
	let parent = $('#num').val();
	let buyer = $("#buyer").val();
	let bid={
		"buyer":buyer,
		"parent":parent,
		"price" :price
	};
    stompClient.publish({
        destination: "/auth/bid/price",
        body: JSON.stringify(bid)
    });
}

function showmax(message) {
    $("#max").text(message);
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    //$( "#connect" ).click(() => connect());
    
    //$( "#disconnect" ).click(() => disconnect());
    $( "#btn" ).click(() => sendPrice());
});
window.onload =()=> connect();
window.onunload=()=>disconnect();
