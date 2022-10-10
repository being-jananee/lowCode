package com.bizBrainz.server.acl.ce;

import com.bizBrainz.server.acl.BizbrainzRole;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.bizBrainz.server.acl.BizbrainzRole.APPLICATION_ADMIN;
import static com.bizBrainz.server.acl.BizbrainzRole.APPLICATION_VIEWER;
import static com.bizBrainz.server.acl.BizbrainzRole.ORGANIZATION_ADMIN;
import static com.bizBrainz.server.acl.BizbrainzRole.ORGANIZATION_DEVELOPER;
import static com.bizBrainz.server.acl.BizbrainzRole.ORGANIZATION_VIEWER;

@Slf4j
public class RoleGraphCE {
    /**
     * This graph defines the hierarchy of permissions from parent objects
     */
    Graph<BizbrainzRole, DefaultEdge> hierarchyGraph = new DirectedMultigraph<>(DefaultEdge.class);

    @PostConstruct
    public void createPolicyGraph() {

        // Initialization of the hierarchical and lateral graphs by adding all the vertices
        EnumSet.allOf(BizbrainzRole.class)
                .forEach(role -> {
                    hierarchyGraph.addVertex(role);
                });

        hierarchyGraph.addEdge(ORGANIZATION_ADMIN, ORGANIZATION_DEVELOPER);
        hierarchyGraph.addEdge(ORGANIZATION_DEVELOPER, ORGANIZATION_VIEWER);
        hierarchyGraph.addEdge(APPLICATION_ADMIN, APPLICATION_VIEWER);
    }

    public Set<BizbrainzRole> generateHierarchicalRoles(String roleName) {
        BizbrainzRole role = BizbrainzRole.generateBizbrainzRoleFromName(roleName);

        Set<BizbrainzRole> childrenRoles = new LinkedHashSet<>();
        childrenRoles.add(role);
        BreadthFirstIterator<BizbrainzRole, DefaultEdge> breadthFirstIterator = new BreadthFirstIterator<>(hierarchyGraph, role);
        while(breadthFirstIterator.hasNext()) {
            childrenRoles.add(breadthFirstIterator.next());
        }

        return childrenRoles;
    }
}
