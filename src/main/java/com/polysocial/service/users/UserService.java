package com.polysocial.service.users;


import java.util.List;
import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.FriendDetailDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getOneUser(Long userId);

    FriendDTO getUserFriend(Long userId, Long friendId);
    
    List<FriendDetailDTO> getAllFriend(Long userId);

    List<UserDTO> searchUserByEmail(String email);

    List<UserDTO> searchUserByName(String name);

    FriendDetailDTO addFriend(Long userConfirmId, Long userInviteId);

    FriendDetailDTO acceptFriend(Long userConfirmId, Long userInviteId);

    void deleteRequestAddFriend(Long userConfirmId, Long userInviteId);

    List<FriendDetailDTO> getAllRequestAddFriend(Long userId);

}
