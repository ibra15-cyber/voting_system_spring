package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.DistrictDTO;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.service.ConstituencyService;
import com.voting.VotingApp.voting_register.service.DistrictService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConstituencySeed {

    private final ConstituencyService constituencyService;
    private final ConstituencyRepository constituencyRepository;

    public ConstituencySeed(ConstituencyService constituencyService, ConstituencyRepository constituencyRepository) {
        this.constituencyService = constituencyService;
        this.constituencyRepository = constituencyRepository;
    }

    public void seeConstituencies() {
//             Check if there are existing regions to prevent duplicate seeding
            List<ConstituencyDTO> constituencyDTOList = List.of(
                    //here lots of constituencies in one district
                    new ConstituencyDTO("JOMORO constituency", "A01", "01", "Half Assini", null),
                    new ConstituencyDTO("ELLEMBELE constituency", "A02", "01", "Half Assini", null),
                    new ConstituencyDTO("EVALUE AJOMORO GWIRA", "A03",  "01", "Half Assini", null),
                    new ConstituencyDTO("AHANTA WEST", "A04", "01","Half Assini", null),
                    new ConstituencyDTO("TAKORADI", "A05", "01", "Half Assini", null),
                    new ConstituencyDTO("SEKONDI", "A06", "01", "Half Assini", null),
                    new ConstituencyDTO("ESSIKADU-KETAN", "A07", "01", "Half Assini", null),
                    new ConstituencyDTO("EFFIA", "A08", "01", "Half Assini", null),
                    new ConstituencyDTO("KWESIMINTSIM", "A09", "01", "Half Assini", null),
                    new ConstituencyDTO("SHAMA", "A10", "01", "Half Assini", null),
                    new ConstituencyDTO("WASSA EAST", "A11", "01", "Half Assini", null),
                    new ConstituencyDTO("MPOHOR", "A12", "01", "Half Assini", null),
                    new ConstituencyDTO("TARKWA NSUAEM", "A13", "01", "Half Assini", null),
                    new ConstituencyDTO("PRESTEA HUNI-VALLEY", "A14", "01", "Half Assini", null),
                    new ConstituencyDTO("AMENFI EAST", "A15", "01", "Half Assini", null),
                    new ConstituencyDTO("AMENFI CENTRAL", "A16", "01", "Half Assini", null),
//                    new ConstituencyDTO("AMENFI WEST", "A17", "001", "Half Assini", null),

                    //constituencies in second region which are mostly districts themselves in CAPE COST B
                    new ConstituencyDTO("KOMENDA EDINA EGUAFO ABREM", "B01", "01", "KOMENDA EDINA EGUAFO ABREM", null),
                    //situation where district is diff from constituency aka many constituencies in the same district
                    new ConstituencyDTO("CAPE COAST SOUTH", "B02", "30", "Half Assini", null),
                    new ConstituencyDTO("CAPE COAST NORTH", "B02", "31", "Half Assini", null)

                    //we can't rely on db generated districtIds, though unique, they don't give us the distinction we want, like grouping consti in the district
                    //what it means is region too must rely on districtCode and not the districtId or vice versa to be right
                    //leave the default id for db stuff
                    //the next question, but how can we assign a mapping to the district code and not the id
                    //it means our code will not change, just the mapped-by will now point to the various electoral codes, for district, constituency, region and polling station instead of the id
                    );


        constituencyDTOList.forEach(constituencyDTO -> {
            if (constituencyRepository.existsByConstituencyElectoralCode(constituencyDTO.getDistrictElectoralCode())) {
                System.out.println("Constituency already exists: ");
            } else {
                constituencyService.createConstituency(constituencyDTO);
                //            constituencyDTOList.forEach(constituencyService::createConstituency); //no need to iterate again
            }
        });
            System.out.println("Constituency seeded successfully.");

        }

    }
