$(document).ready(function(){
    $('body').on('click', '.sort_btn', function(){
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
        
        // 정렬된 행을 다시 tbody에 삽입합니다.
        table.find('tbody').empty().append(rows);
    });

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
});
