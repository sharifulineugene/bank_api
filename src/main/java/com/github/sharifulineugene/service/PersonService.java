package com.github.sharifulineugene.service;

import com.github.sharifulineugene.dao.IPersonDao;
import com.github.sharifulineugene.dto.PersonDto;
import com.github.sharifulineugene.dto.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService implements IPersonService{
    private IPersonDao personDAO;
    private PersonMapper personMapper;

    @Autowired
    public PersonService(IPersonDao personDAO, PersonMapper personMapper) {
        this.personDAO = personDAO;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDto> getAll() {
        return personDAO.index().stream().map(personMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(PersonDto object) {
        personDAO.save(personMapper.toEntity(object));
    }

    @Override
    public void update(PersonDto object) {
        personDAO.update(personMapper.toEntity(object));
    }

    @Override
    public PersonDto get(int id) {
        return personMapper.toDto(personDAO.show(id));
    }

    @Override
    public void delete(int id) {
        personDAO.delete(id);
    }
}
