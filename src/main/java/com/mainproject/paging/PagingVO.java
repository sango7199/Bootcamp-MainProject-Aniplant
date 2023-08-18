package com.mainproject.paging;

public class PagingVO {
    private Criteria cri; // 페이징 정보
    private int totalCount; // 전체 아이템 수
    private int prev; // 이전 페이지 번호
    private int next; // 다음 페이지 번호

    public Criteria getCri() {
        return cri;
    }

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
