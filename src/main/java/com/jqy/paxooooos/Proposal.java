package com.jqy.paxooooos;

public class Proposal implements Comparable<Proposal>{

    private final long voteNum;
    private final String value;

    public Proposal(long voteNum, String value) {
        this.voteNum = voteNum;
        this.value = value;
    }

    public Proposal(){
        this(0,null);
    }

    public long getVoteNum() {
        return voteNum;
    }

    public String getValue() {
        return value;
    }


    @Override
    public int compareTo(Proposal o) {
        return Long.compare(voteNum,o.voteNum);
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "voteNum=" + voteNum +
                ", value='" + value + '\'' +
                '}';
    }
}