package com.bizBrainz.server.acl;

import com.bizBrainz.server.acl.ce.PolicyGeneratorCE;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class PolicyGenerator extends PolicyGeneratorCE {

    @Override
    @PostConstruct
    public void createPolicyGraph() {
        super.createPolicyGraph();
    }

}
