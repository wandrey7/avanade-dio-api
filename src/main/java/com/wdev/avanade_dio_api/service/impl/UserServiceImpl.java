package com.wdev.avanade_dio_api.service.impl;

import com.wdev.avanade_dio_api.model.Account;
import com.wdev.avanade_dio_api.model.Card;
import com.wdev.avanade_dio_api.model.User;
import com.wdev.avanade_dio_api.repository.UserRepository;
import com.wdev.avanade_dio_api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User FindById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if(userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists");
        }

        return userRepository.save(user);
    }


    @Transactional
    @Override
    public User update(Long id, User userToUpdate) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        updateUserName(existingUser, userToUpdate);
        updateUserAccount(existingUser, userToUpdate);
        updateUserCard(existingUser, userToUpdate);
        updateUserFeatures(existingUser, userToUpdate);
        updateUserNews(existingUser, userToUpdate);

        return userRepository.save(existingUser);
    }

    private void updateUserName(User existingUser, User userToUpdate) {
        Optional.ofNullable(userToUpdate.getName())
                .ifPresent(existingUser::setName);
    }

    private void updateUserAccount(User existingUser, User userToUpdate) {
        Optional.ofNullable(userToUpdate.getAccount())
                .ifPresent(updatedAccount -> {
                    Account existingAccount = existingUser.getAccount();

                    Optional.ofNullable(updatedAccount.getNumber())
                            .ifPresent(newNumber -> {
                                if (!newNumber.equals(existingAccount.getNumber()) &&
                                        userRepository.existsByAccountNumber(newNumber)) {
                                    throw new IllegalArgumentException("This Account number already exists");
                                }
                                existingAccount.setNumber(newNumber);
                            });

                    Optional.ofNullable(updatedAccount.getAgency())
                            .ifPresent(existingAccount::setAgency);

                    Optional.ofNullable(updatedAccount.getBalance())
                            .ifPresent(existingAccount::setBalance);

                    Optional.ofNullable(updatedAccount.getLimit())
                            .ifPresent(existingAccount::setLimit);
                });
    }

    private void updateUserCard(User existingUser, User userToUpdate) {
        Optional.ofNullable(userToUpdate.getCard())
                .ifPresent(updatedCard -> {
                    Card existingCard = existingUser.getCard();

                    Optional.ofNullable(updatedCard.getNumber())
                            .ifPresent(existingCard::setNumber);

                    Optional.ofNullable(updatedCard.getLimit())
                            .ifPresent(existingCard::setLimit);
                });
    }

    private void updateUserFeatures(User existingUser, User userToUpdate) {
        Optional.ofNullable(userToUpdate.getFeatures())
                .filter(features -> !features.isEmpty())
                .ifPresent(features -> {
                    existingUser.getFeatures().clear();
                    features.stream()
                            .peek(feature -> feature.setId(null))
                            .forEach(existingUser.getFeatures()::add);
                });
    }

    private void updateUserNews(User existingUser, User userToUpdate) {
        Optional.ofNullable(userToUpdate.getNews())
                .filter(news -> !news.isEmpty())
                .ifPresent(news -> {
                    existingUser.getNews().clear();
                    news.stream()
                            .peek(item -> item.setId(null))
                            .forEach(existingUser.getNews()::add);
                });
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        userRepository.delete(user);
    }
}
