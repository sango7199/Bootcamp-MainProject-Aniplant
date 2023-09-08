$(document).ready(function() {
	$('#join_btn').on('click', function(event) {

		//생일이 입양일 현재시각 관련 유효성 검사
		const birthInput = new Date($('#birthInput').val());
		const adopted_atInput = new Date($('#adopted_atInput').val());
		const currentTime = new Date();
		
		if (birthInput > currentTime || birthInput > adopted_atInput) {
			alert('생일은 현재시각이나 입양일보다 이전이어야 합니다.');
			return;
		}
		
		if (adopted_atInput > currentTime) {
			alert('입양일은 현재시각보다 이전이어야 합니다.');
			return;
		}

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
				if (response.status === 'success') {
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
