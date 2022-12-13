package com.polysocial.service.impl.users;


import java.util.ArrayList;
import java.util.Base64;
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
import com.polysocial.entity.Messages;
import com.polysocial.entity.RoomChats;
import com.polysocial.entity.Users;
import com.polysocial.entity.ViewedStatus;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.ContactRepo;
import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.MessageRepo;
import com.polysocial.repo.RoomChatRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.repo.ViewedStatusRepo;
import com.polysocial.service.group.GroupService;
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

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ViewedStatusRepo viewedStatusRepo;

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
    public List<UserDTO> searchByKeyWord(String keyword) {
        List<Users> list = userRepo.searchByKeyWord(keyword);
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }


    @Override
    public FriendDetailDTO addFriend(Long userConfirmId, String studentCode) {
        try {
            Users user = userRepo.findByStudentCode(studentCode);
            if(user.getUserId() == userConfirmId) {
                return null;
            }
            List<Friends> list = friendRepo.getFriendByUserInviteIdAndUserConfirm(userConfirmId, user.getUserId());
            if(list.size() == 0) {
                Users userConfirm = userRepo.findByUserId(user.getUserId());
                Users userInvite = userRepo.findByUserId(userConfirmId);
                FriendDetailDTO friendDetailDTO = new FriendDetailDTO(user.getUserId(), userConfirmId,
                        userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar(), userConfirm.getAvatar());
              
                Groups group = new Groups();
                group.setName(userConfirm.getFullName() + "," + userInvite.getFullName());
                group.setTotalMember(2L);
                group.setDescription("Friend with chat rooms");
                group.setClassName("Friend Chat");

                Groups groupCreate = groupRepo.save(group);
                Friends friend = new Friends();
                friend.setUserInviteId(userInvite.getUserId());
                friend.setUserConfirmId(userConfirm.getUserId());
                friend.setGroup(groupCreate);
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
        friendDetailDTO.setEmailInvite( userRepo.findById(userInviteId).get().getEmail());
        Groups group = new Groups(friendDetailDTO.getFullNameUserConfirm()+","+friendDetailDTO.getFullNameUserInvite(), 0L, "Friend with chat rooms", "Friends chat");
        group.setAvatar(friendDetailDTO.getAvatarUserConfirm()+","+friendDetailDTO.getAvatarUserInvite());
        Groups groupCreated = groupRepo.save(group);
        
        RoomChats roomChat = new RoomChats(groupCreated);
        roomChat.setLastMessage("Hai bạn đã trở thành bạn bè của nhau");
        //encodedString
		String encodedStringRoom = Base64.getEncoder().encodeToString(roomChat.getLastMessage().getBytes());
		roomChat.setLastMessage(encodedStringRoom);
        Long roomChatId = roomChatRepo.save(roomChat).getRoomId();
        friendDetailDTO.setRoomId(roomChatId);
        Contacts contact = new Contacts(userConfirmId, roomChatId);
        contactRepo.save(contact);
        Contacts contact2 = new Contacts(userInviteId, roomChatId);
        contactRepo.save(contact2);
        Friends friends = friendRepo.getFriendByUserInviteIdAndUserConfirm(userInviteId, userConfirmId).get(0);
        friends.setStatus(true);
        friends.setGroup(groupCreated);
        friendRepo.save(friends);

		
        Messages messageConfirm = new Messages("Bạn và "+friendDetailDTO.getFullNameUserConfirm()+" đã trở thành bạn bè của nhau",false);
        //encodedString
		String encodedStringConfirm = Base64.getEncoder().encodeToString(messageConfirm.getContent().getBytes());
        messageConfirm.setContent(encodedStringConfirm);
        messageConfirm.setContact(contact);

        Messages messageInvite = new Messages("Bạn và "+friendDetailDTO.getFullNameUserInvite()+" đã trở thành bạn bè của nhau",false);
        String encodedStringInvite = Base64.getEncoder().encodeToString(messageInvite.getContent().getBytes());
        messageInvite.setContent(encodedStringInvite);

        messageInvite.setContact(contact2);
        messageRepo.save(messageConfirm);
        messageRepo.save(messageInvite);

        ViewedStatus viewedStatusConfirm = new ViewedStatus();
        viewedStatusConfirm.setContactId(contact.getContactId());
        viewedStatusRepo.save(viewedStatusConfirm);

        ViewedStatus viewedStatusInvite = new ViewedStatus();
        viewedStatusInvite.setContactId(contact2.getContactId());
        viewedStatusRepo.save(viewedStatusInvite);

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
            friendDTO.setEmailInvite(userRepo.findById(friend.getUserInvite().getUserId()).get().getEmail());
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
