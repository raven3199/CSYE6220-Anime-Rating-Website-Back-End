package com.csye6220.ratingwebsitespringboot.Service.implementations;

import com.csye6220.ratingwebsitespringboot.DAO.interfaces.OperationDAO;
import com.csye6220.ratingwebsitespringboot.Entity.Operation;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDAO operationDAO;

    @Transactional
    @Override
    public List<Operation> getAllOperations() {
        return operationDAO.getAllOperations();
    }

    @Transactional
    @Override
    public Operation getOperationById(int id) {
        return operationDAO.getOperationById(id);
    }

    @Transactional
    @Override
    public Operation getOperationByUser(int user_id, int anime_id) {
        return operationDAO.getOperationByUser(user_id, anime_id);
    }

    @Transactional
    @Override
    public void saveOperation(Operation operation) {
        operationDAO.saveOperation(operation);
    }

}
