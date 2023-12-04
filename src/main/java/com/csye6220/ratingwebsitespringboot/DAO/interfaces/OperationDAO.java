package com.csye6220.ratingwebsitespringboot.DAO.interfaces;

import com.csye6220.ratingwebsitespringboot.Entity.Operation;

import java.util.List;

public interface OperationDAO {
    List<Operation> getAllOperations();

    Operation getOperationById(int id);

    Operation getOperationByUser(int user_id, int anime_id);

    void saveOperation(Operation operation);

}
