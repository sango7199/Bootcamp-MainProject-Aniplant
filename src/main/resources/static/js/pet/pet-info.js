function calculateAge(birth) {
	const birthDate = new Date(birth);
	const today = new Date();
	let age = today.getFullYear() - birthDate.getFullYear() + 1;
	const monthDifference = today.getMonth() - birthDate.getMonth();

	if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthDate.getDate())) {
		age--;
	}

	return age;
}

// DOM이 완전히 로드된 후에 나이 계산 함수 실행
document.addEventListener('DOMContentLoaded', function() {
	const agePara = document.querySelector('.age');
	const birth = agePara.dataset.birth;
	const age = calculateAge(birth);
	agePara.innerText = '나이: ' + age + '살';
});

function showEditForm() {
	document.getElementById('info-display').style.display = 'none';
	document.getElementById('edit-form').style.display = 'block';
}

function hideEditForm() {
	document.getElementById('edit-form').style.display = 'none';
	document.getElementById('info-display').style.display = 'block';

}
function getCurrentTimestamp() {
	let date = new Date();
	let offset = 9 * 60 * 60 * 1000;
	let kstDate = new Date(date.getTime() + offset);

	return kstDate.toISOString();
}



function submitUpdates() {
	const petNo = document.getElementById("petNo").value;
	const name = document.getElementById("nameUpdateInput").value;
	const birth = document.getElementById("birthUpdateInput").value;
	const adopted_at = document.getElementById("adoptedAtUpdateInput").value;
	const updated_at = getCurrentTimestamp(); // 현재 시각 추가
	const currentTime = new Date();

	if (birth > currentTime || birth > adopted_at) {
		alert('생일은 현재시각이나 입양일보다 이전이어야 합니다.');
		return;
	}

	if (adopted_at > currentTime) {
		alert('입양일은 현재시각보다 이전이어야 합니다.');
		return;
	}

	// AJAX를 사용해 서버에 수정된 정보를 전송합니다.
	$.ajax({
		url: '/api/update-pet',
		contentType: 'application/json',
		data: JSON.stringify({ pet_no: petNo, name, birth, adopted_at, updated_at }),
		type: 'POST',
		dataType: 'json',  // Expect a JSON response
		success: function(response) {
			if (response.status === 'success') {
				alert('수정이 완료되었습니다.');
				location.href = response.redirectUrl;
			} else {
				alert('수정에 실패하였습니다.');
			}
		},
		error: function() {
			alert('수정에 실패하였습니다.');
		}
	});
}

function submitDelete() {
	const petNo = document.getElementById("petNo").value;
	const deleted_at = getCurrentTimestamp();

	$.ajax({
		url: '/api/delete-pet',
		contentType: 'application/json',
		data: JSON.stringify({ pet_no: petNo, is_deleted: true, deleted_at }),
		type: 'POST',
		dataType: 'json', // Expect a JSON response
		success: function(response) {
			if (response.status === 'success') {
				alert('삭제가 완료되었습니다.');
				location.href = response.redirectUrl;
			} else {
				alert('삭제에 실패하였습니다.');
			}
		},
		error: function() {
			alert('삭제에 실패하였습니다.')
		}
	});
}


