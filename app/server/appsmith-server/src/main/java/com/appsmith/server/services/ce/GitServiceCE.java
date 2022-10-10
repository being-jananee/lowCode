package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.dtos.GitBranchDTO;
import com.bizBrainz.external.dtos.GitLogDTO;
import com.bizBrainz.external.dtos.GitStatusDTO;
import com.bizBrainz.external.dtos.MergeStatusDTO;
import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.GitApplicationMetadata;
import com.bizBrainz.server.domains.GitAuth;
import com.bizBrainz.server.domains.GitProfile;
import com.bizBrainz.server.dtos.GitCommitDTO;
import com.bizBrainz.server.dtos.GitConnectDTO;
import com.bizBrainz.server.dtos.ApplicationImportDTO;
import com.bizBrainz.server.dtos.GitDocsDTO;
import com.bizBrainz.server.dtos.GitMergeDTO;
import com.bizBrainz.server.dtos.GitPullDTO;
import reactor.core.publisher.Mono;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public interface GitServiceCE {

    Mono<Map<String, GitProfile>> updateOrCreateGitProfileForCurrentUser(GitProfile gitProfile);

    Mono<Map<String, GitProfile>> updateOrCreateGitProfileForCurrentUser(GitProfile gitProfile, String defaultApplicationId);

    Mono<GitProfile> getDefaultGitProfileOrCreateIfEmpty();

    Mono<GitProfile> getGitProfileForUser(String defaultApplicationId);

    Mono<Application> connectApplicationToGit(String defaultApplicationId, GitConnectDTO gitConnectDTO, String origin);

    Mono<Application> updateGitMetadata(String applicationId, GitApplicationMetadata gitApplicationMetadata);

    Mono<String> commitApplication(GitCommitDTO commitDTO, String defaultApplicationId, String branchName, boolean doAmend);

    Mono<String> commitApplication(GitCommitDTO commitDTO, String defaultApplicationId, String branchName);

    Mono<List<GitLogDTO>> getCommitHistory(String defaultApplicationId, String branchName);

    Mono<String> pushApplication(String defaultApplicationId, String branchName);

    Mono<Application> detachRemote(String defaultApplicationId);

    Mono<Application> createBranch(String defaultApplicationId, GitBranchDTO branchDTO, String srcBranch);

    Mono<Application> checkoutBranch(String defaultApplicationId, String branchName);

    Mono<GitPullDTO> pullApplication(String defaultApplicationId, String branchName);

    Mono<List<GitBranchDTO>> listBranchForApplication(String defaultApplicationId, Boolean pruneBranches, String currentBranch);

    Mono<GitApplicationMetadata> getGitApplicationMetadata(String defaultApplicationId);

    Mono<GitStatusDTO> getStatus(String defaultApplicationId, String branchName);

    Mono<MergeStatusDTO> mergeBranch(String applicationId, GitMergeDTO gitMergeDTO);

    Mono<MergeStatusDTO> isBranchMergeable(String applicationId, GitMergeDTO gitMergeDTO);

    Mono<String> createConflictedBranch(String defaultApplicationId, String branchName);

    Mono<ApplicationImportDTO> importApplicationFromGit(String organisationId, GitConnectDTO gitConnectDTO);

    Mono<GitAuth> generateSSHKey(String keyType);

    Mono<Boolean> testConnection(String defaultApplicationId);

    Mono<Application> deleteBranch(String defaultApplicationId, String branchName);

    Mono<Application> discardChanges(String defaultApplicationId, String branchName, Boolean doPull);

    Mono<List<GitDocsDTO>> getGitDocUrls();

    Mono<Long> getApplicationCountWithPrivateRepo(String workspaceId);

    Mono<Boolean> isRepoLimitReached(String workspaceId, Boolean isClearCache);
}
