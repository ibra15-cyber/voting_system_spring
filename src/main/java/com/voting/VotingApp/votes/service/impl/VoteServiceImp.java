package com.voting.VotingApp.votes.service.impl;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.votes.repository.VoteRepository;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public VoteServiceImp(VoteRepository voteRepository, VoterRepository voterRepository,
                          ConstituencyRepository constituencyRepository,
                          ParliamentaryCandidateRepository parliamentaryCandidateRepository,
                          DistrictRepository districtRepository, RegionRepository regionRepository, PresidentialCandidateRepository presidentialCandidateRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.presidentialCandidateRepository = presidentialCandidateRepository;
    }

    @Override
    public Response vote(VoteDTO voteDTO) {

        Vote newVote = new Vote();


        //to be replaced by AuthVoter
        Voter voter = voterRepository.findById(voteDTO.getVoterId()).orElseThrow(()-> new RuntimeException("Voter not found"));
        newVote.setVoter(voter);

        //no need to do double work, by finding the constituency from the user, we can get that from the constituency candidate
        //so i can choose to stop sending constituencyId over in DTO
        Constituency constituency = constituencyRepository.findById(voteDTO.getConstituencyId()).orElseThrow(()-> new RuntimeException("Constituency not found"));
        newVote.setConstituencyId(constituency.getConstituencyId());

        //from the parliamentary candidate a voter is voting for, we can get their constituencyId
        //we could also use the user's constituencyid to double check
        ParliamentaryCandidate parliamentaryCandidate = parliamentaryCandidateRepository.findById(voteDTO.getParliamentaryCandidateId()).orElseThrow(()-> new RuntimeException("Parlimentary candidate does not exist"));
        newVote.setParliamentaryCandidate(parliamentaryCandidate);


        PresidentialCandidate presidentialCandidate = presidentialCandidateRepository.findById(voteDTO.getPresidentialCandidateId()).orElseThrow(()-> new RuntimeException("Presidential candidate does not exist"));
        newVote.setPresidentialCandidate(presidentialCandidate);
        

        //using the candidate to pull his constituency; we could use the voter
        //i am commenting this bc i have a need for an obj consti for something else
//        newVote.setConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());

        voteRepository.save(newVote); //save vote


        /**
         * GET CONSTITUENCY VOTES
         */
        List<Vote> constituencyVotes = voteRepository.findVotesByConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());
        //after vote is cast, aggregate the total
//        BigDecimal totalConstituencyVotes = votes.stream().map(Vote::getParliamentaryCandidate).reduce(BigDecimal.ZERO, BigDecimal::add);

//        System.out.println(votes);
        //get the constituency vote
        Long totalConstituencyVotes = constituencyVotes.stream()
                .filter(vote -> vote.getConstituencyId().equals(parliamentaryCandidate.getConstituency().getConstituencyId()))
                .count();

        //get the total constituency property and update then save
        constituency.setConstituencyTotalVotes(totalConstituencyVotes);
        constituencyRepository.save(constituency);

        //set total for each candidate
        // Group votes by parliamentary candidate and calculate totals for each
        Map<Long, Long> totalVotesByCandidate = constituencyVotes.stream()
//                .filter(vote -> vote.getConstituencyId().equals(parliamentaryCandidate.getConstituency().getConstituencyId()))
                .collect(Collectors.groupingBy(
                        vote -> vote.getParliamentaryCandidate().getId(), // Group by candidate ID
                        Collectors.counting() // Count votes for each candidate
                ));

        totalVotesByCandidate.forEach((candidateId, totalVotes) -> {
            ParliamentaryCandidate candidate = parliamentaryCandidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));
            candidate.setTotalVotesAttained(totalVotes);
            parliamentaryCandidateRepository.save(candidate); // Save the updated total votes
        });



        /**
         *  GET DISTRICT VOTES
         */

        // Fetch all constituencies in the given district
        List<Constituency> constituenciesInDistrict = constituencyRepository.findConstituenciesByDistrict(constituency.getDistrict());

        // Map to store total votes for each district
        Map<Long, Long> totalVotesByDistrict = new HashMap<>();

        // Loop through constituencies and aggregate votes by district
        for (Constituency constituency1 : constituenciesInDistrict) {
            // Get total votes for the constituency
            Long totalVotesForConstituency = constituency1.getConstituencyTotalVotes();

            // Add to the district's total vote count
            totalVotesByDistrict.merge(constituency1.getDistrict().getDistrictId(), totalVotesForConstituency, Long::sum);
        }

        // Now you have the total votes for each district
        totalVotesByDistrict.forEach((districtId, totalVotes) -> {
            District district = districtRepository.findById(districtId)
                    .orElseThrow(() -> new RuntimeException("District not found"));
            district.setDistrictTotalVotesCast(totalVotes);
            districtRepository.save(district); // Save the updated total votes for the district
        });

        /**
         *  GET REGIONAL VOTES
         */

        List<District> districtsInRegions = districtRepository.findDistrictsByRegion(constituency.getDistrict().getRegion());
        Map<Long, Long> totalVotesByRegion = new HashMap<>();
        for (District district : districtsInRegions) {
           Long totalVotesForDistrict =  district.getDistrictTotalVotesCast();
            totalVotesByRegion.merge(district.getDistrictId(), totalVotesForDistrict, Long::sum);
        }
        totalVotesByRegion.forEach((regionId, totalVotes)-> {
            Region region = regionRepository.findById(regionId).orElseThrow(() -> new RuntimeException("District not found"));
            region.setRegionalTotalVotesCast(totalVotes);
            regionRepository.save(region);
        });


        /**
         * NATIONAL VOTE TOTAL
         */

        Long totalVotes = (long) voteRepository.findAll().size();



        return Response.builder()
                .parliamentaryTotal(totalConstituencyVotes)
                .message("Vote successfully cast")
                .totalVoteCastNationwide(totalVotes)
                .build();
    }

    //there's a diff btw constituency total and candidate constituency totoal
}
