package com.jqy.paxooooos;

public class Promise {
    private final boolean accepted;
    private final Proposal proposal;

    public Promise(boolean accepted, Proposal proposal) {
        this.accepted = accepted;
        this.proposal = proposal;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public Proposal getProposal() {
        return proposal;
    }
}