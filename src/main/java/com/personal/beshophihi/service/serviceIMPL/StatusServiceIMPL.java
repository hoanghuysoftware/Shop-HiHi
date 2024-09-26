package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.StatusDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.Status;
import com.personal.beshophihi.repository.StatusRepo;
import com.personal.beshophihi.service.StatusService;
import com.personal.beshophihi.utils.StatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceIMPL implements StatusService {
    private final StatusRepo statusRepo;

    @Override
    public List<Status> getAllStatus() {
        return statusRepo.findAll();
    }

    @Override
    public Status getStatusById(Long id) {
        return statusRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found status with id: "+id)
        );
    }

    @Override
    public Status getStatusByName(StatusName statusName) {
        return statusRepo.getStatusByName(statusName).orElseThrow(
                () -> new EntityNotFound("Not found status with name: "+statusName)
        );
    }

    @Override
    public Status addStatus(StatusDTO statusDTO) {
        boolean isExists = statusRepo.existsByName(StatusName.valueOf(statusDTO.getNameStatus()));
        if(isExists){
            throw new ExistsEntityException("Name status already exists !");
        }
        Status status = Status.builder()
                .name(StatusName.valueOf(statusDTO.getNameStatus()))
                .build();
        return statusRepo.save(status);
    }

    // Tam chua can cac method phia duoi
    @Override
    public Status updateStatus(Long id, Status status) {
        return null;
    }

    @Override
    public void deleteStatus(Long id) {
    }
}
