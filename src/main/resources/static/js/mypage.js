// 나의 반려친구 나이 계산 함수
function calculateAge(birthDateString) {
    const today = new Date();
    const birthDate = new Date(birthDateString);
    
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDifference = today.getMonth() - birthDate.getMonth();
    
    // 생일이 아직 오지 않은 경우 나이를 조정
    if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    
    return age;
}

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var today = new Date().toISOString().split('T')[0];

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: today, // 캘린더가 처음 표시될 때의 날짜
        locale: 'es',
        editable: true, // 이벤트 드래그 여부
        selectable: true, // 날짜 시간 범위 선택 여부
        businessHours: true, // 업무시간 표시 여부
        dayMaxEvents: true, // 하루 표시할 이벤트 수 제한 
        events: [ // 표시될 이벤트들의 배열
        {
            title: 'All Day Event',
            start: '2023-01-01'
        },
        {
            title: 'Long Event',
            start: '2023-01-07',
            end: '2023-01-10'
        },
        {
            groupId: 999,
            title: 'Repeating Event',
            start: '2023-01-09T16:00:00'
        },
        {
            groupId: 999,
            title: 'Repeating Event',
            start: '2023-01-16T16:00:00'
        },
        {
            title: 'Conference',
            start: '2023-01-11',
            end: '2023-01-13'
        },
        {
            title: 'Meeting',
            start: '2023-01-12T10:30:00',
            end: '2023-01-12T12:30:00'
        },
        {
            title: 'Lunch',
            start: '2023-01-12T12:00:00'
        },
        {
            title: 'Meeting',
            start: '2023-01-12T14:30:00'
        },
        {
            title: 'Happy Hour',
            start: '2023-01-12T17:30:00'
        },
        {
            title: 'Dinner',
            start: '2023-01-12T20:00:00'
        },
        {
            title: 'Birthday Party',
            start: '2023-01-13T07:00:00'
        },
        {
            title: 'Click for Google',
            url: 'http://google.com/',
            start: '2023-01-28'
        }
        ]
    });
    calendar.setOption('locale', 'ko');
    calendar.render();

    // 나이 출력
    const birthSpan = document.getElementById('pet-birth');
    if (birthSpan) {
        const ageSpan = birthSpan.parentElement.previousElementSibling.querySelector('span');
        const age = calculateAge(birthSpan.textContent);
        ageSpan.textContent = age + "세";
    }
});
