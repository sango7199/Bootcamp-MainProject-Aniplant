$(document).ready(function() {
    const $searchInput = $('[name="search_input"]');
    const $searchArea = $('.search-area');
    const $recentSearches = $('#recent_searches');
    const $searchBtn = $('#search_btn');

    const MAX_RECENT_SEARCHES = 10; // 최근 검색어의 최대 개수

    function saveSearchTerm(term) {
        let searches = JSON.parse(localStorage.getItem('recentSearches') || '[]');
        // 이미 같은 검색어가 있는 경우 해당 검색어를 제거
        const index = searches.indexOf(term);
        if (index !== -1) {
            searches.splice(index, 1);
        }
        // 새로운 검색어를 배열의 시작에 추가
        searches.unshift(term);
        if (searches.length > MAX_RECENT_SEARCHES) {
            searches.pop(); // 가장 오래된 검색어 제거
        }
        localStorage.setItem('recentSearches', JSON.stringify(searches));
    }

    function loadRecentSearches() {
        let searches = JSON.parse(localStorage.getItem('recentSearches') || '[]');
        $recentSearches.empty();
        searches.forEach(term => {
            $recentSearches.append(`<li><img class="recent-search-img" src="/img/recent-search.png">${term}</li>`);
        });
    }

    // 검색창 클릭 이벤트
    $searchInput.on('focus', function() {
        loadRecentSearches();
        $recentSearches.show();
        $searchArea.addClass('active');
    });

    // 검색창에서 포커스가 사라질 때
    $searchInput.on('blur', function() {
        $recentSearches.hide();
        $searchArea.removeClass('active');
    });

    // 검색창에서 텍스트 입력 이벤트
    $searchInput.on('input', function() {
        const query = $(this).val();
        if (query) {
            $recentSearches.hide();
        } else {
            loadRecentSearches();
            $recentSearches.show();
        }
    });

    $searchBtn.on('click', function() {
        const query = $searchInput.val();
        if (query) {
            saveSearchTerm(query);
            $recentSearches.hide();
        }
    });

    loadRecentSearches();
});
