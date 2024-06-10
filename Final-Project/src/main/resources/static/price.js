/**
 * 
 */
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8081/auth/auction'
});
let flag=true;
let time="";
stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/sub/bid', (max) => {
		let response=JSON.parse(max.body);
		if(response.parent == $('#num').val()){
			if(response.msg != null ){
				disconnect();
				flag=false;
				return;
			}
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
	let price = parseInt($('#price').val());
	let max=parseInt($('#max').text());
	if(price <= max ){
		alert("최금 낮")
		return
	}
	let parent = $('#num').val();
	let buyer = $("#buyer").val();
	let bid={
		"buyer":buyer,
		"parent":parent,
		"price" :price,
		"time" : time
	};
    stompClient.publish({
        destination: "/pub/price",
        body: JSON.stringify(bid)
    });
}

function updateStatus(){
	let parent = $('#num').val();
	  stompClient.publish({
        destination: "/pub/status",
        body: JSON.stringify(parent)
    });
}

function updateClock() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    const timeString = `${hours}:${minutes}:${seconds}`;
    time=timeString;
    $("#clock").text(timeString);
}


function showmax(message) {
    $("#max").text(message);
}
$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    //$( "#connect" ).click(() => connect());
    updateClock();

    setInterval(updateClock, 1000);
    if(flag){
    	setInterval(updateStatus,1000*60);
    }
    //$( "#disconnect" ).click(() => disconnect());
    $( "#btn" ).click(() => sendPrice());
});
window.onload =()=> connect();
window.onunload=()=>disconnect();
