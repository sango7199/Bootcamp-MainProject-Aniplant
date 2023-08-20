    $(document).ready(function(){
    let currentPage = 1;
    let itemsPerPage = parseInt(document.getElementById('itemsPerPageSelect').value, 10);
    const totalItems = parseInt(document.getElementById('usersLength').textContent, 10);
    document.getElementById('previousPageButton').addEventListener('click', previousPage);
    document.getElementById('nextPageButton').addEventListener('click', nextPage);

    function formatDate(dateString) {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2);
        const day = ('0' + date.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    }

    // 테이블 데이터 표시 함수
    function displayTableData() {
        const start = (currentPage - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const currentItems = users.slice(start, end);
        
        document.getElementById('startRange').textContent = start + 1;
        document.getElementById('endRange').textContent = Math.min(end, totalItems);

        let tbodyContent = '';
        currentItems.forEach(user => {
            tbodyContent += `
                <tr class="user-row">
                    <td>${user.user_num}</td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.nickname}</td>
                    <td>${user.rank}</td>
                    <td>${formatDate(user.birth)}</td>
                    <td>${user.gender === 'M' ? '남성' : '여성'}</td>
                    <td>${user.is_deleted}</td>
                </tr>
            `;
        });
        document.querySelector('tbody').innerHTML = tbodyContent;
    }

    // 페이지 업데이트 함수
    function updatePageDisplay() {
        const totalPages = Math.ceil(totalItems / itemsPerPage);
    
        let startPage = Math.max(1, currentPage - 2);
        let endPage = Math.min(totalPages, currentPage + 2);
    
        if (totalPages <= 5) {
            startPage = 1;
            endPage = totalPages;
        }
    
        const pageNumberContainer = document.getElementById('pageNumberContainer');
        let pageLinks = '';
    
        for (let i = startPage; i <= endPage; i++) {
            if (i === currentPage) {
                pageLinks += `<span class="current-page" style="font-weight: bold; color: #14a6b8;">${i}</span>`;
            } else {
                pageLinks += `<span class="page-link" data-page="${i}" style="cursor: pointer;">${i}</span>`;
            }
        }
    
        pageNumberContainer.innerHTML = pageLinks;
    
        // 페이지 번호 클릭 이벤트 추가
        document.querySelectorAll('.page-link').forEach(link => {
            link.addEventListener('click', function() {
                currentPage = parseInt(this.getAttribute('data-page'), 10);
                displayTableData();
                updatePageDisplay();
            });
        });
    }

    // 이벤트 핸들러 함수 선언
    function previousPage() {
        if (currentPage > 1) {
            currentPage--;
            displayTableData();
            updatePageDisplay();
        }
    }
    function nextPage() {
        if (currentPage < Math.ceil(totalItems / itemsPerPage)) {
            currentPage++;
            displayTableData();
            updatePageDisplay();
        }
    }

    // 이벤트 리스너 연결
    document.querySelector('.pagination > button:nth-child(1)').addEventListener('click', previousPage);
    document.querySelector('.pagination > button:nth-child(3)').addEventListener('click', nextPage);
    document.getElementById('itemsPerPageSelect').addEventListener('change', function() {
        itemsPerPage = parseInt(this.value, 10);
        currentPage = 1;
        displayTableData();
        updatePageDisplay();
    });

    // Sort 기능 로직
    $('body').on('click', '.sort_btn', function(){ // 처음 클릭 시 오름차 순 정렬
        $('.sort_btn, .sort_btn_up, .sort_btn_down').not(this).removeClass('sort_btn_up').removeClass('sort_btn_down').addClass('sort_btn');
        $(this).removeClass('sort_btn').addClass('sort_btn_up');
        // 클릭된 열의 인덱스를 찾습니다.
        var columnIndex = $(this).parent().index();
        var table = $(this).closest('table');
        
        // 해당 열의 데이터를 기준으로 행을 정렬
        var rows = table.find('tbody tr').sort(function(a, b) {
            var cellA = $(a).find('td').eq(columnIndex).text();
            var cellB = $(b).find('td').eq(columnIndex).text();
            
            // 데이터가 숫자인지 확인
            if (!isNaN(cellA) && !isNaN(cellB)) {
                return parseFloat(cellB) - parseFloat(cellA);  // 숫자로 변환하여 내림차순 정렬
            } else {
                return cellB.localeCompare(cellA);  // 문자열을 내림차순으로 정렬
            }
        });
        
        // 정렬된 행을 다시 tbody에 삽입
        table.find('tbody').empty().append(rows);
    });

    // 오름차순 버튼 클릭시 내림차순 버튼으로 변경 열 내림차로 정렬 로직
    $('body').on('click', '.sort_btn_up', function() { 
        $(this).removeClass('sort_btn_up').addClass('sort_btn_down');
        // 클릭된 열의 인덱스를 찾음
        var columnIndex = $(this).parent().index();
        var table = $(this).closest('table');
        
        // 해당 열의 데이터를 기준으로 행을 정렬
        var rows = table.find('tbody tr').sort(function(a, b) {
            var cellA = $(a).find('td').eq(columnIndex).text();
            var cellB = $(b).find('td').eq(columnIndex).text();
            
            // 데이터가 숫자인지 확인
            if (!isNaN(cellA) && !isNaN(cellB)) {
                return parseFloat(cellA) - parseFloat(cellB);  // 숫자로 변환하여 오름차순 정렬
            } else {
                return cellA.localeCompare(cellB);  // 문자열을 오름차순으로 정렬
            }
        });
        
        // 정렬된 행을 다시 tbody에 삽입
        table.find('tbody').empty().append(rows);
    });

    // 내림차순 버튼 클릭 시 오름차순 버튼으로 변경 후 열 오름차로 정렬 로직
    $('body').on('click', '.sort_btn_down', function() { 
        $(this).removeClass('sort_btn_down').addClass('sort_btn_up');
        // 클릭된 열의 인덱스를 찾음
        var columnIndex = $(this).parent().index();
        var table = $(this).closest('table');
        
        // 해당 열의 데이터를 기준으로 행을 정렬
        var rows = table.find('tbody tr').sort(function(a, b) {
            var cellA = $(a).find('td').eq(columnIndex).text();
            var cellB = $(b).find('td').eq(columnIndex).text();
            
            // 데이터가 숫자인지 확인
            if (!isNaN(cellA) && !isNaN(cellB)) {
                return parseFloat(cellB) - parseFloat(cellA);  // 숫자로 변환하여 내림차순 정렬
            } else {
                return cellB.localeCompare(cellA);  // 문자열을 내림차순으로 정렬
            }
        });
        
        // 정렬된 행을 다시 tbody에 삽입
        table.find('tbody').empty().append(rows);
    });

    // 검색 기능 로직
    document.querySelector('.table-search-area > input').addEventListener('input', function() {
        const searchQuery = this.value.toLowerCase();

        // 사용자 목록을 필터링
        const filteredUsers = window.users.filter(user => 
            user.user_num.toString().toLowerCase().includes(searchQuery) ||
            user.id.toLowerCase().includes(searchQuery) ||
            user.name.toLowerCase().includes(searchQuery) ||
            user.nickname.toLowerCase().includes(searchQuery) ||
            user.rank.toString().toLowerCase().includes(searchQuery) ||
            formatDate(user.birth).toLowerCase().includes(searchQuery) ||
            (user.gender === 'M' ? '남성' : '여성').toLowerCase().includes(searchQuery) ||
            user.is_deleted.toString().toLowerCase().includes(searchQuery)
        );

        // 필터링된 사용자 목록을 기반으로 테이블 데이터를 표시
        displayFilteredTableData(filteredUsers);
    });

    function displayFilteredTableData(filteredUsers) {
        const start = (currentPage - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const currentItems = filteredUsers.slice(start, end);
        
        document.getElementById('startRange').textContent = start + 1;
        document.getElementById('endRange').textContent = Math.min(end, filteredUsers.length);

        let tbodyContent = '';
        currentItems.forEach(user => {
            tbodyContent += `
                <tr>
                    <td>${user.user_num}</td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.nickname}</td>
                    <td>${user.rank}</td>
                    <td>${formatDate(user.birth)}</td>
                    <td>${user.gender === 'M' ? '남성' : '여성'}</td>
                    <td>${user.is_deleted}</td>
                </tr>
            `;
        });
        document.querySelector('tbody').innerHTML = tbodyContent;
        updatePageDisplayForFilteredUsers(filteredUsers.length);
    }

    function updatePageDisplayForFilteredUsers(filteredLength) {
        document.getElementById('currentPageDisplay').textContent = currentPage;
        document.getElementById('totalPagesDisplay').textContent = Math.ceil(filteredLength / itemsPerPage);
    }

    // 초기 표시
    displayTableData();
    updatePageDisplay();
    
    // 행 클릭 이벤트 리스너
    $('body').off('click', 'tbody tr.user-row').on('click', 'tbody tr.user-row', function() {
    const clickedRow = $(this);
    let userNum = clickedRow.find('td:first').text().trim();
    
    // 상세 내용이 이미 표시되어 있다면 제거
    if (clickedRow.next().hasClass('detail-content-row')) {
        clickedRow.next().remove();
        return;
    }
    
    // 그렇지 않다면 상세 내용 로드
    $('<td colspan="8"></td>').load('/privacy-admin/user-management/user-detail.do?user_num=' + userNum, function(response, status, xhr) {
        if (status == "error") {
            console.error("Error loading detail content:", xhr.status + " " + xhr.statusText);
            return;
        }
        
        const detailContent = `
            <tr class="detail-content-row">
                <td colspan="8">
                    ${response}
                </td>
            </tr>
        `;
    
        // 가져온 내용을 현재 클릭된 행 바로 다음에 삽입
        clickedRow.after(detailContent);
    });
    });
});
