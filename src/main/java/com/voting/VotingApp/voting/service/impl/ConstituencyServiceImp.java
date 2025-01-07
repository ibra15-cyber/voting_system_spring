package com.voting.VotingApp.voting.service.impl;

import com.voting.VotingApp.voting.dto.ConstituencyDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.entity.District;
import com.voting.VotingApp.voting.repository.ConstituencyRepository;
import com.voting.VotingApp.voting.repository.DistrictRepository;
import com.voting.VotingApp.voting.service.ConstituencyService;
import org.springframework.stereotype.Service;

@Service
public class ConstituencyServiceImp implements ConstituencyService {

    private final ConstituencyRepository constituencyRepository;
    private final DistrictRepository districtRepository;

    public ConstituencyServiceImp(ConstituencyRepository constituencyRepository, DistrictRepository districtRepository) {
        this.constituencyRepository = constituencyRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public Response createConstituency(ConstituencyDTO constituencyDTO) {

        System.out.println(constituencyDTO);

        Constituency newConstituency = new Constituency();
        newConstituency.setConstituencyName(constituencyDTO.getConstituencyName());

        District district = districtRepository.findById(constituencyDTO.getDistrictId())
                .orElseThrow(()-> new RuntimeException("District does not exist"));

        newConstituency.setDistrict(district);

        constituencyRepository.save(newConstituency);

        return Response.builder()
                .message("Constituency created successfully!")
                .build();
    }
}
