package com.ttbkk.api.user;

import com.ttbkk.api.annotations.auth.IsAdmin;
import com.ttbkk.api.annotations.auth.IsUser;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 클래스 생성자.
      * @param userService UserService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * header로 넘어온 토큰을 통해 얻은 유저 본인을 삭제합니다.
     *
     * @param user 요청한 유저
     * @return 200 OK
     */
    @DeleteMapping
    @IsUser
    public ResponseEntity<String> deleteByUser(
        User user
    ) {
        this.userService.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    };

    /**
     * 어드민이 유저를 삭제하는 경우 사용합니다.
     * 어드민이 아닌 경우 요청이 불가능합니다.
     *
     * @param id 삭제할 유저의 id
     * @return 200 OK
     * @throws NotFoundException id에 해당하는 유저가 존재하지 않는 경우.
     */
    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<String> deleteByAdmin(
        @PathVariable String id
    ) throws NotFoundException {
        this.userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    };
}
