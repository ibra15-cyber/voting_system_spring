package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.PollingStationDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface PollingStationService {
    Response createPollingStation(PollingStationDTO PollingStationDTO);
    Response getAllPollingStations();
    Response getPollingStationById(String PollingStationId);
    Response updatePollingStation(String PollingStationId, PollingStationDTO pollingStationDTO);
    Response deletePollingStation(String pollingStationId);

    Response deleteAllConstituencies();

}
