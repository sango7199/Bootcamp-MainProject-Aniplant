$(document).ready(function() {

	var croppedImageBlob = null;

	// 프로필 사진 변경 시 메시지 리스너 추가
	window.addEventListener("message", function(event) {
		let croppedImageBase64 = event.data.base64;
		croppedImageBlob = event.data.blob;
		if (croppedImageBase64) {
			changeEditProfile(croppedImageBase64);
		}
		// 프로필 사진 변경 버튼 비활성화
		$("#editProfile").prop("disabled", true);
	}, false);
	function changeEditProfile(base64) {
		$("#curProfileImage").attr("src", base64);
		$("#profileInput").val(base64);
	}

	// 프로필 사진 변경 버튼
	$("#editProfile").click(function() {
		window.open("/mypage/my-info-profile-edit.do?currentProfilePicSrc=" + originalProfile, '_blank', 'width=1030,height=630,scrollbars=no,menubar=no,toolbar=no,location=no');
	});

	// 프로필 초기화 버튼
	$("#resetProfile").click(function() {
		$("#curProfileImage").attr("src", originalProfile);
		$("#profileInput").val(originalProfile);
	});

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

		// 프로필 사진 변경시 프로필 업로드
		function saveEditProfile(blob) {
			var profileData = new FormData();
			profileData.append("profile_picture", blob, userId + ".png");

			$.ajax({
				url: "/api/update-profile",
				type: "POST",
				data: profileData,
				processData: false,
				contentType: false,
				success: function(response) {
					alert("프로필 사진이 업데이트되었습니다.");
				},
				error: function() {
					alert("프로필 사진 업데이트 중 오류가 발생했습니다.");
				}
			});
		}
		var profileInput = $("#profileInput").val();
		if (profileInput !== originalProfile) {
			saveEditProfile(croppedImageBlob);
		}
	});
});
