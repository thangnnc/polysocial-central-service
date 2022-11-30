package com.polysocial.service.impl.users;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.ContactDTO;
import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.FriendDetailDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Contacts;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Groups;
import com.polysocial.entity.RoomChats;
import com.polysocial.entity.Users;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.ContactRepo;
import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.RoomChatRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.service.users.UserService;
import com.polysocial.type.TypeNotifications;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FriendRepo friendRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private RoomChatRepo roomChatRepo;

    @Autowired
    private ContactRepo contactRepo;

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
            Users userInvite = userRepo.findById(friend.getUserInviteId()).get();
            Users userConfirm = userRepo.findById(friend.getUserConfirmId()).get();
            Long roomChatId = roomChatRepo.getRoomChatByGroupId(friend.getGroup().getGroupId()).getRoomId();
            FriendDetailDTO friendDTO = new FriendDetailDTO(userConfirm.getUserId(), userInvite.getUserId(),
                    userConfirm.getFullName(),userInvite.getFullName(), userInvite.getAvatar(), userConfirm.getAvatar());
            friendDTO.setRoomId(roomChatId);
            Long group_Id = friendRepo.getGroupByUser(userConfirm.getUserId(), userInvite.getUserId()).get(0).getGroup().getGroupId();
            friendDTO.setGroupId(group_Id);
            if(userId == userConfirm.getUserId()) {
                friendDTO.setFriendName(userInvite.getFullName());
                friendDTO.setFriendAvatar(userInvite.getAvatar());
            }
            else{
                friendDTO.setFriendName(userConfirm.getFullName());
                friendDTO.setFriendAvatar(userConfirm.getAvatar());
            }
            friendDTO.setStatus(friend.getStatus());
            Long groupId = friendRepo.getFriendByUserInviteIdAndUserConfirm(userInvite.getUserId(), userConfirm.getUserId()).get(0).getGroup().getGroupId();
            Long roomId = roomChatRepo.getRoomChatByGroupId(groupId).getRoomId();
            List<Contacts> contacts = contactRepo.getContactByRoomId(roomId);
            List<ContactDTO> listContactDTO = new ArrayList<>();
            for (Contacts contact : contacts) {
                Users user = userRepo.findById(contact.getUserId()).get();
                ContactDTO contactDTO = new ContactDTO(user.getUserId(), user.getFullName(), user.getEmail(), user.getAvatar(), user.getStudentCode(), contact.getContactId());
                listContactDTO.add(contactDTO);
                friendDTO.setListContact(listContactDTO);
            }

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
                        userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar(), userConfirm.getAvatar());
                Groups group = new Groups("Friends chat", 2L, "Friend with chat rooms", "Friends chat");
                Groups groupCreated = groupRepo.save(group);
                
                RoomChats roomChat = new RoomChats(groupCreated);
                Long roomId = roomChatRepo.save(roomChat).getRoomId();
                friendDetailDTO.setRoomId(roomId);
                Contacts contact = new Contacts(userConfirm.getUserId(), roomId);
                contactRepo.save(contact);
                Contacts contact2 = new Contacts(userInvite.getUserId(), roomId);
                contactRepo.save(contact2);

                Friends friend = new Friends();
                friend.setUserInviteId(userInvite.getUserId());
                friend.setUserConfirmId(userConfirm.getUserId());
                friend.setGroup(groupCreated);
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
                    userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar(), userConfirm.getAvatar());
            e.printStackTrace();
            return friendDetailDTO;
        }

    }

    @Override
    public FriendDetailDTO acceptFriend(Long userConfirmId, Long userInviteId) {
        friendRepo.acceptFriend(userConfirmId, userInviteId);
        FriendDetailDTO friendDetailDTO = new FriendDetailDTO(userConfirmId, userInviteId,
                userRepo.findById(userConfirmId).get().getFullName(),
                userRepo.findById(userInviteId).get().getFullName(), userRepo.findById(userInviteId).get().getAvatar(),  userRepo.findById(userConfirmId).get().getAvatar());
        friendDetailDTO.setStatus(true);

        Long groupId = friendRepo.getFriendByUserInviteIdAndUserConfirm(userInviteId ,userConfirmId).get(0).getGroup().getGroupId();
        Friends friend = new Friends();
        friend.setUserInviteId(userRepo.findByUserId(userInviteId).getUserId());
        friend.setUserConfirmId(userRepo.findById(userConfirmId).get().getUserId());
        friend.setStatus(true);
        friend.setGroup(groupRepo.findById(groupId).get());
        friendRepo.save(friend);
        Long roomId = roomChatRepo.getRoomChatByGroupId(groupId).getRoomId();
        friendDetailDTO.setRoomId(roomId);
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
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirm().getUserId(), friend.getUserInvite().getUserId(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getFullName(),  userRepo.findById(friend.getUserInvite().getUserId()).get().getAvatar(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getAvatar());
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
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirm().getUserId(), friend.getUserInvite().getUserId(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getFullName(),userRepo.findById(friend.getUserInvite().getUserId()).get().getAvatar(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getAvatar() );
            friendDTO.setStatus(friend.getStatus());
            listDTO.add(friendDTO);
        }
        return listDTO;
    }

}
