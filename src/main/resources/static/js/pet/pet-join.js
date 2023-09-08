$(document).ready(function() {

	$('#join_btn').on('click', function(event) {
		let profile_picture;
		const nameInput = $('#nameInput').val();
		const birth_input = new Date($('#birth_input').val());
		const adopted_at_input = new Date($('#adopted_at_input').val());
		const currentTime = new Date();

		if (birth_input > currentTime || birth_input > adopted_at_input) {
			alert('생일은 현재시각이나 입양일보다 이전이어야 합니다.');
			return;
		}

		if (adopted_at_input > currentTime) {
			alert('입양일은 현재시각보다 이전이어야 합니다.');
			return;
		}
		
		// 이름이 비어있는지 확인합니다.
        if (!nameInput) {
            alert('이름을 입력해 주세요.');
            return;
        }

        // 생일이 비어있거나 유효하지 않은지 확인합니다.
        if ($('#birth_input').val() === '' || isNaN(birth_input.getTime())) {
            alert('유효한 생일을 입력해 주세요.');
            return;
        }

         // 입양일이 비어있거나 유효하지 않은지 확인합니다.
         if ($('#adopted_at_input').val() === '' || isNaN(adopted_at_input.getTime())) {
             alert('유효한 입양일을 입력해 주세요.');
             return;
         }

		event.preventDefault();

		const species = $('input[name=species]:checked').val();

		if (species == "animal") {
			profile_picture = "/img/pet-profile/pet.png"
		} else if (species == "plant") {
			profile_picture = "/img/pet-profile/plant.png"
		} else {
			alert("종류를 선택해주세요.");
			return;
		}
		
		
		const petData = {
			name: $('#nameInput').val(),
			birth: $('#birth_input').val(),
			adopted_at: $('#adopted_at_input').val(),
			profile_picture: profile_picture
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
