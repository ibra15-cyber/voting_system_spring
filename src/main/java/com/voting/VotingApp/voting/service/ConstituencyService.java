package com.voting.VotingApp.voting.service;

import com.voting.VotingApp.voting.dto.ConstituencyDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.entity.Constituency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ConstituencyService {
    public Response createConstituency(ConstituencyDTO constituencyDTO);
}
