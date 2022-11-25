package com.polysocial.service.impl.users;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.FriendDetailDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.service.users.UserService;
import com.polysocial.type.TypeNotifications;
import com.polysocial.utils.QrCode;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    FriendRepo friendRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private NotificationsService notificationsService;


    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> list = userRepo.findAll();
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public UserDTO getOneUser(Long userId) {
        Users user = userRepo.findByUserId(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public FriendDTO getUserFriend(Long userId, Long friendId) {
        List<Friends> friends = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, friendId);
        Friends friend = friends.get(0);
        FriendDTO friendDTO = modelMapper.map(friend, FriendDTO.class);
        return friendDTO;
    }

    @Override
    public List<FriendDetailDTO> getAllFriend(Long userId) {
        List<Friends> list = friendRepo.getAllFriends(userId);
        List<FriendDetailDTO> listDTO = new ArrayList<>();
        for (Friends friend : list) {
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirmId(), friend.getUserInviteId(),
                    userRepo.findById(friend.getUserConfirmId()).get().getFullName(),
                    userRepo.findById(friend.getUserInviteId()).get().getFullName(), userRepo.findById(friend.getUserInviteId()).get().getAvatar() );
            friendDTO.setStatus(friend.getStatus());
            listDTO.add(friendDTO);
        }
        return listDTO;
    }

    @Override
    public List<UserDTO> searchUserByEmail(String email) {
        List<Users> list = userRepo.findByEmail(email);
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) {
        List<Users> list = userRepo.findByFullName(name);
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public FriendDetailDTO addFriend(Long userConfirmId, String studentCode) {
        try {
            Users user = userRepo.findByStudentCode(studentCode);
            List<Friends> list = friendRepo.getFriendByUserInviteIdAndUserConfirm(userConfirmId, user.getUserId());
            System.out.println(list.size());
            if(list.size() == 0) {
                Users userConfirm = userRepo.findByUserId(user.getUserId());
                Users userInvite = userRepo.findByUserId(userConfirmId);
                FriendDetailDTO friendDetailDTO = new FriendDetailDTO(user.getUserId(), userConfirmId,
                        userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar());
                Friends friend = modelMapper.map(friendDetailDTO, Friends.class);
                friendRepo.save(friend);
                String nameFriend = userInvite.getFullName();
                NotificationsDTO notificationsDTO = new NotificationsDTO(String.format(ContentNotifications.NOTI_CONTENT_ADD_FRIEND, nameFriend), TypeNotifications.NOTI_TYPE_ADD_FRIEND, userConfirm.getUserId());
                notificationsService.createNoti(notificationsDTO);
                return friendDetailDTO;
            }
           return null;
        } catch (Exception e) {
            Users user = userRepo.findByStudentCode(studentCode);
            Users userConfirm = userRepo.findByUserId(user.getUserId());
            Users userInvite = userRepo.findByUserId(userConfirmId);
            FriendDetailDTO friendDetailDTO = new FriendDetailDTO(user.getUserId(), userConfirmId,
                    userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar());
                    e.printStackTrace();
            return friendDetailDTO;
        }

    }

    @Override
    public FriendDetailDTO acceptFriend(Long userConfirmId, Long userInviteId) {
        friendRepo.acceptFriend(userConfirmId, userInviteId);
        FriendDetailDTO friendDetailDTO = new FriendDetailDTO(userConfirmId, userInviteId,
                userRepo.findById(userConfirmId).get().getFullName(),
                userRepo.findById(userInviteId).get().getFullName(), userRepo.findById(userInviteId).get().getAvatar());
        friendDetailDTO.setStatus(true);
        Friends friend = modelMapper.map(friendDetailDTO, Friends.class);
        friendRepo.save(friend);
        NotificationsDTO notificationsDTO = new NotificationsDTO(String.format(ContentNotifications.NOTI_CONTENT_ACCEPT_FRIEND, userRepo.findById(userConfirmId).get().getFullName()), TypeNotifications.NOTI_TYPE_ACCEPT_FRIEND, userInviteId);
        notificationsService.createNoti(notificationsDTO);
        return friendDetailDTO;
    }

    @Override
    public void deleteRequestAddFriend(Long userConfirmId, Long userInviteId) {
        try {
            friendRepo.deleteRequestAddFriend(userInviteId, userConfirmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FriendDetailDTO> getAllRequestAddFriend(Long userId) {
        List<Friends> list = friendRepo.getAllRequestAddFriend(userId);
        List<FriendDetailDTO> listDTO = new ArrayList<>();
        for (Friends friend : list) {
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirmId(), friend.getUserInviteId(),
                    userRepo.findById(friend.getUserConfirmId()).get().getFullName(),
                    userRepo.findById(friend.getUserInviteId()).get().getFullName(),  userRepo.findById(friend.getUserInviteId()).get().getAvatar());
            friendDTO.setStatus(friend.getStatus());
            listDTO.add(friendDTO);
        }
        return listDTO;
    }

    @Override
    public List<FriendDetailDTO> getAllRequestAddFriendByUserIntive(Long userId) {
        List<Friends> list = friendRepo.getAllRequestAddFriendByUserInviteId(userId);
        List<FriendDetailDTO> listDTO = new ArrayList<>();
        for (Friends friend : list) {
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirmId(), friend.getUserInviteId(),
                    userRepo.findById(friend.getUserConfirmId()).get().getFullName(),
                    userRepo.findById(friend.getUserInviteId()).get().getFullName(),userRepo.findById(friend.getUserInviteId()).get().getAvatar() );
            friendDTO.setStatus(friend.getStatus());
            listDTO.add(friendDTO);
        }
        return listDTO;
    }

}
