package com.voting.VotingApp.votes.service.impl;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.entity.ConstituencyPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.DistrictPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.RegionalPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.votes.repository.ConstituencyPresidentialVoteSummaryRepository;
import com.voting.VotingApp.votes.repository.DistrictPresidentialVoteSummaryRepository;
import com.voting.VotingApp.votes.repository.RegionalPresidentialVoteSummaryRepository;
import com.voting.VotingApp.votes.repository.VoteRepository;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private final ConstituencyPresidentialVoteSummaryRepository constituencyPresidentialVoteSummaryRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final DistrictPresidentialVoteSummaryRepository districtPresidentialVoteSummaryRepository;
    private final RegionalPresidentialVoteSummaryRepository regionalPresidentialVoteSummaryRepository;

    public VoteServiceImp(VoteRepository voteRepository, VoterRepository voterRepository,
                          ConstituencyRepository constituencyRepository,
                          ParliamentaryCandidateRepository parliamentaryCandidateRepository,
                          DistrictRepository districtRepository, RegionRepository regionRepository, PresidentialCandidateRepository presidentialCandidateRepository, ConstituencyPresidentialVoteSummaryRepository constituencyPresidentialVoteSummaryRepository, EntityDTOMapper entityDTOMapper, DistrictPresidentialVoteSummaryRepository districtPresidentialVoteSummaryRepository, RegionalPresidentialVoteSummaryRepository regionalPresidentialVoteSummaryRepository) {
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
        //we could also use the user's constituencyId to double check
        ParliamentaryCandidate parliamentaryCandidate = parliamentaryCandidateRepository.findById(voteDTO.getParliamentaryCandidateId()).orElseThrow(()-> new RuntimeException("Parlimentary candidate does not exist"));
        newVote.setParliamentaryCandidate(parliamentaryCandidate);

        PresidentialCandidate presidentialCandidate = presidentialCandidateRepository.findById(voteDTO.getPresidentialCandidateId()).orElseThrow(()-> new RuntimeException("Presidential candidate does not exist"));
        newVote.setPresidentialCandidate(presidentialCandidate);


        //using the candidate to pull his constituency; we could use the voter
        //i am commenting this bc i have a need for an obj consti for something else
//        newVote.setConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());

        voter.setVote(newVote);
        voteRepository.save(newVote); //save vote

//        //find the vote id of the voter and set it to voter property
//        Vote vt = voteRepository.findVoteByVoter(newVote.getVoter());
//
//        voter.setVote(vt); //do not create a new voter obj; just use the above
//        voterRepository.save(voter);



        /**
         * GET CONSTITUENCY VOTES
         *
         */

        List<Vote> listOfVotesForAParticularConstituency = voteRepository.findVotesByConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());
        //after vote is cast, aggregate the total
//        BigDecimal totalConstituencyVotes = votes.stream().map(Vote::getParliamentaryCandidate).reduce(BigDecimal.ZERO, BigDecimal::add);
        //sql : select *
        //      from votes_table
        //      where constituencyId = :constituencyId
        //output = votesForAParticularConstituency(everything for a particular constituency)

        //get the total votes cast in a constituency
        Long totalVotesCastInAParticularConstituency = (long) listOfVotesForAParticularConstituency.size();        //sql version //filter is like select
        //SELECT COUNT(*) AS totalVotesCastInAParticularConstituency
        //FROM listOfVotesForAParticularConstituency;

        //get the total constituency property and update then save
        constituency.setConstituencyTotalVotesCast(totalVotesCastInAParticularConstituency);
        constituencyRepository.save(constituency);


        //set total for each candidate
        // Group votes by parliamentary candidate and calculate totals for each
        Map<Long, Long> totalVotesByParliamentaryCandidateInAConstituency = listOfVotesForAParticularConstituency.stream()
                .collect(Collectors.groupingBy(
                        vote -> vote.getParliamentaryCandidate().getId(), // Group by candidate ID
                        Collectors.counting() // Count votes for each candidate
                ));
        //select candidateId, count(*) as totalVotes
        //from listOfVotesForAParticularConstituency
        //group by parliamentary_id
        //save totalVotesByCandidate(candidateId, totalVotes)

        //for each candidate, get him by his id and update his total
        totalVotesByParliamentaryCandidateInAConstituency.forEach((candidateId, totalVotes) -> {
            ParliamentaryCandidate candidate = parliamentaryCandidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
            candidate.setTotalVotesAttained(totalVotes);

            parliamentaryCandidateRepository.save(candidate); // Save the updated total votes for an mp
        });


        //PRESIDENTIAL CANDIDATE VOTE BY CONSTITUENCY
        // votes exist already
        Map<Long, Long> mapOfTotalVotesForPresidentialCandidateInConstituencyById = listOfVotesForAParticularConstituency.stream()
                .filter(vote -> vote.getPresidentialCandidate() != null) // Ensure valid presidential candidate
                .collect(Collectors.groupingBy(
                        vote -> vote.getPresidentialCandidate().getPresidentialCandidateId(), // Group by candidate ID
                        Collectors.counting() // Count votes for each presidential candidate
                ));
        //SELECT presidentialId, count(*) as totalVotes
        //FROM listOfVotesForAParticularConstituency
        //GROUP by presidentialId
        //votesByPresidentialCandidateInConstituencyMap(presidentialId, voteTotal)

        // Print or process the result
        mapOfTotalVotesForPresidentialCandidateInConstituencyById.forEach((candidateId, totalVotes) -> {
            /**
             *    Save for presidential candidate
             *            this should not be saved for the final presidential total but constituency presidential summary;
             */
//            PresidentialCandidate presidentialCandidateToBeUpdated =  presidentialCandidateRepository.findById(candidateId)
//                    .orElseThrow(() -> new RuntimeException("Presidential candidate not found!"));
//            presidentialCandidateToBeUpdated.setTotalVotesAttained(totalVotes); //set a property of presidential candidate and save to that repo
//            presidentialCandidateRepository.save(presidentialCandidateToBeUpdated);

//            (id, constituencyId, presidentialCandidateId, presidentialCandidateTotal)
//            it will be a bad idea to iterate something,
//            //we need this table to see the votes attained by presidential candidates in a constituency
            ConstituencyPresidentialVoteSummary existingConstituencyPresidentialVoteSummaryRecord =
                    constituencyPresidentialVoteSummaryRepository.findByConstituencyIdAndPresidentialCandidateId(
                            constituency.getConstituencyId(), candidateId
                    );

            if (existingConstituencyPresidentialVoteSummaryRecord == null) {
                ConstituencyPresidentialVoteSummary constituencyPresidentialVoteSummaryRecord = new  ConstituencyPresidentialVoteSummary();

                constituencyPresidentialVoteSummaryRecord.setConstituencyId(constituency.getConstituencyId());
                constituencyPresidentialVoteSummaryRecord.setPresidentialCandidateId(candidateId);
                constituencyPresidentialVoteSummaryRecord.setDistrictId(constituency.getDistrict().getDistrictId());
                constituencyPresidentialVoteSummaryRecord.setPresidentialCandidateVoteTotal(totalVotes);

                constituencyPresidentialVoteSummaryRepository.saveAndFlush(constituencyPresidentialVoteSummaryRecord);
            } else  {
                existingConstituencyPresidentialVoteSummaryRecord.setPresidentialCandidateVoteTotal(totalVotes);

                constituencyPresidentialVoteSummaryRepository.saveAndFlush(existingConstituencyPresidentialVoteSummaryRecord);

            }
        });


                /**
                 *  GET DISTRICT VOTES
                 */

        // Fetch all constituencies in the given district
        List<Constituency> listOfConstituenciesInDistrict = constituencyRepository.findConstituenciesByDistrict(constituency.getDistrict());

        // Map to store total votes for each district
        //totalVotesByDistrict(districtId, districtTotal)
        //I we have already aggregated constituency votes
        //we got 2 options, aggregate the districts votes from the constituency presidential vote summary or repeat from the constituencyTable
        //advantages, if you use the former, you got to include district id in the summary table, less computation, but if there errors from the initial computation
        //it will propagate and leads to redundancy (adding a district that can be infered from the constituency. Second option, do it directly from constituency, highly computative;
        //higher chances of getting right
        //constituency then require to have a property that saves total for each candidate which isn't possible bc of dimention (tow values of candidates to 1 field)
        //so change it and use constituency summary
        //aka add district id or iterate from the constituency Id



//
//        (constid, presidentialId, districtid, total)
//        1,          1                1          50
//        2           1                1          100
//        3           1                1          300
        List<ConstituencyPresidentialVoteSummary> listOfRecordsOfVotesForPresidentialCandidateForConstituenciesInAParticularDistrict =
                constituencyPresidentialVoteSummaryRepository.findByConstituencyIdAndDistrictId(constituency.getConstituencyId(), constituency.getDistrict().getDistrictId());
//        select *
//        from constituency_presidential_vote_summary
//        where constituency_id = 1 And presidential_candidate_id=1;


//        mapOfTotalVotesForPresidentialCandidateByDistrict(districtId, totalVotes)
        //issue is here, grouping by and collectors
        Map<Long, Long> mapOfTotalVotesForPresidentialCandidateByDistrict = listOfRecordsOfVotesForPresidentialCandidateForConstituenciesInAParticularDistrict.stream()
                .collect(Collectors.groupingBy(
                        ConstituencyPresidentialVoteSummary::getDistrictId,
                        Collectors.summingLong(ConstituencyPresidentialVoteSummary::getPresidentialCandidateVoteTotal)
                ));
        //SELECT districtId, count(*) as districtTotalVotesByPresidentialCandidate
        //FROM listOfRecordsOfVotesForPresidentialCandidateForConstituenciesInAParticularDistrict
        //GROUP BY districtId


        // Now you have the total votes for each district
        mapOfTotalVotesForPresidentialCandidateByDistrict.forEach((districtId, totalVotes) -> {

            DistrictPresidentialVoteSummary districtPresidentialVoteSummaryRecord =
                    districtPresidentialVoteSummaryRepository.findByDistrictIdAndPresidentialCandidateId(
                            districtId, voteDTO.getPresidentialCandidateId()
                    ).orElse(new DistrictPresidentialVoteSummary());

            districtPresidentialVoteSummaryRecord.setDistrictId(districtId);
            districtPresidentialVoteSummaryRecord.setPresidentialCandidateId(voteDTO.getPresidentialCandidateId());
//            districtPresidentialVoteSummary.setDistrictId(constituency.getDistrict().getDistrictId());
            districtPresidentialVoteSummaryRecord.setPresidentialCandidateVoteTotal(totalVotes);

            districtPresidentialVoteSummaryRepository.save(districtPresidentialVoteSummaryRecord);

        });

        //DISTRICT PRESIDENTIAL CANDIDATE TOTAL
        // Fetch Constituency IDs in the List of constituencies in a given district
        //I have fetched the constituencies already above constituenciesInDistrict
        //aka for each them, get the constituencyId and keep it as a list
        List<Long> constituencyIds = listOfConstituenciesInDistrict.stream()
                .map(Constituency::getConstituencyId)
                .collect(Collectors.toList());

        // Fetch Presidential Vote Summaries for These Constituencies
        List<ConstituencyPresidentialVoteSummary> presidentialVoteSummariesInDistrict = constituencyPresidentialVoteSummaryRepository
                .findAllById(constituencyIds);

        // Aggregate Votes by Presidential Candidate
        Map<Long, Long> votesByPresidentialCandidateInDistrict = presidentialVoteSummariesInDistrict.stream()
                .collect(Collectors.groupingBy(
                        ConstituencyPresidentialVoteSummary::getPresidentialCandidateId,
                        Collectors.summingLong(ConstituencyPresidentialVoteSummary::getPresidentialCandidateVoteTotal)
                ));

        votesByPresidentialCandidateInDistrict.forEach((candidateId, totalVotes) -> {

            DistrictPresidentialVoteSummary existingDistrictPresidentialVoteSummary = new DistrictPresidentialVoteSummary();
            if (existingDistrictPresidentialVoteSummary == null) {
                DistrictPresidentialVoteSummary districtPresidentialVoteSummary = new DistrictPresidentialVoteSummary();

                districtPresidentialVoteSummary.setPresidentialCandidateId(candidateId);
                districtPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotes);
                districtPresidentialVoteSummary.setDistrictId(constituency.getDistrict().getDistrictId());

                districtPresidentialVoteSummaryRepository.saveAndFlush(districtPresidentialVoteSummary);

            } else {
                existingDistrictPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotes);
                districtPresidentialVoteSummaryRepository.saveAndFlush(existingDistrictPresidentialVoteSummary);
            }

        });



        /**
         *  GET REGIONAL VOTES
         */

        //REGIONAL PARLIAMENTARY CANDIDATE TOTAL

        List<District> districtsInRegion = districtRepository.findDistrictsByRegion(constituency.getDistrict().getRegion());
        List<District> listOfDistrictsInAParticularRegion = districtRepository.findByDistrictIdAndRegion(constituency.getDistrict().getDistrictId(), constituency.getDistrict().getRegion());


        Map<Long, Long> totalVotesByRegion = listOfDistrictsInAParticularRegion.stream().collect(
                Collectors.groupingBy(
                        District::getDistrictId,
                        Collectors.summingLong(District::getDistrictTotalVotesCast)
                ));

        totalVotesByRegion.forEach((regionId, totalVotes)-> {
            Region region = regionRepository.findById(regionId).orElseThrow(() -> new RuntimeException("District not found"));
            region.setRegionalTotalVotesCast(totalVotes);
            regionRepository.save(region);
        });


        //REGIONAL PRESIDENTIAL CANDIDATE TOTAL
        List<Long> allDistrictIds = districtsInRegion.stream()
                .map(District::getDistrictId).collect(Collectors.toList());
//
//        List<Long> constituencyIds = districtsInRegion.stream()
//                .flatMap(district -> district.getConstituency().stream()) // Get constituencies from each district
//                .map(Constituency::getConstituencyId) // Extract constituency IDs
//                .toList();


//        List<RegionalPresidentialVoteSummary> presidentialVoteSummariesInRegions =
//                regionalPresidentialVoteSummaryRepository.findAllById(constituencyIds);
        List<DistrictPresidentialVoteSummary> districtPresidentialVoteSummaries =
                districtPresidentialVoteSummaryRepository.findAllById(allDistrictIds);



        Map<Long, Long> votesByPresidentialCandidateInRegion = districtPresidentialVoteSummaries.stream()
               .collect(Collectors.groupingBy(
                       DistrictPresidentialVoteSummary::getPresidentialCandidateId,
                       Collectors.summingLong(DistrictPresidentialVoteSummary::getPresidentialCandidateVoteTotal)
               ));

       votesByPresidentialCandidateInRegion.forEach((candidateId, totalVotes) -> {
           RegionalPresidentialVoteSummary existingRegionalPresidentialVoteSummary =
                   regionalPresidentialVoteSummaryRepository
                           .findRegionalPresidentialVoteSummariesByRegionIdAndAndPresidentialCandidateId(constituency.getDistrict().getRegion().getRegionId(), candidateId);

           if (existingRegionalPresidentialVoteSummary == null ) {
               RegionalPresidentialVoteSummary regionalPresidentialVoteSummary = new RegionalPresidentialVoteSummary();

               regionalPresidentialVoteSummary.setPresidentialCandidateId(candidateId);
               regionalPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotes);
               regionalPresidentialVoteSummary.setRegionId(constituency.getDistrict().getRegion().getRegionId());

               regionalPresidentialVoteSummaryRepository.save(regionalPresidentialVoteSummary);
           } else  {
               existingRegionalPresidentialVoteSummary.setPresidentialCandidateVoteTotal(totalVotes);
               regionalPresidentialVoteSummaryRepository.save(existingRegionalPresidentialVoteSummary);

           }

       });



        /**
         * NATIONAL VOTE TOTAL
         */

        Long totalVotes = (long) voteRepository.findAll().size();

        return Response.builder()
                .parliamentaryTotal(totalVotesCastInAParticularConstituency)
                .message("Vote successfully cast")
                .totalVoteCastNationwide(totalVotes)
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


    //there's a diff btw constituency total and candidate constituency totoal
    //get all votes (using the jpa
    //but bc we have votes by constituencies and const has relation with dist
    //filter by presidential candidate
    //group by district
    //aggregate votes


}
