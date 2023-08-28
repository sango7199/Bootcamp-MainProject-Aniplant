$(document).ready(function() {
	$('#join_btn').on('click', function(event) {
		event.preventDefault();

		const petData = {
			name: $('#nameInput').val(),
			birth: $('#birthInput').val(),
			adopted_at: $('#adopted_atInput').val()
		};

		$.ajax({
			url: '/api/register-pet',
			contentType: 'application/json',
			data: JSON.stringify(petData),
			type: 'POST',
            dataType: 'json',  // Expect a JSON response
            success: function(response) {
                if(response.status === 'success') {
                    alert('등록이 완료되었습니다.');
                    location.href = response.redirectUrl;
                } else {
                    alert('등록에 실패하였습니다.');
                }
            },
            error: function() {
                alert('등록에 실패하였습니다.');
            }
        });
    });
});
