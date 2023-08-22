package com.mainproject.paging;

public class Criteria {
    private int page; // 현재 페이지 번호
    private int perPageNum; // 페이지당 아이템 수

    public Criteria() {
        this.page = 1; // 기본값으로 1페이지를 설정
        this.perPageNum = 10; // 기본값으로 페이지당 10개의 아이템을 설정
    }

    public Criteria(int page, int perPageNum) {
        this.page = page;
        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        // 페이지 번호가 1보다 작거나 같으면 1로 설정, 그 외의 경우에는 주어진 값 그대로 설정
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        // 페이지당 아이템 수가 0보다 작거나 100보다 크면 기본값 10으로 설정, 그 외의 경우에는 주어진 값 그대로 설정
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
        } else {
            this.perPageNum = perPageNum;
        }
    }

    // SQL 쿼리에서 사용할 시작 로우 계산
    public int getStartRow() {
        return (page - 1) * perPageNum;
    }
}
