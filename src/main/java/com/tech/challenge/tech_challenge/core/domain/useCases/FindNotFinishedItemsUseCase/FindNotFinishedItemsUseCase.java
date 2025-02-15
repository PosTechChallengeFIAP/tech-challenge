package com.tech.challenge.tech_challenge.core.domain.useCases.FindNotFinishedItemsUseCase;

import com.tech.challenge.tech_challenge.core.domain.entities.Queue;
import com.tech.challenge.tech_challenge.core.domain.repositories.IQueueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindNotFinishedItemsUseCase implements IFindNotFinishedItemsUseCase {

    @Autowired
    private IQueueRepository queueRepository;

    public List<Queue> execute() {
        return this.queueRepository.findAllNotFinishedItems();
    }

}
