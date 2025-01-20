package com.voting.VotingApp.votes.service.impl;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.entity.*;
import com.voting.VotingApp.votes.repository.*;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteServiceImp implements VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final ConstituencyRepository constituencyRepository;
    private final ParliamentaryCandidateRepository parliamentaryCandidateRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final PresidentialCandidateRepository presidentialCandidateRepository;
    private final ConstituencyPresidentialVoteSummaryRepository constituencyPresidentialVoteSummaryRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final DistrictPresidentialVoteSummaryRepository districtPresidentialVoteSummaryRepository;
    private final RegionalPresidentialVoteSummaryRepository regionalPresidentialVoteSummaryRepository;
    private final PollingStationRepository pollingStationRepository;
    private final ConstituencyParliamentaryVoteSummaryRepository constituencyParliamentaryVoteSummaryRepository;

    public VoteServiceImp(VoteRepository voteRepository, VoterRepository voterRepository,
                          ConstituencyRepository constituencyRepository,
                          ParliamentaryCandidateRepository parliamentaryCandidateRepository,
                          DistrictRepository districtRepository, RegionRepository regionRepository, PresidentialCandidateRepository presidentialCandidateRepository, ConstituencyPresidentialVoteSummaryRepository constituencyPresidentialVoteSummaryRepository, EntityDTOMapper entityDTOMapper, DistrictPresidentialVoteSummaryRepository districtPresidentialVoteSummaryRepository, RegionalPresidentialVoteSummaryRepository regionalPresidentialVoteSummaryRepository, PollingStationRepository pollingStationRepository, ConstituencyParliamentaryVoteSummaryRepository constituencyParliamentaryVoteSummaryRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.presidentialCandidateRepository = presidentialCandidateRepository;
        this.constituencyPresidentialVoteSummaryRepository = constituencyPresidentialVoteSummaryRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.districtPresidentialVoteSummaryRepository = districtPresidentialVoteSummaryRepository;
        this.regionalPresidentialVoteSummaryRepository = regionalPresidentialVoteSummaryRepository;
        this.pollingStationRepository = pollingStationRepository;
        this.constituencyParliamentaryVoteSummaryRepository = constituencyParliamentaryVoteSummaryRepository;
    }

    @Override
    public Response vote(VoteDTO voteDTO) {

        Vote newVote = new Vote();

        //to be replaced by AuthVoter
        Voter voter = voterRepository.findByVoterNumber(voteDTO.getVoterIdNumber())
                .orElseThrow(()-> new RuntimeException("Voter not found"));
        newVote.setVoter(voter);

        //from the parliamentary candidate a voter is voting for, we can get their constituencyId
        //we could also use the user's constituencyId to double check
        ParliamentaryCandidate parliamentaryCandidate = parliamentaryCandidateRepository.findByParliamentaryCandidateNumber(voteDTO.getParliamentaryCandidateId())
                .orElseThrow(()-> new RuntimeException("Parliamentary candidate does not exist"));
        newVote.setParliamentaryCandidate(parliamentaryCandidate);

        //no need to do double work, by finding the constituency from the user, we can get that from the constituency candidate
        //so i can choose to stop sending constituencyId over in DTO
        PollingStation pollingStation;
        if (voter.getPollingStation().getConstituency().getConstituencyElectoralCode().equals(parliamentaryCandidate.getConstituency().getConstituencyElectoralCode())) {
            pollingStation = pollingStationRepository.findPollingStationByVoters(voter) //use the voter to find his    pollingStation
                    .orElseThrow(() -> new RuntimeException("Polling Station not found!"));
            newVote.setPollingStation(pollingStation);
        } else {
            throw new IllegalArgumentException("Sorry, you don't belong to this constituency, so you can't vote for the candidate");
        }

        PresidentialCandidate presidentialCandidate = presidentialCandidateRepository.findByPresidentialVoterIdNumber(voteDTO.getPresidentialCandidateId())
                .orElseThrow(()-> new RuntimeException("Presidential candidate does not exist"));
        newVote.setPresidentialCandidate(presidentialCandidate);

//        String constituencyCode = voteDTO.getConstituencyCode().substring(0, 5);
        String constituencyElectoralCode = pollingStation.getConstituency().getConstituencyElectoralCode();
        newVote.setConstituencyCode(constituencyElectoralCode); // or use the voterDTO.getVoterId and then get the constituency code from here or use the presidential candidate ro truncate the polling station

//        String regionCode = voteDTO.getConstituencyCode().substring(0, 1);
        String regionCode = pollingStation.getConstituency().getDistrict().getRegion().getRegionElectoralCode(); //to evade voter passing the regionCode themselves
        newVote.setRegionalCode(regionCode);
//        voter.setVote(newVote); //set the vote to voter before you proceed
//        voterRepository.save(voter);

        voteRepository.save(newVote); //save vote

//        //find the vote id of the voter and set it to voter property
//        Vote vt = voteRepository.findVoteByVoter(newVote.getVoter());
//
//        voter.setVote(vt); //do not create a new voter obj; just use the above
//        voterRepository.save(voter);

        /** GET polling station votes
         *
         * */

        //total votes at a polling station
        Long totalVotesByPollingStation = voteRepository.findTotalVotesByPollingStation(pollingStation.getPollingStationCode());
        pollingStation.setTotalVoteCastAtPollingStation(totalVotesByPollingStation);
        pollingStationRepository.save(pollingStation);

        //votes gotten by a parliamentary candidate at a polling station; will require a summary of votes by polling station for parliamentary candidates
        //(polling station code, name, constituency, parliamentary candidate,parliamentary candidate vote total)
        Long totalPollingStationVotesAttainedByParliamentaryCandidateAtAPollingStation = voteRepository.findTotalVotesForParliamentaryCandidateAtAPollingStation(pollingStation.getPollingStationCode(), parliamentaryCandidate.getParliamentaryCandidateNumber());

        //denormalize to get constituency in the vote table, so that we could easily get parliamentary candidate's vote from the table
        Long  totalVotesForParliamentaryCandidateAtAConstituency = voteRepository.findTotalVotesForParliamentaryCandidateAtAConstituency(parliamentaryCandidate.getParliamentaryCandidateNumber(), constituencyElectoralCode);
        parliamentaryCandidate.setTotalVotesAttained(totalVotesForParliamentaryCandidateAtAConstituency);
        parliamentaryCandidateRepository.save(parliamentaryCandidate);
//
        //save votes by constituency
        Long totalVotesCastInAConstituency = voteRepository.totalVotesByConstituency(constituencyElectoralCode);
        Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(constituencyElectoralCode).orElseThrow(()-> new RuntimeException("Constituency not found"));
        constituency.setConstituencyTotalVotesCast(totalVotesCastInAConstituency);
        constituencyRepository.save(constituency);


        //votes got by presidential candidate at the polling station
        //(polling station code, name, constituency, presidential candidate,presidential candidate vote total)
        Long totalVotesByPresidentialCandidateAtAPollingStation = voteRepository.totalVotesByPresidentialCandidateAtAPollingStation(presidentialCandidate.getPresidentialVoterIdNumber(), pollingStation.getPollingStationCode());

        //total votes got by presidential candidate at a constituency
        if (constituencyPresidentialVoteSummaryRepository.existsConstituencyPresidentialVoteSummaryById(presidentialCandidate.getPresidentialVoterIdNumber())) {
            ConstituencyPresidentialVoteSummary existConstituencyPresidentialVoteSummary = constituencyPresidentialVoteSummaryRepository.findByConstituencyId(constituencyElectoralCode).orElseThrow(()-> new RuntimeException("Constituency does not exist"));
            existConstituencyPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotesByPresidentialCandidateAtAPollingStation);

            constituencyPresidentialVoteSummaryRepository.save(existConstituencyPresidentialVoteSummary);
        } else  {
            ConstituencyPresidentialVoteSummary newConstituencyPresidentialVoteSummary = new ConstituencyPresidentialVoteSummary();
            newConstituencyPresidentialVoteSummary.setConstituencyId(constituencyElectoralCode);
            newConstituencyPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotesByPresidentialCandidateAtAPollingStation);
            newConstituencyPresidentialVoteSummary.setPresidentialCandidateId(presidentialCandidate.getPresidentialVoterIdNumber());
            newConstituencyPresidentialVoteSummary.setDistrictId(constituency.getDistrict().getDistrictElectoralCode());

            constituencyPresidentialVoteSummaryRepository.save(newConstituencyPresidentialVoteSummary);
        }

        //save regional presidential vote summary
        Long totalVotesForParticularPresidentialCandidateAtARegion = voteRepository.totalVotesForPresidentialCandidateForARegion(presidentialCandidate.getPresidentialVoterIdNumber(), regionCode);
        if (regionalPresidentialVoteSummaryRepository.existsByRegionId(regionCode)){
            RegionalPresidentialVoteSummary existRegionalPresidentialVoteSummary = regionalPresidentialVoteSummaryRepository.findByRegionId(regionCode).orElseThrow(()->new RuntimeException("Regional Presidential Vote Summary not found"));
            existRegionalPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotesForParticularPresidentialCandidateAtARegion);

            regionalPresidentialVoteSummaryRepository.save(existRegionalPresidentialVoteSummary);
        } else  {
            RegionalPresidentialVoteSummary newRegionalPresidentialVoteSummary = new RegionalPresidentialVoteSummary();
            newRegionalPresidentialVoteSummary.setRegionId(regionCode);
            newRegionalPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotesForParticularPresidentialCandidateAtARegion);
            newRegionalPresidentialVoteSummary.setPresidentialCandidateId(presidentialCandidate.getPresidentialVoterIdNumber());

            regionalPresidentialVoteSummaryRepository.save(newRegionalPresidentialVoteSummary);
        }

        //across nation total for presidential candidate
        Long totalVotesByPre = voteRepository.findTotalVotesForPresidentialCandidate(presidentialCandidate.getPresidentialVoterIdNumber());
        presidentialCandidate.setTotalVotesAttained(totalVotesByPre);
        presidentialCandidateRepository.save(presidentialCandidate);

        //total votes cast
        Long totalVotesCast = voteRepository.totalVotesCast();


        return Response.builder()
//                .parliamentaryTotal(totalVotesCastInAParticularConstituency)
                .message("Vote successfully cast")
                .totalVoteCastNationwide(totalVotesCast)
                .build();
    }

    @Override
    public Response getPresidentialVoteSummaryByConstituency() {
        List<ConstituencyPresidentialVoteSummary> presidentialVoteSummaryByConstituencies = constituencyPresidentialVoteSummaryRepository.findAll();
        return Response.builder()
                .presidentialVoteSummaryByConstituencies(presidentialVoteSummaryByConstituencies)
                .build();
    }

    @Override
    public Response getParliamentaryVoteSummary() {
        List<ParliamentaryCandidate> parliamentaryCandidate = parliamentaryCandidateRepository.findAll();

        List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOS = parliamentaryCandidate.stream().map(entityDTOMapper::parliamentaryCandidateToParliamentaryCandidateDTO).collect(Collectors.toList());
        return Response.builder()
                .parliamentaryCandidateDTOList(parliamentaryCandidateDTOS)
                .build();
    }

    @Override
    public Response getPresidentialVoteSummaryByDistrict() {
        List<DistrictPresidentialVoteSummary> districtPresidentialVoteSummaries = districtPresidentialVoteSummaryRepository.findAll();
        return Response.builder().presidentialVoteSummaryByDistricts(districtPresidentialVoteSummaries).build();
    }

    @Override
    public Response getAllVotes() {
        //give it dto to take only what you want
        List<Vote> listOfAllVotesCast = voteRepository.findAll();

        return Response.builder().
                listAllVotes(listOfAllVotesCast)
                .build();
    }


}
