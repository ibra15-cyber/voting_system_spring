package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.DistrictDTO;
import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.service.DistrictService;
import com.voting.VotingApp.voting_register.service.RegionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistrictSeed {

    private final DistrictService districtService;
    private final DistrictRepository districtRepository;

    public DistrictSeed(DistrictService districtService, DistrictRepository districtRepository) {
        this.districtService = districtService;
        this.districtRepository = districtRepository;
    }

    public void seeDistricts() {
//             Check if there are existing regions to prevent duplicate seeding
                List<DistrictDTO> districtDTOList = List.of(
                        new DistrictDTO("JOMORO Municipal", "A", "01", "Half Assini"),
                        new DistrictDTO("ELLEMBELE District", "A", "02", "Half Nkroful"),
                        new DistrictDTO("Nzema East Municipal", "A", "03", "Axim"),
                        new DistrictDTO("AHANTA WEST", "A", "04", "Agona Ahanta"),
                        new DistrictDTO("TAKORADI Meteropolitan", "A", "05", "Takoradi"),
                        new DistrictDTO("SEKONDI", "A", "06", "Sekondi"),
                        new DistrictDTO("EFFIA-KWESIMINTSIM", "A", "07", "Sekondi"),
                        new DistrictDTO("ESSIKADO-KETAN", "A", "08", "Sekondi"),
                        new DistrictDTO("SHAMA", "A", "09", "SHAMA"),
                        new DistrictDTO("WASSA EAST", "A", "10", "Wassa-Akropong"),
                        new DistrictDTO("MPOHOR", "A", "11", "Wassa-Akropong"),
                        new DistrictDTO("TARKWA NSUAEM", "A", "12", "TARKWA NSUAEM"),
                        new DistrictDTO("PRESTEA HUNI-VALLEY", "A", "13", "TARKWA NSUAEM"),
                        new DistrictDTO("WASSA AMENFI EAST", "A", "14", "TARKWA NSUAEM"),
                        new DistrictDTO("WASSA AMENFI CENTRAL", "A", "15", "TARKWA NSUAEM"),
                        new DistrictDTO("WASSA AMENFI WEST", "A", "16", "TARKWA NSUAEM"),

                        //districts that belong to a second region
                        new DistrictDTO("KOMENDA EDINA EGUAFO ABREM", "B", "01", "ABREM"),
                        new DistrictDTO("CAPE COAST", "B", "02", "CAPE COAST") // this district has instance where 2 constituency in the district

                        //region with 2 constituencies in the same district
//                        new DistrictDTO("CAPE COAST", 4L, "02", "CAPE COAST")

                );

                districtDTOList.forEach(districtDTO -> {
                    if (!districtRepository.existsByDistrictElectoralCode(districtDTO.getDistrictElectoralCode())){
                        districtService.createDistrict(districtDTO);
//                        districtDTOList.forEach(districtService::createDistrict);
                    } else {
                        System.out.println("District already exists" + districtDTO.getDistrictElectoralCode());

                    }
                });

                System.out.println("District seeded successfully.");

            } ;

}
