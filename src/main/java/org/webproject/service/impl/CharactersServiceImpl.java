package org.webproject.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webproject.dto.Char;
import org.webproject.entity.CharacterEntity;
import org.webproject.repository.CharactersRepository;
import org.webproject.service.CharactersService;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CharactersServiceImpl implements CharactersService {

    private final CharactersRepository charactersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CharactersServiceImpl(CharactersRepository charactersRepository, ModelMapper modelMapper){
        this.charactersRepository = charactersRepository;
        this.modelMapper = modelMapper;
    }

     @Override
    public Collection<Char> getAll() {
        Iterable<CharacterEntity> iterable = charactersRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, Char.class))
                .collect(Collectors.toList());
          }
   }


