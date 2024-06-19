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
	if(buyer == $('#seller').text()){
		alert("본인 상품에 입찰 금지입니다")
		return
	}
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
    $( "#btn" ).click(() => sendPrice())
});
window.onload =()=> connect();
window.onunload=()=>disconnect();

document.addEventListener("DOMContentLoaded", function() {
    const slideshowImages = document.querySelector(".slideshow-images");
    const dotButtonsContainer = document.querySelector(".dot-buttons");

    // 이미지 요소들을 모두 선택
    const images = slideshowImages.querySelectorAll("img");

    // 이미지 개수에 따라 점(dot) 버튼 생성
    images.forEach((image, index) => {
        const dot = document.createElement("span");
        dot.classList.add("dot");
        if (index === 0) {
            dot.classList.add("active"); // 첫 번째 이미지에 대한 점은 활성화
        }
        dotButtonsContainer.appendChild(dot);

        // 점(dot) 클릭 시 해당 이미지로 이동
        dot.addEventListener("click", function() {
            // 모든 점 버튼의 활성화 클래스 제거
            document.querySelectorAll(".dot").forEach(dot => {
                dot.classList.remove("active");
            });

            // 클릭한 점(dot) 버튼을 활성화
            dot.classList.add("active");

            // 해당 인덱스에 맞는 이미지를 보여줌
            slideshowImages.style.transform = `translateX(-${index * 100}%)`;
        });
    });
});

// 초기 슬라이드를 보여줍니다
