package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AllSeed implements CommandLineRunner {

    private final RegionSeed regionSeed;
    private final DistrictSeed districtSeed;
    private final ConstituencySeed constituencySeed;
    private final PresidentialCandidateSeed presidentialCandidateSeed;
    private final ParliamentaryCandidateSeed parliamentaryCandidateSeed;
    private final PollingStationSeed pollingStationSeed;
    private final VoterSeed voterSeed;
    private final ConstituencyService constituencyService;
    private final RegionService regionService;
    private final DistrictService districtService;
    private final ParliamentaryCandidateService parliamentaryCandidateService;
    private final PresidentialCandidateService presidentialCandidateService;
    private final PollingStationService pollingStationService;
    private  final VoterService voterService;
    private final VoteSeed voteSeed;

    public AllSeed(RegionSeed regionSeed, DistrictSeed districtSeed, ConstituencySeed constituencySeed, PresidentialCandidateSeed presidentialCandidateSeed, ParliamentaryCandidateSeed parliamentaryCandidateSeed, PollingStationSeed pollingStationSeed, VoterSeed voterSeed, ConstituencyService constituencyService, RegionService regionService, DistrictService districtService, ParliamentaryCandidateService parliamentaryCandidateService, PresidentialCandidateService presidentialCandidateService, PollingStationService pollingStationService, VoterService voterService, VoteSeed voteSeed) {
        this.regionSeed = regionSeed;
        this.districtSeed = districtSeed;
        this.constituencySeed = constituencySeed;
        this.presidentialCandidateSeed = presidentialCandidateSeed;
        this.parliamentaryCandidateSeed = parliamentaryCandidateSeed;
        this.pollingStationSeed = pollingStationSeed;
        this.voterSeed = voterSeed;
        this.constituencyService = constituencyService;

        this.regionService = regionService;
        this.districtService = districtService;
        this.parliamentaryCandidateService = parliamentaryCandidateService;
        this.presidentialCandidateService = presidentialCandidateService;
        this.pollingStationService = pollingStationService;
        this.voterService = voterService;
        this.voteSeed = voteSeed;
    }

    @Override
    public void run(String... args) throws Exception {
//        regionService.deleteAllRegions();
//        districtService.deleteAllDistricts();
//        constituencyService.deleteAllConstituencies();
//        pollingStationService.deleteAllConstituencies();
//        parliamentaryCandidateService.deleteAllPresidentialCandidates();
//        presidentialCandidateService.deleteAllPresidentialCandidates();
//        voterService.deleteAllVoters();



//
        regionSeed.seedRegions();
        districtSeed.seeDistricts();
        constituencySeed.seeConstituencies();
        pollingStationSeed.seedPollingStations();
        presidentialCandidateSeed.seedPresidentialCandidates();
        parliamentaryCandidateSeed.seedPresidentialCandidates();
        voterSeed.seedVoters();
        voteSeed.seedVotes();

    }
}
