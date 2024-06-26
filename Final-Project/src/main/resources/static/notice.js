    let stompClient = null;
    let sessionId = document.getElementById("sessionId").value
    function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    console.log("frame:"+frame)
    stompClient.subscribe("/sub/notice/list/"+sessionId,function(message) {
    // displayNotifications(message)
    // console.log("Wlahrl")
    // console.log("sessionId : "+sessionId)
    $.ajax({
    url: '/auth/notifications', // 서버의 요청 처리 주소
    type: 'GET',
    data:{member:sessionId},
    success: function(data) {
    console.log(data)
    displayNotifications(data); // 성공적으로 데이터를 받으면 화면에 표시
},
    error: function() {
    alert('Failed to fetch notifications.');
}
});
});
    console.log('Connected: ' + frame);
});
}
    $(document).ready(function() {
    $('#bell').click(function() {
        $.ajax({
            url: '/auth/notifications', // 서버의 요청 처리 주소
            type: 'GET',
            data:{member:sessionId},
            success: function(data) {
                console.log(data)
                displayNotifications(data); // 성공적으로 데이터를 받으면 화면에 표시
            },
            error: function() {
                alert('Failed to fetch notifications.');
            }
        });
    });

});
    function addscrap(val){
    let num=parseInt(val)
    $.ajax({
    url: '/auth/scrap/scrap', // 서버의 요청 처리 주소
    type: 'POST',
    contentType: 'application/x-www-form-urlencoded',
    dataType: 'json',
    data:{
    member:sessionId,
    auction:num
},
    success: function(response) {
    if(response.state==1){
    alert("추가되었습니다");
}else{
    alert("삭제되었습니다");
}
},
    error: function() {
    alert('스크랩실패; 로그인 필요 ');
}
});
}
    function displayNotifications(notifications) {
    let notificationList = $('#notificationList');
    notificationList.empty(); // 기존 목록 지우기

    $.each(notifications, function(index, notification) {
    let listItem = $('<li class="list-group-item"></li>');
    listItem.append('<h5 class="mb-1">' + notification.title + '</h5>');
    listItem.append('<p class="mb-1">' + notification.content + '</p>');
    listItem.append('<small>' + notification.name + ' - ' + notification.time + '</small>');

    notificationList.append(listItem);
});
}
    if (sessionId!==null){
    console.log("rlahWl")
    connect()
    console.log(sessionId)

}