package com.jqy.paxooooos;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Acceptor> acceptors=new ArrayList<>();
        acceptors.add(new Acceptor("A"));
        acceptors.add(new Acceptor("B"));
        acceptors.add(new Acceptor("C"));
        acceptors.add(new Acceptor("D"));
        Proposer proposer=new Proposer();
        proposer.vote(new Proposal(1L,null),acceptors);
    }
}