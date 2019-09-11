package com.jqy.paxooooos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Proposer {

    private static final Logger LOGGER= LoggerFactory.getLogger(Proposal.class);
    private static String[] propasalList={"PlanA","PlanB","PlanC"};

    public void vote(Proposal proposal, Collection<Acceptor> acceptors){
        int num=acceptors.size()/2+1;   //保持系统可用性的前提
        int round=0;    //投票轮数
        while (true){
            LOGGER.info("开始竞选意见领袖,现在是第"+ ++round+"轮");
            List<Proposal> proposals=new ArrayList<>();

            /**
             * Proposer选择一个提案，向所有Acceptors发送请求，并得到相应(有可能无法获取)
             * 如果该Proposal得到半数以上Acceptor的通过，则可以生成Proposal-[N,V]。
             */
            for (Acceptor acceptor:acceptors){
                Promise promise=acceptor.prepare(proposal);
                if (promise!=null&&promise.isAccepted())
                    proposals.add(promise.getProposal());
            }
            if (proposals.size()<num){
                LOGGER.warn("提议者["+proposal+"]的提案于准备阶段被驳回");
                proposal= next(proposal.getVoteNum(),proposals);
                continue;
            }

            /**
             * 生成提案后，Proposer将提案发送给所有Acceptor，并期望Acceptor可以接受改提案
             *
             */
            int acceptCount=0;
            for (Acceptor acceptor:acceptors){
                if (acceptor.accept(proposal))
                    acceptCount++;
            }
            if (acceptCount<num){
                LOGGER.warn("提议者["+proposal+"]的提案未被通过");
                proposal= next(proposal.getVoteNum(),proposals);
                continue;
            }
            break;
        }
        LOGGER.info("恭喜！！！！提议者["+proposal+"]的提案被通过");
    }

    private Proposal next(long currentNum, List<Proposal> proposals){
        Random random=new Random();
        long voteNum=currentNum+1;
        if (proposals.isEmpty())
            return new Proposal(voteNum,propasalList[random.nextInt(propasalList.length)]);
        Collections.sort(proposals);
        Proposal selectPrososal=proposals.get(proposals.size()-1);
        long maxNum=selectPrososal.getVoteNum();
        String value=selectPrososal.getValue();
        if (maxNum>=currentNum)
            throw new IllegalStateException("Wrong!");
        if (value!=null)
            return new Proposal(voteNum,value);
        return new Proposal(voteNum,propasalList[random.nextInt(propasalList.length)]);

    }
}