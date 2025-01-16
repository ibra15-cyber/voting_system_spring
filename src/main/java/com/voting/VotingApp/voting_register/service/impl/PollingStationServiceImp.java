package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.PollingStationDTO;
import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.PollingStation;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.PollingStationRepository;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
import com.voting.VotingApp.voting_register.service.PollingStationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PollingStationServiceImp implements PollingStationService {

    private final EntityDTOMapper entityDTOMapper;
    private final ConstituencyRepository constituencyRepository;
    private final PollingStationRepository pollingStationRepository;

    public PollingStationServiceImp(EntityDTOMapper entityDTOMapper, ConstituencyRepository constituencyRepository, PollingStationRepository pollingStationRepository) {
        this.entityDTOMapper = entityDTOMapper;
        this.constituencyRepository = constituencyRepository;
        this.pollingStationRepository = pollingStationRepository;
    }

    @Transactional
    @Override
    public Response createPollingStation(PollingStationDTO pollingStationDTO) {

        PollingStation newPollingStation = new PollingStation();
        newPollingStation.setPollingStationName(pollingStationDTO.getPollingStationName());

        Constituency constituency = constituencyRepository.findByConstituencyElectoralCode(pollingStationDTO.getConstituencyCode()).orElseThrow(()-> new RuntimeException("constituency not found"));
        newPollingStation.setConstituency(constituency);

        String constituencyElectoralCode = constituency.getConstituencyElectoralCode();
        newPollingStation.setPollingStationCode(constituencyElectoralCode + pollingStationDTO.getPollingStationCode());

//        newPollingStation.setTotalVoteCastAtPollingStation(); //will come from aggregation of votes

        pollingStationRepository.save(newPollingStation);

        return Response.builder()
                .message("PollingStation created successfully!")
                .build();
    }

    @Transactional
    @Override
    public Response getAllPollingStations() {
        List<PollingStation> listOfAllPollingStations = pollingStationRepository.findAll();

        String message = "";
        if (listOfAllPollingStations.isEmpty()){
            message = "No PollingStation found";
        }

        List<PollingStationDTO> pollingStationDTO = listOfAllPollingStations.stream().map(entityDTOMapper::mapPollingStationToPollingStationDTO).collect(Collectors.toList());

        return Response.builder()
                .pollingStationDTOList(pollingStationDTO)
                .message(message)
                .build();
    }

    @Transactional
    @Override
    public Response getPollingStationById(String pollingStationCode) {
        PollingStation pollingStation = pollingStationRepository.findByPollingStationCode(pollingStationCode).orElseThrow(()-> new RuntimeException("PollingStation not found!"));

        return Response.builder()
                .message("District retrieved successfully!")
                .pollingStationDTO(entityDTOMapper.mapPollingStationToPollingStationDTO(pollingStation))
                .build();
    }

    @Override
    public Response updatePollingStation(String pollingStationCode, PollingStationDTO pollingStationDTO) {
        PollingStation pollingStation = pollingStationRepository.findByPollingStationCode(pollingStationCode).orElseThrow(()-> new RuntimeException("PollingStation not found!"));


        if (pollingStationDTO.getPollingStationName() != null ) pollingStation.setPollingStationName(pollingStationDTO.getPollingStationName());
//        if (pollingStationDTO.getPollingStationCode() != null ) pollingStation.setPollingStationCode(pollingStationDTO.getPollingStationCode());

        if (pollingStationDTO.getConstituencyCode() != null ) {
            Constituency constituency = constituencyRepository.findByConstituencyElectoralCode(pollingStationDTO.getConstituencyCode()).orElseThrow(()-> new RuntimeException("constituency not found"));
            pollingStation.setConstituency(constituency);
        }

        pollingStationRepository.save(pollingStation);

        return Response.builder()
                .message("District retrieved successfully!")
                .pollingStationDTO(entityDTOMapper.mapPollingStationToPollingStationDTO(pollingStation))
                .build();
    }

    @Override
    public Response deletePollingStation(String pollingStationCode) {
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findByPollingStationCode(pollingStationCode);

        PollingStation pollingStation;
        if (optionalPollingStation.isPresent()) {
            pollingStation = optionalPollingStation.get();
            pollingStationRepository.delete(pollingStation);
        }

        return Response.builder()
                .message("PollingStation successfully deleted")
                .build();
    }

    @Override
    public Response deleteAllConstituencies(){
        pollingStationRepository.deleteAll();
        return Response.builder().message("deleted successful").build();

    }
}
