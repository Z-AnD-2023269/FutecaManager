package com.andersonlopez.futecaManager.service.IService;

import java.util.List;

import com.andersonlopez.futecaManager.models.SoccerField;


public interface ISoccerFieldService {
    public List<SoccerField> listFields();

    public SoccerField findFieldById(Long id);

    public SoccerField save (SoccerField soccerFields);
}