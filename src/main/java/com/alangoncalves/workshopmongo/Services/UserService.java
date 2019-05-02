package com.alangoncalves.workshopmongo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

import com.alangoncalves.workshopmongo.Services.exception.ObjectNotFoundException;
import com.alangoncalves.workshopmongo.domain.User;
import com.alangoncalves.workshopmongo.dto.UserDTO;
import com.alangoncalves.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
				
	}
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	public User update(User obj) {
		User newObj = findById(obj.getId());
		Update(newObj, obj);
		return repo.save(newObj);
		
	}
	
	private void Update(User newObj, User obj) {
	
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
		
	}
}
