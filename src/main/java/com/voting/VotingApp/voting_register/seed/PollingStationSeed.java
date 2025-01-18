package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.PollingStationDTO;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.PollingStationRepository;
import com.voting.VotingApp.voting_register.service.ConstituencyService;
import com.voting.VotingApp.voting_register.service.PollingStationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PollingStationSeed {

    private final PollingStationService pollingStationService;
    private final PollingStationRepository pollingStationRepository;

    public PollingStationSeed(PollingStationService pollingStationService, PollingStationRepository pollingStationRepository) {

        this.pollingStationService = pollingStationService;
        this.pollingStationRepository = pollingStationRepository;
    }

    public void seedPollingStations() {
//             Check if there are existing regions to prevent duplicate seeding

            List<PollingStationDTO> pollingStationDTOList = List.of(
                    new PollingStationDTO("METH JSS WORKSHOP BLK HALF-ASSINI", "A0101", "01",  null),
                    new PollingStationDTO("METH NURSERY BLK HALF-ASSINI", "A0101", "02",  null),
                    new PollingStationDTO("PEACE INTERNATIONAL PRIM SCH COMBODIA HALF-ASSINI", "A0101", "03",  null),

                    //create another set of polling stations from other constituency
                    new PollingStationDTO("PEACE INTERNATIONAL diff", "B0230", "01",  null),
                    new PollingStationDTO("PEACE INTERNATIONAL diif 2", "B0230", "02",  null)
                    );


            pollingStationDTOList.forEach(pollingStationDTO -> {
                if (pollingStationRepository.existsByPollingStationCode(pollingStationDTO.getPollingStationCode())){
                    System.out.println("Polling station exist skipping...");
                } else  {
//                    pollingStationDTOList.forEach(pollingStationService::createPollingStation); //no need to iterate again
                    pollingStationService.createPollingStation(pollingStationDTO);
                    System.out.println("PollingStation seeded successfully.");
                }
            });


        }

    }
