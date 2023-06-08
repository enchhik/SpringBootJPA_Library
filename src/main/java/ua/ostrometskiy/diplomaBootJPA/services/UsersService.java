package ua.ostrometskiy.diplomaBootJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.diplomaBootJPA.models.Users;
import ua.ostrometskiy.diplomaBootJPA.repositories.UsersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> index() {      //findAll
    return usersRepository.findAll();
    }

    public Users show(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    public void update(int id, Users updatedUsers) {
        Users userToBeUpdated = usersRepository.findById(id).orElse(null);

        if (userToBeUpdated != null) {
            userToBeUpdated.setName(updatedUsers.getName());
            userToBeUpdated.setEmail(updatedUsers.getEmail());
            userToBeUpdated.setPassword(updatedUsers.getPassword());
            userToBeUpdated.getPassport().setPassportNumber(updatedUsers.getPassport().getPassportNumber());

            usersRepository.save(userToBeUpdated);
        }
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}
