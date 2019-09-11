package com.jqy.paxooooos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Acceptor {
    private static final Logger LOGGER= LoggerFactory.getLogger(Acceptor.class);
    private Proposal lastProposal=new Proposal();
    private String name;

    public Acceptor(String name){
        this.name=name;
    }

    public Promise prepare(Proposal proposal){
        if (Math.random()>0.5){
            LOGGER.error("接受者"+name+"宕机,无法回复");
            return null;
        }
        if (proposal==null)
            throw new IllegalArgumentException("Wrong!");

        if (proposal.getVoteNum()>lastProposal.getVoteNum()){
            Promise promise=new Promise(true,lastProposal);
            lastProposal=proposal;
            LOGGER.info("接受者"+name+"准备接受提案"+proposal);
            return promise;
        }else {
            LOGGER.warn("接受者"+name+"拒绝接受提案"+proposal);
            return new Promise(false,null);
        }
    }

    public boolean accept(Proposal proposal){
        if (Math.random()>0.5){
            LOGGER.error("接受者"+name+"宕机,无法回复");
            return false;
        }
        LOGGER.info("接受者"+name+"接受提案"+proposal);
        return lastProposal.equals(proposal);
    }
}