package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.entity.Region;
import com.voting.VotingApp.voting_register.repository.RegionRepository;
import com.voting.VotingApp.voting_register.service.RegionService;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionSeed {

    private final RegionService regionService;
    private final RegionRepository regionRepository;

    public RegionSeed(RegionService regionService, RegionRepository regionRepository) {
        this.regionService = regionService;
        this.regionRepository = regionRepository;
    }

    public void seedRegions() {
//             Check if there are existing regions to prevent duplicate seeding
        List<RegionDTO> regionDTOList = List.of(
                new RegionDTO("Greater Accra", "C", "Accra"),
                new RegionDTO("Brong Ahafo", "J", "Sunyani"),
                new RegionDTO("Ashanti", "F", "Kumasi"),
                new RegionDTO("Central", "B", "Cape Coast"),
                new RegionDTO("Eastern", "E", "Koforidua"),
                new RegionDTO("Western", "A", "Sekondi-Takoradi"),
                new RegionDTO("Northern", "M", "Tamale"),
                new RegionDTO("Volta", "D", "Ho"),
                new RegionDTO("Western North", "G", "Sefwi Wiawso"),
                new RegionDTO("North East", "Q", "Nalerigu"),
                new RegionDTO("Ahafo", "H", "Goaso"),
                new RegionDTO("Upper West", "P", "Wa"),
                new RegionDTO("Upper East", "R", "Bolgatanga"),
                new RegionDTO("Bono East", "K", "Techiman"),
                new RegionDTO("Oti", "L", "Dambai"),
                new RegionDTO("Savanna", "N", "Damongo")
        );

            regionDTOList.forEach(regionDTO -> {
                if (!regionRepository.existsByRegionElectoralCode(regionDTO.getRegionElectoralCode())){
                    regionService.createRegion(regionDTO);
//                    regionDTOList.forEach(regionService::createRegion);
                } else {
                    System.out.println("Region Exist");

                }
            });
        System.out.println("Regions seeded successfully.");

    }
}
