package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.StatusDTO;
import com.personal.beshophihi.model.Status;
import com.personal.beshophihi.utils.StatusName;

import java.util.List;

public interface StatusService {
    List<Status> getAllStatus();
    Status getStatusById(Long id);
    Status getStatusByName(StatusName statusName);
    Status addStatus(StatusDTO statusDTO);
    Status updateStatus(Long id, Status status);
    void deleteStatus(Long id);
}
