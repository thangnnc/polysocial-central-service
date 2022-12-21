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
import com.polysocial.dto.UserFriendDTO;
import com.polysocial.dto.UsersDTO;
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
    public UserFriendDTO getOneUser(Long userId, Long userBytoken) {
        Users user = userRepo.findByUserId(userId);
        UserFriendDTO userDTO = modelMapper.map(user, UserFriendDTO.class);
        try {
            List<Friends> th1 = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, userBytoken);
            Friends th2 = friendRepo.findFriendByUserInviteIdAndUserConfirmId(userBytoken, userId);
            if (th1.size() == 0) {
                userDTO.setStatus(1L);
            } else {
                if (th1.get(0).getStatus() == true) {
                    userDTO.setStatus(2L);
                } else {
                    if (th2 != null && th2.getStatus() == false) {
                        userDTO.setStatus(3L);
                    } else if (th2 == null) {
                        userDTO.setStatus(4L);
                    }
                }
            }

            // userDTO.setStatus(status);
            List<Friends> fr = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, userBytoken);
            if (fr.size() > 0) {
                userDTO.setIsConfirm(fr.get(0).getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    userConfirm.getFullName(), userInvite.getFullName(), userInvite.getAvatar(),
                    userConfirm.getAvatar());
            friendDTO.setRoomId(roomChatId);
            Long group_Id = friendRepo.getGroupByUser(userConfirm.getUserId(), userInvite.getUserId()).get(0).getGroup()
                    .getGroupId();
            friendDTO.setGroupId(group_Id);
            if (userId == userConfirm.getUserId()) {
                friendDTO.setFriendName(userInvite.getFullName());
                friendDTO.setFriendAvatar(userInvite.getAvatar());
                friendDTO.setFriendEmail(userInvite.getEmail());
                friendDTO.setFriendUserId(userInvite.getUserId());
            } else {
                friendDTO.setFriendName(userConfirm.getFullName());
                friendDTO.setFriendAvatar(userConfirm.getAvatar());
                friendDTO.setFriendEmail(userConfirm.getEmail());
                friendDTO.setFriendUserId(userConfirm.getUserId());
            }
            friendDTO.setStatus(friend.getStatus());
            Long groupId = friendRepo
                    .getFriendByUserInviteIdAndUserConfirm(userInvite.getUserId(), userConfirm.getUserId()).get(0)
                    .getGroup().getGroupId();
            Long roomId = roomChatRepo.getRoomChatByGroupId(groupId).getRoomId();
            List<Contacts> contacts = contactRepo.getContactByRoomId(roomId);
            List<ContactDTO> listContactDTO = new ArrayList<>();
            for (Contacts contact : contacts) {
                Users user = userRepo.findById(contact.getUserId()).get();
                ContactDTO contactDTO = new ContactDTO(user.getUserId(), user.getFullName(), user.getEmail(),
                        user.getAvatar(), user.getStudentCode(), contact.getContactId());
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
    public List<UserFriendDTO> searchByKeyWord(String keyword, Long userId) {
        List<Users> list = userRepo.searchByKeyWord(keyword);
        List<UserFriendDTO> listDTO = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserId() == userId)
                continue;
            UserFriendDTO userFr = new UserFriendDTO();
            userFr.setAvatar(list.get(i).getAvatar());
            userFr.setEmail(list.get(i).getEmail());
            userFr.setFullName(list.get(i).getFullName());
            userFr.setStudentCode(list.get(i).getStudentCode());
            List<Friends> listFr = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, list.get(i).getUserId());
            userFr.setUserId(list.get(i).getUserId());
            List<Friends> listFr2 = friendRepo.getFriendByUserInviteIdAndUserConfirm(list.get(i).getUserId(), userId);

            Long groupId = 0L;
            try {
                if (listFr2.size() > 0) {
                    groupId = listFr2.get(0).getGroup().getGroupId();
                    Long roomId = roomChatRepo.getRoomChatByGroupId(groupId).getRoomId();
                    List<Contacts> contacts = contactRepo.getContactByRoomId(roomId);
                    List<ContactDTO> listContactDTO = contacts.stream()
                            .map(element -> modelMapper.map(element, ContactDTO.class))
                            .collect(Collectors.toList());

                    for (int j = 0; j < listContactDTO.size(); j++) {
                        Users user = userRepo.findById(listContactDTO.get(j).getUserId()).get();
                        listContactDTO.get(j).setAvatar(user.getAvatar());
                        listContactDTO.get(j).setEmail(user.getEmail());
                        listContactDTO.get(j).setFullName(user.getFullName());
                        listContactDTO.get(j).setStudentCode(user.getStudentCode());
                    }
                    userFr.setRoomId(roomId);
                    userFr.setListContact(listContactDTO);

                }
                List<Friends> th1 = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, userFr.getUserId());
                Friends th2 = friendRepo.findFriendByUserInviteIdAndUserConfirmId(userFr.getUserId(), userId);
                if (th1.size() == 0) {
                    userFr.setStatus(1L);
                } else {
                    if (th1.get(0).getStatus() == true) {
                        userFr.setStatus(2L);
                    } else {
                        if (th2 != null && th2.getStatus() == false) {
                            userFr.setStatus(3L);
                        } else if (th2 == null) {
                            userFr.setStatus(4L);
                        }
                    }
                }
            } catch (Exception e) {

            }
            try {
                // userFr.setStatus(listFr.get(0).getStatus());
            } catch (Exception e) {
                // e.printStackTrace();
            }
            List<Friends> fr = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, userFr.getUserId());
            if (fr.size() > 0) {
                userFr.setIsConfirm(fr.get(0).getStatus());
            }

            listDTO.add(userFr);
        }
        return listDTO;
    }

    @Override
    public FriendDetailDTO addFriend(Long userConfirmId, String studentCode) {
        try {
            Users user = userRepo.findByStudentCode(studentCode);
            if (user.getUserId() == userConfirmId) {
                return null;
            }
            List<Friends> list = friendRepo.getFriendByUserInviteIdAndUserConfirm(userConfirmId, user.getUserId());
            if (list.size() == 0) {
                Users userConfirm = userRepo.findByUserId(user.getUserId());
                Users userInvite = userRepo.findByUserId(userConfirmId);
                FriendDetailDTO friendDetailDTO = new FriendDetailDTO(user.getUserId(), userConfirmId,
                        userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar(),
                        userConfirm.getAvatar());

                Groups group = new Groups();
                group.setName(userConfirm.getFullName() + "," + userInvite.getFullName());
                group.setTotalMember(0L);
                group.setDescription("Friend with chat rooms");
                group.setClassName("Friend Chat");

                Groups groupCreate = groupRepo.save(group);
                Friends friend = new Friends();
                friend.setUserInviteId(userInvite.getUserId());
                friend.setUserConfirmId(userConfirm.getUserId());
                friend.setGroup(groupCreate);
                friendRepo.save(friend);

                String nameFriend = userInvite.getFullName();
                NotificationsDTO notificationsDTO = new NotificationsDTO(
                        String.format(ContentNotifications.NOTI_CONTENT_ADD_FRIEND, nameFriend),
                        TypeNotifications.NOTI_TYPE_ADD_FRIEND, userConfirm.getUserId());
                notificationsService.createNoti(notificationsDTO);
                return friendDetailDTO;
            }
            return null;
        } catch (Exception e) {
            Users user = userRepo.findByStudentCode(studentCode);
            Users userConfirm = userRepo.findByUserId(user.getUserId());
            Users userInvite = userRepo.findByUserId(userConfirmId);
            FriendDetailDTO friendDetailDTO = new FriendDetailDTO(user.getUserId(), userConfirmId,
                    userInvite.getFullName(), userConfirm.getFullName(), userInvite.getAvatar(),
                    userConfirm.getAvatar());
            e.printStackTrace();
            return friendDetailDTO;
        }

    }

    @Override
    public FriendDetailDTO acceptFriend(Long userConfirmId, Long userInviteId) {
        friendRepo.acceptFriend(userConfirmId, userInviteId);
        FriendDetailDTO friendDetailDTO = new FriendDetailDTO(userConfirmId, userInviteId,
                userRepo.findById(userConfirmId).get().getFullName(),
                userRepo.findById(userInviteId).get().getFullName(), userRepo.findById(userInviteId).get().getAvatar(),
                userRepo.findById(userConfirmId).get().getAvatar());
        String nameGroup = friendDetailDTO.getFullNameUserConfirm() + "," + friendDetailDTO.getFullNameUserInvite();
        // Groups groupCheck = groupRepo.findGroupByName(nameGroup);
        friendDetailDTO.setStatus(true);
        friendDetailDTO.setEmailInvite(userRepo.findById(userInviteId).get().getEmail());
        Groups group = new Groups(nameGroup, null,
                "Friend with chat rooms", "Friends chat");
        group.setAvatar(friendDetailDTO.getAvatarUserConfirm() + "," + friendDetailDTO.getAvatarUserInvite());
        Groups groupCreated = groupRepo.save(group);

        RoomChats roomChat = new RoomChats(groupCreated);
        roomChat.setLastMessage("Hai bạn đã trở thành bạn bè của nhau");
        // encodedString
        String encodedStringRoom = Base64.getEncoder().encodeToString(roomChat.getLastMessage().getBytes());
        roomChat.setLastMessage(encodedStringRoom);
        Long roomChatId = roomChatRepo.save(roomChat).getRoomId();
        friendDetailDTO.setRoomId(roomChatId);
        friendDetailDTO.setGroupId(groupCreated.getGroupId());
        Contacts contact = new Contacts(userConfirmId, roomChatId);
        contactRepo.save(contact);
        Contacts contact2 = new Contacts(userInviteId, roomChatId);
        contactRepo.save(contact2);
        Friends friends = friendRepo.getFriendByUserInviteIdAndUserConfirm(userInviteId, userConfirmId).get(0);
        friends.setStatus(true);
        friends.setGroup(groupCreated);
        friendRepo.save(friends);

        Messages messageConfirm = new Messages(
                "Bạn và " + friendDetailDTO.getFullNameUserConfirm() + " đã trở thành bạn bè của nhau", false);
        // encodedString
        String encodedStringConfirm = Base64.getEncoder().encodeToString(messageConfirm.getContent().getBytes());
        messageConfirm.setContent(encodedStringConfirm);
        messageConfirm.setContact(contact);

        Messages messageInvite = new Messages(
                "Bạn và " + friendDetailDTO.getFullNameUserInvite() + " đã trở thành bạn bè của nhau", false);
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

        NotificationsDTO notificationsDTO = new NotificationsDTO(
                String.format(ContentNotifications.NOTI_CONTENT_ACCEPT_FRIEND,
                        userRepo.findById(userConfirmId).get().getFullName()),
                TypeNotifications.NOTI_TYPE_ACCEPT_FRIEND, userInviteId);
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
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirm().getUserId(),
                    friend.getUserInvite().getUserId(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getAvatar(),
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
            FriendDetailDTO friendDTO = new FriendDetailDTO(friend.getUserConfirm().getUserId(),
                    friend.getUserInvite().getUserId(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getFullName(),
                    userRepo.findById(friend.getUserInvite().getUserId()).get().getAvatar(),
                    userRepo.findById(friend.getUserConfirm().getUserId()).get().getAvatar());
            friendDTO.setStatus(friend.getStatus());
            listDTO.add(friendDTO);
        }
        return listDTO;
    }

    @Override
    public List<UsersDTO> getAllUserNotStudent() {
        List<Users> list = userRepo.findUserByRoleNotStudent();
        List<UsersDTO> listDTO = new ArrayList<>();
        for (Users user : list) {
            UsersDTO userDTO = modelMapper.map(user, UsersDTO.class);
            userDTO.setRole(user.getRole().getName());
            listDTO.add(userDTO);
        }
        return listDTO;
    }

}
